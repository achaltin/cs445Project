package cs445.parkPayment;

import java.util.ArrayList;

import datastructs.Address;
import datastructs.Date;
import datastructs.Geo;
import entities.*;
import interfaces.BoundaryInterface;

public class ParkManager implements BoundaryInterface {
	private static ArrayList<Park> parks = new ArrayList<Park>();
	private static ArrayList<Note> notes = new ArrayList<Note>();
	private static ArrayList<Order> orders = new ArrayList<Order>();
	private static ArrayList<Visitor> visitors = new ArrayList<Visitor>();
	private static ArrayList<Report> reports = new ArrayList<Report>();
	
	
	public Park createPark(String name, Address addr, String website, Geo geo, int[][] paymentInfo) {
		Park p = new Park(name, addr, website, geo, paymentInfo);
		parks.add(p);
		return p;
	}
	public Park createPark(String name, String region, Address addr, String phone, String website, Geo geo, int[][] paymentInfo) {
		Park p = new Park(name, region, addr, website, phone, geo, paymentInfo);
		parks.add(p);
		return p;
	}
	public void updatePark(int pid, String name, Address addr, String website, Geo geo, int[][] paymentInfo) {
		Park p = findParkById(pid);
		p.setName(name);
		p.setAddress(addr);
		p.setWebsite(website);
		p.setGeo(geo);
		p.setPaymentInfo(paymentInfo);
	}
	public void updatePark(int pid, String name, String region, Address addr, String phone, String website, Geo geo, int[][] paymentInfo) {
		Park p = findParkById(pid);
		p.setName(name);
		p.setRegion(region);
		p.setAddress(addr);
		p.setPhone(phone);
		p.setWebsite(website);
		p.setGeo(geo);
		p.setPaymentInfo(paymentInfo);
	}

	public void deletePark(int pid) {
		int removeIndex = -1;
		for(int i = 0; i<parks.size(); i++) {
			if(parks.get(i).getPid() == pid)
				removeIndex = i;
		}
		try {
			parks.remove(removeIndex);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Park not found");
		}
	}
	public ArrayList<Park> viewAllParks() {
		return parks;
	}
	public Park viewParkDetail(int pid) {
		Park p = findParkById(pid);
		return p;
	}
	public ArrayList<Park> searchParks(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Note> viewParkNotes(int pid) {
		Park p = findParkById(pid);
		return p.getNotes();
	}
	public Note viewParkNoteDetail(int pid, int nid) {
		ArrayList<Note> parkNotes = viewParkNotes(pid);
		for(Note n : parkNotes) {
			if(n.getNid() == nid)
				return n;
		}
		return new Note(0, 0, null, null, null);
	}
	public Note createNoteForPark(int pid, int vid, String title, String text) {
		java.util.Date today = new java.util.Date();
		Date myDate = new Date(today.getMonth(), today.getDay(), today.getYear());
		Note n = new Note(pid, vid, myDate, title, text);
		notes.add(n);
		Park p = findParkById(pid);
		p.addNote(n);
		Visitor v = findVisitorById(vid);
		v.addNote(n);
		return n;
	}
	public Note createNoteForPark(int pid, int vid, Date d, String title, String text) {
		Note n = new Note(pid, vid, d, title, text);
		notes.add(n);
		Park p = findParkById(pid);
		p.addNote(n);
		Visitor v = findVisitorById(vid);
		v.addNote(n);
		return n;
	}
	public ArrayList<Note> viewAllNotes() {
		return notes;
	}
	public Note viewNoteDetail(int nid) {
		Note n = findNoteById(nid);
		return n;
	}
	public void updateNote(int nid, int vid, String title, String text) {
		Note n = findNoteById(nid);
		if(n.getVid() == vid) {
			n.setTitle(title);
			n.setText(text);
			n.updateDate();
		}
		
	}
	public void deleteNote(int nid) {
		int removeIndex = -1;
		for(int i = 0; i<notes.size(); i++) {
			if(notes.get(i).getNid() == nid)
				removeIndex = i;
		}
		try {
			notes.remove(removeIndex);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Park not found");
		}		
	}
	public ArrayList<Note> searchNotes(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	public Order createOrder(int pid, Vehicle veh, Visitor vi, CreditCard cc) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Order> viewAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}
	public Order viewOrderDetails(int oid) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Order> searchOrders(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Visitor> viewAllVisitors() {
		// TODO Auto-generated method stub
		return null;
	}
	public Visitor viewVisitorDetail(int vid) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Visitor> searchVisitors(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Report> viewAllReports() {
		// TODO Auto-generated method stub
		return null;
	}
	public Report viewReportDetail(int rid) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Object> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private Park findParkById(int pid) {
		for(Park p : parks) {
			if( p.getPid() == pid)
					return p;
		}
		return new Park(null, null, null, null, null);
	}
	private Note findNoteById(int nid) {
		for(Note n : notes) {
			if(n.getNid() == nid)
				return n;
		}
		return new Note(0, 0, null, null, null);
	}
	private Order findOrderById(int oid) {
		for(Order o: orders) {
			if(o.getOid() == oid)
				return o;
		}
		return new Order(0, null, null, null);
	}
	private Visitor findVisitorById(int vid) {
		for(Visitor v: visitors) {
			if(v.getVid() == vid)
				return v;
		}
		return new Visitor(null);
	}
	private Report findReportById(int rid) {
		for(Report r : reports) {
			if(r.getRid()==rid)
				return r;
		}
		return new Report(null, null, null, null);
	}
}
