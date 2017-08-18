package com.alacriti.bookRental.Delegate;

import java.sql.Connection;
import java.util.List;

import com.alacriti.bookRental.bo.impl.BookBO;
import com.alacriti.bookRental.model.vo.Book;
import com.alacriti.bookRental.model.vo.BookRentalResponse;
import com.alacriti.bookRental.model.vo.ErrorResponse;
import com.alacriti.bookRental.model.vo.SearchModel;

public class BookDelegate extends BaseDelegate {

	public BookRentalResponse<Book> addBook(Book book, Long userId) {
		System.out.println("in book delegate");
		boolean rollBack = false;
		Connection connection = null;
		BookRentalResponse<Book> bookRentalResponse = null;
		try {
			bookRentalResponse = new BookRentalResponse<Book>();
			ErrorResponse errorResponse = new ErrorResponse();
			bookRentalResponse.setErrorResponse(errorResponse);
			connection = startDBTransaction();
			setConnection(connection);
			System.out.println("getConnection(): " + getConnection());
			BookBO bookBO = new BookBO(getConnection());
			bookBO.addBook(book, userId, bookRentalResponse);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			bookRentalResponse.setStatusCode(500);
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return bookRentalResponse;
	}
	public BookRentalResponse<Book> deleteBook(Book book) {
		System.out.println("in book delegate");
		boolean rollBack = false;
		Connection connection = null;
		BookRentalResponse<Book> bookRentalResponse = null;
		try {
			bookRentalResponse = new BookRentalResponse<Book>();
			ErrorResponse errorResponse = new ErrorResponse();
			bookRentalResponse.setErrorResponse(errorResponse);
			connection = startDBTransaction();
			setConnection(connection);
			System.out.println("getConnection(): " + getConnection());
			BookBO bookBO = new BookBO(getConnection());
			bookBO.deleteBook(book, bookRentalResponse);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			bookRentalResponse.setStatusCode(500);
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return bookRentalResponse;
	}
	public BookRentalResponse<Book> editBooks(Book book) {
		System.out.println("in book delegate");
		boolean rollBack = false;
		Connection connection = null;
		BookRentalResponse<Book> bookRentalResponse = null;
		try {
			bookRentalResponse = new BookRentalResponse<Book>();
			ErrorResponse errorResponse = new ErrorResponse();
			bookRentalResponse.setErrorResponse(errorResponse);
			connection = startDBTransaction();
			setConnection(connection);
			System.out.println("getConnection(): " + getConnection());
			BookBO bookBO = new BookBO(getConnection());
			bookBO.editBooks(book, bookRentalResponse);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			bookRentalResponse.setStatusCode(500);
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return bookRentalResponse;
	}
	public BookRentalResponse<List<Book>> getBooks(int id) {
		System.out.println("in book delegate");
		boolean rollBack = false;
		Connection connection = null;
		BookRentalResponse<List<Book>> bookRentalResponse = null;
		try {
			bookRentalResponse = new BookRentalResponse<List<Book>>();
			ErrorResponse errorResponse = new ErrorResponse();
			bookRentalResponse.setErrorResponse(errorResponse);
			connection = startDBTransaction();
			setConnection(connection);
			System.out.println("getConnection(): " + getConnection());
			BookBO bookBO = new BookBO(getConnection());
			bookBO.getBooks( id, bookRentalResponse);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			bookRentalResponse.setStatusCode(500);
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return bookRentalResponse;
	}
	
	
	public List<Book> searchBooks(String searchKey) {
		System.out.println("in book delegate");
		boolean rollBack = false;
		Connection connection = null;
		List<Book> searchBooks = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			BookBO bookBO = new BookBO(getConnection());
			searchBooks=bookBO.searchBooks(searchKey);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return searchBooks;
	}
	
	
	
	
	public BookRentalResponse<List<Book>> getBookDetails(Long bookid) {
		System.out.println("in book delegate");
		boolean rollBack = false;
		Connection connection = null;
		BookRentalResponse<List<Book>> bookRentalResponse = null;
		try {
			bookRentalResponse = new BookRentalResponse<List<Book>>();
			ErrorResponse errorResponse = new ErrorResponse();
			bookRentalResponse.setErrorResponse(errorResponse);
			connection = startDBTransaction();
			setConnection(connection);
			System.out.println("getConnection(): " + getConnection());
			BookBO bookBO = new BookBO(getConnection());
			bookBO.getBookDetails( bookid, bookRentalResponse);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			bookRentalResponse.setStatusCode(500);
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return bookRentalResponse;
	}
}
