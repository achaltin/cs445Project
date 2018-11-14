package entities;

import java.util.Date;

import cs445.parkPayment.UniqueIdGenerator;

public class Note {
	int nid;
	int pid;
	int vid;
	Date date;
	String title;
	String text;
	public Note(int p, int v, String ti, String te) {
		nid = UniqueIdGenerator.getUniqueID();
		pid = p;
		vid = v;
		date = new Date();
		title = ti;
		text = te;
	}
	

}
