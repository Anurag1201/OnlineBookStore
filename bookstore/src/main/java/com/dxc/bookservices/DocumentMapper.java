package com.dxc.bookservices;

import org.bson.Document;

import com.dxc.bean.Books_Details;
import com.dxc.user.User_Detail;

public class DocumentMapper {

	public static Books_Details getBook(Document d) {
		Books_Details emp = new Books_Details();
		emp.setBook_name(d.getString("book_name"));
		emp.setAuthor(d.getString("author"));
		emp.setTitle(d.getString("title"));
	    emp.setCategory(d.getString("category"));
		emp.setPublished_year(d.getString("published_year"));
		emp.setPrinted_price(d.getDouble("printed_price"));
		emp.setDiscount_price(d.getDouble("discount_price"));
		
		return emp;

	}

	public static Document getDocument(Books_Details emp) {
		Document doc = new Document()
				.append("book_name", emp.getBook_name())
				.append("author", emp.getAuthor())
				.append("title", emp.getTitle())
				.append("category", emp.getCategory())
			     .append("published_year", emp.getPublished_year())
				.append("printed_price", emp.getPrinted_price())
				.append("discount_price", emp.getDiscount_price());
		
		return doc;

	}
	
	public static User_Detail getuser(Document d) {
		User_Detail usr = new User_Detail();
		usr.setFname(d.getString("fname"));
		usr.setLname(d.getString("lname"));
		usr.setAddress(d.getString("address"));
		usr.setMobile_no(d.getString("mobile_no"));
		usr.setEmail(d.getString("email"));
		usr.setPassword(d.getString("password"));
		return usr;

	}
	
	public static  Document getUserDoc(User_Detail user) {
		
		Document doc=new Document().append("fname",user.getFname())
				.append("lname", user.getLname())
				.append("address", user.getAddress())
				.append("email", user.getEmail())
				.append("password",user.getPassword())
				.append("mobile_no",user.getMobile_no());
				
		return doc;
		
	}
}
