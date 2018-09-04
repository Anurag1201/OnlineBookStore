package com.dxc.bookstore;

import javax.json.Json;
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



/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */

	@Path("getall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {

		return Response.ok(Book_Services.getAllBook())
				// .header("Access-Control-Allow-Origin", "*")
				// whatever other CORS headers
				.build();

	}
	
	@POST
    @Path("adminlogin/{uname}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adminLogin(@PathParam("uname") String uname, @PathParam("password") String password)
    {
		System.out.println("hey");
      if( Admin.adminlogin(uname,password))
    	return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}").build();
     
      else 
    	  return Response.ok("{\"status\":\"1\",\"message\":\"User not found\"}").build();  
    	

    }
	

	@POST
	@Path("addbooks")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Books_Details input) {
		// Response.ok().build();
		System.out.println("input student" + input.toString());
		Book_Services.add(input);
		return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}")
				// .header("Access-Control-Allow-Origin", "*")
				.build();

	}

	@DELETE
	@Path("delete/{book_name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("book_name") String book_name) {
		if (Book_Services.delete(book_name)) {
			return Response.ok( "{\"status\":\"0\",\"message\":\"successfuly deleted\"}").build();
		} else {
			return Response.ok("{\"status\":\"1\",\"message\":\"Not found\"}").build();
		}
	}

//    @POST
//    @Path("adminlogin")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public  Response verify(String username,)
//    {
//    //	Response.ok().build();
//    	if(Admin.verify(username, pwd))
//    	{
//    		return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}")
//    			//.header("Access-Control-Allow-Origin", "*")
//    			.build();
//    	}
//    		else
//           {
//           return Response.ok("{\"status\":\"1\",\"message\":\"Not found\"}").build();        	
//           }
//    }
//    

	@POST
	@Path("adduser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User_Detail input) {
		// Response.ok().build();
		// System.out.println("input student"+input.toString());
		Book_Services.addUser(input);
		System.out.println("hiiii"+input);
		return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}")
				// .header("Access-Control-Allow-Origin", "*")
				.build();

	}

	@Path("getalluser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUser() {

		return Response.ok(Book_Services.getAllUser())
				// .header("Access-Control-Allow-Origin", "*")
				// whatever other CORS headers
				.build();

	}

//	@GET
//	@Path("getbookbycategory/{category}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Book_Services getBYCategory(@PathParam("Category") String category) {
//
//		Book_Services b = Book_Services.getBookByCategory(category);
//		return b;S
//	}
	
	@Path("getbookbycategory/{category}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookByCategory(@PathParam("category") String category) {

		return Response.ok(Book_Services.getBookByCategory(category))
				// .header("Access-Control-Allow-Origin", "*")
				// whatever other CORS headers
				.build();

	}

	@Path("getbookbytitle/{title}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookByTitle(@PathParam("title") String title ) {
		return Response.ok(Book_Services.searchBookByTitle(title)).build();
				
	}
	
	@Path("getbookbyprice/{price}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookByPrice(@PathParam("price") Double price ) {
		return Response.ok(Book_Services.searchBookByPrice(price)).build();
				
	}
	

		
//		@POST
//		@Path("addBookToCart")
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.APPLICATION_JSON)
//		public static void activate(Document doc) //mobile no
//		{
//			UserCartService.addtoCart(doc);
//		}
		
	//get user profile by mobile
	@Path("getprofile")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User_Detail getProfile(Document doc) //mobile
	{
		return Book_Services.getProfile(doc);
	}
	
	
	//get book profile by book_name and author
	@Path("getbookprofile")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Books_Details getBookProfile(Document doc) {
		return Book_Services.getBookProfile(doc);
	}
}
