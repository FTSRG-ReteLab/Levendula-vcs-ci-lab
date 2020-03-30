package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController mockTC;
    TrainUser mockTU;
    TrainSensorImpl mockTS;

    @Before
    public void before() {
        mockTC = mock(TrainController.class);
        mockTU = mock(TrainUser.class);
        mockTS = new TrainSensorImpl(mockTC, mockTU);
        // TODO Add initializations
    }


    @Test
    public void TestSpeedLimitWithSimpleNumbers() {
        Assert.assertEquals(10,10);
    }
    @Test
    public void TestTheAbsoluteMarginWithNegativeNumber() {
        //Teszt arra, ha pl kisebb a szam, mint nulla
        mockTS.overrideSpeedLimit(-1);
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void TestRelativeMarginCheck() {
        when(mockTC.getReferenceSpeed()).thenReturn(105);
        mockTS.overrideSpeedLimit(160);
        verify(mockTU, times(1)).setAlarmState(true);
        verify(mockTU, times(0)).setAlarmState(false);
    }

    @Test
    public void TestTheAbsoluteMarginWithLargerThan500() {
        //Teszt arra, ha pl nagyobb a szam mint 500
        mockTS.overrideSpeedLimit(600);
        verify(mockTU, times(1)).setAlarmState(true);
    }
    @Test
    public void TestBetweenTheMargings() {
        mockTS.overrideSpeedLimit(300);
        verify(mockTU, times(0)).setAlarmState(false);
    }

    @Test
    public void TestgetSpeedLimit() {
        mockTS.getSpeedLimit();
    }


}
