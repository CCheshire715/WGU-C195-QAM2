package controller;

import DAO.AppointmentsDAO;
import DAO.UsersDAO;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
/**This is the controller for the login screen.
 * <p><b>
 *     Note: Lambda expression is used in the initialize method.
 * </b></p>
 */
public class LoginController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private Label login;

    @FXML
    private Button loginExitButton;

    @FXML
    private Button loginLoginButton;

    @FXML
    private TextField loginPasswordTxt;

    @FXML
    private Label loginTimezoneTxt;

    @FXML
    private TextField loginUsernameTxt;

    @FXML
    private Label password;

    @FXML
    private Label username;
    /**This is the exit button. Closes the database connection and exits the application.
     * @param event
     */
    @FXML
    void onActionExit(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    /**This is the Login button. This checks what the system language is along with the correct username and password combination. Throws error if the login is incorrect. This also throws a dialog box to warn of upcoming appointments.
     * @param event
     */
    @FXML
    void onActionLogin(ActionEvent event) throws IOException, SQLException {
        String fileName = "login_activity.txt", item;
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("Etc/UTC"));

        String username = loginUsernameTxt.getText();
        String password = loginPasswordTxt.getText();
        item = username;
        int userId = UsersDAO.userValidation(username, password);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language/lang", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            if (userId > 0) {
                printWriter.println("User " + item + " logged in successfully at " + zonedDateTime);
                printWriter.close();

                LocalDateTime localDateTime = LocalDateTime.now();
                LocalDateTime localDateTimePlus15 = localDateTime.plusMinutes(15);

                ObservableList<Appointments> upcomingAppointments = FXCollections.observableArrayList();

                ObservableList<Appointments> appointments = AppointmentsDAO.getAllAppointments();

                if (appointments != null) {
                    for (Appointments a : appointments) {
                        if (a.getAppointmentStart().isAfter(localDateTime) && a.getAppointmentStart().isBefore(localDateTimePlus15)) {
                            upcomingAppointments.add(a);

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Upcoming Appointments");
                            alert.setHeaderText("Upcoming Appointments");
                            alert.setContentText("Upcoming appointment within 15 minutes: Appointment ID #" + a.getAppointmentId() + " scheduled for " + a.getAppointmentStart());
                            alert.showAndWait();
                        }
                    }
                }
                if (upcomingAppointments.size() < 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Upcoming Appointments");
                    alert.setHeaderText("Upcoming Appointments");
                    alert.setContentText("No upcoming appointments within the next 15 minutes.");
                    alert.showAndWait();
                }

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else if (userId < 0){
                if (username.isBlank() || username.isEmpty()) {
                    printWriter.println("Login attempt failed with no username input at " + zonedDateTime);
                    printWriter.close();
                } else {
                    printWriter.println("User " + item + " attempted to log in but was unsuccessful at " + zonedDateTime);
                    printWriter.close();
                }

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("Error"));
                alert.setHeaderText(resourceBundle.getString("LoginError"));
                alert.setContentText(resourceBundle.getString("Credentials"));
                Button okButton = (Button)alert.getDialogPane().lookupButton(ButtonType.OK);
                okButton.setText(resourceBundle.getString("OK"));
                alert.showAndWait();
                loginUsernameTxt.clear();
                loginPasswordTxt.clear();
            }
        }
    }
    /**This is a lambda expression to set the Zone label on the login screen.
     */
    public interface ZoneInterface {
        String getMessage(ZoneId zone);
    }

    /**This is the initialize method. This sets the labels depending on what the system language is (either English or French). Lambda expression is also used here to set the Zone label.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneInterface zoneInterface = zone -> {
          String result = zone.toString();
          return result;
        };
        loginTimezoneTxt.setText(zoneInterface.getMessage(ZoneId.systemDefault()));

        resourceBundle = ResourceBundle.getBundle("language/lang", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            login.setText(resourceBundle.getString("Login"));
            username.setText(resourceBundle.getString("Username"));
            password.setText(resourceBundle.getString("Password"));
            loginExitButton.setText(resourceBundle.getString("Exit"));
            loginLoginButton.setText(resourceBundle.getString("Enter"));
        }
    }
}