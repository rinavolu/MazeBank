package com.bank.maze.mazebank.Controllers;

import com.bank.maze.mazebank.Models.Model;
import com.bank.maze.mazebank.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> account_selector;
    public TextField payee_address_fld;
    public TextField password_fld;
    public Label error_lbl;
    public Button login_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN,AccountType.CLIENT));
        account_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());

        account_selector.valueProperty().addListener(observable ->
                Model.getInstance().getViewFactory().setLoginAccountType(account_selector.getValue()));

        login_btn.setOnAction(e -> onLogin());
    }

    private void onLogin(){
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        if(Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.CLIENT){
            //Check Login Credentials
            Model.getInstance().validateClientCredentials(payee_address_fld.getText(),
                    password_fld.getText());
            if(Model.getInstance().isClientLoggedIn()) {
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showClientWindow();
            }else{
                payee_address_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("Invalid Credentials !!! Please try again");
            }
        }
        else {
            Model.getInstance().getViewFactory().closeStage(stage);
            Model.getInstance().getViewFactory().showAdminWindow();
        }
    }
}
