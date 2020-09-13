package com.project.services;

import java.util.Scanner;

public class Employee extends User {
	
	public Employee(String username, String password, int id) {
		super(username, password);
		this.accessLevel = 2;
		this.id = id;
		// TODO Auto-generated constructor stub
	}
	
//	public Employee() {
//		super();
//		this.accessLevel = 2;
//	}
	
	/*approve accounts*/
	// View Account
	
	public String viewAccounts() {
		Scanner scanner = new Scanner(System.in); 
		//grab list of accounts
		//display accounts with # option next to each
		int employeeSelection = scanner.nextInt();
		int n = 5; //number of accounts
		for(int i = 0; i < n; i++) {
			approveAccount();
			i++;
		}
		
		return account;
	}
	
	public Boolean approveAccount() {
		boolean approval = false;
		
		return approval;
	}
	
	// view transaction logs
	
	// view / approve loans

}
