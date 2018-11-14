package entities;

import cs445.parkPayment.UniqueIdGenerator;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Visitor {
	int vid;
	String name;
	String email;
	ArrayList<Order> orders;
	ArrayList<Note> notes;
	
	public Visitor(String e) {
		vid = UniqueIdGenerator.getUniqueID();
		name = "";
		email = "";
		if(isValidEmail(e))
			email = e;
		orders = new ArrayList<Order>();
		notes = new ArrayList<Note>();
	}
	public Visitor(String n, String e) {
		vid = UniqueIdGenerator.getUniqueID();
		name = n;
		email = "";
		if(isValidEmail(e))
			email = e;
		orders = new ArrayList<Order>();
		notes = new ArrayList<Note>();
	}
	public void addOrder(Order o) {
		if(o.visitor.vid == vid)
			orders.add(o);
	}
	public void addNote(Note n) {
		if(n.vid == vid)
			notes.add(n);
	}
	public static boolean isValidEmail(String email) {
		Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
		Matcher match = regex.matcher(email);
		return match.find();
	}
}

