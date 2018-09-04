package com.dxc.dbConnection;

import java.util.ArrayList;
import com.dxc.bookservices.DocumentMapper;
import com.dxc.user.User_Detail;

import java.util.Iterator;

import javax.print.attribute.standard.RequestingUserName;

import org.bson.Document;

import com.dxc.bean.Books_Details;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DbConnection {

	private static final String DBNAME = "bookdetails";
	private static final String BOOK_COLLECTION = "details";
	public static MongoCollection<Document> collection;
	public static MongoCollection<Document> adminCollection;
	public static MongoCollection<Document> userCollection;
	public static MongoCollection<Document> usercart;
	public static MongoDatabase database;

	public static void connect() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		database = mongo.getDatabase(DBNAME);
		collection = database.getCollection(BOOK_COLLECTION);
		adminCollection=database.getCollection("admin");
		userCollection =database.getCollection("enduser");
		usercart=database.getCollection("usercart");
	}
	


	// add book
	public static void addBook(Document book) {
		collection.insertOne(book);
	}

	// retrive all the book
	public static ArrayList retreiveAllBook() {
		ArrayList al = new ArrayList();
		FindIterable<Document> fit = collection.find();
		Iterator<Document> it = fit.iterator();
		while (it.hasNext()) {
			Document bookDocument = (Document) it.next();
			Books_Details bookObject = DocumentMapper.getBook(bookDocument);
			al.add(bookObject);
		}
		return al;
	}
	
	
	//add enduser
	public static void addUser(Document user) {
		userCollection.insertOne(user);
	}
	
	// retrive all the user
	public static ArrayList retreiveAllUser() {
		ArrayList al = new ArrayList();
		FindIterable<Document> fit = userCollection.find();
		Iterator<Document> it = fit.iterator();
		while (it.hasNext()) {
			Document userDocument = (Document) it.next();
			User_Detail userObject = DocumentMapper.getuser(userDocument);
			al.add(userObject);
		}
		return al;
	}
	
	//search by category
	public static ArrayList searchByCategory(String category) {
		
		ArrayList al=new ArrayList();
		FindIterable<Document> find=collection.find().filter(Filters.eq("category",category));
		Iterator it= find.iterator();
		while(it.hasNext()) {
			al.add(it.next());
			//System.out.println(al);
		}
		return al;
	}
	
	//search by title
	
	public static ArrayList searchByTitle(String title) {
		ArrayList a=new ArrayList();
		FindIterable<Document> obj=collection.find().filter(Filters.eq("title",title));
		Iterator it =obj.iterator();
		while(it.hasNext()) {
			a.add(it.next());
		}
		return a;
	}
	// search by price
	public static ArrayList searchByprice(Double price) {
		ArrayList a=new ArrayList();
		FindIterable<Document> obj=collection.find().filter(Filters.lte("printed_price",price));
		Iterator it =obj.iterator();
		while(it.hasNext()) {
			a.add(it.next());
		}
		return a;
	}
	
	//admin login
	
	public static boolean adminLogin(String uname,String passwd)
	{
		FindIterable<Document> fitr=adminCollection.find();
		Boolean flag=null;
		 Iterator it=fitr.iterator();
		  while(it.hasNext())
		  {
			  Document d=(Document)it.next();
			 String unm = d.getString("username");
			 String pass = d.getString("pwd");
			 System.out.println("hey");
			 if(unm.equals(uname) && pass.equals(passwd))
			 {
				 System.out.println(unm+""+passwd);
				 flag=true;
			 }
			 else
				 flag=false;
		  }
		  return flag;
	}
	
	//getuserprofile
	
	public static User_Detail getProfile(Document doc){
		String mobile=doc.getString("mobile");
		return viewProfile(mobile);
	} 
	//view profile(mobile)
	public static User_Detail viewProfile(String mobile)
	{
		User_Detail userProfile=new User_Detail();
		System.out.println(mobile);
		FindIterable<Document> fit=userCollection.find(Filters.eq("mobile", mobile));
		userProfile=DocumentMapper.getuser(fit.first());	
		return userProfile;
	}
	
	
	//view book by(bookname and author)
	public static Books_Details viewbook(Document doc) {
		String book_name=doc.getString("book_name");
		String author=doc.getString("author");
		return viewBook(book_name,author);
		
	}
	
	
	//view Book (bookname and author)
	
	public static Books_Details viewBook(String book_name,String author) {
		Books_Details viewBook=new Books_Details();
		System.out.println(book_name+" "+author);
		FindIterable<Document>f=collection.find(Filters.and(Filters.eq("book_name", book_name), Filters.eq("author",author)));
		viewBook=DocumentMapper.getBook(f.first());
		return viewBook;
	}


	//add to cart 
	public static void addToCart(Document addtoCart) {
		usercart.insertOne(addtoCart);
		
		
	}


	//view from cart
	public static ArrayList viewCartDetail(Document d) {
		// TODO Auto-generated method stub
		ArrayList al=new ArrayList<>();
		Books_Details viewcart=new Books_Details();
		String mobile_no=d.getString("mobile_no");
		FindIterable <Document>doc=usercart.find(Filters.eq("mobile_no",mobile_no));
		Iterator<Document> it=doc.iterator();
		while(it.hasNext()) {
			al.add(DocumentMapper.getBook(it.next()));
		}
		
		return al;
	}
}
