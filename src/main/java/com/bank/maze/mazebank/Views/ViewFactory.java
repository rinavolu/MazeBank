package com.bank.maze.mazebank.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
