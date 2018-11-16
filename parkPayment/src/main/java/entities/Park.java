package entities;

import java.util.ArrayList;
import java.util.Scanner;

import cs445.parkPayment.UniqueIdGenerator;
import datastructs.Address;
import datastructs.Date;
import datastructs.Geo;

public class Park {
	int pid;
	String name;
	String region;
	Address address;
	String phone;
	String web;
	Geo geo;
	int[][] paymentInfo;
	ArrayList<Date> admissions;
	ArrayList<Note> notes;
	
	
	public Park(String n, Address a, String w, Geo g, int[][] pi) {
		pid = UniqueIdGenerator.getUniqueID();
		name = n;
		region = "";
		address = a;
		phone = "";
		web = w;
		geo = g;
		if(isValidPaymentArray(pi))
			paymentInfo = pi;
		else
			paymentInfo = makeValidPaymentArray();
		admissions = new ArrayList<Date>();
		notes = new ArrayList<Note>();
	}
	public Park(String n, String r, Address a, String p, String w, Geo g, int[][] pi) {
		pid = UniqueIdGenerator.getUniqueID();
		name = n;
		region = r;
		address = a;
		phone = p;
		web = w;
		geo = g;
		if(isValidPaymentArray(pi))
			paymentInfo = pi;
		else
			paymentInfo = makeValidPaymentArray();
		admissions = new ArrayList<Date>();
		notes = new ArrayList<Note>();
	}
	public String toString() {
		String ret = "PID: "+pid+": Name: "+name+" Region: "+region
				+" Address: "+address.toString() + " Website: "+web
				+" Phone: "+phone+" Geo: "+geo.toString() +"\nPayment Info: \n"+
				"\t\tIn-State\tOut-Of-State\nRV:\t\t"+paymentInfo[0][0]
				+"\t\t"+paymentInfo[0][1]+"\nCar:\t\t"+paymentInfo[1][0]
				+"\t\t"+paymentInfo[1][1]+"\nMotorcycle\t"+paymentInfo[2][0]
				+"\t\t"+paymentInfo[2][1]+"\nNotes:";
		for(Note n : notes) {
			ret+="\n\t"+n.title+": "+n.text;
		}
		return ret;
	}
	
	public static boolean isValidPaymentArray(int [][] pi) {
		return pi.length == 3 && pi[0].length ==2 && pi[1].length == 2 && pi[2].length==2;
	}
	
	public static int[][] makeValidPaymentArray(int inMot, int outMot, int inCar, int outCar, 
			int inRv, int outRv){
		int [][] ret = { {inMot, outMot}, {inCar, outCar}, {inRv, outRv} };
		return ret;
	}
	public static int[][] makeValidPaymentArray(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter in-state motercyle price: ");
		int inMot = scan.nextInt();
		System.out.println("Enter out-of-state motorcyle price: ");
		int outMot = scan.nextInt();
		System.out.println("Enter in-state car price: ");
		int inCar = scan.nextInt();
		System.out.println("Enter out-of-state car price: ");
		int outCar = scan.nextInt();		
		System.out.println("Enter in-state RV price: ");
		int inRv = scan.nextInt();
		System.out.println("Enter out-of-state RV price: ");
		int outRv = scan.nextInt();
		scan.close();
		
		int [][] ret = { {inMot, outMot}, {inCar, outCar}, {inRv, outRv} };
		return ret;
	}
	public void addAdmission(Date d) {		
		admissions.add(d);
	}
	public void addAdmission(int m, int d, int y) {
		admissions.add(new Date(m, d, y));
	}
	public void addAdmission() {
		java.util.Date today = new java.util.Date();
		Date date = new Date(today.getMonth(), today.getDate(), today.getYear());
		admissions.add(date);
	}
	public void addNote(Note n) {
		if(n.pid == pid)
			notes.add(n);
	}
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public int getPid() {
		return pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public void setAddress(Address a) {
		address = a;
	}
	public void setRegion(String r) {
		region = r;
	}
	public void setPhone(String p) {
		phone = p;
	}
	public void setWebsite(String w) {
		web = w;
	}
	public void setGeo(Geo g) {
		geo = g;
	}
	public void setPaymentInfo(int [][] pi) {
		paymentInfo = pi;
	}
	public int[][] getPaymentInfo(){
		return paymentInfo;
	}
	public Address getAddress() {
		return address;
	}
	public String getWebsite() {
		return web;
	}
	public String getRegion() {
		return region;
	}
	public String getPhone() {
		return phone;
	}
	public boolean equals(Park other) {
		return pid==other.pid;
	}
}

