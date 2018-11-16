package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import cs445.parkPayment.UniqueIdGenerator;
import datastructs.Address;
import datastructs.Date;
import datastructs.Geo;

public class NoteTest {
	
	@Test
	public void testNote() {
		Date d = new Date(1,2,2018);
		Note n = new Note(123, 456, d,"Title", "Some text for the note");
		assertTrue(n.date.equals(d));
		assertEquals(n.pid, 123);
		assertEquals(n.vid, 456);
		assertEquals(n.title, "Title");
		assertEquals(n.text,"Some text for the note");
	}
	@Test
	public void testAddNoteToVisitor() {
		Visitor v = new Visitor("email@email.com");
		Date d = new Date(1,2,2018);
		Note n = new Note(123, v.vid, d, "Title", "Some text for the note");
		v.addNote(n);
		assertEquals(v.notes.size(),1);
		assertEquals(v.notes.get(0), n);
	}
	@Test
	public void testAddNoteToWrongVisitor() {
		Visitor v = new Visitor("email@email.com");
		Date d = new Date(1,2,2018);
		Note n = new Note(123, UniqueIdGenerator.getUniqueID(), d, "Title", "Some text for the note");
		v.addNote(n);
		assertEquals(v.notes.size(),0);
	}
	@Test
	public void testAddNoteToPark() {
		Address a = new Address("street", "city", "state","zip");
		Geo g = new Geo(1.1, 2.2);
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p = new Park("name", a, "www.web.com",g, goodArray);
		
		Note n = new Note(p.pid, 2, new Date(1,2,2018), "Title", "Text");
		
		p.addNote(n);
		assertEquals(p.notes.size(), 1);
		assertEquals(p.notes.get(0), n);
	}
	@Test
	public void testNoteEqualToSelf() {
		Date d = new Date(1,2,2018);
		Note n = new Note(123, 456, d, "Title", "Some text for the note");
		Note copy = n;
		assertEquals(n, copy);
	}
	@Test
	public void testNoteNotEqualToAnyOtherNote()
	{
		Date d = new Date(1,2,2018);
		Note n1 = new Note(123, 456, d, "Title", "Some text for the note");
		Note n2 = new Note(123, 456, d, "Title", "Some text for the note");
		assertNotEquals(n1.nid, n2.nid);
		assertNotEquals(n1, n2);
	}
	@Test
	public void testDuplicateNoteCheck()
	{
		Date d = new Date(1,2,2018);
		Note n1 = new Note(123, 456, d, "Title", "Some text for the note");
		Note n2 = new Note(123, 456, d, "Title", "Some text for the note");
		assertTrue(n1.isDuplicate(n2));
		Note copy = n1;
		assertTrue(n1.isDuplicate(copy));
		Note n3 = new Note(123, 456, d, "Other", "More text");
		assertFalse(n1.isDuplicate(n3));
	}
	
	@Test
	public void testAccessorsAndMutators() {
		Date d = new Date(1,2,2018);
		Note n = new Note(123, 456, d, "Title", "Some text for the note");
		assertEquals(n.getVid(), 456);
		assertEquals(n.getPid(), 123);
		assertEquals(n.title, "Title");
		assertEquals(n.text,"Some text for the note");
		assertEquals(n.getDate(), d);
		n.setText("new text");
		n.setTitle("new title");
		assertEquals(n.title, "new title");
		assertEquals(n.text, "new text");
		n.updateDate();
		assertNotEquals(n.date, d);
		java.util.Date today = new java.util.Date();
		Date newDate = new Date(today.getMonth(), today.getDate(),today.getYear());
		assertEquals(n.toString(), "NID: "+n.nid+" PID: 123 VID: 456 Date Written: "+newDate.toString()+" Title: new title Text: new text");
}
}
