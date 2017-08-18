package com.alacriti.bookRental.Delegate;

import java.sql.Connection;
import java.util.List;

import com.alacriti.bookRental.bo.IUserBO;
import com.alacriti.bookRental.bo.impl.userBO;
import com.alacriti.bookRental.model.vo.Book;
import com.alacriti.bookRental.model.vo.BookRentalResponse;
import com.alacriti.bookRental.model.vo.User;

public class UserDelegate extends BaseDelegate {

	public List<User> getMessage(Long userId) {
		boolean rollBack = false;
		Connection connection = null;
		List<User> msg = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			IUserBO sampleBO = new userBO();
			msg = sampleBO.retrieveMessage();
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}

		return msg;
	}

	public User loginUser(User userAddVO) {
		System.out.println("in UserDelegate, createUserRole");
		boolean rollBack = false;
		Connection connection = null;
		User user = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			userBO userBO = new userBO(getConnection());
			user = userBO.loginUser(userAddVO);
		} catch (Exception e) {
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return user;
	}

	private boolean isValidLoginRequest(User userAddVO) {

		if (userAddVO == null) {
			return false;
		} else {
			return true;
		}

	}

	public BookRentalResponse<User> registerUser(User userAddVO) {
		System.out.println("delegate");
		boolean rollBack = false;
		Connection connection = null;
		BookRentalResponse<User> bookRentalResponse = null;
		try {
			bookRentalResponse = new BookRentalResponse<User>();
			connection = startDBTransaction();
			setConnection(connection);
			userBO userBO = new userBO(getConnection());
			userBO.registerUser(userAddVO);
			bookRentalResponse.setResponseVo(userAddVO);
			bookRentalResponse.setStatusCode(200);
		} catch (Exception e) {
			rollBack = true;
			bookRentalResponse.setStatusCode(500);
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return bookRentalResponse;
	}
}
