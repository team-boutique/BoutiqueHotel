package com.jdbc.vo.child;

import com.jdbc.vo.Accommodation;

public class Hotel extends Accommodation{
	private int star;
	private boolean breakfast;
	
	public Hotel(int id, String accomName, String location, String accomType, int price, int people, int star, boolean breakfast) {
		super(id, accomName, location, accomType, price, people);
		this.star = star;
		this.breakfast = breakfast;
	}

	public int getStar() {
		return star;
	}

	public void changeStar(int star) {
		this.star = star;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public void changeBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	@Override
	public String toString() {
		return super.toString() + "\n호텔 " + star + "성\t조식: " + breakfast;
	}
	
	

}
