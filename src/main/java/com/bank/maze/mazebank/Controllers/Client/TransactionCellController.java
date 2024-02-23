package com.bank.maze.mazebank.Controllers.Client;

import com.bank.maze.mazebank.DTO.TransactionDTO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public FontAwesomeIconView transaction_credit_icon;
    public FontAwesomeIconView transaction_debit_icon;
    public Label transaction_date;
    public Label sender;
    public Label receiver;
    public Label transaction_amount;

    private final TransactionDTO transactionDTO;

    public TransactionCellController(TransactionDTO transactionDTO) {
        this.transactionDTO = transactionDTO;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
