package com.jdbc.vo.child;

import com.jdbc.vo.Accommodation;

public class Resorts extends Accommodation{
	private boolean pool;

	public Resorts(int id, String accomName, String location, String accomType, int price, int people, boolean pool) {
        super(id, accomName, location, accomType, price, people);
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
		return super.toString() + "\n리조트 " + "\t수영장: " + pool;
	}
	
	
	
}
