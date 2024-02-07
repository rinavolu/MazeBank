module com.bank.maze.mazebank {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.bank.maze.mazebank to javafx.fxml;
    exports com.bank.maze.mazebank;
    exports com.bank.maze.mazebank.Controllers;
    exports com.bank.maze.mazebank.Models;
    exports com.bank.maze.mazebank.Views;
}