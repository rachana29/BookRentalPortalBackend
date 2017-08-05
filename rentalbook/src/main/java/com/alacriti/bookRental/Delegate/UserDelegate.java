package com.alacriti.bookRental.Delegate;
import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.Response;

import com.alacriti.bookRental.bo.IUserBO;
import com.alacriti.bookRental.bo.impl.userBO;
import com.alacriti.bookRental.model.vo.Login;
public class UserDelegate extends BaseDelegate{


	public List<Login> getMessage(Long userId) {
		boolean rollBack = false;
		Connection connection = null;
		List<Login> msg = null;
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

}

