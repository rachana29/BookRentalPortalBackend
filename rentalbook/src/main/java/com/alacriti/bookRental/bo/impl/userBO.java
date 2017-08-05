package com.alacriti.bookRental.bo.impl;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.Response;

import com.alacriti.bookRental.bo.IUserBO;
import com.alacriti.bookRental.dao.IUserDAO;
import com.alacriti.bookRental.dao.impl.DAOException;
import com.alacriti.bookRental.dao.impl.UserDAO;
import com.alacriti.bookRental.model.vo.Login;


public class userBO extends BaseBO implements IUserBO {

	public userBO() {

	}

	public userBO(Connection conn) {
		super(conn);
	}
	public List<Login> retrieveMessage() throws BOException {
		IUserDAO accountDAO = null;
		List<Login> msg = null;
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
}
