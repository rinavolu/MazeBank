package com.bank.maze.mazebank.DTO;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class AccountDTO {

    private final StringProperty owner;

    private final StringProperty accountNumber;

    private final DoubleProperty balance;

    public AccountDTO(String owner, String accountNumber, Double balance) {
        this.owner = new SimpleStringProperty(owner);
        this.accountNumber = new SimpleStringProperty(accountNumber);
        this.balance = new SimpleDoubleProperty(balance);
    }

    public String getOwner() {
        return owner.get();
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    public double getBalance() {
        return balance.get();
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }
}
