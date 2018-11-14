package cs445.parkPayment;

import java.util.ArrayList;

import entities.*;
import interfaces.BoundaryInterface;

public class ParkManager /*implements BoundaryInterface*/ {
	private static ArrayList<Park> parks = new ArrayList<Park>();
	private static ArrayList<Note> notes = new ArrayList<Note>();
	private static ArrayList<Order> orders = new ArrayList<Order>();
	private static ArrayList<Visitor> visitors = new ArrayList<Visitor>();
	private static ArrayList<Report> reports = new ArrayList<Report>();
	
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
