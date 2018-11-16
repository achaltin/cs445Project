package cs445.parkPayment;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import interfaces.BoundaryInterface;
import datastructs.*;
import entities.*;

public class ParkManagerTest {
	BoundaryInterface pm;
	Address addr;
	Geo geo;
	int[][] paymentArray = { {1,2}, {3,4},{5,6}};
	@Before
	public void setup() {
		pm = new ParkManager();
		
		addr = new Address("123 Street", "CityTown", "IL", "60616");
		geo = new Geo(1.1, 2.2);
	}

	@Test
	public void testCreatePark() {
		Park p = pm.createPark("Park with Mandatory Only", addr, "www.web.com", geo, paymentArray);
		Park p1 = pm.createPark("Park with All Attributes", "Northern IL", addr, "555-123-4567", "www.site.com", geo, paymentArray);
				
		assertEquals(p.getName(), "Park with Mandatory Only");
		assertTrue(Park.isValidPaymentArray(p.getPaymentInfo()));
		assertEquals(p.getAddress(), addr);
		assertEquals(p.getWebsite(), "www.web.com");
		
		assertEquals(p1.getName(), "Park with All Attributes");
		assertTrue(Park.isValidPaymentArray(p1.getPaymentInfo()));
		assertEquals(p1.getAddress(), addr);
		assertEquals(p1.getWebsite(), "www.site.com");
	}
	@Test
	public void testUpdatePark() {
		Park p = pm.createPark("Park with Mandatory Only", addr, "www.web.com", geo, paymentArray);
		pm.updatePark(p.getPid(), "New Name", addr, "www.newweb.com", geo, paymentArray);
		assertEquals(p.getName(), "New Name");
		assertEquals(p.getWebsite(), "www.newweb.com");
		pm.updatePark(p.getPid(), "New Name", "A Region!", addr, "555-109-8765", "www.newweb.com", geo, paymentArray);
		assertEquals(p.getName(), "New Name");
		assertEquals(p.getWebsite(), "www.newweb.com");
		assertEquals(p.getRegion(), "A Region!");
		assertEquals(p.getPhone(), "555-109-8765"); 
	}
	@Test
	public void testDeletePark() {
		int startSize = pm.viewAllParks().size();
		Park p = pm.createPark("A Park to be Deleted :(", addr, "www.web.com", geo, paymentArray);
		assertEquals(pm.viewAllParks().size(), startSize+1);
		assertEquals(pm.viewAllParks().get(startSize), p);
		pm.deletePark(p.getPid());
		assertEquals(pm.viewAllParks().size(), startSize);	
	}
	@Test
	public void testSearchParks() {
		assertTrue(pm.viewAllParks().size() > 0);
		assertEquals(pm.searchParks("sdsfpvj3isdfkj").size(), 0);
		pm.createPark("Central Park", addr, "web.com",geo, paymentArray);
		assertEquals(pm.searchParks("Central Park").size(), 1);
	}
	@Test 
	public void testCreateAndViewParkNoteDetails() {
		Park p = pm.createPark("Park with Note", addr, "web.com",geo, paymentArray);
		Visitor v = pm.createVisitor("mail@email.com");
		Note n = pm.createNoteForPark(p.getPid(), v.getVid(), "Nice Park", "It's a good park");
		assertTrue(pm.viewParkNoteDetail(p.getPid(), n.getNid()).equals(n));
	}
	@Test
	public void testViewAllNotes() {
		Park p = pm.createPark("Park with Note", addr, "web.com",geo, paymentArray);
		Visitor v = pm.createVisitor("mail@email.com");
		Note n = pm.createNoteForPark(p.getPid(), v.getVid(), "Nice Park", "It's a good park");
		Visitor v2 = pm.createVisitor("another@email.com");
		Note n2 = pm.createNoteForPark(p.getPid(), v.getVid(), "Okay Park", "This park is just okay");
		assertEquals(pm.viewAllNotes().size(), 3);
	
	}
	@Test
	public void testCreateNoteForPark() {
		Date d = new Date(1,2,2018);
		Park p = pm.createPark("Park with Note", addr, "web.com",geo, paymentArray);
		Visitor v = pm.createVisitor("mail@email.com");
		Note n = pm.createNoteForPark(p.getPid(), v.getVid(), d, "Nice Park", "It's a good park");
		assertTrue(pm.viewParkNoteDetail(p.getPid(), n.getNid()).equals(n));
		assertEquals(n.getDate(), d);
			
	}
	@Test
	public void testViewParkDetail() {
		Park p = pm.createPark("North Park", addr, "web.com",geo, paymentArray);
		assertEquals(pm.viewParkDetail(p.getPid()), p);
	}
	@Test
	public void testViewNoteDetails() {
		Park p = pm.createPark("Park with Note", addr, "web.com",geo, paymentArray);
		Visitor v = pm.createVisitor("mail@email.com");
		Note n = pm.createNoteForPark(p.getPid(), v.getVid(), new Date(1,2,2018), "Nice Park", "It's a good park");
		assertEquals(pm.viewNoteDetail(n.getNid()), n);
	}
	@Test
	public void testUpdateNote() {
		Park p = pm.createPark("Park with Note", addr, "web.com",geo, paymentArray);
		Visitor v = pm.createVisitor("mail@email.com");
		Note n = pm.createNoteForPark(p.getPid(), v.getVid(), new Date(1,2,2018), "Nice Park", "It's a good park");
		pm.updateNote(n.getNid(), v.getVid(), "New Title", "New Text");
		assertEquals(n.getTitle(),"New Title");
		assertEquals(n.getText(),"New Text");
	}
	@Test
	public void testDeleteNote() {
		int originalSize=pm.viewAllNotes().size();
		Park p = pm.createPark("Park with Note", addr, "web.com",geo, paymentArray);
		Visitor v = pm.createVisitor("mail@email.com");
		Note n = pm.createNoteForPark(p.getPid(), v.getVid(), new Date(1,2,2018), "Nice Park", "It's a good park");
		assertEquals(pm.viewAllNotes().size(), originalSize+1);
		pm.deleteNote(n.getNid());
		assertEquals(pm.viewAllNotes().size(), originalSize);
	}
	@Test
	public void testSearchNotes() {
		assertEquals(pm.searchNotes("lskdfj iweh").size(), 0);
		assertEquals(pm.searchNotes("NID").size(), pm.viewAllNotes().size());
	}
	@Test
	public void testCreateViewSearchOrder() {
		Park p = pm.createPark("Park with Note", addr, "web.com",geo, paymentArray);
		Visitor vi = pm.createVisitor("mail@email.com");
		Vehicle veh = new Vehicle("car", new LicensePlate("IL","A12345"));
		CreditCard cc = new CreditCard("1234567812345678", "John Doe", "01/20", addr);
		Order o = pm.createOrder(p.getPid(), veh, vi, cc);
		ArrayList<Order> orders = pm.viewAllOrders();
		assertEquals(orders.size(), 1);
		assertTrue(o.isValidOrder());
		assertEquals(pm.viewOrderDetails(o.getOid()), o);
		assertEquals(pm.searchOrders("5678").size(), 1);
		assertEquals(pm.searchOrders("ld oifwd jkfs").size(), 0);
	}
	@Test
	public void testCreateViewSearchVisitor() {
		int startSize = pm.viewAllVisitors().size();
		Visitor noName = pm.createVisitor("jdoe@mail.com");
		Visitor withName = pm.createVisitor("Jane Smith", "jsmith@mail.com");
		assertEquals(pm.viewAllVisitors().size(), startSize+2);
		assertEquals(pm.viewVisitorDetail(noName.getVid()), noName);
		assertEquals(pm.searchVisitors("Jane Smith").size(), 1);
		assertEquals(pm.searchVisitors("slkd jfoiw e").size(), 0);
	}
	@Test
	public void testCreateViewReport() {
		Report r = pm.createReport("Report 1", new Date(1,1,2000), new Date(12,31, 3000));
		assertEquals(pm.viewAllReports().size(),1);
		assertEquals(pm.viewReportDetail(r.getRid()), r);
	}
	@Test
	public void testSearch() {
		assertEquals(pm.search("any key").size(),4); //holds four arraylists for the other 4 searches, which are already tested
	}
}
