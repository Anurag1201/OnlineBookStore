package com.dxc.bookservices;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.dxc.bean.Books_Details;
import com.dxc.dbConnection.DbConnection;
import com.dxc.user.User_Detail;
import com.mongodb.DBCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

public class Book_Services {

	// to connect db
	static {

		DbConnection.connect();
	}

	// for add new book
	public static void add(Books_Details book) {
		Document bookDoc = DocumentMapper.getDocument(book);
		DbConnection.addBook(bookDoc);
	}

	// for retreive books
	public static ArrayList getAllBook() {

		ArrayList al = DbConnection.retreiveAllBook();
		return al;
	}

	// delete book by name
	public static boolean delete(String book_name) {

		Document d = new Document();
		d.append("book_name", book_name);
 
		DbConnection.collection.deleteOne(d);
		// problem always true
		return true;
	}

	// update book

	public static void update(String book_name, Books_Details newemp) {
		Document d = new Document();
		d.append("book_name", book_name);

		DbConnection.collection.find();
	}

	// for add new User
	public static void addUser(User_Detail user) {

		System.out.println("objectss " + user);
		Document userDoc = DocumentMapper.getUserDoc(user);
		System.out.println("doc "+userDoc);
		DbConnection.addUser(userDoc);
	}

	// for retreive user
	public static ArrayList getAllUser() {

		ArrayList al = DbConnection.retreiveAllUser();
		return al;
	}

	// search using category
	public static ArrayList getBookByCategory(String category) {

		ArrayList a = DbConnection.searchByCategory(category);

		return a;
	}
	
	//search using title
	public static ArrayList searchBookByTitle(String title) {
		ArrayList a =DbConnection.searchByTitle(title);
		return a;
	}
	
	//search using title
		public static ArrayList searchBookByPrice(Double price) {
			ArrayList a =DbConnection.searchByprice(price);
			return a;
		}
		//get user profile
		public static User_Detail getProfile(Document doc) {
			
			return DbConnection.getProfile(doc);
		}
		
		//get book profile
		public static Books_Details getBookProfile(Document doc) {
			return DbConnection.viewbook(doc);
		}
		
		
		//add to cart
		public static void addToCart(Document d)
		{
			Document doc=new Document("book_name",d.getString("book_name"));
			doc.append("author", d.getString("author"));
			String mobile_no=d.getString("mobile_no");
			System.out.println(mobile_no);
			Document addtoCart=DocumentMapper.getDocument(Book_Services.getBookProfile(doc));
			addtoCart.append("mobile_no",mobile_no);
			System.out.println(addtoCart);
			DbConnection.addToCart(addtoCart);
			
			
		}
		
		//view cart
		public static ArrayList viewCart(Document d) {
			
			return DbConnection.viewCartDetail(d);
			
			
		}
}
