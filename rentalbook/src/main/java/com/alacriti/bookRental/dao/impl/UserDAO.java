package com.alacriti.bookRental.dao.impl;


 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.core.Response;

import com.alacriti.bookRental.dao.IUserDAO;
import com.alacriti.bookRental.model.vo.Login;

public class UserDAO extends BaseDAO implements IUserDAO {

	public UserDAO() {

	}

	public UserDAO(Connection conn) {
		super(conn);
	}
	public List<Login> selectMessage() throws DAOException {
	DataSource dataSource = null;
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	List<Login> list = null;
	Login login = null;
	try {
		dataSource = (DataSource) new InitialContext()
				.lookup("java:jboss/datasources/TRAINEEE");
		connection = dataSource.getConnection();
		statement = connection
				.prepareStatement("select * from rachanas_bookRental_UserDetails");
		result = statement.executeQuery();
		list = new ArrayList<Login>();
		while (result.next()) {
			login = new Login();
			login.setUser_id(result.getInt(1));
			login.setUser_name(result.getString(2));
			login.setPassword(result.getString(3));
			login.setUser_type(result.getString(4));
			login.setEmail_id(result.getString(5));
			login.setMobile_number(result.getString(6));
			list.add(login);
			System.out.println(login.getUser_name());
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return list;
	}
	public Response selectMessage(Login l[]) throws DAOException {

		DataSource dataSource = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {

		dataSource = (DataSource) new InitialContext()
		.lookup("java:jboss/datasources/TRAINEEE");

		connection = dataSource.getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement("insert into rachanas_bookRental_UserDetails values(?,?,?,?,?,?);");

		for(int i=0;i<(l.length);i++){
		preparedStatement.setInt(1, l[i].User_id);
		preparedStatement.setString(2, l[i].User_name);
		preparedStatement.setString(3, l[i].Password);
		preparedStatement.setString(4, l[i].User_type);
		preparedStatement.setString(5, l[i].email_id);
		preparedStatement.setString(6, l[i].mobile_number);
		preparedStatement.executeUpdate();
		}

		} catch (Exception e) {

		System.out.println("Error  :" + e.getMessage());


		} finally {

		if (connection != null) {
		try {
		connection.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		}

	return Response.status(200).entity(result).build();
}
}

	
	
	
	/*public String selectMessage() throws DAOException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Connection connection = null;
		try {
			connection = getConnection();
			String sqlCmd = "sql command";
			stmt = super.getPreparedStatementReturnPK(connection, sqlCmd);
			rs = stmt.getGeneratedKeys();
		} catch (SQLException e) {
			throw new DAOException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}
		return "Heelloo World titlke";
	}

}
*/
