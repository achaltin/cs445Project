package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import datastructs.Address;
import datastructs.Geo;
import datastructs.LicensePlate;

public class OrderTest {

	@Test
	public void testGoodOrder() {
		Address a = new Address("street","city","state","zip");
		CreditCard cc = new CreditCard("1234567812345678", "name","01/19", a);
		Visitor vi = new Visitor("email@mail.com");
		Vehicle veh = new Vehicle("car", new LicensePlate("IL", "A12345"));
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p = new Park("name",a,"web.com",new Geo(1.1,-2.2), goodArray);
		
		Order o = new Order(p.pid, veh, vi, cc);
		assertEquals(o.pid, p.pid);
		assertEquals(o.vehicle, veh);
		assertEquals(o.visitor, vi);
		assertEquals(o.cc, cc);
		assertTrue(o.isValidOrder());
	}
	
	@Test
	public void testBadCCOrder() {
		Address a = new Address("street","city","state","zip");
		CreditCard tooShort = new CreditCard("1234567", "name","01/19", a);
		CreditCard badExp = new CreditCard("1234567812345678", "name","not a date", a);
		Visitor vi = new Visitor("email@mail.com");
		Vehicle veh = new Vehicle("car", new LicensePlate("IL", "A12345"));
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p = new Park("name",a,"web.com",new Geo(1.1,-2.2), goodArray);
		
		Order tooShortCC = new Order(p.pid, veh, vi, tooShort);
		Order badExpCC = new Order(p.pid, veh, vi, badExp);
		assertFalse(tooShortCC.isValidOrder());
		assertFalse(badExpCC.isValidOrder());
	}
	
	@Test
	public void testBadVehicleOrder() {
		Address a = new Address("street","city","state","zip");
		CreditCard cc = new CreditCard("1234567812345678", "name","01/19", a);
		Visitor vi = new Visitor("email@mail.com");
		Vehicle badType = new Vehicle("bad type", new LicensePlate("IL", "A12345"));
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p = new Park("name",a,"web.com",new Geo(1.1,-2.2), goodArray);
		
		Order badVehicle = new Order(p.pid, badType, vi, cc);
		assertFalse(badVehicle.isValidOrder());
	}
	
	@Test
	public void testBadLisencePlateOrder() {
		Address a = new Address("street","city","state","zip");
		CreditCard cc = new CreditCard("1234567812345678", "name","01/19", a);
		Visitor vi = new Visitor("email@mail.com");
		LicensePlate badNum = new LicensePlate("IL","dsi#s");
		LicensePlate badState = new LicensePlate("blah", "A12345");
		Vehicle badPlateNum = new Vehicle("car", badNum);
		Vehicle badPlateState = new Vehicle("car", badState);
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p = new Park("name",a,"web.com",new Geo(1.1,-2.2), goodArray);
		
		Order badLPNum = new Order(p.pid, badPlateNum, vi, cc);
		Order badLPState = new Order(p.pid, badPlateState, vi, cc);
		assertFalse(badLPNum.isValidOrder());
		assertFalse(badLPState.isValidOrder());
	}

}
