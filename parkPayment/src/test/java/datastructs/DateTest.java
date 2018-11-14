package datastructs;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTest {

	@Test
	public void testDateCreation() {
		Date d = new Date(1, 2, 2018);
		assertEquals(d.day, 2);
		assertEquals(d.month, 1);
		assertEquals(d.year, 2018);
		assertEquals(d.toString(), "1/2/2018");
	}
	@Test
	public void testDateEquals() {
		Date d = new Date(1, 2, 2018);
		Date d2 = new Date(1, 2, 2018);
		Date d3 = new Date(1, 3, 2018);
		assertTrue(d.equals(d2));
		assertFalse(d.equals(d3));
	}
	@Test
	public void testDateBefore() {
		Date d = new Date(1, 2, 2018);
		Date laterYear = new Date(1, 2, 2019);	
		Date laterMonth = new Date(2, 2, 2018);
		Date laterDay = new Date(1, 3, 2018);
		Date earlier = new Date(1,1,2018);
		assertTrue(d.before(laterYear));
		assertTrue(d.before(laterMonth));
		assertTrue(d.before(laterDay));
		assertFalse(d.before(earlier));
	}
	@Test
	public void testDateAfter() {
		Date d = new Date(2,2,2018);
		Date earlierYear = new Date(2,2,2017);
		Date earlierMonth = new Date(1,2,2018);
		Date earlierDay = new Date(2,1,2018);
		Date later = new Date(2,3,2018);
		assertTrue(d.after(earlierDay));
		assertTrue(d.after(earlierMonth));
		assertTrue(d.after(earlierYear));
		assertFalse(d.after(later));
	}

}
