package com.project.depreciated;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.deprecated.SimpleStatementExample;

public class SimpleStatementTest {
	
	SimpleStatementExample s;
	
	@BeforeEach
	public void setup() {
		s = new SimpleStatementExample();
	}
	
	@Test
	public void testSelectTableName() {
		List<String> list = s.selectTableName();
		//assertNotNull(list);
		assertNotEquals(0, list.size());
		System.out.println(list);
	}
	
	//showcasing vulnerability by sql injection
//	@Test
//	public void sqlITest() {
//		s.selectWhere("''; drop table table_name;--");
//	}
	
}
