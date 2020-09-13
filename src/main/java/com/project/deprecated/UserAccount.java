package com.project.deprecated;

public class UserAccount {
	
	private int id;
	private int accessLevel;
	private String username;
	private String password;
	
	//constructor
	public UserAccount(int id, int accessLevel, String username, String password) {
		super();
		this.id = id;
		this.accessLevel = accessLevel;
		this.username = username;
		this.password = password;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}
