package hu.bme.mit.train.sensor;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainUser mockTU;
    TrainController mockTC;

    TrainSensorImpl ts;
   @Before
    public void before() {
        mockTU = mock(TrainUser.class);
        mockTC = mock(TrainController.class);

        ts = new TrainSensorImpl(mockTC, mockTU);

    }
    @Test
    public void absoluteUnderMarginCheck() {
        ts.overrideSpeedLimit(-10);
        verify(mockTU, times(1)).setAlarmState(true);
        verify(mockTU, times(0)).setAlarmState(false);
    }
    @Test
    public void absoluteOverMarginCheck() {
        ts.overrideSpeedLimit(505);
        verify(mockTU, times(1)).setAlarmState(true);
        verify(mockTU, times(0)).setAlarmState(false);
    }
    @Test
    public void relativeUnderMarginCheck() {
        when(mockTC.getReferenceSpeed()).thenReturn(100);
        ts.overrideSpeedLimit(40);//set true
        verify(mockTU, times(1)).setAlarmState(true);
        verify(mockTU, times(0)).setAlarmState(false);
    }
    @Test
    public void relativeOverMarginCheck() {
        when(mockTC.getReferenceSpeed()).thenReturn(105);
        ts.overrideSpeedLimit(160);
        verify(mockTU, times(1)).setAlarmState(true);
        verify(mockTU, times(0)).setAlarmState(false);
    }
    @Test
    public void correctOverride() {
        when(mockTC.getReferenceSpeed()).thenReturn(200);
        ts.overrideSpeedLimit(190);
        verify(mockTU, times(0)).setAlarmState(true);
        verify(mockTU, times(1)).setAlarmState(false);
    }
}
