package entities;

import cs445.parkPayment.UniqueIdGenerator;
import java.util.ArrayList;

public class Visitor {
	int vid;
	String name;
	String email;
	ArrayList<Order> orders;
	ArrayList<Note> notes;
	
	public Visitor(String e) {
		vid = UniqueIdGenerator.getUniqueID();
		name = "";
		email = e;
	}
	public Visitor(String n, String e) {
		vid = UniqueIdGenerator.getUniqueID();
		name = n;
		email = e;
	}
	public void addOrder(Order o) {
		if(o.visitor.vid == vid)
			orders.add(o);
	}
	public void addNote(Note n) {
		if(n.vid == vid)
			notes.add(n);
	}
}

