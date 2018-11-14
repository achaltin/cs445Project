package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import datastructs.Address;
import datastructs.Geo;
import datastructs.Date;

import java.util.ArrayList;

public class ReportTest {

	@Test
	public void testReportOnNoParks() {
		Date d1 = new Date(1,1,1900);
		Date d2 = new Date(12,31,3000);
		Report r = new Report("Empty Report", d1, d2, new ArrayList<Park>());
		assertEquals(r.name, "Empty Report");
		assertEquals(r.start, d1);
		assertEquals(r.end, d2);
		assertEquals(r.parks.size(), 0);
		assertEquals(r.totalAdmissions, 0);
	}
	@Test
	public void testReportOnOneParks() {
		Date d1 = new Date(1,1,2017);
		
		Address a = new Address("street", "city", "state","zip");
		Geo g = new Geo(1.1, 2.2);
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p = new Park("name", a, "www.web.com",g, goodArray);
		p.addAdmission(1,1,2018);
		
		ArrayList<Park> parks = new ArrayList<Park>();
		parks.add(p);
		Date d2 = new Date(12, 31, 2018);
		Report r = new Report("Single Park Report", d1, d2, parks);

		assertEquals(r.parks.size(), 1);
		assertEquals(r.totalAdmissions, 1);
	}
	@Test
	public void testMultipleParksWithMultipleAdmissions() {
		Address a = new Address("street", "city", "state","zip");
		Geo g = new Geo(1.1, 2.2);
		int[][] goodArray = { {1,2}, {3,4}, {5,6} };
		Park p1 = new Park("park 1", a, "www.web.com",g, goodArray);
		p1.addAdmission(1,1,2018);
		p1.addAdmission(1,1,2018);
		p1.addAdmission(1,2,2018);
		Park p2 = new Park("park 2", a, "www.web.com",g, goodArray);
		p2.addAdmission(1,1,2018);
		p2.addAdmission(1,3,2018);
		
		ArrayList<Park> parks = new ArrayList<Park>();
		parks.add(p1);
		parks.add(p2);
		Date start = new Date(1,1,2018);
		Date end = new Date(1,3,2018);
		Report r = new Report("2 Parks", start, end, parks);
		
		assertEquals(r.parks.size(), 2);
		assertEquals(r.totalAdmissions, 5);
		assertEquals(r.parkAdmins.get(0).toString(), "pid: "+p1.pid+" park name: park 1 park admissions: 3");
	}

}
