package com.jdbc.dao;

import config.ServerInfo;

import java.sql.*;
import java.util.ArrayList;

import com.jdbc.exception.InvalidBookingException;
import com.jdbc.exception.RecordNotFoundException;
import com.jdbc.vo.Accommodation;
import com.jdbc.vo.Book;
import com.jdbc.vo.Customer;

public class Database implements DatabaseTemplate{
    private static Database db = new Database();
    private Database(){
        System.out.println("Using Singletone..");
    }
    public static Database getInstance(){
        return db;
    }
    @Override
    public Connection getConnect() throws SQLException {
        Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
        System.out.println("DB Connect...");

        return conn;
    }

    @Override
    public void closeAll(Connection conn, PreparedStatement ps) throws SQLException {
        if(ps!=null) ps.close(); //null이 아니다 == 생성이 되어서 닫아줘야 한다.
        if(conn!=null) conn.close();
    }

    @Override
    public void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        if(rs!=null) rs.close();
        closeAll(conn, ps);
    }

    @Override
    public boolean isExist(Connection conn, String ssn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT ssn FROM customer WHERE ssn=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, ssn);
        rs = ps.executeQuery();

        return rs.next();
    }

    @Override
    public boolean isExist(Connection conn, int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT ssn FROM accommodation WHERE id=?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }

    @Override
    public boolean canBook(Connection conn, int id, String bookDate, int people) throws SQLException, RecordNotFoundException, InvalidBookingException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(!isExist(conn, id)) {
        	throw new RecordNotFoundException();
        }
        
        String query = "SELECT book_date FROM book WHERE id=? AND book_date=?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, bookDate);
        rs = ps.executeQuery();
        if (rs.next()){ //true == 해당 날짜의 예약이 있다
            throw new InvalidBookingException("해당 날짜의 예약은 마감되었습니다.");
        }

        query = "SELECT people FROM accommodatioin WHERE id=?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()){
            if(rs.getInt("people") < people) throw new InvalidBookingException("수용 인원을 초과하였습니다.");
        }

        return true;
    }

    @Override
    public void booking(Book book) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = getConnect();
            //
            // 구현부
            //
        }finally {
            closeAll(conn, ps);
        }
    }

    @Override
    public void updateBooking(Book book) {

    }

    @Override
    public void deleteBooking(String ssn, int id) {

    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(String ssn) {

    }

    @Override
    public void addAccom(Accommodation accom) {

    }

    @Override
    public void updateAccom(Accommodation accom) {

    }

    @Override
    public void deleteAccom(int id) {

    }

    @Override
    public ArrayList<Book> getBook(String ssn) {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomerByName(String name) {
        return null;
    }

    @Override
    public Customer getCustomerBySsn(String ssn) {
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() {
        return null;
    }

    @Override
    public Accommodation getAccom(int id) {
        return null;
    }

    @Override
    public ArrayList<Accommodation> printAllAccom() {
        return null;
    }

    @Override
    public ArrayList<Accommodation> findAccomsBylocation(String location) {
        return null;
    }

    @Override
    public ArrayList<Accommodation> findAccomsByPrice(int s_price, int e_price) {
        return null;
    }

    @Override
    public ArrayList<Accommodation> findAccomsByStar(int star) {
        return null;
    }

    @Override
    public ArrayList<Accommodation> findAccomsByAccomName(String name) {
        return null;
    }

    @Override
    public ArrayList<Accommodation> findAccomsByType(String type) {
        return null;
    }

    @Override
    public void playGame(String ssn) throws SQLException {
        Customer cust = getCustomerBySsn(ssn);
        int point = cust.getPoint();
        int n = (int)(Math.random() * 1000) + 100;
        boolean[] arr = new boolean[n+1];
        for(int i=0; i<n; i++) arr[i] = true;

        for(int i=2; i<(int)(Math.sqrt(n))+1; i++){
            if(arr[i]) {
                int j = 2;
                while (i*j <= n){
                    arr[i*j] = false;
                    j++;
                }
            }
        }

        System.out.println("오늘의 랜덤생성숫자: "+n+"는 소수인가요? ");

        if(arr[n]){
            Connection conn = null;
            PreparedStatement ps = null;
            try{
                conn = getConnect();

                String query = "UPDATE customer SET point=? WHERE ssn=?";
                point += 10;
                ps.setInt(1, point);
                ps.setString(2, ssn);
                if(ps.executeUpdate() != 0){
                    System.out.println("소수입니다! 게임을 이겼습니다. 10포인트를 획득했습니다.");
                }else{
                    System.out.println("DB 저장 과정에서 오류가 발생했습니다...");
                }
            }finally {
                closeAll(conn, ps);
            }

        }else{
            System.out.println("소수가 아닙니다! 게임에서 패배했습니다.");
        }
    }
}
