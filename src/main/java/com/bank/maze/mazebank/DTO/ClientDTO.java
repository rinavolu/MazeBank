package com.bank.maze.mazebank.DTO;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.joda.time.LocalDate;

public class ClientDTO {

    private final StringProperty firstName;

    private final StringProperty lastName;

    private final StringProperty pAddress;


    private final ObjectProperty<AccountDTO> checkingAccount;

    private final ObjectProperty<AccountDTO> savingsAccount;

    private final ObjectProperty<org.joda.time.LocalDate> creationDate;

    public ClientDTO(String firstName,
                     String lastName,
                     String pAddress,
                     AccountDTO checkingAccount,
                     AccountDTO savingsAccount,
                     LocalDate creationDate) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.pAddress = new SimpleStringProperty(pAddress);
        this.checkingAccount = new SimpleObjectProperty<AccountDTO>(checkingAccount);
        this.savingsAccount = new SimpleObjectProperty<AccountDTO>(savingsAccount);
        this.creationDate = new SimpleObjectProperty<org.joda.time.LocalDate>(creationDate);
    }


    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getpAddress() {
        return pAddress.get();
    }

    public StringProperty pAddressProperty() {
        return pAddress;
    }

    public AccountDTO getCheckingAccount() {
        return checkingAccount.get();
    }

    public ObjectProperty<AccountDTO> checkingAccountProperty() {
        return checkingAccount;
    }

    public AccountDTO getSavingsAccount() {
        return savingsAccount.get();
    }

    public ObjectProperty<AccountDTO> savingsAccountProperty() {
        return savingsAccount;
    }

    public org.joda.time.LocalDate getCreationDate() {
        return creationDate.get();
    }

    public ObjectProperty<org.joda.time.LocalDate> creationDateProperty() {
        return creationDate;
    }
}
