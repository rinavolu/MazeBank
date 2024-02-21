package com.bank.maze.mazebank.Controllers.Client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    public ListView transactions_list_view;
    public FontAwesomeIconView transaction_credit_icon;
    public FontAwesomeIconView transaction_debit_icon;
    public Label transaction_date;
    public Label sender;
    public Label receiver;
    public Label transaction_amount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
