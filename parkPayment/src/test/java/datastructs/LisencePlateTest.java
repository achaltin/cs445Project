package datastructs;

import static org.junit.Assert.*;
import org.junit.Test;
public class LisencePlateTest {

	@Test 
	public void testGoodLisencePlate() {
		LicensePlate lp = new LicensePlate("IL", "A12345");
		assertEquals(lp.getState(), "IL");
		assertEquals(lp.getNum(),"A12345");
	}
	
	@Test
	public void testBadLisencePlateNumber() {
		LicensePlate tooLong = new LicensePlate("IL","123456789");
		LicensePlate specialChars = new LicensePlate("IL", "A123$5");
		assertEquals(tooLong.getNum(), "");
		assertEquals(specialChars.getNum(),"");
	}
	
	@Test
	public void testBadState() {
		LicensePlate tooLong = new LicensePlate("ILL", "12345");
		LicensePlate tooShort = new LicensePlate("I", "12345");
		assertEquals(tooLong.getState(),"");
		assertEquals(tooShort.getState(),"");
	}
}
