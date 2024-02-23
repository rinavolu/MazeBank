package com.bank.maze.mazebank.ListCellFactories;

import com.bank.maze.mazebank.Controllers.Admin.ClientCellController;
import com.bank.maze.mazebank.DTO.ClientDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClientCellFactory extends ListCell<ClientDTO> {

    @Override
    protected void updateItem(ClientDTO clientDTO, boolean empty) {
        super.updateItem(clientDTO, empty);
        setFxml(clientDTO, empty);
    }

    private void setFxml(ClientDTO clientDTO, boolean empty){
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Admin/client-list-cell.fxml"));
            fxmlLoader.setController(new ClientCellController(clientDTO));
            setText(null);
            try{
                setGraphic(fxmlLoader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
