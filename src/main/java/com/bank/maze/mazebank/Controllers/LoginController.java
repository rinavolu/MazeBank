package com.bank.maze.mazebank.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox account_selector;
    public TextField payee_address_fld;
    public TextField password_fld;
    public Label error_lbl;
    public Button login_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
