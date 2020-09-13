package com.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.services.Customer;
import com.project.services.NewUser;
//import com.project.model.NewUserTable;

public class UserTableTest {
	UserTable nut;
	
	@BeforeEach
	public void setup() {
		nut = new UserTable();
	}
	
//	@Test
//	public void insertTest() {
//		NewUser nt = new NewUser("rogue", "testpassword");
//		assertEquals(1, nut.insert(nt));
//	}
	
//	@Test
//	public void verifyLoginTest1() {
//		Customer r = new Customer("rogue", "test");
//		assertEquals(false, nut.verifyLogin(r));
//	}
//	
//	@Test
//	public void verifyLoginTest2() {
//		Customer e = new Customer("explosivedeer", "testpassword");
//		assertEquals(true, nut.verifyLogin(e));
//	}
	
//	@Test
//	public void verifyLoginTest3() {
//		NewUser d = new NewUser("test1", "testpassword");
//		assertEquals(true, nut.verifyLogin(d));
//	}
	
	@Test
	public void isTakenTest() {
		//NewUser nu = new NewUser("explosivedeer", "test");
		
		assertEquals(true, nut.isTaken("explosivedeer"));
	}
	
//	@Test
//	public void createAccountTest() {
//		Customer c = new Customer("pgsqltest2", "testpassword", 4);
//		assertEquals(4, nut.createAccount(c));
//	}
	
	@Test
	public void viewBalanceTest() {
		Customer c = new Customer("pgsqltest2", "testpassword", 4);
		assertEquals(5963.06, nut.viewBalance(c));
	}
	
}


