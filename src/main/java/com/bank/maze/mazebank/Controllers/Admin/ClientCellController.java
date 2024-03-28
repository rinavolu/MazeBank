package com.bank.maze.mazebank.Controllers.Admin;

import com.bank.maze.mazebank.DTO.ClientDTO;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label firstName;
    public Label lastName;
    public Label pAddress;
    public Label checkingAccountNumber;
    public Label savingsAccountNumber;
    public Label accountCreatedDate;
    public Button userDeleteButton;

    private final ClientDTO clientDTO;

    public ClientCellController(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.textProperty().bind(clientDTO.firstNameProperty());
        lastName.textProperty().bind(clientDTO.lastNameProperty());
        pAddress.textProperty().bind(clientDTO.pAddressProperty());
        checkingAccountNumber.textProperty().bind(clientDTO.checkingAccountProperty().asString());
        savingsAccountNumber.textProperty().bind(clientDTO.savingsAccountProperty().asString());
        accountCreatedDate.textProperty().bind(clientDTO.getCreationDate());
    }
}
