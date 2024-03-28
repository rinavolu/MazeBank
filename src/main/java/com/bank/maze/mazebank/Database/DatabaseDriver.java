package com.bank.maze.mazebank.Database;

import org.joda.time.LocalDate;

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
    public ResultSet getAdminData(String adminName, String password){
        ResultSet resultSet = null;
        Statement statement;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM admins where username = '"+adminName+ "' and admin_password = '"+password+"';");
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }

    //Create Client @Admin

    public void createClient(String fName, String lName, String pAddress, String password, LocalDate date){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            String createClientSql = ("INSERT INTO clients (first_name, last_name, payee_address, " +
                    "payee_password, creation_date) VALUES('%s','%s','%s','%s','%s' )")
                    .formatted(fName, lName, pAddress, password, date.toString());
            statement.executeUpdate(createClientSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void createCheckingAccount(String owner, String accountNumber, Double transactionLimit , Double balance){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            String createClientSql = ("INSERT INTO checking_accounts (acc_owner, account_number, transaction_limit, balance) " +
                    "VALUES('%s','%s','%s','%s')")
                    .formatted(owner, accountNumber, transactionLimit, balance);
            statement.executeUpdate(createClientSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createSavingsAccount(String owner, String accountNumber, Double withdrawalLimit, Double balance){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            String createClientSql = ("INSERT INTO savings_accounts (acc_owner, account_number, withdrawal_limit, balance)" +
                    " VALUES('%s','%s','%s','%s')")
                    .formatted(owner, accountNumber, withdrawalLimit, balance);
            System.out.println(createClientSql);
            statement.executeUpdate(createClientSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public Integer getLastClientId(){
        Integer id=null;
        Statement statement;
        ResultSet resultSet;
        try{
            statement = this.conn.createStatement();
            String sql = "SELECT id FROM clients ORDER BY ID DESC LIMIT 1";
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) id=  resultSet.getInt("id");
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }


    public ResultSet getAllClients(){
        Statement statement;
        ResultSet resultSet =null;
        try{
            statement = this.conn.createStatement();
            String sql = "SELECT * FROM CLIENTS";
            resultSet = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }


     public ResultSet getCheckingAccountData(String payeeAddress){
         Statement statement;
         ResultSet resultSet =null;
         try{
             statement = this.conn.createStatement();
             String sql = "SELECT * FROM checking_accounts WHERE acc_owner = '"+payeeAddress+ '\'';
             resultSet = statement.executeQuery(sql);
         }catch (Exception e){
             e.printStackTrace();
         }
         return resultSet;
     }


    public ResultSet getSavingsAccountData(String payeeAddress){
        Statement statement;
        ResultSet resultSet =null;
        try{
            statement = this.conn.createStatement();
            String sql = "SELECT * FROM savings_accounts WHERE acc_owner = '"+payeeAddress+ '\'';
            resultSet = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
