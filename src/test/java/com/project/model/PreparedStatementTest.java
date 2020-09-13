package com.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.deprecated.PreparedStatementExample;
import com.project.deprecated.TableName;

public class PreparedStatementTest {
	PreparedStatementExample pse;
	
	@BeforeEach
	public void setup() {
		pse = new PreparedStatementExample();
	}
	
//	@Test
//	public void sqlITest() {
//		List<String> list = pse.selectWhere("''; truncate table table_name;--");
//		assertNotNull(list);
//	}
	
	@Test
	public void insertTest() {
		TableName t = new TableName(3, "explosivedeer", "test", 25);
		assertEquals(1, pse.insert(t));
	}
	
}
