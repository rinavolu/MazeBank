package com.bank.maze.mazebank.Controllers.Client;

import com.bank.maze.mazebank.DTO.TransactionDTO;
import com.bank.maze.mazebank.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public FontAwesomeIconView transaction_credit_icon;
    public FontAwesomeIconView transaction_debit_icon;
    public Label transaction_date;
    public Label sender;
    public Label receiver;
    public Label transaction_amount;

    private final TransactionDTO transaction;

    public Button message_btn;

    public TransactionCellController(TransactionDTO transactionDTO) {
        this.transaction = transactionDTO;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sender.textProperty().bind(transaction.senderAddressProperty());
        receiver.textProperty().bind(transaction.receiverAddressProperty());
        transaction_date.textProperty().bind(transaction.transactionDateProperty().asString());
        transaction_amount.textProperty().bind(transaction.transactionAmountProperty().asString());
        transactionIcon();
        message_btn.setOnAction(e -> Model.getInstance().getViewFactory().
                showMessageWindow(transaction.senderAddressProperty().get(), transaction.messageProperty().get()));
    }

    private void transactionIcon(){
        if(transaction.getSenderAddress().equals(Model.getInstance().getClientDTO().getpAddress())){
            //Expense
            transaction_debit_icon.setFill(Color.rgb(240,240,240));
            transaction_credit_icon.setFill(Color.RED);
        }else{
            transaction_credit_icon.setFill(Color.rgb(240,240,240));
            transaction_debit_icon.setFill(Color.GREEN);
        }
    }
}
