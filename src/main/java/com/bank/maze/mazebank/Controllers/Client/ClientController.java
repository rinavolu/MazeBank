package com.bank.maze.mazebank.Controllers.Client;

import com.bank.maze.mazebank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getUserSelectedViewProperty()
                .addListener(((observableValue, oldVal, newVal) -> {
                    switch (newVal){
                        case "Transactions" -> client_parent.setCenter(Model.getInstance().getViewFactory().getTransactionsView());
                        case "Accounts" -> client_parent.setCenter(Model.getInstance().getViewFactory().getAccountsView());
                        default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
                    }
                }
                ));
    }
}
