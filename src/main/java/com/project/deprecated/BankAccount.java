package com.project.deprecated;

public class BankAccount {
	
	private int id;
	private String balance;
	private String loanBalance;
	
	//constructor
	public BankAccount(int id, String balance, String loanBalance) {
		super();
		this.id = id;
		this.balance = balance;
		this.loanBalance = loanBalance;
	}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(String loanBalance) {
		this.loanBalance = loanBalance;
	}
	
	
	
}
