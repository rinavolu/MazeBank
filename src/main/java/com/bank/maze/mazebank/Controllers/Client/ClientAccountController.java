package com.bank.maze.mazebank.Controllers.Client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientAccountController {

    /* Checking Account */
    public Label ch_acc_number;
    public Label transaction_limit;
    public Label ch_acc_date_created;
    public Label ch_acc_balance;

    /* Savings Account */
    public Label sav_acc_number;
    public Label withdrawal_limit;
    public Label sav_acc_date_created;
    public Label sav_acc_balance;


    /* Checking Account Transfer Funds Section */
    public TextField ch_acc_move_funds_amount;
    public Button ch_acc_trans_money_btn;

    /* Savings Account Transfer Funds Section */
    public TextField sav_acc_move_funds_amount;
    public Button sav_acc_trans_money_btn;
}
