package com.jdbc.vo;

import java.util.ArrayList;

public class Customer {
	private String ssn;
	private String custName;
	private String email;
	private String tel;  
	private Date birthdate;
	private int point;
	private ArrayList<Book> bookingList;
	
	public Customer() {}
	public Customer(String ssn, String custName, String email, String tel, Date birthdate, int point,
			ArrayList<Book> bookingList) {
		super();
		this.ssn = ssn;
		this.custName = custName;
		this.email = email;
		this.tel = tel;
		this.birthdate = birthdate;
		this.point = point;
		this.bookingList = bookingList;
	}
	public Customer(String ssn, String custName, String email, String tel, Date birthdate, int point) {
		super();
		this.ssn = ssn;
		this.custName = custName;
		this.email = email;
		this.tel = tel;
		this.birthdate = birthdate;
		this.point = point;
	}


	public String getSsn() {
		return ssn;
	}

	public void changeSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getCustName() {
		return custName;
	}

	public void changeCustName(String custName) {
		this.custName = custName;
	}

	public String getEmail() {
		return email;
	}

	public void changeEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void changeTel(String tel) {
		this.tel = tel;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void changeBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public ArrayList<Book> getBookingList() {
		return bookingList;
	}

	public void changeBookingList(ArrayList<Book> bookingList) {
		this.bookingList = bookingList;
	}

	public int getPoint() {
		return point;
	}
	public void changePoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "Customer [ssn=" + ssn + ", custName=" + custName + ", email=" + email + ", tel=" + tel + ", birthdate="
				+ birthdate + ", point=" + point + ", bookingList=" + bookingList + "]";
	}
}
