package com.jdbc.vo.child;

import com.jdbc.vo.Accommodation;

public class Pension extends Accommodation{
	private boolean bbq;

	public Pension(int id, String accomName, String location, String accomType, int price, int people, boolean bbq) {
		super(id,accomName, location, accomType, price, people);
		this.bbq = bbq;
	}

	public boolean isBbq() {
		return bbq;
	}

	public void changeBbq(boolean bbq) {
		this.bbq = bbq;
	}

	@Override
	public String toString() {
		return super.toString() + "\n팬션 " + "\tBBQ장: " + bbq;
	}
	
	
	
}
