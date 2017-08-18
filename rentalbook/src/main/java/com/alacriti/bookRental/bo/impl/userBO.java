package com.alacriti.bookRental.bo.impl;

import java.sql.Connection;
import java.util.List;

import com.alacriti.bookRental.bo.IUserBO;
import com.alacriti.bookRental.dao.IUserDAO;
import com.alacriti.bookRental.dao.impl.DAOException;
import com.alacriti.bookRental.dao.impl.UserDAO;
import com.alacriti.bookRental.model.vo.User;

public class userBO extends BaseBO implements IUserBO {

	public userBO() {

	}

	public userBO(Connection conn) {
		super(conn);
	}

	public List<User> retrieveMessage() throws BOException {
		IUserDAO accountDAO = null;
		List<User> msg = null;
		try {
			accountDAO = new UserDAO(getConnection());
			msg = accountDAO.selectMessage();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new BOException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException();
		}
		return msg;
	}

	public User loginUser(User userAddVO) throws DAOException, BOException {
		User user = null;
		System.out.println("in userBO,createUserRole ");
		try {
			UserDAO userDAO = new UserDAO(getConnection());
			user = userDAO.checkLogin(userAddVO);
		} catch (Exception e) {
			throw new BOException();
		}
		return user;

	}

	public void registerUser(User userAddVO) throws DAOException, BOException {
		System.out.println("userbo");
		try {
			UserDAO userDAO = new UserDAO(getConnection());
			userDAO.registerUser(userAddVO);

		} catch (Exception e) {
			throw new BOException();
		}
	}

}
