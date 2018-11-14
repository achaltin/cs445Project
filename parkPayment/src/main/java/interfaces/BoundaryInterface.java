package interfaces;

import java.util.ArrayList;
import entities.*;
import datastructs.*;

public interface BoundaryInterface {
	Park createPark(String name, Address addr, String website, Geo geo, int[][] paymentInfo);
	Park createPark(String name, String region, Address addr, String phone, String website, Geo geo, int[][] paymentInfo);
	void updatePark(int pid);
	void deletePark(int pid);
	ArrayList<Park> viewAllParks();
	Park viewParkDetail(int pid);
	ArrayList<Park> searchParks(String key);
	ArrayList<Note> viewParkNotes(int pid);
	Note viewParkNoteDetail(int pid, int nid);
	Note createNoteForPark(int pid, int vid, String title, String text);
	
	ArrayList<Note> viewAllNotes();
	Note viewNoteDetail(int nid);
	void updateNote(int pid, int vid, String title, String text);
	void deleteNote(int nid);
	ArrayList<Note> searchNotes(String key);
	
	Order createOrder(int pid, Vehicle veh, Visitor vi, CreditCard cc);
	ArrayList<Order> viewAllOrders();
	Order viewOrderDetails(int oid);
	ArrayList<Order> searchOrders(String key);
	
	ArrayList<Visitor> viewAllVisitors();
	Visitor viewVisitorDetail(int vid);
	ArrayList<Visitor> searchVisitors(String key);
	
	ArrayList<Report> viewAllReports();
	Report viewReportDetail(int rid);
	
	ArrayList<Object> search(String key);
}
