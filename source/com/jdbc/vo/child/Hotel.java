package com.jdbc.vo.child;

import com.jdbc.vo.Accommodation;

public class Hotel extends Accommodation{
	private int star;
	private boolean breakfast;
	
	public Hotel(int star, boolean breakfast) {
		super();
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
		return super.toString() + "Hotel [star=" + star + ", breakfast=" + breakfast + "]";
	}
	
	

}
