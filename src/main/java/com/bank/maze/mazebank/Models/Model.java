package com.bank.maze.mazebank.Models;

import com.bank.maze.mazebank.DTO.CheckingAccount;
import com.bank.maze.mazebank.DTO.ClientDTO;
import com.bank.maze.mazebank.DTO.SavingsAccount;
import com.bank.maze.mazebank.Database.DatabaseDriver;
import com.bank.maze.mazebank.Views.AccountType;
import com.bank.maze.mazebank.Views.ViewFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.sql.ResultSet;

public class Model {

    public static Model model;

    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;

    private AccountType loginAccountType = AccountType.CLIENT;

    private final ObservableList<ClientDTO> clients;

    //Client
    private ClientDTO clientDTO;
    private boolean isClientLoggedIn;

    //Admin
    private boolean isAdminLoggedIn;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.clientDTO = new ClientDTO("","","",null,null,null);
        this.isClientLoggedIn = false;
        this.isAdminLoggedIn = false;
        this.clients = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*
    * Client Section
    * */

    public boolean isClientLoggedIn() {
        return isClientLoggedIn;
    }

    public void setClientLoggedIn(boolean clientLoggedIn) {
        isClientLoggedIn = clientLoggedIn;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }


    public void validateClientCredentials(String payeeAddress, String password){
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getClientData(payeeAddress, password);
        try{
            if(resultSet.next()){
                this.clientDTO.firstNameProperty().set(resultSet.getString("first_name"));
                this.clientDTO.lastNameProperty().set(resultSet.getString("last_name"));
                this.clientDTO.pAddressProperty().set(resultSet.getString("payee_address"));

                DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
                DateTime dateTime = FORMATTER.parseDateTime(resultSet.getString("creation_date"));
                LocalDate localDate = dateTime.toLocalDate();
                checkingAccount = getCheckingAccount(payeeAddress);
                savingsAccount = getSavingsAccount(payeeAddress);
                this.clientDTO.creationDateProperty().set(localDate);
                this.clientDTO.savingsAccountProperty().set(savingsAccount);
                this.clientDTO.checkingAccountProperty().set(checkingAccount);
                this.isClientLoggedIn=true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /*
    * Admin Section
    * */

    public boolean isAdminLoggedIn() {
        return isAdminLoggedIn;
    }

    public void setAdminLoggedIn(boolean adminLoggedIn) {
        isAdminLoggedIn = adminLoggedIn;
    }

    public void validateAdminCredentials(String adminName, String adminPassword){
        ResultSet resultSet = databaseDriver.getAdminData(adminName, adminPassword);
        try{
            if(resultSet.next()){
                this.isAdminLoggedIn=true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<ClientDTO> getClients() {
        return clients;
    }

    public void loadClients(){
        setClients();
    }

    private void setClients(){
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getAllClients();
        try{
            while(resultSet.next()){
                String fName = resultSet.getString("first_name");
                String lName = resultSet.getString("last_name");
                String payee_address = resultSet.getString("payee_address");
                DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
                DateTime dateTime = FORMATTER.parseDateTime(resultSet.getString("creation_date"));
                LocalDate localDate = dateTime.toLocalDate();
                checkingAccount = getCheckingAccount(payee_address);
                savingsAccount = getSavingsAccount(payee_address);
                this.clients.add(new ClientDTO(fName,lName,payee_address,checkingAccount,savingsAccount,localDate));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resetLoginSession(){

        if(this.isAdminLoggedIn) {
            this.isAdminLoggedIn = false;
        }

        if(this.isClientLoggedIn) {
            this.isClientLoggedIn = false;
            this.clientDTO = new ClientDTO("", "", "",
                    null, null, null);
        }

    }

    /*
    * Utility Methods
    * */
    public CheckingAccount getCheckingAccount(String payeeAddress){
        CheckingAccount account = null;
        ResultSet resultSet = databaseDriver.getCheckingAccountData(payeeAddress);
        try {
            if(resultSet.next()) {
                String num = resultSet.getString("account_number");
                int tLimit = (int) resultSet.getFloat("transaction_limit");
                Double balance = (double) resultSet.getFloat("balance");
                account = new CheckingAccount(payeeAddress, num, balance, tLimit);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    public SavingsAccount getSavingsAccount(String payeeAddress){
        SavingsAccount account = null;
        ResultSet resultSet = databaseDriver.getSavingsAccountData(payeeAddress);
        try {
            if(resultSet.next()) {
                String num = resultSet.getString("account_number");
                Double withdrawalLimit = (double) resultSet.getFloat("withdrawal_limit");
                Double balance = (double) resultSet.getFloat("balance");
                account = new SavingsAccount(payeeAddress, num, balance, withdrawalLimit);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }


    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }
}
