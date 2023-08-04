package com.jdbc.test;

import com.jdbc.dao.Database;
import config.ServerInfo;

public class DatabaseTest {
    public static void main(String args[]){
        Database db = Database.getInstance();

    }
    static {
        try {
            Class.forName(ServerInfo.DRIVER_NAME);
            System.out.println("Driver Loading Success...");
        } catch(ClassNotFoundException e){
            System.out.println("Driver Loading Fail...");
        }    }
}
