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

    TrainSensor ts;
    @Before
    public void before() {
        mockTU = mock(TrainUser.class);
        mockTC = mock(TrainController.class);

        ts = new TrainSensorImpl(mockTC, mockTU);

    }
    @Test
    public void absoluteUnderMarginCheck() {
        ts.overrideSpeedLimit(-10);
        when(mockTU.getAlarmState()).thenReturn(true);
    }
    @Test
    public void absoluteOverMarginCheck() {
        ts.overrideSpeedLimit(505);
        when(mockTU.getAlarmState()).thenReturn(true);
    }
    @Test
    public void relativeUnderMarginCheck() {
        ts.overrideSpeedLimit(99);
        ts.overrideSpeedLimit(100);
        when(mockTU.getAlarmState()).thenReturn(false);//change to 100 was successful without alarm flag
        ts.overrideSpeedLimit(40);
        when(mockTU.getAlarmState()).thenReturn(true);
    }
    @Test
    public void relativeOverMarginCheck() {
        ts.overrideSpeedLimit(99);
        ts.overrideSpeedLimit(105);
        when(mockTU.getAlarmState()).thenReturn(false);//change to 105 was successful without alarm flag
        ts.overrideSpeedLimit(160);
        when(mockTU.getAlarmState()).thenReturn(true);
    }
    @Test
    public void correctOverride() {
        ts.overrideSpeedLimit(199);
        ts.overrideSpeedLimit(200);
        when(mockTU.getAlarmState()).thenReturn(false);//change to 200 was successful without alarm flag
        ts.overrideSpeedLimit(190);
        when(mockTU.getAlarmState()).thenReturn(true);
    }
}
