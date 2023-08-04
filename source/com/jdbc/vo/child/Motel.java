package com.jdbc.vo.child;

import com.jdbc.vo.Accommodation;

public class Motel extends Accommodation{
	private boolean pc;

	public Motel(int id, String accomName, String location, String accomType, int price, int people, boolean pc) {
		super(id, accomName, location, accomType, price, people);
		this.pc = pc;
	}

	public boolean isPc() {
		return pc;
	}

	public void changePc(boolean pc) {
		this.pc = pc;
	}

	@Override
	public String toString() {
		return super.toString() + "\n모텔 " +"PC: " + pc;
	}
	
	
	
}
