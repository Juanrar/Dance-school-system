package com.mycompany.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cursor {
    protected Connection conn;
    
    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/DanceDB";
    private final String USER = "postgres";
    private final String PASS = "1234";
    
    public void Connect() throws ClassNotFoundException{
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);
        } catch (SQLException ex) {
            Logger.getLogger(Cursor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Close() throws SQLException{
        if(conn != null){
            if(!conn.isClosed()){
                conn.close();
            }
        }
    }
}
