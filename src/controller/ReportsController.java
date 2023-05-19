package controller;

import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import DAO.CountriesDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Countries;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
/**This is the controller for the reports screen.
 * <p><b>
 *     Note: Lambda expression is used in nActionReportContact to filter appointments by contact.
 * </b></p>*/
public class ReportsController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<Appointments, String> reportsAppointmentMonthCol;

    @FXML
    private TableColumn<Appointments, String> reportsAppointmentTypeCol;

    @FXML
    private TableView<Appointments> reportsAppointmentTypeTableView;

    @FXML
    private TableColumn<Appointments, Integer> reportsAppointmentTypeTotalCol;

    @FXML
    private ComboBox<Contacts> reportsContactComboBox;

    @FXML
    private TableColumn<Appointments, String> reportsCountryCol;

    @FXML
    private TableView<Countries> reportsCountryTableView;

    @FXML
    private TableColumn<Appointments, Integer> reportsCountryTotalCol;

    @FXML
    private TableColumn<Appointments, Integer> reportsCustomerIdCol;

    @FXML
    private TableColumn<Appointments, String> reportsDescriptionCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> reportsEndCol;

    @FXML
    private TableColumn<Appointments, Integer> reportsIdCol;

    @FXML
    private Button reportsMainMenuButton;

    @FXML
    private TableColumn<Appointments, LocalDateTime> reportsStartCol;

    @FXML
    private TableView<Appointments> reportsTableView;

    @FXML
    private TableColumn<Appointments, String> reportsTitleCol;

    @FXML
    private TableColumn<Appointments, String> reportsTypeCol;
    /**This is a combo box where the user can filter appointments by contact. Lambda expression is used here to filter the appointments.
     * @param event
     */
    @FXML
    void onActionReportContact(ActionEvent event) {
        Contacts contacts = reportsContactComboBox.getValue();
        if (contacts == null) {
            return;
        }
        ObservableList<Appointments> appointmentList = AppointmentsDAO.getAllAppointments();
        ObservableList<Appointments> contactList = appointmentList.filtered(a -> {
            if (a.getContactId() == contacts.getContactId()) {
                return true;
            }
            return false;
        });
        reportsTableView.setItems(contactList);
    }
    /**This is the main menu button. Takes the user back to the main menu
     * @param event
     */
    @FXML
    void onActionReportsMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the initialize method. This method pre-populates the reports table view, reportsCountry table view, and the reportsAppointmentType table view. It also fills the contact combo box with the appropriate values.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportsContactComboBox.setItems(ContactsDAO.getAllContacts());

        reportsTableView.setItems(AppointmentsDAO.getAllAppointments());
        reportsIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        reportsTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        reportsTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        reportsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        reportsStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        reportsEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        reportsCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        reportsCountryTableView.setItems(CountriesDAO.getCountryTotal());
        reportsCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        reportsCountryTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        reportsAppointmentTypeTableView.setItems(AppointmentsDAO.getAppointmentsMonthType());
        reportsAppointmentMonthCol.setCellValueFactory(new PropertyValueFactory<>("appointmentMonth"));
        reportsAppointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        reportsAppointmentTypeTotalCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTotal"));
    }
}
