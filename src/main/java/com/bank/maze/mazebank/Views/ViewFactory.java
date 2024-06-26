package com.bank.maze.mazebank.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private AccountType loginAccountType;
    private final ObjectProperty<ClientMenuOptions> userSelectedView;

    //Client Views
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;

    //Admin Views

    private final ObjectProperty<AdminMenuOptions> adminSelectedView;
    private AnchorPane createClientView;

    private AnchorPane clientsListView;

    private AnchorPane depositView;

    public ViewFactory(){
        this.loginAccountType = AccountType.CLIENT;
        this.userSelectedView = new SimpleObjectProperty<>();
        this.adminSelectedView = new SimpleObjectProperty<>();
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

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/admin.fxml"));
        createStage(loader);
    }

    public void showMessageWindow(String pAddress, String messageText){
        StackPane pane = new StackPane();
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER);
        Label sender = new Label(pAddress);
        Label message = new Label(messageText);
        hbox.getChildren().addAll(sender,message);
        pane.getChildren().add(hbox);
        Scene scene= new Scene(pane, 300, 100);
        Stage stage= new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/app-icon.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();

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
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/app-icon.png"))));
        stage.setResizable(false);
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

    public AnchorPane getAccountsView(){
        if(accountsView == null){
            try{
                accountsView = new FXMLLoader(getClass().getResource("/fxml/Client/accounts.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    public AnchorPane getCreateClientView(){
        if(createClientView == null){
            try{
                createClientView = new FXMLLoader(getClass().getResource("/fxml/Admin/create-client.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return createClientView;
    }

    public AnchorPane getDepositView(){
        if(depositView == null){
            try{
                depositView = new FXMLLoader(getClass().getResource("/fxml/Admin/deposit.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return depositView;
    }

    public AnchorPane getClientsListView(){
        if(clientsListView == null){
            try{
                clientsListView = new FXMLLoader(getClass().getResource("/fxml/Admin/clients-list.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientsListView;
    }

    public ObjectProperty<ClientMenuOptions> getUserSelectedViewProperty() {
        return userSelectedView;
    }

    public ObjectProperty<AdminMenuOptions> getAdminSelectedViewProperty() {
        return adminSelectedView;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }
}
