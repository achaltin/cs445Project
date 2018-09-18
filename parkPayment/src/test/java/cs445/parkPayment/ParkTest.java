package cs445.parkPayment;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParkTest {

	@Test 
	public void testDefualtParkIsFree() {
		Park p = new Park();
		assertEquals(p.viewFee(), 0.0f, .01); //assert it's the same within +/- .01
	}
	@Test
	public void testViewParkFee() {
		Park p = new Park(2.50f);
		assertEquals(p.viewFee(), 2.50f, .01); //assert it's the same within +/- .01
	}
	
	@Test 
	public void testViewState() {
		Park p = new Park(1.0f);
		assertEquals(p.getState(), "Illinois");
	}

}
