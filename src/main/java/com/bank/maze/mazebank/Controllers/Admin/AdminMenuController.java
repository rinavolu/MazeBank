package com.bank.maze.mazebank.Controllers.Admin;

import com.bank.maze.mazebank.Models.Model;
import com.bank.maze.mazebank.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
    }

    private void onCreateClient(){
        Model.getInstance().getViewFactory().getAdminSelectedViewProperty().set(AdminMenuOptions.CREATE_CLIENT);
    }
}
