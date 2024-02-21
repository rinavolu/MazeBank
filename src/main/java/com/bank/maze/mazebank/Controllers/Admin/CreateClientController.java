package com.bank.maze.mazebank.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
