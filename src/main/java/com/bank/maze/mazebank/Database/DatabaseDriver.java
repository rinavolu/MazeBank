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

    public ResultSet searchClient(String payeeAddress){
        Statement statement;
        ResultSet resultSet =null;
        try{
            statement = this.conn.createStatement();
            String sql = "SELECT * FROM clients WHERE payee_address = '"+payeeAddress+ '\'';
            resultSet = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }


    public void depositSavings(String payee_address,String amount){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            String sql = "UPDATE savings_accounts SET balance = "+amount+" WHERE acc_owner = '"+payee_address+ '\'';
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public ResultSet getTransactions(String payeeAddress,int limit){
        Statement statement;
        ResultSet resultSet =null;
        try{
            statement = this.conn.createStatement();
            String sql;

            if(limit>=0) {
                sql = "SELECT * FROM transactions WHERE sender = '" + payeeAddress + '\''
                        + " or receiver = '" + payeeAddress + '\'' + " ORDER BY CREATION_DATE DESC LIMIT " + limit;
            }else{
                sql = "SELECT * FROM transactions WHERE sender = '" + payeeAddress + '\''
                        + " or receiver = '" + payeeAddress + '\'' + " ORDER BY CREATION_DATE DESC ";
            }

            resultSet = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }


    public void updateBalance(String payee_address, double amount){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            String sql;
            //String sql = "UPDATE savings_accounts SET balance = "+amount+" WHERE acc_owner = '"+payee_address+ '\'';
            sql= "with subquery as (" +
                    "select * from savings_accounts) update savings_accounts set balance = "+amount+" + subquery.balance\n" +
                    "from subquery where savings_accounts.acc_owner = subquery.acc_owner and subquery.acc_owner= '"+payee_address+'\'';
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Double getSavingsAccountBalance(String payee_address){
        Statement statement;
        ResultSet resultSet;
        //Double balance;
        try{
            statement = this.conn.createStatement();
            String sql = "SELECT * FROM savings_accounts WHERE payee_address = '"+payee_address+ '\'';
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) return resultSet.getDouble("balance");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //New transaction
    public void commitTransaction(String sender, String receiver, Double amount, String message){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            String sql= "INSERT INTO TRANSACTIONS( sender, receiver, amount, creation_date, message) VALUES (" +
                    "'"+sender+"', " +
                    "'"+receiver+"', " +
                    " "+amount+"," +
                    "'"+LocalDate.now()+"', " +
                    "'"+message+"'" +
                    ")";
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
