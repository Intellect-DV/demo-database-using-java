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

    // private constructor to prevent object instance
    private DBConnection() {
        try {
            Class.forName(oracle_driver); // load oracle driver class
            conn = DriverManager.getConnection(oracle_url, user, password); // assign connection
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static Connection getInstance() { // use DBConnection.getInstance() to get static connection
        if(conn == null) {
            new DBConnection(); // run constructor to initialize connection
        }
        return conn; // return connection
    }
}
