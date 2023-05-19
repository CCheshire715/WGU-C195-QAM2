package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**This is the controller for the main menu screen.*/
public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button mainMenuLogOut;

    @FXML
    private Button reportsButton;
    /**This is the button to take the user to the appointments screen.
     * @param event
     */
    @FXML
    void onActionAppointmentsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the button to take the user to the customers screen.
     * @param event
     */
    @FXML
    void onActionCustomersButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the button that logs the user out of the application. Takes the user back to the login screen.
     * @param event
     */
    @FXML
    void onActionLogOut(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the button to take the user to the report menu.
     * @param event
     */
    @FXML
    void onActionReportsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the initialize method. Unused.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
