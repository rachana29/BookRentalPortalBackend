package com.alacriti.bookRental.model.vo;

public class SearchModel {

	
		public String bookName,authorName;
		public SearchModel()
		{
			
		}
		public SearchModel(String bookName, String authorName) {
			this.bookName = bookName;
			this.authorName = authorName;
		}
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		
}
