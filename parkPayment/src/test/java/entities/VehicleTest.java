package entities;

import static org.junit.Assert.*;
import datastructs.LicensePlate;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testRv() {
		Vehicle v = new Vehicle("rv", new LicensePlate("IL", "12345"));
		assertEquals(v.getType(), "rv");
		assertEquals(v.lp.getNum(), "12345");
		assertEquals(v.lp.getState(), "IL");
	}
	@Test
	public void testCar() {
		Vehicle v = new Vehicle("car", new LicensePlate("IL", "12345"));
		assertEquals(v.getType(), "car");
		assertEquals(v.lp.getNum(), "12345");
		assertEquals(v.lp.getState(), "IL");
	}
	@Test
	public void testMotorcyle() {
		Vehicle v = new Vehicle("motorcycle", new LicensePlate("IL", "12345"));
		assertEquals(v.getType(), "motorcycle");
		assertEquals(v.lp.getNum(), "12345");
		assertEquals(v.lp.getState(), "IL");
	}
	@Test
	public void testOther() {
		Vehicle v = new Vehicle("badvalue", new LicensePlate("IL", "12345"));
		assertEquals(v.getType(), "");
		assertEquals(v.lp.getNum(), "12345");
		assertEquals(v.lp.getState(), "IL");
	}
}
