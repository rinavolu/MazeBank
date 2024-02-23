package com.bank.maze.mazebank.ListCellFactories;

import com.bank.maze.mazebank.Controllers.Client.TransactionCellController;
import com.bank.maze.mazebank.DTO.TransactionDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class TransactionCellFactory extends ListCell<TransactionDTO> {

    @Override
    protected void updateItem(TransactionDTO transactionDTO, boolean empty) {
        super.updateItem(transactionDTO, empty);
        setFxml(transactionDTO, empty);
    }

    private void setFxml(TransactionDTO transactionDTO, boolean empty){
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Client/transaction-cell.fxml"));
            fxmlLoader.setController(new TransactionCellController(transactionDTO));
            setText(null);
            try {
                setGraphic(fxmlLoader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
