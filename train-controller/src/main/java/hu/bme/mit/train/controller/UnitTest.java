import org.junit.Test;
import static org.junit.Assert.*;

public class ControllerImplTest{
	@Test
	public void myTest(){
		TrainControllerImpl tc =  new TrainControllerImpl();
		assertEquals(0, tc.getReferenceSpeed());
	}
}
