package com.bank.maze.mazebank.DTO;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SavingsAccount extends AccountDTO{

    //Withdrawal Limit From Savings
    private final DoubleProperty withdrawalLimit;
    public SavingsAccount(String owner, String accountNumber, Double balance, Double withdrawalLimit) {
        super(owner, accountNumber, balance);
        this.withdrawalLimit = new SimpleDoubleProperty(withdrawalLimit);
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit.get();
    }

    public DoubleProperty withdrawalLimitProperty() {
        return withdrawalLimit;
    }
}
