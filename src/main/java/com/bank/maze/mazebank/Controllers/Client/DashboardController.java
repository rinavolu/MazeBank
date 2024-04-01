package com.bank.maze.mazebank.Controllers.Client;

import com.bank.maze.mazebank.DTO.ClientDTO;
import com.bank.maze.mazebank.DTO.TransactionDTO;
import com.bank.maze.mazebank.ListCellFactories.TransactionCellFactory;
import com.bank.maze.mazebank.Models.Model;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text user_id;
    public Label date_lbl;
    public Label checking_balance;
    public Label checking_acc_number;

    public Label savings_acc_num;
    public Label savings_balance;
    public Label income_amount;
    public Label expense_amount;
    public ListView<TransactionDTO> transaction_list_view;
    public TextField payee_address_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;

    private ClientDTO client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindData();
        initLatestTransactions();
        transaction_list_view.setItems(Model.getInstance().getLatestTransactions());
        transaction_list_view.setCellFactory(e -> new TransactionCellFactory());
        calculateSummary();
        this.send_money_btn.setOnAction(e -> onSendMoney());
    }

    private void bindData(){
        this.client = Model.getInstance().getClientDTO();
        user_id.textProperty().bind(Bindings.concat("Hi, ").concat(client.firstNameProperty().get()));
        date_lbl.setText("Today, "+ LocalDate.now());
        checking_balance.textProperty().bind(client.getCheckingAccount().balanceProperty().asString());
        checking_acc_number.textProperty().bind(client.checkingAccountProperty().get().accountNumberProperty());

        savings_acc_num.textProperty().bind(client.savingsAccountProperty().get().accountNumberProperty());
        savings_balance.textProperty().bind(client.savingsAccountProperty().get().balanceProperty().asString());
    }

    private void initLatestTransactions(){
        if(Model.getInstance().getLatestTransactions().isEmpty()){
            Model.getInstance().setLatestTransactions();
        }
    }

    private void onSendMoney(){
        String receiver_payee_address = payee_address_fld.getText();
        Double amount = Double.valueOf(amount_fld.getText());
        Double sender_savings_balance = client.getSavingsAccount().getBalance();
        String message = message_fld.getText();
        //Subtract balance in sender
        Model.getInstance().getDatabaseDriver().updateBalance(client.getpAddress(),-1*amount);
        Model.getInstance().getDatabaseDriver().commitTransaction(client.getpAddress(), receiver_payee_address,amount,message);
        //Add balance in receiver
        Model.getInstance().getDatabaseDriver().updateBalance(receiver_payee_address,amount);

        this.payee_address_fld.clear();
        this.amount_fld.clear();
        this.message_fld.clear();

        sender_savings_balance= -1*amount+sender_savings_balance;
        Model.getInstance().getClientDTO().savingsAccountProperty().get().setBalance(sender_savings_balance);
        Model.getInstance().setLatestTransactions();
        calculateSummary();
        //Model.getInstance().setAllTransactions();
    }


    private void calculateSummary(){
        Double expenses= 0.0;
        Double savings = 0.0;
        Model.getInstance().setAllTransactions();
        List<TransactionDTO> transactions = Model.getInstance().getAllTransactions();
        if(transactions!=null){
            for(TransactionDTO transaction: transactions){
                if(transaction.getSenderAddress().equals(client.getpAddress())){
                    expenses+= transaction.getTransactionAmount();
                }
                if(transaction.getReceiverAddress().equals(client.getpAddress())){
                    savings+= transaction.getTransactionAmount();
                }
            }
        }
        this.expense_amount.setText("-$ "+String.valueOf(expenses));
        this.income_amount.setText("+$ "+String.valueOf(savings));
    }
}
