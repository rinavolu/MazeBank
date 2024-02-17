package com.bank.maze.mazebank.Views;

import com.bank.maze.mazebank.Controllers.Client.ClientController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private StringProperty userSelectedView;

    //Client Views
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;

    public ViewFactory(){
        this.userSelectedView = new SimpleStringProperty("");
    }


    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        createStage(loader);
    }

    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Client/client.fxml"));
        /*ClientController clientController = new ClientController();
        loader.setController(clientController);*/
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene =null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Maze Bank");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }

    public AnchorPane getTransactionsView() {
        if(transactionsView==null){
            try{
                transactionsView = new FXMLLoader(getClass().getResource("/fxml/Client/transactions.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getDashboardView() {
        if(dashboardView == null){
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/fxml/Client/dashboard.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public StringProperty userSelectedViewProperty() {
        return userSelectedView;
    }
}
