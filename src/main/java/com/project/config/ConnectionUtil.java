package com.project.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private String url="";  // jdbc:postgresql://amazonurl.com:port/postgres?current-schema=public"
	private String username="";		//database username
	private String password="";		//database password
	private static ConnectionUtil c;
	
	private ConnectionUtil() {
		super();
	}
	
	public static ConnectionUtil getInstance() {
		if(c == null) {
			c = new ConnectionUtil();
		}
		return c;
	}
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
}