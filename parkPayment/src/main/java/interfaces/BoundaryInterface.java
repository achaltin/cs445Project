package interfaces;

import java.util.ArrayList;
import entities.*;
import datastructs.*;

public interface BoundaryInterface {
	public Park createPark(String name, Address addr, String website, Geo geo, int[][] paymentInfo);
	public Park createPark(String name, String region, Address addr, String phone, String website, Geo geo, int[][] paymentInfo);
	public void updatePark(int pid, String name, Address addr, String website, Geo geo, int[][] paymentInfo);
	public void updatePark(int pid, String name, String region, Address addr, String phone, String website, Geo geo, int[][] paymentInfo);
	public void deletePark(int pid);
	public ArrayList<Park> viewAllParks();
	public Park viewParkDetail(int pid);
	public ArrayList<Park> searchParks(String key);
	public ArrayList<Note> viewParkNotes(int pid);
	public Note viewParkNoteDetail(int pid, int nid);
	public Note createNoteForPark(int pid, int vid, String title, String text);
	public Note createNoteForPark(int pid, int vid, Date d, String title, String text);
	
	public ArrayList<Note> viewAllNotes();
	public Note viewNoteDetail(int nid);
	public void updateNote(int nid, int vid, String title, String text);
	public void deleteNote(int nid);
	public ArrayList<Note> searchNotes(String key);
	
	public Order createOrder(int pid, Vehicle veh, Visitor vi, CreditCard cc);
	public ArrayList<Order> viewAllOrders();
	public Order viewOrderDetails(int oid);
	public ArrayList<Order> searchOrders(String key);
	
	public Visitor createVisitor(String email);
	public Visitor createVisitor(String name, String email);
	public ArrayList<Visitor> viewAllVisitors();
	public Visitor viewVisitorDetail(int vid);
	public ArrayList<Visitor> searchVisitors(String key);
	
	public Report createReport(String name, Date start, Date end);
	public ArrayList<Report> viewAllReports();
	public Report viewReportDetail(int rid);
	
	public ArrayList<Object> search(String key);
}
