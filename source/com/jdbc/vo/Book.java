package com.jdbc.vo;

/*
 * Shares 테이블의 정보를 담는 클래스...
 * 누가...ssn
 * 어떤주식을...symbol
 * 얼만큼 ...quantity
 * 보유하는 지에 대한 정보를 담고 있다.
 */
public class Book {
	
	private String ssn;
	private int id;
	private String bookDate;
	private int people;
	
	public Book(String ssn, int id, String bookDate, int people) {
		super();
		this.ssn = ssn;
		this.id = id;
		this.bookDate = bookDate;
		this.people = people;
	}

	public String getSsn() {
		return ssn;
	}

	public void changeSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getId() {
		return id;
	}

	public void changeId(int id) {
		this.id = id;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void changeBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public int getPeople() {
		return people;
	}

	public void changePeople(int people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "Book [ssn=" + ssn + ", id=" + id + ", bookDate=" + bookDate + ", people=" + people + "]";
	}
	
}






