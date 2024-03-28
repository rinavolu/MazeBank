package com.bank.maze.mazebank.Controllers.Admin;

import com.bank.maze.mazebank.DTO.ClientDTO;
import com.bank.maze.mazebank.ListCellFactories.ClientCellFactory;
import com.bank.maze.mazebank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsListController implements Initializable {
    public ListView<ClientDTO> clients_list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        clients_list.setItems(Model.getInstance().getClients());
        clients_list.setCellFactory(e -> new ClientCellFactory());
    }

    private void initData(){
        /*if(Model.getInstance().getClients().isEmpty()){
            Model.getInstance().loadClients();
        }*/

        Model.getInstance().loadClients();
    }
}
