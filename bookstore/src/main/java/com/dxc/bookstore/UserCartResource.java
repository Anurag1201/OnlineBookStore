package com.dxc.bookstore;

import javax.ws.rs.*;

import java.util.ArrayList;

import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.dxc.bean.Books_Details;
import com.dxc.bookservices.Book_Services;

import com.dxc.dbConnection.*;
import com.dxc.user.User_Detail;

@Path("usercart")
public class UserCartResource {
	//add book to cart
	@POST
	@Path("addtocart")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static void addBookToCart(Document doc) // book&author&mobile
	{
		Book_Services.addToCart(doc);
	}
	//view book from cart
	@POST
	@Path("viewcart")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList viewcart(Document doc) {
		return Book_Services.viewCart(doc);
		
	}

}
