package com.jdbc.test;

import java.util.ArrayList;

import com.jdbc.dao.Database;
import com.jdbc.vo.Accommodation;
import com.jdbc.vo.Book;
import com.jdbc.vo.Customer;
import com.jdbc.vo.Date;
import com.jdbc.vo.child.Hotel;
import com.jdbc.vo.child.Motel;

import config.ServerInfo;

public class DatabaseTest {
    public static void main(String args[]){
        Database db = Database.getInstance();
        
//        try {
//        	db.addCustomer(new Customer("000921-1231231", "이해연", "gaamjaa@xxxx.com", "010-1234-1234", new Date(2000,9,21), 0));
//        }catch(Exception e) {
//        	System.out.println(e.getMessage());
//        }
        /*
        try {
        	db.updateCustomer(new Customer("123-123", "이해연", "gaamjaa@xxxx.com", "010-1234-1235", new Date(2000,9,21), 0));
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        try {
        	db.deleteCustomer("123-123");
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        */

//        try {
//        	System.out.println("모텔...생...성");
//        	db.addAccom(new Motel(0, "부띠크호텔", "서울", "Motel", 200000, 3, true)); //int id, String accomName, String location, String accomType, int price, int people, int star, boolean breakfast
//        }catch(Exception e) {
//        	System.out.println(e.getMessage());
//        }
//        
        
        
        
//        try {
//        	ArrayList<Customer> custs = db.getAllCustomer();
//        	for(Customer c:custs) System.out.println(c.toString());
//        }catch(Exception e) {
//        	System.out.println("?"); 
//        	System.out.println(e.getMessage());
//        }
//        try {
//        	db.deleteAccom(3);
//        }catch(Exception e) {
//        	System.out.println(e.getMessage());
//        }
        // 지역으로 숙박 시설 조회
//        try {
//        	System.out.println(db.findAccomsBylocation("서울")); 
//        }catch(Exception e) {
//        	System.out.println(e.getMessage());
//        }
        
//        try {
//        	db.updateAccom(new Motel(5, "부띠크호텔", "서울", "Motel", 210000, 3, false));
//        }catch(Exception e) {
//        	System.out.println(e.getMessage());
//        }
        
//        try {
//        	db.getBookList(null);
//        }catch(Exception e) {
//        	System.out.println(e.getMessage());
//        }
        
//        try {
//        	for(Accommodation a : db.printAllAccom()) {
//        		System.out.println(a);
//        	}
//        } catch(Exception e) {
//        	System.out.println(e.getMessage());
//        }
         //예약하기
        try {
        	db.booking(new Book("123123", 4, "20230523", 2));
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        // 특정 회원예약 목록 조회
//      try {
//    	System.out.println(db.getBookList("123123"));
//    } catch (Exception e) {
//    	System.out.println(e.getMessage());
//    }
        
        
      //ssn으로 고객 정보 가져오기
//      try {
//    	System.out.println(db.getCustomerBySsn("123123"));
//    } catch (Exception e) {
//    	System.out.println(e.getMessage());
//    }
//        
      try {
    	 db.updateBooking(new Book("123123", 4, "20230524", 2));
    	System.out.println();
    } catch (Exception e) {
    	System.out.println(e.getMessage());
    }
        
        
        
        
        
    }
    static {
        try {
            Class.forName(ServerInfo.DRIVER_NAME);
            System.out.println("Driver Loading Success...");
        } catch(ClassNotFoundException e){
            System.out.println("Driver Loading Fail...");
        }    }
}
