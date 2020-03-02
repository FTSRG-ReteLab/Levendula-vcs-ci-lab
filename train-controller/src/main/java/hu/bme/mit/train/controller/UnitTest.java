import org.junit.Test;
import org.junit.Assert;
import static org.mockito.Mockito.*;

public class UnitTest{
	@Test
	public void myTest(){
		TrainControllerImpl tc =  new TrainControllerImpl();
		assertEquals(0, tc.getReferenceSpeed());
	}
}
