package com.alacriti.bookRental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.alacriti.bookRental.dao.IUserDAO;
import com.alacriti.bookRental.model.vo.User;

public class UserDAO extends BaseDAO implements IUserDAO {

	public UserDAO() {

	}

	public UserDAO(Connection conn) {
		super(conn);
	}

	public List<User> selectMessage() throws DAOException {
		DataSource dataSource = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<User> list = null;
		User login = null;
		try {
			dataSource = (DataSource) new InitialContext()
					.lookup("java:jboss/datasources/TRAINEEE");
			connection = dataSource.getConnection();
			statement = connection
					.prepareStatement("select * from rachanas_bookRental_UserDetails");
			result = statement.executeQuery();
			list = new ArrayList<User>();
			while (result.next()) {
				login = new User();
				//login.setUser_id(result.getInt(1));
				login.setUser_name(result.getString(2));
				login.setPassword(result.getString(3));
				//login.setUser_type(result.getString(4));
				login.setEmail_id(result.getString(5));
				login.setMobile_number(result.getString(6));
				list.add(login);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public void registerUser(User userAddVO) throws DAOException {
		System.out.println("in userDAO,createUserRole() ");
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sqlCmd = "insert into rachanas_bookRental_UserDetails (User_name,Password,email_id,mobile_number) values(?,?,?,?);";

			stmt = getPreparedStatement(getConnection(), sqlCmd);
			stmt.setString(1, userAddVO.getUser_name());
			stmt.setString(2, userAddVO.getPassword());
			stmt.setString(3, userAddVO.getEmail_id());
			stmt.setString(4, userAddVO.getMobile_number());
			stmt.executeUpdate();
			System.out.println("after insert");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("SQLException in createUserRole():", e);
		} finally {
			close(stmt, rs);
		}
	}

	public User checkLogin(User userAddVO) throws DAOException {
		System.out.println("in userDAO,checkLogin() ");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			String sqlCmd = "SELECT * FROM rachanas_bookRental_UserDetails WHERE email_id = ? AND BINARY(Password) = BINARY(?)";

			stmt = getPreparedStatement(getConnection(), sqlCmd);
			stmt.setString(1, userAddVO.getEmail_id());
			stmt.setString(2, userAddVO.getPassword());

			rs = stmt.executeQuery();

			if (rs.next()) {
				System.out.println("user exists");
				user = new User();
				user.setUser_id(rs.getInt("User_id"));
				user.setUser_name(rs.getString("User_name"));
				user.setPassword(rs.getString("Password"));
				user.setEmail_id(rs.getString("email_id"));
				user.setMobile_number(rs.getString("mobile_number"));

				System.out.println("user_id: " + user.getUser_id());
				System.out.println("User_name: " + user.getUser_name());
				System.out.println("Password: " + user.getPassword());
				System.out.println("email_id: " + user.getEmail_id());
				System.out.println("mobile_number: " + user.getMobile_number());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("SQLException in createUserRole():", e);
		} finally {
			close(stmt, rs);
		}
		return user;
	}
}
