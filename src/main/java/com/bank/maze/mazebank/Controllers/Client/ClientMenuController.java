package com.bank.maze.mazebank.Controllers.Client;

import com.bank.maze.mazebank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button account_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }


    private void addListeners(){
        dashboard_btn.setOnAction(event -> onDashBoard());
        transaction_btn.setOnAction(event -> onTransactions());
        account_btn.setOnAction(event -> onAccounts());
    }

    private void onTransactions() {
        Model.getInstance().getViewFactory().getUserSelectedViewProperty().set("Transactions");
    }

    private void onDashBoard() {
        Model.getInstance().getViewFactory().getUserSelectedViewProperty().set("Dashboard");
    }

    private void onAccounts(){
        Model.getInstance().getViewFactory().getUserSelectedViewProperty().set("Accounts");
    }


}
