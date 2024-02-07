module com.bank.maze.mazebank {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bank.maze.mazebank to javafx.fxml;
    exports com.bank.maze.mazebank;
}