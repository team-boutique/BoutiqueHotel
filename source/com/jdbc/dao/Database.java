package com.jdbc.dao;

import config.ServerInfo;

import java.sql.*;
import java.util.ArrayList;

import com.jdbc.exception.DuplicateSSNException;
import com.jdbc.exception.InvalidBookingException;
import com.jdbc.exception.RecordNotFoundException;
import com.jdbc.vo.Accommodation;
import com.jdbc.vo.Book;
import com.jdbc.vo.Customer;
import com.jdbc.vo.child.Hotel;
import com.jdbc.vo.child.Motel;
import com.jdbc.vo.child.Pension;
import com.jdbc.vo.child.Resorts;

import com.jdbc.vo.Date;

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

        String query = "SELECT id FROM accommodation WHERE id=?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        return rs.next();   
    }
    
    public boolean isExist(Connection conn, int id, String ssn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT ssn FROM book WHERE id=? AND ssn=?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, ssn);
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
        query = "SELECT people FROM accommodation WHERE id=?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()){
            if(rs.getInt("people") < people) throw new InvalidBookingException("수용 인원을 초과하였습니다.");
        }
        return true;
    }

    @Override
    public void booking(Book book) throws SQLException, RecordNotFoundException, InvalidBookingException {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = getConnect();
        try{
            if(canBook(conn, book.getId(), book.getBookDate(), book.getPeople())) {
            	System.out.println("???");
                String query = "INSERT INTO book (ssn, id, book_date, people) VALUES(?,?,?,?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, book.getSsn());
                ps.setInt(2, book.getId());
                ps.setString(3, book.getBookDate());
                ps.setInt(4, book.getPeople());

                System.out.println(ps.executeUpdate() + "개의 예약이 완료되었습니다.");
            }

        }finally {
            closeAll(conn, ps);
        }
    }

    @Override
    public void updateBooking(Book book) throws SQLException, RecordNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = getConnect();
            if(isExist(conn, book.getId(), book.getSsn())) {
            	System.out.println("?");
                String query = "UPDATE book SET book_date = ?, people = ? WHERE ssn = ? AND id = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, book.getBookDate());
                ps.setInt(2, book.getPeople());
                ps.setString(3, book.getSsn());
                ps.setInt(4, book.getId());
                ps.executeUpdate();
                
                System.out.println(ps.executeUpdate()+"개의 예약이 변경되었습니다.");
            }else {
                throw new RecordNotFoundException("해당하는 예약 정보가 없습니다.");
            }
        }finally {
            closeAll(conn, ps);
        }
    }

    @Override
    public void deleteBooking(String ssn, int id) {

    }

    @Override
    public void addCustomer(Customer customer) throws SQLException, DuplicateSSNException {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = getConnect();
            if(2023-customer.getBirthdate().getYear()<=19) {
            	System.out.println("회원가입은 성인만 가능합니다.");
            	return;
            }
            if(!isExist(conn, customer.getSsn())) {
            	String query = "INSERT INTO customer (ssn, cust_name, email, tel, birthdate, point) VALUES (?,?,?,?,?,?)";
            	ps = conn.prepareStatement(query);
            	ps.setString(1, customer.getSsn());
            	ps.setString(2, customer.getCustName());
            	ps.setString(3, customer.getEmail());
            	ps.setString(4, customer.getTel());
            	ps.setString(5, customer.getBirthdate().toString());
            	ps.setInt(6, customer.getPoint());
            	
            	System.out.println(ps.executeUpdate()+"명의 고객을 추가했습니다.");
 
            }else {
            	throw new DuplicateSSNException();
            }
        }finally {
            closeAll(conn, ps);
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException, RecordNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = getConnect();

	    try {
	        if(isExist(conn, customer.getSsn())) {
	            String query = "UPDATE customer SET cust_name = ? , email = ?, tel = ?, birthdate = ?, point = ? WHERE ssn = ?";
	            ps = conn.prepareStatement(query);
	            ps.setString(1, customer.getCustName());
	            ps.setString(2, customer.getEmail());
	            ps.setString(3, customer.getTel());
	            ps.setString(4, customer.getBirthdate().toString());
	            ps.setInt(5, customer.getPoint());
	            ps.setString(6, customer.getSsn());
	
	            ps.executeUpdate();
	            
	            System.out.println("회원 변경 성공");
	        } else {
	            // 찾는 값이 없을 때
	            throw new RecordNotFoundException("해당 회원이 존재하지 않습니다.");
	        }
	    }
	    finally {
	        closeAll(conn, ps);
	    }
    
    }

    @Override
    public void deleteCustomer(String ssn) throws SQLException, RecordNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = getConnect();

        try {
            if(isExist(conn, ssn)) {
                String query = "DELETE customer WHERE ssn = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, ssn);
        

                ps.executeUpdate();
                
                System.out.println("회원 삭제 성공");
            } else {
                // 찾는 값이 없을 때
                throw new RecordNotFoundException("해당 회원이 존재하지 않습니다.");
            }

        } finally {
            closeAll(conn, ps);

        }
    }
    
    @Override
    public void addAccom(Accommodation accom) throws SQLException {
        
        Connection conn = null;
        PreparedStatement ps = null;
        String breakfast = null;
        String pool = null;
        String bbq = null;
        String pc = null;
        
        try {
            conn = getConnect();
            if(accom.getAccomType().equals("Hotel")) {
                String query = "INSERT INTO accommodation(id, accom_name, location, accom_type, price, star, breakfast, people) VALUES(id_seq.nextVal, ?, ?, ?, ?, ?, ?, ?)";
                ps=  conn.prepareStatement(query);
                ps.setString(1, accom.getAccomName());
                ps.setString(2, accom.getLocation());
                ps.setString(3, accom.getAccomType());
                ps.setInt(4, accom.getPrice());
                ps.setInt(5, ((Hotel)accom).getStar());
                if(((Hotel)accom).isBreakfast()) breakfast = "Y";
                else breakfast = "N";
                ps.setString(6, breakfast);
                ps.setInt(7, accom.getPeople());
                
                System.out.println(ps.executeUpdate()+"개의 호텔 등록 성공...addAccom()..");
            }
            else if(accom.getAccomType().equals("Pension")) {
                String query = "INSERT INTO accommodation(id, accom_name, location, accom_type, price, bbq, people) VALUES(id_seq.nextVal, ?, ?, ?, ?, ?, ?)";
                ps=  conn.prepareStatement(query);
                ps.setString(1, accom.getAccomName());
                ps.setString(2, accom.getLocation());
                ps.setString(3, accom.getAccomType());
                ps.setInt(4, accom.getPrice());
                if(((Pension)accom).isBbq()) bbq = "Y";
                else bbq = "N";
                ps.setString(5, bbq);
                ps.setInt(6, accom.getPeople());
                System.out.println(ps.executeUpdate()+"개의 펜션 등록 성공...addAccom()..");
            }
            else if(accom.getAccomType().equals("Resorts")){
                String query = "INSERT INTO accommodation(id, accom_name, location, accom_type, price, pool, people) VALUES(id_seq.nextVal, ?, ?, ?, ?, ?, ?)";
                ps=  conn.prepareStatement(query);
                ps.setString(1, accom.getAccomName());
                ps.setString(2, accom.getLocation());
                ps.setString(3, accom.getAccomType());
                ps.setInt(4, accom.getPrice());
                if(((Resorts)accom).isPool()) pool = "Y";
                else pool = "N";
                ps.setString(5, pool);
                ps.setInt(6, accom.getPeople());
                System.out.println(ps.executeUpdate()+"개의 리조트 등록 성공...addAccom()..");
            }
            else {    // 모텔 등록
                String query = "INSERT INTO accommodation(id, accom_name, location, accom_type, price, pool, people) VALUES(id_seq.nextVal, ?, ?, ?, ?, ?, ?)";
                ps=  conn.prepareStatement(query);
                ps.setString(1, accom.getAccomName());
                ps.setString(2, accom.getLocation());
                ps.setString(3, accom.getAccomType());
                ps.setInt(4, accom.getPrice());
                if(((Motel)accom).isPc()) pc = "Y";
                else pc = "N";
                ps.setString(5, pool);
                ps.setInt(6, accom.getPeople());
                System.out.println(ps.executeUpdate()+"개의 모텔 등록 성공...addAccom()..");
                
            }
        }finally {
            closeAll(conn, ps);
        }
    }

    @Override
    public void updateAccom(Accommodation accom) throws SQLException, RecordNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = getConnect();
        int row = 0;

        try {
            if(isExist(conn, accom.getId())) {
                if(accom instanceof Hotel) {
                    String query = "UPDATE accommodation SET accom_name = ? , location = ?, accom_type = ?, price = ?, star = ?, breakfast = ? WHERE id = ?";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, accom.getAccomName());
                    ps.setString(2, accom.getLocation());
                    ps.setString(3, accom.getAccomType());
                    ps.setInt(4, accom.getPrice());
                    ps.setInt(5, ((Hotel)accom).getStar());
                    ps.setString(6, ((Hotel)accom).isBreakfast()?"Y":"N");
                    ps.setInt(7, accom.getId());
                    
                    row = ps.executeUpdate();
                }else if (accom instanceof Motel) {
                    String query = "UPDATE accommodation SET accom_name = ? , location = ?, accom_type = ?, price = ?, pc = ? WHERE id = ?";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, accom.getAccomName());
                    ps.setString(2, accom.getLocation());
                    ps.setString(3, accom.getAccomType());
                    ps.setInt(4, accom.getPrice());
                    ps.setString(5, ((Motel)accom).isPc()?"Y":"N");
                    ps.setInt(6, accom.getId());

                    row = ps.executeUpdate();
                }else if (accom instanceof Pension) {
                    String query = "UPDATE accommodation SET accom_name = ? , location = ?, accom_type = ?, price = ?, bbq = ? WHERE id = ?";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, accom.getAccomName());
                    ps.setString(2, accom.getLocation());
                    ps.setString(3, accom.getAccomType());
                    ps.setInt(4, accom.getPrice());
                    ps.setString(5, ((Pension)accom).isBbq()?"Y":"N");
                    ps.setInt(6, accom.getId());
                    
                    row = ps.executeUpdate();
                }else if (accom instanceof Resorts) {
                    String query = "UPDATE accommodation SET accom_name = ? , location = ?, accom_type = ?, price = ?, pool = ? WHERE id = ?";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, accom.getAccomName());
                    ps.setString(2, accom.getLocation());
                    ps.setString(3, accom.getAccomType());
                    ps.setInt(4, accom.getPrice());
                    ps.setString(5, ((Resorts)accom).isPool()?"Y":"N");
                    ps.setInt(6, accom.getId());
                    
                    row = ps.executeUpdate();
                }

                
                if(row!=0) System.out.println(accom.getAccomName()+" 정보 변경 성공");
                
            } else {
                // 찾는 값이 없을 때
                throw new RecordNotFoundException("해당 숙소가 존재하지 않습니다.");
            }

        } finally {
            closeAll(conn, ps);

        }

    }
    
    @Override
    public void deleteAccom(int id) throws SQLException, RecordNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnect();
            if(isExist(conn, id)) {
                String query = "DELETE FROM accommodation WHERE id = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                System.out.println(ps.executeUpdate() + "개의 숙박시설 삭제 완료..");
            }else {
                throw new RecordNotFoundException("삭제할 대상의 고객이 없습니다 ");
            }
        }finally {
            closeAll(conn, ps);
        }
    }

    @Override
    public ArrayList<Book> getBookList(String ssn) throws SQLException {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<Book> list = new ArrayList<>();
        try {
            conn = getConnect();
            String query = "SELECT ssn, id, book_date, people FROM book WHERE ssn = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, ssn);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                list.add(new Book(ssn, rs.getInt("id"), rs.getString("book_date"), rs.getInt("people")));
            }
            
        }finally {
            closeAll(conn, ps, rs);
        }
        
        return list;
    }

    @Override
    public ArrayList<Customer> getCustomerByName(String name) {
        return null;
    }

    @Override
    public Customer getCustomerBySsn(String ssn) throws NumberFormatException, SQLException {
    	Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Customer cust = null;
	    try {
	    	conn = getConnect();
	    	String query = "SELECT ssn, cust_name, email, tel, birthdate, point FROM customer WHERE ssn=?";
	    	ps = conn.prepareStatement(query);
	    	ps.setString(1, ssn);
	    	
	    	rs = ps.executeQuery();
	    	if(rs.next()) {
	    		cust = new Customer(ssn, 
	    							   rs.getString("cust_name"), 
	    							   rs.getString("email"),
	    							   rs.getString("tel"),
	    							   new Date(
	    				                        Integer.parseInt(rs.getString("birthdate").substring(0,3)),
	    				                        Integer.parseInt(rs.getString("birthdate").substring(4,5)),
	    				                        Integer.parseInt(rs.getString("birthdate").substring(6))
	    				                        ),
	    							   rs.getInt("point"));
	    		cust.changeBookingList(getBookList(ssn));
	    	}
	    }finally {
	    	closeAll(conn, ps, rs);
	    }
		return cust;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException {
        ArrayList<Customer> list = new ArrayList<>();
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            connect = getConnect();
            String query = "SELECT ssn, cust_name, email, tel, birthdate, point FROM customer ORDER BY 1";
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Date date = new Date(
                        Integer.parseInt(rs.getString("birthdate").substring(0,3)),
                        Integer.parseInt(rs.getString("birthdate").substring(4,5)),
                        Integer.parseInt(rs.getString("birthdate").substring(6))
                        );
                list.add(new Customer(rs.getString("ssn"), rs.getString("cust_name"), rs.getString("email"), rs.getString("tel"), date, rs.getInt("point")));
            }

        } finally {
            closeAll(connect, ps, rs);
        }
        
        return list;

    }

    @Override
    public Accommodation getAccom(int id) {
        return null;
    }

    @Override
    public ArrayList<Accommodation> printAllAccom() throws SQLException {
        ArrayList<Accommodation> list = new ArrayList<>();
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connect = getConnect();
            String query = "SELECT id, accom_name, location, accom_type, price, people FROM accommodation ORDER BY 1";
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                list.add(new Accommodation(
                        rs.getInt("id"),
                        rs.getString("accom_name"),
                        rs.getString("location"),
                        rs.getString("accom_type"),
                        rs.getInt("price"),
                        rs.getInt("people")
                        ));
            }

    } finally {
        closeAll(connect, ps, rs);
    }
    
    return list;
}

    public ArrayList<Accommodation> findAccomsBylocation(String location) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Accommodation> result = new ArrayList<>();
        try {
            conn = getConnect();
            String query = "SELECT id ,accom_name, location, accom_type, price, star ,breakfast ,bbq ,pc ,pool ,people FROM accommodation where location=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, location);

            rs = ps.executeQuery();

            while(rs.next()) {
                if(rs.getString("star")!=null) {
                    boolean temp = false;
                    if(rs.getString("breakfast").equals("Y")) {
                        temp = true;
                    }result.add(new Hotel(rs.getInt("id"), rs.getString("accom_name"), 
                            rs.getString("location"), rs.getString("accom_type"),rs.getInt("price"),rs.getInt("star"),rs.getInt("people"),
                            temp));
                }
                else if(rs.getString("bbq")!=null){
                    boolean temp = false;
                    if(rs.getString("bbq").equals("Y")) {
                        temp = true;
                    }result.add(new Pension(rs.getInt("id"), rs.getString("accom_name"), 
                            rs.getString("location"), rs.getString("accom_type"),rs.getInt("price"),rs.getInt("people"),temp));
                }
                else if(rs.getString("pc")!=null) {
                    boolean temp = false;
                    if(rs.getString("pc").equals("Y")) {
                        temp = true;
                    }result.add(new Motel(rs.getInt("id"), rs.getString("accom_name"), 
                            rs.getString("location"), rs.getString("accom_type"),rs.getInt("price"),rs.getInt("people"),temp));
                }
                else if(rs.getString("pool")!=null) {
                    boolean temp = false;
                    if(rs.getString("pool").equals("Y")) {
                        temp = true;
                    }result.add(new Motel(rs.getInt("id"), rs.getString("accom_name"), 
                            rs.getString("location"), rs.getString("accom_type"),rs.getInt("price"),rs.getInt("people"),temp));
                }
//                }
//                result.add(new Accommodation(rs.getInt("id"), rs.getString("accom_name"), 
//                        rs.getString("location"), rs.getString("accom_type"),rs.getInt("price"),rs.getInt("star"),
//                        rs.getString("breakfast"),rs.getString("bbq"),rs.getString("pc"),rs.getString("pool"),rs.getInt("people")));
            }
        }finally {
            closeAll(conn, ps, rs);
        }
        return result;
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
