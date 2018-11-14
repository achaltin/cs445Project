package entities;

import datastructs.Date;

import cs445.parkPayment.UniqueIdGenerator;

public class Note {
	int nid;
	int pid;
	int vid;
	Date date;
	String title;
	String text;
	public Note(int p, int v, Date d, String ti, String te) {
		nid = UniqueIdGenerator.getUniqueID();
		pid = p;
		vid = v;
		date = d;
		title = ti;
		text = te;
	}
	public boolean equals(Note other) {
		return nid == other.nid;
	}
	public boolean isDuplicate(Note other) {
		return pid == other.pid
				&& vid == other.vid
				&& date.equals(other.date)
				&& title.equals(other.title)
				&& text.equals(other.text);
	}
}
