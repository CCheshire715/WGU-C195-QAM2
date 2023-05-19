package controller;

import DAO.AppointmentsDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
/**This is the controller for the appointments screen.
 * <p><b>
 *     Note: Lambda expression is used in the "else if" section of onActionAppointmentsDeleteButton.
 * </b></p>
 */
public class AppointmentsController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button appointmentsAddButton;

    @FXML
    private RadioButton appointmentsByMonthRBtn;

    @FXML
    private RadioButton appointmentsByWeekRBtn;

    @FXML
    private TableColumn<Appointments, Integer> appointmentsContactIdCol;

    @FXML
    private TableColumn<Appointments, Integer> appointmentsCustomerIdCol;

    @FXML
    private Button appointmentsDeleteButton;

    @FXML
    private TableColumn<Appointments, String> appointmentsDescriptionCol;

    @FXML
    private TableColumn<Appointments, Calendar> appointmentsEndCol;

    @FXML
    private TableColumn<Appointments, Integer> appointmentsIdCol;

    @FXML
    private TableColumn<Appointments, String> appointmentsLocationCol;

    @FXML
    private Button appointmentsMenuButton;

    @FXML
    private TableColumn<Appointments, Calendar> appointmentsStartCol;

    @FXML
    private TableView<Appointments> appointmentsTableView;

    @FXML
    private TableColumn<Appointments, String> appointmentsTitleCol;

    @FXML
    private TableColumn<Appointments, String> appointmentsTypeCol;

    @FXML
    private Button appointmentsUpdateButton;

    @FXML
    private TableColumn<Appointments, Integer> appointmentsUserIdCol;

    @FXML
    private RadioButton appointmentsViewAllRBtn;

    @FXML
    private ToggleGroup appointmentsViewTG;
    /**This is the button to add appointments. Takes the user to the add appointments screen.
     * @param event
     */
    @FXML
    void onActionAppointmentsAddButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the current month filter radio button. Filters all appointments by current month.
     * @param event
     */
    @FXML
    void onActionAppointmentsByMonthRBtn(ActionEvent event) throws SQLException {
        appointmentsTableView.setItems(AppointmentsDAO.getAppointmentCurrentMonth());
        appointmentsIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentsTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentsLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentsContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appointmentsTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentsStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointmentsEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointmentsCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentsUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    /**This is the current week filter radio button. Filters all appointments by current week.
     * @param event
     */
    @FXML
    void onActionAppointmentsByWeekRBtn(ActionEvent event) throws SQLException {
        appointmentsTableView.setItems(AppointmentsDAO.getAppointmentCurrentWeek());
        appointmentsIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentsTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentsLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentsContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appointmentsTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentsStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointmentsEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointmentsCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentsUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    static ObservableList<Appointments> appointments;
    /**This is the delete button for appointments. Deletes the selected appointment.
     * Lambda expression is used to throw a confirmation dialog.
     * @param event
     */
    @FXML
    void onActionAppointmentsDeleteButton(ActionEvent event) throws SQLException {
        Appointments selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection error");
            alert.setContentText("Please select an appointment to delete.");
            alert.showAndWait();
        } else if (selectedAppointment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm delete");
            alert.setContentText("This will permanently delete this appointment. Continue?");

            Optional<ButtonType> result = alert.showAndWait();
            result.ifPresent(btnType -> {
                if (btnType == ButtonType.OK) {
                    int appointmentId = appointmentsTableView.getSelectionModel().getSelectedItem().getAppointmentId();
                    String appointmentType = appointmentsTableView.getSelectionModel().getSelectedItem().getAppointmentType();
                    try {
                        AppointmentsDAO.deleteAppointment(appointmentId);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Appointment cancelled");
                    alert1.setHeaderText("Appointment successfully cancelled.");
                    alert1.setContentText(appointmentType + " appointment with appointment ID #" + appointmentId + " has been cancelled.");
                    alert1.showAndWait();
                    appointments = AppointmentsDAO.getAllAppointments();
                    appointmentsTableView.setItems(appointments);
                    appointmentsTableView.refresh();
                }
            });
        }
    }
    /**This is the menu button. Takes the user back to the menu screen.
     * @param event
     */
    @FXML
    void onActionAppointmentsMenuButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the update button. Lets the user update a selected appointment and throws error if no appointment is selected.
     * @param event
     */
    @FXML
    void onActionAppointmentsUpdateButton(ActionEvent event) throws IOException {
        Appointments selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection error");
            alert.setContentText("Please select an appointment to update.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateAppointments.fxml"));
            loader.load();

            UpdateAppointmentsController UAController = loader.getController();
            try {
                UAController.sendAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**This is the method that checks for appointment overlaps. If there is an overlap, an error dialog is shown to let the user know of the overlap.
     * @param customerId
     * @param appointmentId
     * @param start
     * @param end
     */
    public static boolean appointmentOverlap(int customerId, int appointmentId, LocalDateTime start, LocalDateTime end) {
        ObservableList<Appointments> appointmentList = AppointmentsDAO.getAllAppointments();
        LocalDateTime appointmentStart;
        LocalDateTime appointmentEnd;

        for (Appointments a : appointmentList) {
            appointmentStart = a.getAppointmentStart();
            appointmentEnd = a.getAppointmentEnd();
            if (customerId != a.getCustomerId()) {
                continue;
            }
            if (appointmentId == a.getAppointmentId()) {
                continue;
            } else if ((start.isAfter(appointmentStart) || start.isEqual(appointmentStart)) && start.isBefore(appointmentEnd)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Appointment Overlap");
                alert.setContentText("Appointment start conflicts with customer ID#" + customerId + "'s already existing appointment. Please correct.");
                alert.showAndWait();
                return true;
            } else if (end.isAfter(appointmentStart) && (end.isBefore(appointmentEnd) || end.isEqual(appointmentEnd))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Appointment Overlap");
                alert.setContentText("Appointment end conflicts with customer ID#" + customerId + "'s already existing appointment. Please correct.");
                alert.showAndWait();
                return true;
            } else if ((start.isBefore(appointmentStart) || start.isEqual(appointmentStart)) && (end.isAfter(appointmentEnd) || end.isEqual(appointmentEnd))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Appointment Overlap");
                alert.setContentText("Appointment start and end conflicts with customer ID#" + customerId + "'s already existing appointment. Please correct.");
                alert.showAndWait();
                return true;
            }
        }
        return false;
    }
    /**This is the radio button to view all appointments.
     * @param event
     */
    @FXML
    void onActionAppointmentsViewAllRBtn(ActionEvent event) {
        appointmentsTableView.setItems(AppointmentsDAO.getAllAppointments());
        appointmentsIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentsTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentsLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentsContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appointmentsTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentsStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointmentsEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointmentsCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentsUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    /**This is the initialize method to populate the appointment table. Shows all appointments to the user.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsTableView.setItems(AppointmentsDAO.getAllAppointments());
        appointmentsIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentsTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentsLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentsContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appointmentsTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentsStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointmentsEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointmentsCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentsUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
}
