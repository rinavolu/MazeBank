package com.bank.maze.mazebank.DTO;

import javafx.beans.property.*;
import org.joda.time.LocalDate;


public class TransactionDTO {

    private final StringProperty senderAddress;

    private final StringProperty receiverAddress;

    private final DoubleProperty transactionAmount;

    private final ObjectProperty<LocalDate> transactionDate;

    private final StringProperty message;

    public TransactionDTO(String senderAddress,
                          String receiverAddress,
                          Double transactionAmount,
                          LocalDate transactionDate,
                          String message) {
        this.senderAddress = new SimpleStringProperty(senderAddress);
        this.receiverAddress = new SimpleStringProperty(receiverAddress);
        this.transactionAmount = new SimpleDoubleProperty(transactionAmount);
        this.transactionDate = new SimpleObjectProperty<LocalDate>(transactionDate);
        this.message = new SimpleStringProperty(message);
    }


    public String getSenderAddress() {
        return senderAddress.get();
    }

    public StringProperty senderAddressProperty() {
        return senderAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress.get();
    }

    public StringProperty receiverAddressProperty() {
        return receiverAddress;
    }

    public double getTransactionAmount() {
        return transactionAmount.get();
    }

    public DoubleProperty transactionAmountProperty() {
        return transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate.get();
    }

    public ObjectProperty<LocalDate> transactionDateProperty() {
        return transactionDate;
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }
}
