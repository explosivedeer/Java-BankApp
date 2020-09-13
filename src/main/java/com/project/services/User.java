package com.project.services;

public abstract class User {
	
	protected int accessLevel;
	private String username;
	private String password;
	protected int id;
	
	
	//TODO:
	/*
	 * Generate ID by searching for max primarykey of register_user table
	 * Add 1 to the value
	 * 
	 * 
	 */
	// Set access level:
	
	
//	public User() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	//constructor
	public User(String username, String password) {
		super();
		accessLevel = 0;
		//id = 1;
		this.username = username;
		this.password = password;
	}

	public int getAccessLevel() {
		return accessLevel;
	}
	
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [accessLevel=" + accessLevel + ", username=" + username + ", password=" + password + ", id=" + id
				+ "]";
	}
	
	
}