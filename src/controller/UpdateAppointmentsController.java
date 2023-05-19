package controller;

import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import DAO.CustomersDAO;
import DAO.UsersDAO;
import helper.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
/**This is the controller for the update appointments screen.*/
public class UpdateAppointmentsController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button updateAppointmentCancelButton;

    @FXML
    private ComboBox<Contacts> updateAppointmentContactComboBox;

    @FXML
    private ComboBox<Customers> updateAppointmentCustomerIdComboBox;

    @FXML
    private TextField updateAppointmentDescriptionTxt;

    @FXML
    private DatePicker updateAppointmentEndDateBox;

    @FXML
    private ComboBox<LocalTime> updateAppointmentEndTimeComboBox;

    @FXML
    private TextField updateAppointmentIdTxt;

    @FXML
    private TextField updateAppointmentLocationTxt;

    @FXML
    private Button updateAppointmentSaveButton;

    @FXML
    private DatePicker updateAppointmentStartDateBox;

    @FXML
    private ComboBox<LocalTime> updateAppointmentStartTimeComboBox;

    @FXML
    private TextField updateAppointmentTitleTxt;

    @FXML
    private TextField updateAppointmentTypeTxt;

    @FXML
    private ComboBox<Users> updateAppointmentUserIdComboBox;
    /**This is the cancel button. Cancels the updates if the user decides not to change the appointment and returns the user to the appointments screen.
     * @param event
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the save button. Saves any updated information and throws any necessary logic or blank fields errors.
     * @param event
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            int appointmentId = Integer.parseInt(updateAppointmentIdTxt.getText());
            String title = updateAppointmentTitleTxt.getText();
            String description = updateAppointmentDescriptionTxt.getText();
            String location = updateAppointmentLocationTxt.getText();
            String type = updateAppointmentTypeTxt.getText();

            LocalDateTime start = LocalDateTime.of(updateAppointmentStartDateBox.getValue(), updateAppointmentStartTimeComboBox.getSelectionModel().getSelectedItem());

            LocalDateTime end = LocalDateTime.of(updateAppointmentEndDateBox.getValue(), updateAppointmentEndTimeComboBox.getSelectionModel().getSelectedItem());

            int contact = updateAppointmentContactComboBox.getValue().getContactId();
            int customerId = updateAppointmentCustomerIdComboBox.getValue().getCustomerId();
            int userId = updateAppointmentUserIdComboBox.getValue().getUserId();

            if (title.isBlank() || description.isBlank() || location.isBlank() || (type.isBlank())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input error");
                alert.setContentText("All fields must be filled with valid data.");
                alert.showAndWait();
            } else if (updateAppointmentStartDateBox.getValue().isBefore(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date error");
                alert.setContentText("Appointment cannot be made previous to the current date.");
                alert.showAndWait();
            } else if (updateAppointmentStartDateBox.getValue().isAfter(updateAppointmentEndDateBox.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date error");
                alert.setContentText("Appointment end date cannot be before the start date.");
                alert.showAndWait();
            } else if (updateAppointmentStartDateBox.getValue().isBefore(updateAppointmentEndDateBox.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date error");
                alert.setContentText("Appointment start and end must be on the same day.");
                alert.showAndWait();
            } else if (updateAppointmentStartTimeComboBox.getSelectionModel().getSelectedItem().isAfter(updateAppointmentEndTimeComboBox.getSelectionModel().getSelectedItem())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Time error");
                alert.setContentText("Appointment end time cannot before the start time.");
                alert.showAndWait();
            } else if (updateAppointmentStartTimeComboBox.getSelectionModel().getSelectedItem().equals(updateAppointmentEndTimeComboBox.getSelectionModel().getSelectedItem())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Time error");
                alert.setContentText("Appointment start and end time cannot have the same value.");
                alert.showAndWait();
            } else if (AppointmentsController.appointmentOverlap(customerId, appointmentId, start, end) == true) {
                return;
            } else {
                AppointmentsDAO.updateAppointment(appointmentId, title, description, location, type, start, end, customerId, userId, contact);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Input error");
            alert.setContentText("All fields must be filled with valid data.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionUpdateAppointmentContact(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointmentCustomerId(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointmentEndDate(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointmentEndTime(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointmentStartDate(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointmentStartTime(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointmentUserId(ActionEvent event) {

    }
    /**This method populates the update appointment fields. This takes the information from the selected appointment and populates it into each respective field.
     * @param appointment
     */
    public void sendAppointment(Appointments appointment) throws SQLException {
        updateAppointmentIdTxt.setText(String.valueOf(appointment.getAppointmentId()));
        updateAppointmentTitleTxt.setText(appointment.getAppointmentTitle());
        updateAppointmentDescriptionTxt.setText(appointment.getAppointmentDescription());
        updateAppointmentLocationTxt.setText(appointment.getAppointmentLocation());
        updateAppointmentTypeTxt.setText(appointment.getAppointmentType());
        updateAppointmentContactComboBox.setValue(ContactsDAO.getContactById(appointment.getContactId()));
        updateAppointmentCustomerIdComboBox.setValue(CustomersDAO.selectCustomerById(appointment.getCustomerId()));
        updateAppointmentUserIdComboBox.setValue(UsersDAO.selectUserById(appointment.getUserId()));
        updateAppointmentStartTimeComboBox.setValue(appointment.getAppointmentStart().toLocalTime());
        updateAppointmentEndDateBox.setValue(LocalDate.from(appointment.getAppointmentEnd().toLocalDate()));
        updateAppointmentEndTimeComboBox.setValue(appointment.getAppointmentEnd().toLocalTime());
        updateAppointmentStartDateBox.setValue(LocalDate.from(appointment.getAppointmentStart().toLocalDate()));
    }

    /**This is the initialize method. This method fills the contact, customer, user, start time, and end time combo boxes with the appropriate information.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateAppointmentContactComboBox.setItems(ContactsDAO.getAllContacts());
        updateAppointmentContactComboBox.setVisibleRowCount(5);

        updateAppointmentCustomerIdComboBox.setItems(CustomersDAO.getAllCustomers());
        updateAppointmentCustomerIdComboBox.setVisibleRowCount(4);

        updateAppointmentUserIdComboBox.setItems(UsersDAO.getAllUsers());
        updateAppointmentUserIdComboBox.setVisibleRowCount(3);

        updateAppointmentStartTimeComboBox.setItems(TimeZone.getStarTime());
        updateAppointmentStartTimeComboBox.setVisibleRowCount(5);

        updateAppointmentEndTimeComboBox.setItems(TimeZone.getEndTime());
        updateAppointmentEndTimeComboBox.setVisibleRowCount(5);
    }
}
