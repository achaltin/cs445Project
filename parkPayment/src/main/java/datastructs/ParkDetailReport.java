package datastructs;

import entities.Park;

public class ParkDetailReport {
	int pid;
	String parkName;
	int totalAdmissions;
	
	public ParkDetailReport(Park p, int admin) {
		pid = p.getPid();
		parkName = p.getName();
		totalAdmissions = admin;
	}
	public ParkDetailReport(int p, String pName, int admin) {
		pid = p;
		parkName = pName;
		totalAdmissions = admin;
	}
	public void incrementReport() {
		totalAdmissions++;
	}
	public String toString() {
		return "pid: "+pid+" park name: "+parkName+" park admissions: "+totalAdmissions;
	}

}
