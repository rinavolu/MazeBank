package com.bank.maze.mazebank.Controllers.Admin;

import com.bank.maze.mazebank.Models.Model;
import com.bank.maze.mazebank.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button view_clients_btn;
    public Button deposit_btn;
    public Button admin_logout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        create_client_btn.setOnAction(event -> onCreateClient());
        view_clients_btn.setOnAction(event -> onClientsListView());
        deposit_btn.setOnAction(event -> onDepositView());
        admin_logout.setOnAction(event -> {
            Model.getInstance().resetLoginSession();
            Model.getInstance().getViewFactory().closeStage((Stage) admin_logout.getScene().getWindow());
            Model.getInstance().getViewFactory().showLoginWindow();
        });
    }

    private void onCreateClient(){
        Model.getInstance().getViewFactory().getAdminSelectedViewProperty().set(AdminMenuOptions.CREATE_CLIENT);
    }

    private void onClientsListView(){
        Model.getInstance().getViewFactory().getAdminSelectedViewProperty().set(AdminMenuOptions.CLIENTS);
    }

    private void onDepositView(){
        Model.getInstance().getViewFactory().getAdminSelectedViewProperty().set(AdminMenuOptions.DEPOSIT);
    }
}
