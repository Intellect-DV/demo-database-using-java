package com.servlet.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    // variable for declaration
    private final String oracle_driver = "oracle.jdbc.OracleDriver";
    private final String oracle_url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String user = "hr";
    private final String password = "system";

    // connection variable
    private static Connection conn = null;
    private DBConnection() {
        try {
            Class.forName(oracle_driver); // load oracle driver
            conn = DriverManager.getConnection(oracle_url, user, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static Connection getInstance() {
        if(conn == null) {
            new DBConnection();
        }
        return conn;
    }
}
