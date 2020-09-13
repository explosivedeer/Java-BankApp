package com.project.model;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import com.project.config.ConnectionUtil;
import com.project.dao.DaoContract;
import com.project.services.Customer;
import com.project.services.NewUser;
import com.project.services.User;

public class UserTable implements DaoContract<User, Integer> {

	// NEW USER SQL Methods:
	
//	@Override
//	public int permissions(NewUser t) {
//		Connection conn;
//		/**
//		 * TODO: Update SQL statement to search Employee and Manager tables to get AccessLevel!
//		 */
//		try {
//			conn = ConnectionUtil.getInstance().getConnection();
//			String sql = "select accesslevel from customers where username=? and password=?";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, t.getUsername());
//			ps.setString(2, t.getPassword());
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				t.setAccessLevel(rs.getInt("accesslevel"));
//			}
//			ps.close();
//			conn.close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return t.getAccessLevel();
//	}
	
	@Override
	public boolean isTaken(String i) {
		Connection conn;
		
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "select username from customer where username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, i);
			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()) {
				return false;
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	// Insert a new Register entry:
//	@Override
//	public int registerUser(NewUser t) {
//		Connection conn;
//		int resultCount = -1;
//		try {
//			conn = ConnectionUtil.getInstance().getConnection();
//			String sql = "insert into register_user (username, password) values (?,?)";
//			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			ps.setString(1, t.getUsername());
//			ps.setString(2, t.getPassword());
//			try (ResultSet rs = ps.getGeneratedKeys()) {
//				if (rs.next()) {
//					t.setId(rs.getInt(1));
//				}
//			} catch (SQLException s) {
//				s.printStackTrace();
//
//			}
//			
//			resultCount = ps.executeUpdate();
//			ps.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return resultCount;
//	}
	
	@Override
	public int registerUser(NewUser t) {
		Connection conn;
		int resultId = 0;
		
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "{? = call register(?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, t.getUsername());
			cstmt.setString(3, t.getPassword());
			
			cstmt.execute();
			resultId = cstmt.getInt(1);
			t.setId(resultId);
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Your ID is: " + resultId);
		return resultId;
		
	}

	// CUSTOMER SQL Methods:

	// Verify a Login Attempt:
//	@Override
//	public boolean verifyLogin(NewUser t) {
//		Connection conn;
//		//boolean flag = true;
//
//		try {
//			conn = ConnectionUtil.getInstance().getConnection();
//			String sql = "select username, password from register_user where username=? and password=?";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, t.getUsername());
//			ps.setString(2, t.getPassword());
//			
//			ResultSet rs = ps.executeQuery();
//			
//			if(!rs.next()) {
//				return false;
//			}
//			
//			ps.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
	
	@Override
	public boolean verifyLogin(NewUser t) {
		Connection conn;
		
		int resultId = 0;
		
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "{? = call login(?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, t.getUsername());
			cstmt.setString(3, t.getPassword());
			
			cstmt.execute();
			
			resultId = cstmt.getInt(1);
			if (resultId == -1) {
				return false;
			}
			t.setId(resultId);
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Your ID is: " + resultId);
		return true;
		
	}
	
	@Override
	public boolean verifyAccountID(Customer c) {
		Connection conn;
		boolean result = false;
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "{? = call check_account(?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1,  Types.BOOLEAN);
			cstmt.setInt(2, c.getAccount_id());
			cstmt.setInt(3, c.getId());
			
			cstmt.execute();
			
			result = cstmt.getBoolean(1);
			
			if (!result) {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	
	}
	
	@Override
	public int createAccount(Integer t) {
		Connection conn;
		int resultId = -1;
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "{? = call create_account(?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, t);
			
			cstmt.execute();
			
			resultId = cstmt.getInt(1);
			
			if (resultId == -1) {
				System.out.println("Something went wrong!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultId;
	}
	
	@Override
	public BigDecimal creditAccount(Customer c) {
		Connection conn;
		 BigDecimal amount = new BigDecimal(0);
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "{? = call deposit(?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.NUMERIC);
			cstmt.setInt(2, c.getId());
			cstmt.setInt(3, c.getAccount_id());
			cstmt.setBigDecimal(4, c.getTransaction());
			
			cstmt.execute();
			
			amount = cstmt.getBigDecimal(1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return amount;
	}
	
	//TODO: Condense creditAccount and debitAccount into a single method.
	
	@Override
	public BigDecimal debitAccount(Customer c) {
		Connection conn;
		 BigDecimal amount = new BigDecimal(0);
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "{? = call withdraw(?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.NUMERIC);
			cstmt.setInt(2, c.getId());
			cstmt.setInt(3, c.getAccount_id());
			cstmt.setBigDecimal(4, c.getTransaction());
			
			cstmt.execute();
			
			amount = cstmt.getBigDecimal(1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return amount;
	}
	
	@Override
	public BigDecimal viewBalance(Customer c) {
		Connection conn;
		BigDecimal resultBal = new BigDecimal(0);
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "{? = call viewBalance(?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.NUMERIC);
			cstmt.setInt(2, c.getId());
			cstmt.setInt(3, c.getAccount_id());
			
			cstmt.execute();
			
			resultBal = cstmt.getBigDecimal(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultBal;
	}
	
	//TODO: CAN ELIMINATE THIS
	/*
	@Override
	public BigDecimal transferFunds(Customer c) {
		Connection conn;
		BigDecimal result = new BigDecimal(0);
		try {
			conn = ConnectionUtil.getInstance().getConnection();
			String sql = "{? = call transferFunds(?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.NUMERIC);
			cstmt.setInt(2, c.getId());
			cstmt.setInt(3, c.getAccount_id());
			
			cstmt.execute();
			
			result = cstmt.getBigDecimal(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	*/
}
