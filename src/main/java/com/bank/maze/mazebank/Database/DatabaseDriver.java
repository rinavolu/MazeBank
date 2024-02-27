package com.bank.maze.mazebank.Database;

import java.sql.*;

public class DatabaseDriver {

    private Connection conn;

    private final String url = "jdbc:postgresql://localhost:5432/";

    private final String userName = "postgres";

    private final String password = "RAvijay@7482";

    public DatabaseDriver(){
        try {
            this.conn = DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * Client Section
    * */
    public ResultSet getClientData(String pAddress, String password){
        ResultSet resultSet = null;
        Statement statement;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM clients where payee_address = '"+pAddress+ "' and payee_password = '"+password+"';");
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }


    /*
    * Admin Section
    * */


}
