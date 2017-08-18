package com.alacriti.bookRental.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.bookRental.Delegate.BookDelegate;
import com.alacriti.bookRental.model.vo.Book;
import com.alacriti.bookRental.model.vo.BookRentalResponse;
import com.alacriti.bookRental.util.SessionUtility;

@Path("/book")
public class BookResource {

	@POST
	@Path("/addBook")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(Book book) {
		System.out.println("in book resource");
		Long userId = getUserFromSession();
		BookDelegate bookDelegate = new BookDelegate();

		BookRentalResponse<Book> bookRentalResponse = bookDelegate.addBook(
				book, userId);
		return Response.status(bookRentalResponse.getStatusCode())
				.entity(bookRentalResponse.getResponseVo()).build();
	}

	private Long getUserFromSession() {
		return 1L;

	}

	@POST
	@Path("/deleteBook")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBook(Book book) {
		System.out.println("in book resource");
		BookDelegate bookDelegate = new BookDelegate();

		BookRentalResponse<Book> bookRentalResponse = bookDelegate
				.deleteBook(book);
		return Response.status(200).entity(bookRentalResponse.getResponseVo())
				.build();
	}

	@POST
	@Path("/editBooks")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editBooks(Book book) {
		System.out.println("in book resource");
		BookDelegate bookDelegate = new BookDelegate();

		BookRentalResponse<Book> bookRentalResponse = bookDelegate
				.editBooks(book);
		return Response.status(200).entity(bookRentalResponse.getResponseVo())
				.build();
	}

	@GET
	@Path("/getBooks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks(@Context HttpServletRequest request) {
		System.out.println("in book resource");
		// Long userId = getUserFromSession();
		HttpSession session = request.getSession(false);
		int id = (Integer) session.getAttribute("id");
		BookDelegate bookDelegate = new BookDelegate();

		BookRentalResponse<List<Book>> bookRentalResponse = bookDelegate
				.getBooks(id);
		return Response.status(200).entity(bookRentalResponse.getResponseVo())
				.build();
	}

	// @GET
	// @Path("/searchBook")
	// @Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Response searchBook() {
	// System.out.println("in book resource");
	// Long userId = getUserFromSession();
	// BookDelegate bookDelegate = new BookDelegate();
	//
	// BookRentalResponse<List<Book>> bookRentalResponse = bookDelegate
	// .getBooks(userId);
	// return Response.status(200)
	// .entity(bookRentalResponse.getResponseVo()).build();
	// }
	//

	@POST
	@Path("/search-books")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Book> searchBooks(@QueryParam("searchkey") String searchKey) {
		System.out.println("searchKey:::::::::::" + searchKey);
		BookDelegate bookDelegate = new BookDelegate();

		return bookDelegate.searchBooks(searchKey);
	}

	@GET
	@Path("/{Book_id}/getBookDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getBookDetails(@PathParam("Book_id") Long bookid) {
		System.out.println("in book resource");
		BookDelegate bookDelegate = new BookDelegate();

		BookRentalResponse<List<Book>> bookRentalResponse = bookDelegate
				.getBookDetails(bookid);
		return Response.status(bookRentalResponse.getStatusCode())
				.entity(bookRentalResponse.getResponseVo()).build();
	}

	@GET
	@Path("/checkForSession")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean checkForSession(@Context HttpServletRequest request) {
		boolean result = false;
		try {
			HttpSession session = request.getSession(false);
			SessionUtility sessionUtility = new SessionUtility();
			result = sessionUtility.checkForSession(session);
		} catch (Exception e) {

		}

		return result;
	}
}
