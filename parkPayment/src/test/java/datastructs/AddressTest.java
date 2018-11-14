package datastructs;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddressTest {

	@Test
	public void testAddress() {
		Address a = new Address("street", "city","state","zip");
		assertEquals(a.street,"street");
		assertEquals(a.city,"city");
		assertEquals(a.state,"state");
		assertEquals(a.zip, "zip");
		assertEquals(a.toString(),"street, city state, zip");
	}

}
