package com.project.services;

import java.math.BigDecimal;
import java.util.Scanner;

import com.project.dao.DaoContract;
import com.project.model.UserTable;

public class Customer extends User {
	
	//eliminate this
	static DaoContract<User, Integer> dc = new UserTable();
	//private Account customerAccount;
	
	//Account Fields
	private int account_id;
	
	private BigDecimal transaction;
	//private BigDecimal transaction2;
	private BigDecimal balance;
	private BigDecimal loanBalance;
	
	
	
//	public Customer() {
//		super();
//		this.accessLevel = 1;
//	}
	
	public Customer(String username, String password, int id) {//int accessLevel, double balance, double loanBalance) {
		super(username, password);
		this.accessLevel = 1;
		this.id = id;
		//balance = 0.0;
		//customerAccount = new Account();
//		this.balance = balance;
//		this.loanBalance = loanBalance;
	}
	
	//Connecting Account class
//	public double viewBalance() {
//		return customerAccount.viewBalance();
//	}
//	
//	public void dePosit(double amount) {
//		customerAccount.dePosit(amount);
//	}
	//Over the course of refactoring the program, I decided Account.java was unecessary.
	
	// Open Account
	public int create_account(int id) {
		return dc.createAccount(id);
	}
	
	// Deposit
//	public void deposit(DaoContract<User, Integer> dc, int accountid, double amount) {
//		balance = balance + amount;
//	}

	// Update Balance After Transaction
	public void updateBalance() {
		BigDecimal result = new BigDecimal(0);
		balance = result.add(this.transaction);
	}

	// Update Balance From Database
	public BigDecimal pullBalance(BigDecimal dbpull) {
		BigDecimal result = new BigDecimal(0);
		balance = result.add(dbpull);
		return result;
	}
	

	// Create a Loan
	public void createLoan(BigDecimal loan) {
		this.loanBalance = loan;
	}

	// View Loan
	public BigDecimal viewLoanBalance() {
		return loanBalance;
	}
	// Pay a Loan

	public BigDecimal payLoan(BigDecimal loan) {
		return balance - loan;
	}

	
	
	//getters and setters
	
	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public BigDecimal getTransaction() {
		return transaction;
	}

	public void setTransaction(BigDecimal transaction) {
		this.transaction = transaction;
	}

	public BigDecimal getBalance() {
		return balance;
	}
	
	
	
// TODO:	(done)
//		- View Balance   	x	viewBalance()
//		- Withdraw       	x	withDraw()
//		- Deposit			x	dePosit()
//		- Transfer			x  	transferFundsIn() & transferFundsOut()
//		- Loan				x	viewLoanBalance()
//		- Pay Loan			x 	payLoan()
	
}
