package entities;

import datastructs.Address;
import java.util.Scanner;

public class CreditCard {
	private int[] fullNum;
	private String nameOnCard;
	private String expDate;
	private int[] lastFour;
	private Address billing;
	
	public CreditCard(int[] num, String name, String exp, Address addr) {
		fullNum = num;
		nameOnCard = name;
		expDate = exp;
		billing = addr;
		lastFour = new int[4];
		lastFour[3] = fullNum[fullNum.length-1];
		lastFour[2] = fullNum[fullNum.length-2];
		lastFour[1] = fullNum[fullNum.length-3];
		lastFour[0] = fullNum[fullNum.length-4];

	}
	
	public static boolean isValidCCNum(int [] num) {
		return num.length == 16 || num.length == 15;
	}
	
	public static boolean isValidExp(String exp) {
		String[] split = exp.split("/");
		try {
			int m = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			return (m<=12 && m>0 && y>=18 && y<100);
		}catch(Exception e) {
			return false;
		}
	}
}
