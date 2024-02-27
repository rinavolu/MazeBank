package com.bank.maze.mazebank.Models;

import com.bank.maze.mazebank.DTO.CheckingAccount;
import com.bank.maze.mazebank.DTO.ClientDTO;
import com.bank.maze.mazebank.DTO.SavingsAccount;
import com.bank.maze.mazebank.Database.DatabaseDriver;
import com.bank.maze.mazebank.Views.AccountType;
import com.bank.maze.mazebank.Views.ViewFactory;

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

    //Client
    private ClientDTO clientDTO;
    private boolean isClientLoggedIn;

    //Admin

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.clientDTO = new ClientDTO("","","",null,null,null);
        this.isClientLoggedIn = false;
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
                this.clientDTO.creationDateProperty().set(localDate);
                this.isClientLoggedIn=true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
