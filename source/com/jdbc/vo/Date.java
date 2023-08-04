package com.jdbc.vo;

public class Date {
	private int year;
	private int month;
	private int day;
	
	public Date(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void changeYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void changeMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void changeDay(int day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "Date [year=" + year + ", month=" + month + ", day=" + day + "]";
	}
	
}
