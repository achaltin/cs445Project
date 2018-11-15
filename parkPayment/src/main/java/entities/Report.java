package entities;

import cs445.parkPayment.UniqueIdGenerator;
import datastructs.Date;
import datastructs.ParkDetailReport;

import java.util.ArrayList;

public class Report {
	int rid;
	String name;
	Date start;
	Date end;
	int totalAdmissions;
	ArrayList<Park> parks;
	ArrayList<ParkDetailReport> parkAdmins;
	
	public Report(String n, Date s, Date e, ArrayList<Park> p) {
		rid = UniqueIdGenerator.getUniqueID();
		name = n;
		start = s;
		end = e;
		parks = p;
		totalAdmissions = 0;
		parkAdmins = new ArrayList<ParkDetailReport>();
		for(Park pk : parks) {
			ParkDetailReport pdr = new ParkDetailReport(pk, 0);
			for(Date d : pk.admissions) {
				if((start.before(d) || start.equals(d))&& (end.after(d) || end.equals(d))) {
					totalAdmissions++;
					pdr.incrementReport();
				}
			}
			parkAdmins.add(pdr);
		}
	}
	public int getRid()
	{
		return rid;
	}
	
	public String toString() {
		String ret = "RID: "+rid+" Name: "+name+" Start Date: "
				+start.toString()+" End Date: "+end.toString()
				+" Total Admissions: "+totalAdmissions+" Detailed Park Reports:";
		for (ParkDetailReport pdr : parkAdmins) {
			ret += "\n\t"+pdr.toString();
		}
		return ret;
	}

}