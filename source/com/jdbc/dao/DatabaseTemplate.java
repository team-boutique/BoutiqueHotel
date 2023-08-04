package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbc.exception.DuplicateSSNException;
import com.jdbc.exception.InvalidBookingException;
import com.jdbc.exception.RecordNotFoundException;
import com.jdbc.vo.Accommodation;
import com.jdbc.vo.Book;
import com.jdbc.vo.Customer;

public interface DatabaseTemplate {
    //공통 디비 관련 함수
    Connection getConnect() throws SQLException;
    void closeAll(Connection conn, PreparedStatement ps) throws SQLException;
    void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException;

    //ssn과 id로 존재 여부 확인하기
    boolean isExist(Connection conn, String ssn) throws SQLException;
    boolean isExist(Connection conn, int id) throws SQLException;
    boolean isExist(Connection conn, int id, String ssn) throws SQLException;

    //예약 관련
    boolean canBook(Connection conn, int id, String bookDate, int people) throws SQLException, RecordNotFoundException, InvalidBookingException;
    void booking(Book book) throws SQLException, RecordNotFoundException, InvalidBookingException; //void booking(String ssn, int id, String bookDate, int people);
    void updateBooking(Book book) throws SQLException, RecordNotFoundException; //void updateBooking(String ssn, int id, String bookDate);
    void deleteBooking(String ssn, int id) throws SQLException, RecordNotFoundException;

    //고객 관련
    //isExist(String ssn) 먼저
    void addCustomer(Customer customer) throws SQLException, DuplicateSSNException;
    void updateCustomer(Customer customer) throws SQLException, RecordNotFoundException;
    void deleteCustomer(String ssn) throws SQLException, RecordNotFoundException;

    //숙소 관련
    //isExist(int id) 먼저
    void addAccom(Accommodation accom) throws SQLException;
    void updateAccom(Accommodation accom) throws SQLException, RecordNotFoundException;
    void deleteAccom(int id) throws SQLException, RecordNotFoundException;

    //고객 조회 기능
    ArrayList<Book> getBookList(String ssn) throws SQLException;
    ArrayList<Customer> getCustomerByName(String name) throws SQLException;
    Customer getCustomerBySsn(String ssn) throws NumberFormatException, SQLException;
    ArrayList<Customer> getAllCustomer() throws NumberFormatException, SQLException;

    //호텔 조회 기능
    Accommodation getAccom(int id) throws SQLException;
    ArrayList<Accommodation> printAllAccom() throws SQLException;
    ArrayList<Accommodation> findAccomsBylocation(String location) throws SQLException;
    ArrayList<Accommodation> findAccomsByPrice(int s_price, int e_price) throws SQLException;
    ArrayList<Accommodation> findAccomsByStar(int star) throws SQLException;
    ArrayList<Accommodation> findAccomsByAccomName(String name) throws SQLException;
    ArrayList<Accommodation> findAccomsByType(String type) throws SQLException;

    //알고리즘 기능
    void playGame(String ssn) throws SQLException;
}
