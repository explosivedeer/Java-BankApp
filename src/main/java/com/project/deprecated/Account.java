package com.project.deprecated;

public class Account {
	
	private double balance;
	private double loanBalance;
	
	// Set balance to 0.00
	public Account() {
		balance = 0.0;
	}
	// Deposit
	public void dePosit(double amount) {
		balance = balance + amount;
	}
	// Withdraw
	public void withDraw(double amount) {
		if(amount <= balance) {
			balance = balance - amount;
		}
		else {
			System.err.println("Transaction failed due to insufficient funds!");
		}
		
	}
	
	// View Balance
	public double viewBalance() {
		return balance;
	}
	
	// Transfer
	
	public double transferFundsIn(double money) {
		//TODO: from another account
		return balance + money;
	}
	
	public double transferFundsOut(double money) {
		//TODO: to another account
		return balance - money;
	}
	
	// Create a Loan
	public void createLoan(double loan) {
		this.loanBalance = loan;	
	}
	
	// View Loan
	public double getLoanBalance() {
		return loanBalance;
	}
	// Pay a Loan
	
	public double payLoan(double loan) {
		return balance - loan;
	}
}
