package entities;
import java.util.Date;

import cs445.parkPayment.UniqueIdGenerator;

import java.util.ArrayList;
import javafx.util.Pair;

public class Report {
	int rid;
	String name;
	Date start;
	Date end;
	int totalAdmissions;
	ArrayList<Park> parks;
	ArrayList<Pair<Park, Integer>> parkAdmins;
	
	public Report(String n, Date s, Date e, ArrayList<Park> p) {
		rid = UniqueIdGenerator.getUniqueID();
		name = n;
		start = s;
		end = e;
		parks = p;
		totalAdmissions = 0;
		for(Park pk : parks) {
			Integer parkCount = 0;
			for(Date d : pk.admissions) {
				if(start.before(d) && end.after(d)) {
					totalAdmissions++;
					parkCount++;
				}
			}
			parkAdmins.add(new Pair<Park, Integer>(pk, parkCount));
		}
	}
	

}