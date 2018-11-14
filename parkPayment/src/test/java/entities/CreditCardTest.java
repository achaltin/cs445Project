package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import datastructs.Address;

public class CreditCardTest {

	@Test
	public void testGoodCC() {
		Address a = new Address("street","city","state","zip");
		CreditCard cc = new CreditCard("1234567812345678", "name","01/19", a);
		assertEquals(cc.getFullNum(), "1234567812345678");
		assertEquals(cc.getAddr().toString(), "street, city state, zip");
		assertEquals(cc.getLastFour(), "5678");
		assertEquals(cc.getName(),"name");
		assertEquals(cc.getExpDate(),"01/19");
	}
	@Test
	public void testBadNum() {
		Address a = new Address("street","city","state","zip");
		CreditCard tooShort = new CreditCard("12345678", "name","01/19", a);
		assertEquals(tooShort.getFullNum(), "    ");
		CreditCard notNumeric = new CreditCard("12345678abcdefg", "name","01/19", a);
		assertEquals(notNumeric.getFullNum(), "    ");
	}
	@Test
	public void setNumOnGoodNumDoesntChange(){
		Address a = new Address("street","city","state","zip");
		CreditCard goodCC = new CreditCard("1234567812345678", "name","01/19", a);
		assertEquals(goodCC.getFullNum(), "1234567812345678");
		goodCC.setfullNum("8765432112345678");
		assertEquals(goodCC.getFullNum(), "1234567812345678");
	}
	@Test
	public void setNumOnBadNumChanges(){
		Address a = new Address("street","city","state","zip");
		CreditCard badCC = new CreditCard("12345", "name","01/19", a);
		assertEquals(badCC.getFullNum(), "    ");
		badCC.setfullNum("1234567812345678");
		assertEquals(badCC.getFullNum(), "1234567812345678");
	}
	@Test
	public void testBadDate() {
		Address a = new Address("street","city","state","zip");
		CreditCard badCC = new CreditCard("12345", "name","not a date", a);
		assertEquals(badCC.getExpDate(),"");
	}
	@Test
	public void setGoodDateOnBadDate() {
		Address a = new Address("street","city","state","zip");
		CreditCard badCC = new CreditCard("12345", "name","not a date", a);
		assertEquals(badCC.getExpDate(),"");
		badCC.updateExpDate("01/19");
		assertEquals(badCC.getExpDate(),"01/19");
	}
	@Test
	public void setLaterDateOnGoodDate() {
		Address a = new Address("street","city","state","zip");
		CreditCard goodCC = new CreditCard("1234567812345678", "name","01/19", a);
		assertEquals(goodCC.getExpDate(),"01/19");
		goodCC.updateExpDate("01/20");
		assertEquals(goodCC.getExpDate(),"01/20");
	}
	@Test
	public void setEarlierDateOnGoodDateDoesntChange() {
		Address a = new Address("street","city","state","zip");
		CreditCard goodCC = new CreditCard("1234567812345678", "name","01/19", a);
		assertEquals(goodCC.getExpDate(),"01/19");
		goodCC.updateExpDate("01/18");
		assertEquals(goodCC.getExpDate(),"01/19");
	}
}
