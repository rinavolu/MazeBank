package com.bank.maze.mazebank.DTO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends AccountDTO {

    private final IntegerProperty transactionLimit;



    public CheckingAccount(String owner, String accountNumber, Double balance, int transactionlimit) {
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(transactionlimit);
    }

    public int getTransactionLimit() {
        return transactionLimit.get();
    }

    public IntegerProperty transactionLimitProperty() {
        return transactionLimit;
    }
}
