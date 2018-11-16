package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import datastructs.Date;

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
		Date d = new Date(1,2,2018);
		Note n = new Note(123, v.vid, d,"Title", "Some text for the note");
		v.addNote(n);
		assertEquals(v.toString(),"VID: 9 Name:  Email:  Orders Placed: \n" + 
				"Notes Given: \n" + 
				"	NID: 10 PID: 123 VID: 9 Date Written: 1/2/2018 Title: Title Text: Some text for the note");
	}

}
