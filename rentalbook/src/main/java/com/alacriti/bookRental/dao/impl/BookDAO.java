package com.alacriti.bookRental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.bookRental.model.vo.Book;
import com.alacriti.bookRental.model.vo.SearchModel;
import com.alacriti.bookRental.model.vo.User;
import com.mysql.jdbc.Statement;

public class BookDAO extends BaseDAO {

	public BookDAO() {
		super();
	}

	public BookDAO(Connection _connection) {
		super(_connection);
	}

	public int addBook(Book book, Long userId) throws DAOException {
		System.out.println("in in book DAO ");
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sqlCmd3 = "insert into rachanas_bookRental_BookDetails(Book_title,Author_name,Price,Book_category,Description, ISBN, count, User_id,status) values(?,?,?,?,?,?,?,?,1);";
			System.out.println("===> before query execute");
			System.out.println("getConnection(): " + getConnection());
			stmt = getPreparedStatement(getConnection(), sqlCmd3);
			System.out.println("===> after getprepared statement: " + stmt);
			stmt.setString(1, book.getBookTitle());
			stmt.setString(2, book.getAuthorName());
			stmt.setLong(3, book.getPrice());
			stmt.setString(4, book.getCategoryName());
			stmt.setString(5, book.getDescription());
			stmt.setString(6, book.getIsbn());
			stmt.setLong(7, book.getCount());
			stmt.setLong(8, userId);
			System.out.println("===> before query execute2");
			int count = stmt.executeUpdate();
			System.out.println("===> after query execute");
			System.out.println("===>" + count);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("SQLException in addBook():", e);
		} finally {
			close(stmt, rs);
		}
	}

	public int deleteBook(Book book) throws DAOException {
		System.out.println("in in book DAO ");
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sqlCmd3 = "update rachanas_bookRental_BookDetails set status = 0 WHERE  isbn=?;";
			System.out.println("===> before query execute");
			System.out.println("getConnection(): " + getConnection());
			stmt = getPreparedStatement(getConnection(), sqlCmd3);
			System.out.println("===> after getprepared statement: " + stmt);

			stmt.setString(1, book.getIsbn());
			System.out.println("===> before query execute2");
			int count = stmt.executeUpdate();
			System.out.println("===> after query execute");
			System.out.println("===>" + count);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("SQLException in addBook():", e);
		} finally {
			close(stmt, rs);
		}
	}

	public int editBooks(Book book) throws DAOException {
		System.out.println("in in book DAO ");
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sqlCmd3 = "update rachanas_bookRental_BookDetails set Author_name=?,Price=?,Description=?, Book_category=?, count=? WHERE  isbn=?;";
			System.out.println("===> before query execute");
			System.out.println("getConnection(): " + getConnection());
			stmt = getPreparedStatement(getConnection(), sqlCmd3);
			System.out.println("===> after getprepared statement: " + stmt);
			stmt.setString(1, book.getAuthorName());
			stmt.setLong(2, book.getPrice());
			stmt.setString(3, book.getDescription());
			stmt.setString(4, book.getCategoryName());
			stmt.setLong(5, book.getCount());
			stmt.setString(6, book.getIsbn());
			System.out.println("===> before query execute2");
			int count = stmt.executeUpdate();
			System.out.println("===> after query execute");
			System.out.println("===>" + count);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("SQLException in addBook():", e);
		} finally {
			close(stmt, rs);
		}
	}

	public boolean isBookExists(Book book, Long bookid) throws DAOException {
		System.out.println("in in book DAO ");
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sqlCmd = "SELECT * FROM rachanas_bookRental_BookDetails WHERE Book_title = ? AND Author_name = ? AND User_id = ?";
			stmt = getPreparedStatement(getConnection(), sqlCmd);
			stmt.setString(1, book.getBookTitle());
			stmt.setString(2, book.getAuthorName());
			stmt.setLong(3, bookid);
			System.out.println("before execute query");
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("SQLException in addBook():", e);
		} finally {
			close(stmt, rs);
		}
	}

	public ArrayList<Book> getBooks(int id) throws DAOException {
		Connection conn = null;
		String bookTitle;
		String authorName;
		int price;
		String categoryName;
		String description;
		String isbn;
		int count;
		User user;
		PreparedStatement preparedStatement = null;
		ArrayList<Book> booklist = new ArrayList<Book>();
		try {
			conn = getConnection();

			String query = "Select Book_title, Author_name, Price,  Book_category ,Description,  ISBN  , count  from rachanas_bookRental_BookDetails where User_id=? and status=1";
			preparedStatement = getPreparedStatement(getConnection(), query);
			preparedStatement.setLong(1, id);
			ResultSet rs1 = preparedStatement.executeQuery();
			while (rs1.next()) {

				bookTitle = rs1.getString("Book_title");
				authorName = rs1.getString("Author_name");
				price = rs1.getInt("Price");
				categoryName = rs1.getString("Book_category");
				description = rs1.getString("Description");
				isbn = rs1.getString("ISBN");
				count = rs1.getInt("count");

				booklist.add(new Book(bookTitle, authorName, price,
						categoryName, description, isbn, count));
				System.out.println("bookTitle : " + bookTitle);
				System.out.println("authorName : " + authorName);
				System.out.println("Price " + price);
				System.out.println("Book_category " + categoryName);
				System.out.println("Description " + description);
				System.out.println("ISBN " + isbn);
				System.out.println("count " + count);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}

		return booklist;

	}

	public ArrayList<Book> getBookDetails(Long bookid) throws DAOException {
		Connection conn = null;
		String bookTitle;
		String authorName;
		int price;
		String categoryName;
		String description;
		String isbn;
		int count;
		User user;
		PreparedStatement preparedStatement = null;
		ArrayList<Book> booklist = new ArrayList<Book>();
		try {
			conn = getConnection();

			String query = "Select Book_title, Author_name, Price,Description,  Book_category ,  ISBN  , count  from rachanas_bookRental_BookDetails where Book_id=?";
			preparedStatement = getPreparedStatement(getConnection(), query);
			preparedStatement.setLong(1, bookid);
			ResultSet rs1 = preparedStatement.executeQuery();
			while (rs1.next()) {

				bookTitle = rs1.getString("Book_title");
				authorName = rs1.getString("Author_name");
				price = rs1.getInt("Price");
				categoryName = rs1.getString("Book_category");
				description = rs1.getString("Description");
				isbn = rs1.getString("ISBN");
				count = rs1.getInt("count");

				booklist.add(new Book(bookTitle, authorName, price,
						categoryName, description, isbn, count));
				System.out.println("bookTitle : " + bookTitle);
				System.out.println("authorName : " + authorName);
				System.out.println("Price " + price);
				System.out.println("Book_category " + categoryName);
				System.out.println("Description " + description);
				System.out.println("ISBN " + isbn);
				System.out.println("count " + count);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}

		return booklist;

	}

	public List<Book> searchBooks(String searchKey) throws DAOException {
		Connection conn = null;
		String bookTitle;
		String authorName;
		int price;
		String categoryName;
		String description;
		String isbn;
		int count;
		User user;
		PreparedStatement preparedStatement = null;
		List<Book> booklist = new ArrayList<Book>();
		try {
			conn = getConnection();

			System.out.println("In searchBOoks DAO");

			String query = "Select Book_title, Author_name, Price,  Book_category ,Description, "
					+ " ISBN  , count  from rachanas_bookRental_BookDetails  "
					+ "where Book_title LIKE '%"
					+ searchKey
					+ "%' or Author_name LIKE '%" + searchKey + "%'";

			preparedStatement = getPreparedStatement(getConnection(), query);

			ResultSet rs1 = preparedStatement.executeQuery();
			while (rs1.next()) {

				bookTitle = rs1.getString("Book_title");
				authorName = rs1.getString("Author_name");
				price = rs1.getInt("Price");
				categoryName = rs1.getString("Book_category");
				description = rs1.getString("Description");
				isbn = rs1.getString("ISBN");
				count = rs1.getInt("count");

				booklist.add(new Book(bookTitle, authorName, price,
						categoryName, description, isbn, count));
				System.out.println("bookTitle : " + bookTitle);
				System.out.println("authorName : " + authorName);
				System.out.println("Price " + price);
				System.out.println("Book_category " + categoryName);
				System.out.println("Description " + description);
				System.out.println("ISBN " + isbn);
				System.out.println("count " + count);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		}

		return booklist;

	}
}
