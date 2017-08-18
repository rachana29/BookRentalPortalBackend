package com.alacriti.bookRental.bo;
import java.util.List;

import com.alacriti.bookRental.bo.impl.BOException;
import com.alacriti.bookRental.model.vo.User;
public interface IUserBO {
	List<User> retrieveMessage() throws BOException;
}
