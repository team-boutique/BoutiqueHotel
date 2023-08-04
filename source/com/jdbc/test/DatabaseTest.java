package com.jdbc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.jdbc.dao.Database;
import com.jdbc.vo.Accommodation;
import com.jdbc.vo.Book;
import com.jdbc.vo.Customer;
import com.jdbc.vo.Date;
import com.jdbc.vo.child.Hotel;
import com.jdbc.vo.child.Motel;
import com.jdbc.vo.child.Pension;

import config.ServerInfo;

public class DatabaseTest {
    public static void main(String args[]){

        Database db = Database.getInstance();
        System.out.println("                                                                  \r\n"

            + " _____                     _____            _    _                \r\n"
            + "|_   _| ___  ___  _____   | __  | ___  _ _ | |_ |_| ___  _ _  ___ \r\n"
            + "  | |  | -_|| .'||     |  | __ -|| . || | ||  _|| || . || | || -_|\r\n"
            + "  |_|  |___||__,||_|_|_|  |_____||___||___||_|  |_||_  ||___||___|\r\n"
            + "                                                     |_|          ");
    System.out.println();
    System.out.println("//////////////////////////////////////////////////////////////////");
    
    
    // 시나리오01 :: 미성년자가 회원가입을 시도하는 경우
    try {
        System.out.println("시나리오01 :: 미성년자가 회원가입을 시도하는 경우");
        db.deleteCustomer("98-04");
        db.deleteCustomer("97-07");

        db.addCustomer(new Customer("07-07", "응애", "newbie@gmail.com", "010-0000-0000",new Date(2007, 10, 10), 0));
 
    } catch(Exception e) {
        System.out.println(e.getMessage());
    }
    
    // 시나리오02 :: 중복된 사용자일 경우
    try {
        System.out.println("시나리오02 :: 중복된 사용자일 경우");
        db.addCustomer(new Customer("97-07", "이준모", "ezurno@gmail.com", "010-0000-0000",new Date(1997, 07, 22), 0));

    } catch(Exception e) {
        System.out.println(e.getMessage());
    }
    
    // 시나리오03 :: 회원가입 성공, 회원 이메일 정보 수정
    try {
        System.out.println("시나리오03 :: 회원가입 성공, 회원 이메일 정보 수정");
        db.addCustomer(new Customer("98-04", "조용훈", "qqqq@gmail.com", "010-0000-0000",new Date(1998, 10, 10), 0));
        System.out.println("=== 이메일 변경 ===");
        db.updateCustomer(new Customer("98-04", "조용훈", "quiethoon@gmail.com", "010-0000-0000",new Date(1998, 10, 10), 0));
        
        System.out.println("=== 회원 조회 ===");
        for(Customer customer : db.getAllCustomer()) {
            System.out.println(customer);
            
        }
    } catch(Exception e) {
        System.out.println(e.getMessage());
    }
    
    try {
        System.out.println("시나리오04 :: 가격 범위 내 숙소 조회, 가장 싼 숙소 예약하기");
        System.out.println("=== 숙소 조회 ===");
        
        for(Accommodation accom : db.findAccomsByPrice(25000, 200000)) {
            System.out.println(accom);
        }
        
    } catch(Exception e) {
        System.out.println(e.getMessage());
    }
    
    try {
    	System.out.println("시나리오 5 :: 회원의 단순 변심으로 취소");
    	for(Book b : db.getBookList("98-04")) {
    		System.out.println(b);
    	}
//    	db.deleteBooking("98-04", 0);
    } catch(Exception e) {
    	System.out.println(e.getMessage());
    }
    
    try {
    	System.out.println("시나리오 6 :: 성급별 조회");
    	System.out.println("=== 5성급 조회 ===");
    	for(Accommodation acc : db.findAccomsByStar(5)) {
    		System.out.println(acc);
    	}
    } catch(Exception e) {
    	System.out.println(e.getMessage());
    }
    
    try {
    	System.out.println("시나리오 7 :: 호텔 이름 검색");
    	for(Accommodation acc : db.findAccomsByAccomName("부띠크호텔")) {
    		System.out.println(acc);
    	}
    } catch(Exception e) {
    	System.out.println(e.getMessage());
    }
    
    try {
        System.out.println("시나리오 8 :: 가입된 회원목록 조회");
        db.getAllCustomer();
    }catch(Exception e){
        System.out.println(e.getMessage());
    }

    try {
        System.out.println("시나리오 9 :: 신규숙소 등록하기");
        db.addAccom(new Pension(10, "왕궁펜션", "서산", "Pension", 4500000, 4, true));
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
    
    // 시나리오10 :: 특정 회원 검색 (회원의 예약 내역, 회원 정보 제공) 및 고객 예약 취소시키기
    try {
        System.out.println("시나리오10 :: 특정 회원 검색 (회원의 예약 내역, 회원 정보 제공) 및 고객 예약 취소시키기");
        Customer cust = db.getCustomerBySsn("123123");
        System.out.println(cust.getCustName()+"님의 정보 검색 : "+cust.getEmail()+" "+cust.getTel()+" "+cust.getBirthdate()+" "+cust.getPoint());
        for(Book book : cust.getBookingList()) {
            System.out.println("- "+book.getBookDate()+"에 "+db.getAccom(book.getId()).getAccomName()+" "+book.getPeople()+"인 예약");
        }
    }catch(Exception e) {
        System.out.println(e.getMessage());
    }

	try {
	    Customer cust = db.getCustomerBySsn("123123");
	    System.out.println(cust.getCustName()+"님의 정보 검색 : "+cust.getEmail()+" "+cust.getTel()+" "+cust.getBirthdate()+" "+cust.getPoint());
	    for(Book book : cust.getBookingList()) {
	    	System.out.println(book);
	    }
	    System.out.println("해당 고객의 모든 예약을 삭제합니다.");
	    for(Book book : cust.getBookingList()) {
	        db.deleteBooking(book.getSsn(), book.getId());
	    }
	}catch(Exception e) {
	    System.out.println(e.getMessage());	
	}
	// 시나리오11 :: 호텔 삭제
    try {
        System.out.println("시나리오11 :: 호텔 삭제");
        db.printAllAccom();
        db.deleteAccom(10);
        db.printAllAccom();
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
    //시나리오12 :: 게임
    try {
		db.playGame("123123");
	} catch (SQLException e) {
        System.out.println(e.getMessage());
	}
    
    //시나리오13 :: 가격 순위 / 분석함수 (RANK)
    try {
    	db.printPriceRank();
    }catch (SQLException e) {
        System.out.println(e.getMessage());
	}
    
    //시나리오14 :: 생일 포인트(CASE)
    try {
    	for(Customer c : db.getAllCustomer()) System.out.println(c);
    	db.birthPoint();
    	for(Customer c : db.getAllCustomer()) System.out.println(c);
    }catch (SQLException e) {
        System.out.println(e.getMessage());
	}
    
    //시나리오15 :: 유형 별 가격 평균(GROUP)
    try {
    	db.avgByType();
    }catch (SQLException e) {
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
		