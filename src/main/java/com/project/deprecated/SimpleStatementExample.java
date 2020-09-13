package com.project.deprecated;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.project.config.ConnectionUtil;

public class SimpleStatementExample {
	
	public List<String> selectTableName(){
		Connection conn;
		List<String> list = new LinkedList<>(); //what we'll store the results from the sql in
		try {
			conn = ConnectionUtil.getInstance().getConnection(); //get the connection from an instance of connectionutil
			Statement s = conn.createStatement(); //the sql command you'll pass to the server
			String sql = "select username from table_name"; //create the query here!
			ResultSet rs = s.executeQuery(sql); //execute that query!
			while(rs.next()) { //get the results through iteration
				//could also do list.add(rs.getString("username"));
				list.add(rs.getString(1));
			}
			//prevent memory leaks!
			s.close();
			rs.close();
			conn.close();
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return list;
	}
	
	public List<String> selectWhere(String where) {
		Connection conn;
		List<String> list = new LinkedList<>();
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "select username from table_name where password = " + where;
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString(1));
				
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	
	}
	
	//additional debug layer (this can help spot user permission errors)
		public static void main(String[] args) {
			System.out.println(new SimpleStatementExample().selectTableName());
		}
}
