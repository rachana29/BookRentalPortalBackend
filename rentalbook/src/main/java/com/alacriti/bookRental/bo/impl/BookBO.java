package com.alacriti.bookRental.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.bookRental.dao.impl.BookDAO;
import com.alacriti.bookRental.dao.impl.DAOException;
import com.alacriti.bookRental.model.vo.Book;
import com.alacriti.bookRental.model.vo.BookRentalResponse;
import com.alacriti.bookRental.model.vo.Error;

public class BookBO extends BaseBO {
	public BookBO(Connection connection) {
		super(connection);
	}

	public void addBook(Book book, Long userId,
			BookRentalResponse<Book> bookRentalResponse) throws DAOException,
			BOException {
		System.out.println("in bookbo");
		try {
			System.out.println("getConnection(): " + getConnection());
			BookDAO bookDAO = new BookDAO(getConnection());

			if (!bookDAO.isBookExists(book, userId)) {
				bookDAO.addBook(book, userId);
				bookRentalResponse.setResponseVo(book);
				bookRentalResponse.setStatusCode(200);
			} else {
				List<Error> errors = new ArrayList<Error>();
				Error error = new Error("", "book already exists", "");
				errors.add(error);
				bookRentalResponse.setStatusCode(400);
				bookRentalResponse.getErrorResponse().setErrors(errors);
			}

		} catch (Exception e) {
			throw new BOException();
		}
	}

	public void deleteBook(Book book,
			BookRentalResponse<Book> bookRentalResponse) throws DAOException,
			BOException {
		System.out.println("in bookbo");
		try {
			System.out.println("getConnection(): " + getConnection());
			BookDAO bookDAO = new BookDAO(getConnection());
			bookDAO.deleteBook(book);
			bookRentalResponse.setResponseVo(book);
			bookRentalResponse.setStatusCode(200);
		} catch (Exception e) {
			throw new BOException();
		}
	}

	public void editBooks(Book book, BookRentalResponse<Book> bookRentalResponse)
			throws DAOException, BOException {
		System.out.println("in bookbo");
		try {
			System.out.println("getConnection(): " + getConnection());
			BookDAO bookDAO = new BookDAO(getConnection());
			bookDAO.editBooks(book);
			bookRentalResponse.setResponseVo(book);
			bookRentalResponse.setStatusCode(200);
		} catch (Exception e) {
			throw new BOException();
		}
	}

	public void getBooks(int id,
			BookRentalResponse<List<Book>> bookRentalResponse)
			throws DAOException, BOException {
		System.out.println("in bookbo");
		ArrayList<Book> booklist = null;
		try {
			System.out.println("getConnection(): " + getConnection());
			BookDAO bookDAO = new BookDAO(getConnection());

			booklist = bookDAO.getBooks(id);
			bookRentalResponse.setResponseVo(booklist);
			bookRentalResponse.setStatusCode(200);

			List<Error> errors = new ArrayList<Error>();
			Error error = new Error("", "book already exists", "");
			errors.add(error);
			bookRentalResponse.setStatusCode(400);
			bookRentalResponse.getErrorResponse().setErrors(errors);

		} catch (Exception e) {
			throw new BOException();
		}
	}

	public List<Book> searchBooks(String searchKey) throws BOException {
		System.out.println("in bookbo");
		List<Book> booklist = null;
		try {
			System.out.println("getConnection(): " + getConnection());
			BookDAO bookDAO = new BookDAO(getConnection());

			booklist = bookDAO.searchBooks(searchKey);

		} catch (Exception e) {
			throw new BOException();
		}
		return booklist;
	}

	public void getBookDetails(Long bookid,
			BookRentalResponse<List<Book>> bookRentalResponse)
			throws DAOException, BOException {
		System.out.println("in bookbo");
		ArrayList<Book> booklist = null;
		try {
			System.out.println("getConnection(): " + getConnection());
			BookDAO bookDAO = new BookDAO(getConnection());

			booklist = bookDAO.getBookDetails(bookid);
			bookRentalResponse.setResponseVo(booklist);
			bookRentalResponse.setStatusCode(200);

			List<Error> errors = new ArrayList<Error>();
			Error error = new Error("", "book already exists", "");
			errors.add(error);
			bookRentalResponse.setStatusCode(400);
			bookRentalResponse.getErrorResponse().setErrors(errors);

		} catch (Exception e) {
			throw new BOException();
		}
	}

}
