package com.dxc.user;

public class User_Detail {
	
	private String fname;
	private String lname;
	private String email;
	private String address;
	private String mobile_no;
	private String password;
	
	
	public User_Detail() {
		
	}
	public User_Detail(String fname, String lname, String email, String address, String mobile_no, String password) {
		
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.address = address;
		this.mobile_no = mobile_no;
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
