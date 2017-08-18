package com.alacriti.bookRental.dao;

import java.util.List;

import javax.ws.rs.core.Response;

import com.alacriti.bookRental.dao.impl.DAOException;
import com.alacriti.bookRental.model.vo.User;

public interface IUserDAO {

 public List<User> selectMessage()throws DAOException;

}
