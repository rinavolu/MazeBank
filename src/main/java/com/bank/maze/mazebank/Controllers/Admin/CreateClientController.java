package com.bank.maze.mazebank.Controllers.Admin;

import com.bank.maze.mazebank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.joda.time.LocalDate;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField first_name;
    public TextField last_name;
    public TextField password;
    public CheckBox pAddress_checkbox;
    public Label pAddress_lbl;
    public CheckBox ch_acc_checkbox;
    public TextField cAccount_balance;
    public CheckBox sv_acc_checkbox;
    public TextField sv_account_balance;
    public Button create_client_btn;
    public Label error_lbl;

    private String payeeAddress;

    private Boolean isCheckingAccountCreationRequired = false;
    private Boolean isSavingsAccountCreationRequired = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(event -> createClient());

        pAddress_checkbox.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue){
                payeeAddress = createPayeeAddress();
                onCreatePayeeAddress();
            }
        }));

        ch_acc_checkbox.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue){
                isCheckingAccountCreationRequired=true;
            }
        }));

        sv_acc_checkbox.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue){
                isSavingsAccountCreationRequired=true;
            }
        }));

    }

    private void createClient(){
        if(isCheckingAccountCreationRequired) createAccount("CHECKING_ACC");

        if(isSavingsAccountCreationRequired) createAccount("SAVINGS_ACC");

        //Create Client Account
        String fName = first_name.getText();
        String lName = last_name.getText();
        String passwordStr = password.getText();
        Model.getInstance().getDatabaseDriver().createClient(fName,lName, payeeAddress,passwordStr, LocalDate.now());
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_lbl.setText("Client Created Successfully");
        emptyFields();
    }

    private void createAccount(String accountType){
        double balance ;
        //Generate Account Number
        String firstNumber = "0293";
        String secondNumber = Integer.toString(new Random().nextInt(9999)+1000);
        String accountNumber = firstNumber+" "+secondNumber;

        //Save to DB
        //Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress,accountNumber, 10.0,balance);

        if(accountType.equals("CHECKING_ACC")){
            balance = Double.parseDouble(cAccount_balance.getText());
            Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress,accountNumber, 10.0,balance);
        }else{
            balance = Double.parseDouble(sv_account_balance.getText());
            Model.getInstance().getDatabaseDriver().createSavingsAccount(payeeAddress,accountNumber, 2000.0,balance);
        }
    }


    private void emptyFields(){
        first_name.setText("");
        last_name.setText("");
        password.setText("");
        pAddress_checkbox.setSelected(false);
        pAddress_lbl.setText("");
        cAccount_balance.setText("");
        ch_acc_checkbox.setSelected(false);
        sv_account_balance.setText("");
        sv_acc_checkbox.setSelected(false);
    }


    private void onCreatePayeeAddress(){
        if(first_name.getText() != null && last_name.getText() != null){
            pAddress_lbl.setText(payeeAddress);
        }
    }

    private String createPayeeAddress(){
        String payeeAddressCreate = "@"+first_name.getText().charAt(0);
        payeeAddressCreate+=last_name.getText();
        payeeAddressCreate+=Model.getInstance().getDatabaseDriver().getLastClientId()+1;
        this.payeeAddress = payeeAddressCreate.toLowerCase();
        return payeeAddressCreate.toLowerCase();
    }

}
