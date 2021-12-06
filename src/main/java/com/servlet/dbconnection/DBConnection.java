package com.servlet.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    // variable for declaration
    
    // -- package name for driver class (can get from OracleXE\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar)
    // -- jar is java archive -- use winrar to see what is inside
    private final String oracle_driver = "oracle.jdbc.OracleDriver"; 

    private final String oracle_url = "jdbc:oracle:thin:@localhost:1521:xe"; // API:Database:driver:hostname:port:servicename
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
