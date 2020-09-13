package com.project.deprecated;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.project.config.ConnectionUtil;
import com.project.dao.DaoContract;
import com.project.services.NewUser;
import com.project.services.Customer;

public class UserDao implements DaoContract<NewUser, Integer>{
	public List<NewUser> findAll() {
		return null;
	}

	@Override
	public NewUser findById(Integer i) {
		NewUser u = new NewUser(null, null);
		
		try {
			Connection conn = ConnectionUtil.getInstance().getConnection();
			String sql = "select * from user where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet  rs = ps.executeQuery();
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setCustomer(
						new Customer(
						rs.getString("username"),		
						rs.getString("password"),		
						rs.getInt("id"),
						rs.getInt("accessLevel"),
						rs.getDouble("balance"),
						rs.getDouble("loanBalance")
								));
			}
			conn.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public NewUser delete(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewUser update(NewUser t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewUser insert(NewUser t) {
		// TODO Auto-generated method stub
		return null;
	}
}
