package com.dxc.dbConnection;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import com.dxc.dbConnection.*;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;

public class Admin {
	private static final String DBNAME = "bookdetails";
	private String user;
	private String pwd;

	// root admin user name and pwd
	

	public static boolean adminlogin(String username, String password)
	{
		return DbConnection.adminLogin(username, password);
	}
	

}
