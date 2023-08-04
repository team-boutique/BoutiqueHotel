package com.jdbc.vo.child;

import com.jdbc.vo.Accommodation;

public class Resorts extends Accommodation{
	private boolean pool;

	public Resorts(boolean pool) {
		super();
		this.pool = pool;
	}

	public boolean isPool() {
		return pool;
	}

	public void changePool(boolean pool) {
		this.pool = pool;
	}

	@Override
	public String toString() {
		return super.toString() + "Resorts [pool=" + pool + "]";
	}
	
	
	
}
