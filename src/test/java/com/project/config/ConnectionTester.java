package com.project.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConnectionTester {
	
	private ConnectionUtil c;
	
	@BeforeEach
	public void setUp() {
		c = ConnectionUtil.getInstance();
		
	}
	
	@Test
	public void testConnection() throws SQLException {
		assertNotNull(c.getConnection());
	}

}
