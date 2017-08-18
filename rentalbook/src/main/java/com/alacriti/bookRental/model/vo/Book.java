package com.alacriti.bookRental.model.vo;

public class Book {
	private String bookTitle;
	private String authorName;
	private int price;
	private String categoryName;
	private String description;
	private String isbn;
	private int count;
	private int bookid;
	private User user;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Book(String bookTitle, String authorName, int price,
			String categoryName, String description, String isbn2, int count2) {
		super();
		this.bookTitle = bookTitle;
		this.authorName = authorName;
		this.price = price;
		this.categoryName = categoryName;
		this.description = description;
		this.count = count2;
		this.isbn = isbn2;
	}

	public Book() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

}
