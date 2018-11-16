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
	public int getNid() {
		return nid;
	}
	public int getPid() {
		return pid;
	}
	public int getVid() {
		return vid;
	}
	public String getTitle() {
		return title;
	}
	public String getText() {
		return text;
	}
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	public void setText(String newText) {
		text = newText;
	}
	public void updateDate() {
		java.util.Date today = new java.util.Date();
		date = new Date(today.getMonth(), today.getDate(), today.getYear());
	}
	public Date getDate() {
		return date;
	}
	public String toString() {
		return "NID: "+nid+" PID: "+pid+" VID: "+vid+" Date Written: "
				+date.toString()+" Title: "+title+" Text: "+text;
	}
}
