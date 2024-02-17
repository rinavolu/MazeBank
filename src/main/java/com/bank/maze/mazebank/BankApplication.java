package com.bank.maze.mazebank;

import com.bank.maze.mazebank.Models.Model;
import com.bank.maze.mazebank.Views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BankApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
