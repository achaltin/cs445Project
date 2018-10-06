package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Park;
import datastructs.Address;;

public class ParkTest {

	@Test 
	public void testDefualtParkIsFree() {
		Park p = new Park(new Address());
		assertEquals(p.viewFee(), 0.0f, .01); //assert it's the same within 1 penny 
	}
	
	@Test 
	public void testNonDefaultPark() {
		Park p = new Park(1.00f,new Address("123 Main St","Chicago","IL","12345"));
		assertEquals(p.viewFee(), 1.00f, .01);
		assertEquals(p.getState(), "IL");
	}
	
	@Test
	public void testViewParkFee() {
		Park p = new Park(2.50f, new Address());
		assertEquals(p.viewFee(), 2.50f, .01); 
	}
	
	@Test 
	public void testViewState() {
		Park p = new Park(1.0f, new Address("123 Street","CityTown","IL","23456"));
		assertEquals(p.getState(), "IL");
	}
	
	@Test 
	public void testUpdateFee() {
		Park p = new Park(new Address());
		assertEquals(p.viewFee(),0.0f, .01);
		p.updateFee(2.50f);
		assertEquals(p.viewFee(),2.50f,.01);
	}
	
	@Test
	public void testGetIds() {
		Park p1 = new Park(new Address());
		Park p2 = new Park(new Address());
		Park p3 = new Park(new Address());
		assertEquals(p1.id(), 5);  //starts at 5 since 4 others were implemented 
		assertEquals(p2.id(), 6);
		assertEquals(p3.id(), 7);
	}

}
