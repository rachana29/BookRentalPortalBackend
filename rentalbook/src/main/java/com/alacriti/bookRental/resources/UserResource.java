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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.bookRental.Delegate.UserDelegate;
import com.alacriti.bookRental.model.vo.BookRentalResponse;
import com.alacriti.bookRental.model.vo.User;

@Path("/user")
public class UserResource {
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("param") Long userId) {
		UserDelegate delegate = new UserDelegate();
		List<User> result = delegate.getMessage(userId);
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUser(User userAddVO,@Context HttpServletRequest request) {
		System.out.print("hii+++++++++++++++++++++++++++");
		UserDelegate userDelegate = new UserDelegate();
		System.out.println(userDelegate.loginUser(userAddVO));

		User user = userDelegate.loginUser(userAddVO);

		if (user != null) {
			System.out.println("user_id: " + user.getUser_id());
			System.out.println("User_name: " + user.getUser_name());
			System.out.println("Password: " + user.getPassword());
			System.out.println("email_id: " + user.getEmail_id());
			System.out.println("mobile_number: " + user.getMobile_number());
			HttpSession session=request.getSession();
			session.setAttribute("id", user.getUser_id());
			return Response.status(200).entity(user).build();
		} else {
			return Response.status(200).entity(false).build();
		}

	}

	@POST
	@Path("/registration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(User userAddVO) {
		UserDelegate userDelegate = new UserDelegate();
		BookRentalResponse<User> bookRentalResponse = userDelegate
				.registerUser(userAddVO);
		return Response.status(bookRentalResponse.getStatusCode())
				.entity(bookRentalResponse.getResponseVo()).build();
	}
	
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean destroySession(@Context HttpServletRequest request){
		HttpSession session=null;
		boolean result=false;
		try{
			session=request.getSession(false);
			if(session!=null){
				session.invalidate();
			}
			result=true;
		}catch(Exception e){
			result=false;
			System.out.println("Exception occured in destroying session object");
		}
		return result;
		
	}
}