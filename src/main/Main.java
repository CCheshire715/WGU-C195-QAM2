package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
/**This is the main class. This will start the application.*/
public class Main extends Application {
    /**This method starts the application.*/
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        stage.setScene(new Scene(root, 500, 350));
        stage.show();
    }
    /**This is the main method. It starts the program and establishes a connection to the database.*/
    public static void main(String[] args) throws SQLException {
//        Locale.setDefault(new Locale("fr"));

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
