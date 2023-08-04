package com.jdbc.vo;

public class Accommodation { //column의 정보가 그대로 들어가 있음
	private int id;
	private String accomName;
	private String location;
	private String accomType;
	private int price;
	  
	public Accommodation(){}
	
	public Accommodation(int id, String accomName, String location, String accomType, int price) {
		super();
		this.id = id;
		this.accomName = accomName;
		this.location = location;
		this.accomType = accomType;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}

	public void changeId(int id) {
		this.id = id;
	}

	public String getAccomName() {
		return accomName;
	}

	public void changeAccomName(String accomName) {
		this.accomName = accomName;
	}

	public String getLocation() {
		return location;
	}

	public void changeLocation(String location) {
		this.location = location;
	}

	public String getAccomType() {
		return accomType;
	}

	public void changeAccomType(String accomType) {
		this.accomType = accomType;
	}

	public int getPrice() {
		return price;
	}

	public void changePrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Accommodation [id=" + id + ", accomName=" + accomName + ", location=" + location + ", accomType="
				+ accomType + ", price=" + price + "]";
	}

}

