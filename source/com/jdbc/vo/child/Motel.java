package com.jdbc.vo.child;

import com.jdbc.vo.Accommodation;

public class Motel extends Accommodation{
	private boolean pc;

	public Motel(boolean pc) {
		super();
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
		return super.toString() + "Motel [pc=" + pc + "]";
	}
	
	
	
}
