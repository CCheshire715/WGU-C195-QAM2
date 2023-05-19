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
import model.Contacts;
import model.Customers;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
/**This is the controller for the add appointments screen.*/
public class AddAppointmentsController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button addAppointmentCancelButton;

    @FXML
    private ComboBox<Contacts> addAppointmentContactComboBox;

    @FXML
    private ComboBox<Customers> addAppointmentCustomerIdComboBox;

    @FXML
    private TextField addAppointmentDescriptionTxt;

    @FXML
    private DatePicker addAppointmentEndDateBox;

    @FXML
    private ComboBox<LocalTime> addAppointmentEndTimeComboBox;

    @FXML
    private TextField addAppointmentIdTxt;

    @FXML
    private TextField addAppointmentLocationTxt;

    @FXML
    private Button addAppointmentSaveButton;

    @FXML
    private DatePicker addAppointmentStartDateBox;

    @FXML
    private ComboBox<LocalTime> addAppointmentStartTimeComboBox;

    @FXML
    private TextField addAppointmentTitleTxt;

    @FXML
    private TextField addAppointmentTypeTxt;

    @FXML
    private ComboBox<Users> addAppointmentUserIdComboBox;

    @FXML
    void onActionAddAppointmentContact(ActionEvent event) {

    }

    @FXML
    void onActionAddAppointmentCustomerId(ActionEvent event) {

    }

    @FXML
    void onActionAddAppointmentEndDate(ActionEvent event) {

    }

    @FXML
    void onActionAddAppointmentEndTime(ActionEvent event) {

    }

    @FXML
    void onActionAddAppointmentStartDate(ActionEvent event) {

    }

    @FXML
    void onActionAddAppointmentStartTime(ActionEvent event) {

    }

    @FXML
    void onActionAddAppointmentUserId(ActionEvent event) {

    }
    /**This is the method to cancel the addition of an appointment. Clicking the user is returned to the application screen.
     * @param event
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the method to save appointments. Clicking the save button saves all the appointment data. Error boxes are shown if the user doesn't fill out all the fields or is there are logical date/time errors.
     * @param event
     */
    @FXML
    void onActionSave(ActionEvent event) {
        try {
            String title = addAppointmentTitleTxt.getText();
            String description = addAppointmentDescriptionTxt.getText();
            String location = addAppointmentLocationTxt.getText();
            String type = addAppointmentTypeTxt.getText();

            LocalDateTime start = LocalDateTime.of(addAppointmentStartDateBox.getValue(), addAppointmentStartTimeComboBox.getSelectionModel().getSelectedItem());

            LocalDateTime end = LocalDateTime.of(addAppointmentEndDateBox.getValue(), addAppointmentEndTimeComboBox.getSelectionModel().getSelectedItem());

            int contact = 0;
            if (addAppointmentContactComboBox.getValue() != null) {
                contact = addAppointmentContactComboBox.getValue().getContactId();
            }

            int customerId = 0;
            if (addAppointmentCustomerIdComboBox.getValue() != null) {
                customerId = addAppointmentCustomerIdComboBox.getValue().getCustomerId();
            }

            int userId = 0;
            if (addAppointmentUserIdComboBox.getValue() != null) {
                userId = addAppointmentUserIdComboBox.getValue().getUserId();
            }

            if (title.isBlank() || description.isBlank() || location.isBlank() || type.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input error");
                alert.setContentText("All fields must be filled with valid data.");
                alert.showAndWait();
            } else if (addAppointmentStartDateBox.getValue().isBefore(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date error");
                alert.setContentText("Appointment cannot be made previous to the current date.");
                alert.showAndWait();
            } else if (addAppointmentStartDateBox.getValue().isAfter(addAppointmentEndDateBox.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date error");
                alert.setContentText("Appointment end date cannot be before the start date.");
                alert.showAndWait();
            } else if (addAppointmentStartDateBox.getValue().isBefore(addAppointmentEndDateBox.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date error");
                alert.setContentText("Appointment start and end must be on the same day.");
                alert.showAndWait();
            } else if (addAppointmentStartTimeComboBox.getSelectionModel().getSelectedItem().isAfter(addAppointmentEndTimeComboBox.getSelectionModel().getSelectedItem())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Time error");
                alert.setContentText("Appointment end time cannot before the start time.");
                alert.showAndWait();
            } else if (addAppointmentStartTimeComboBox.getSelectionModel().getSelectedItem().equals(addAppointmentEndTimeComboBox.getSelectionModel().getSelectedItem())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Time error");
                alert.setContentText("Appointment start and end time cannot have the same value.");
                alert.showAndWait();
            } else if (AppointmentsController.appointmentOverlap(customerId, 0, start, end) == true) {
                return;
            } else {
                AppointmentsDAO.addAppointment(title, description, location, type, start, end, customerId, userId, contact);

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
        } catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Input error");
            alert.setContentText("All fields must be filled with valid data.");
            alert.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**This is the method to set the combo boxes with the appropriate values.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addAppointmentContactComboBox.setItems(ContactsDAO.getAllContacts());
        addAppointmentContactComboBox.setVisibleRowCount(5);

        addAppointmentCustomerIdComboBox.setItems(CustomersDAO.getAllCustomers());
        addAppointmentCustomerIdComboBox.setVisibleRowCount(4);

        addAppointmentUserIdComboBox.setItems(UsersDAO.getAllUsers());
        addAppointmentUserIdComboBox.setVisibleRowCount(3);

        addAppointmentStartTimeComboBox.setItems(TimeZone.getStarTime());
        addAppointmentStartTimeComboBox.setVisibleRowCount(10);

        addAppointmentEndTimeComboBox.setItems(TimeZone.getEndTime());
        addAppointmentEndTimeComboBox.setVisibleRowCount(10);
    }
}
