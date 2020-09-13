package com.project.deprecated;

public class UserOld {
	
	private int accessLevel;
	private String username;
	private String password;
	private int id;
	
	// Set access level:
	
	//constructor
	public UserOld(String username, String password) {
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
	
	/**
	 * <i>This gets the Username!</i>
	 * @return username
	 */
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

	@Override
	public String toString() {
		return "User [accessLevel=" + accessLevel + ", username=" + username + ", password=" + password + ", id=" + id
				+ "]";
	}
	
	
	
	
	
	
	
}
