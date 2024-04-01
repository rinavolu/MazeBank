package com.bank.maze.mazebank.Controllers.Client;

import com.bank.maze.mazebank.DTO.TransactionDTO;
import com.bank.maze.mazebank.ListCellFactories.TransactionCellFactory;
import com.bank.maze.mazebank.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    public ListView<TransactionDTO> transactions_list_view;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllTransactions();
    }

    private void loadAllTransactions(){
        Model.getInstance().setAllTransactions();
        this.transactions_list_view.setItems(Model.getInstance().getAllTransactions());
        this.transactions_list_view.setCellFactory(e -> new TransactionCellFactory());
    }
}
