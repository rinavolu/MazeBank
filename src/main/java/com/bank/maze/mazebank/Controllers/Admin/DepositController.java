package com.bank.maze.mazebank.Controllers.Admin;

import com.bank.maze.mazebank.DTO.ClientDTO;
import com.bank.maze.mazebank.ListCellFactories.ClientCellFactory;
import com.bank.maze.mazebank.Models.Model;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    public TextField payee_address_fld;
    public Button search_btn;
    public ListView<ClientDTO> result_list_view;
    public TextField amount_fld;
    public Button deposit_btn;

    private ClientDTO clientDTO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn.setOnAction(e-> onClientSearch());
        deposit_btn.setOnAction(e-> onDeposit());
    }

    private void onClientSearch(){
        ObservableList<ClientDTO> searchResults = Model.getInstance().searchClient(payee_address_fld.getText());
        result_list_view.setItems(searchResults);
        result_list_view.setCellFactory(e -> new  ClientCellFactory());
        this.clientDTO = searchResults.get(0);
    }


    private void onDeposit(){
        Double amount=  Double.parseDouble(amount_fld.getText());
        Double newBalance = amount + clientDTO.savingsAccountProperty().get().getBalance();
        if(amount_fld.getText()!=null){
            Model.getInstance().getDatabaseDriver().depositSavings(clientDTO.getpAddress(), String.valueOf(newBalance));
        }
        clearFields();
    }

    private void clearFields(){
        payee_address_fld.clear();
        amount_fld.clear();
        result_list_view.getItems().clear();
    }

}
