package cs445.parkPayment;
import entities.*;
import datastructs.*;
import interfaces.ParkManager;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Hello world!
 *
 */
public class App implements ParkManager
{
	ArrayList<Park> parks = new ArrayList<Park>();

    public static void main( String[] args )
    {
    	App pm = new App();
    	pm.addPark(new Address());
    	pm.listParks();
    	pm.addPark(2.50f, new Address());
    	pm.addPark(new Address("456 Smile Rd","Peoria","IL","98765"));
    	pm.listParks();
    	Scanner scan = new Scanner(System.in);
    	String loop;
    	do {
	        System.out.print( "Welcome to Park Manager! Are you a Visitor (V), Ranger (R), or Employee (E)?  " );
	        char userType = scan.next().charAt(0);
	        switch(userType) {
	        case 'v':
	        case 'V':
	        	userMenu();
	        	break;
	        case 'r':
	        case 'R':
	        	rangerMenu();
	        	break;
	        case 'E':
	        case 'e':
	        	empMenu();
	        	break;
	        }
	        System.out.println("Enter q to quit, or anything else to continue:  ");
	        loop = scan.next();
    	} while(!loop.equals("q"));
    	scan.close();
    }
    public void listParks() {
    	System.out.println("Parks\n---------------\nFee\tAddress");
    	for(Park p : parks)
    		System.out.println(p.toString());
    }
	public int addPark(float f, Address addr) {
		Park p = new Park(f, addr);
		parks.add(p);
		return p.id;
	}
	public int addPark(Address addr) {
		Park p = new Park(addr);
		parks.add(p);
		return p.id;
	}
	public void updatePark(int id, float f) {
		for(Park p : parks) {
			if(p.id == id) {
				p.updateFee(f);
				break;
			}
		}
	}
	public static void userMenu() {
		
	}
	public static void rangerMenu() {
		
	}
	public static void empMenu() {
		final String ADMIN_USERNAME = "admin";
		final String ADMIN_PWD = "pwd";
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter your username: ");
		String un = scan.next();
		System.out.print("Please enter your password: ");
		String pass = scan.next();
		if(!un.equals(ADMIN_USERNAME) || !pass.equals(ADMIN_PWD)) {
			System.out.println("Wrong username or password");
			return;
		}
		System.out.println("What would you like to do?");
		System.out.println("\tAdd Park (a)"
				+ "\n\tDelete Park (d)"
				+ "\n\tUpdatePark (u)"
				+ "\n\tSearch for Order (o)");
		
	}
}