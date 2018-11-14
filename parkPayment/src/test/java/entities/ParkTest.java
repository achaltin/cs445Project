package entities;

import static org.junit.Assert.*;
import org.junit.Test;
import entities.Park;
import datastructs.Address;
import datastructs.Geo;
import datastructs.Date;

public class ParkTest {
	@Test
	public void testValidArrayChecker() {
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		assertTrue(Park.isValidPaymentArray(goodArray));
		int[][] tooShort = {{1,2}, {3,4}};
		assertFalse(Park.isValidPaymentArray(tooShort));
		int[][] tooLong = { {1,2}, {3,4}, {5,6}, {7,8} };
		assertFalse(Park.isValidPaymentArray(tooLong));
	}
	@Test
	public void testMakeValidPaymentArray() {
		int[][] valid = Park.makeValidPaymentArray(1,2,3,4,5,6);
		assertTrue(Park.isValidPaymentArray(valid));
		assertEquals(valid.length, 3);
		assertEquals(valid[0].length, 2);
		assertEquals(valid[0][0], 1);
		assertEquals(valid[0][1], 2);
		assertEquals(valid[1][0], 3);
		assertEquals(valid[1][1], 4);
		assertEquals(valid[2][0], 5);
		assertEquals(valid[2][1], 6);
	}
	@Test
	public void testOnlyRequirementsConstructor() {
		Address a = new Address("street", "city", "state","zip");
		Geo g = new Geo(1.1, 2.2);
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p = new Park("name", a, "www.web.com",g, goodArray);
		assertEquals(p.name, "name");
		assertEquals(p.address.toString(), "street, city state, zip");
		assertEquals(p.geo.toString(),"lat: 1.1 lng: 2.2");
		int [][] valid = p.paymentInfo;
		assertEquals(valid[0][0], 1);
		assertEquals(valid[0][1], 2);
		assertEquals(valid[1][0], 3);
		assertEquals(valid[1][1], 4);
		assertEquals(valid[2][0], 5);
		assertEquals(valid[2][1], 6);
		assertEquals(p.region,"");
		assertEquals(p.phone,"");
		assertEquals(p.admissions.size(),0);
	}
	@Test
	public void testAllFieldsConstructor() {
		Address a = new Address("street", "city", "state","zip");
		Geo g = new Geo(1.1, 2.2);
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p = new Park("name", "region", a, "phone", "www.web.com",g, goodArray);
		assertEquals(p.name, "name");
		assertEquals(p.address.toString(), "street, city state, zip");
		assertEquals(p.geo.toString(),"lat: 1.1 lng: 2.2");
		int [][] valid = p.paymentInfo;
		assertEquals(valid[0][0], 1);
		assertEquals(valid[0][1], 2);
		assertEquals(valid[1][0], 3);
		assertEquals(valid[1][1], 4);
		assertEquals(valid[2][0], 5);
		assertEquals(valid[2][1], 6);
		assertEquals(p.region,"region");
		assertEquals(p.phone,"phone");
		assertEquals(p.admissions.size(),0);
	}
	@Test
	public void testAddAdmission() {
		Address a = new Address("street", "city", "state","zip");
		Geo g = new Geo(1.1, 2.2);
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p = new Park("name", "region", a, "phone", "www.web.com",g, goodArray);
		assertEquals(p.admissions.size(),0);
		Date d = new Date(1,2,2018);
		p.addAdmission(1,2,2018);
		assertEquals(p.admissions.size(),1);
		Date pDate = p.admissions.get(0);
		assertTrue(pDate.equals(d));
	}
}
