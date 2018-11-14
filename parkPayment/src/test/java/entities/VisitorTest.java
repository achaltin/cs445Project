package entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class VisitorTest {

	@Test
	public void testConstructorJustEmail() {
		Visitor v = new Visitor("some@mail.com");
		assertEquals(v.name, "");
		assertEquals(v.email, "some@mail.com");
		assertNotEquals(v.vid, null);
		assertEquals(v.orders.size(),0);
		assertEquals(v.notes.size(),0);
	}
	@Test
	public void testConstructorEmailName() {
		Visitor v = new Visitor("name","some@mail.com");
		assertEquals(v.name, "name");
		assertEquals(v.email, "some@mail.com");
		assertNotEquals(v.vid, null);
		assertEquals(v.orders.size(),0);
		assertEquals(v.notes.size(),0);
	}
	@Test
	public void testInvalidEmailNotAllowed() {
		Visitor v = new Visitor("blah");
		assertEquals(v.email,"");
	}

}
