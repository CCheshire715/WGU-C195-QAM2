package controller;

import DAO.AppointmentsDAO;
import DAO.CustomersDAO;
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
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
/**This is the controller for the customers screen.*/
public class CustomersController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button customersAddButton;

    @FXML
    private TableColumn<Customers, String> customersAddressCol;

    @FXML
    private TableColumn<Customers, String> customersCountryCol;

    @FXML
    private Button customersDeleteButton;

    @FXML
    private TableColumn<Customers, Integer> customersIdCol;

    @FXML
    private Button customersMenuButton;

    @FXML
    private TableColumn<Customers, String> customersNameCol;

    @FXML
    private TableColumn<Customers, String> customersPhoneNumberCol;

    @FXML
    private TableColumn<Customers, String> customersPostalCodeCol;

    @FXML
    private TableColumn<Customers, Integer> customersDivisionCol;

    @FXML
    private TableView<Customers> customersTableView;

    @FXML
    private Button customersUpdateButton;
    /**This is the button to add customers. Clicking add will take the user to the add customer screen.
     * @param event
     */
    @FXML
    void onActionCustomersAddButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addCustomers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    static ObservableList<Customers> customers;
    /**This method gets all appointments by customer ID.
     * @param selectedCustomer
     * @return true or false
     */
    private boolean checkAppointments(Customers selectedCustomer) {
        ObservableList<Appointments> appointments = AppointmentsDAO.getAppointmentsByCustomerId(selectedCustomer.getCustomerId());
        if (appointments !=null && appointments.size() < 1) {
            return true;
        } else {
            return false;
        }
    }
    /**This is the button to delete a customer. This also checks to see if the selected customer has any appointments and throws dialog boxes confirming the customer and associated appointments will be deleted.
     * @param event
     */
    @FXML
    void onActionCustomersDeleteButton(ActionEvent event) throws SQLException {
        Customers selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection error");
            alert.setContentText("Please select a customer to delete.");
            alert.showAndWait();
        } else if (customersTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete this customer. Continue?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    boolean valid = checkAppointments(selectedCustomer);
                    if (valid) {
                        int customerId = customersTableView.getSelectionModel().getSelectedItem().getCustomerId();
                        String customerName = customersTableView.getSelectionModel().getSelectedItem().getCustomerName();
                        CustomersDAO.deleteCustomer(customerId);
                        alert = new Alert(Alert.AlertType.INFORMATION, customerName + ", ID #" + customerId + ", successfully deleted.");
                        alert.showAndWait();
                        customers = CustomersDAO.getAllCustomers();
                        customersTableView.setItems(customers);
                        customersTableView.refresh();
                    } else {
                        int customerId = customersTableView.getSelectionModel().getSelectedItem().getCustomerId();
                        String customerName = customersTableView.getSelectionModel().getSelectedItem().getCustomerName();
                        alert = new Alert(Alert.AlertType.CONFIRMATION, "All appointments associated with " + customerName + " will be deleted as well. Continue?");
                        Optional<ButtonType> result1 = alert.showAndWait();

                        if (result1.isPresent() && result1.get() == ButtonType.OK) {
                            AppointmentsDAO.deleteAppointmentsByCustomerId(customerId);
                            CustomersDAO.deleteCustomer(customerId);
                            alert = new Alert(Alert.AlertType.INFORMATION, customerName + ", ID #" + customerId + ", successfully deleted.");
                            alert.showAndWait();
                            customers = CustomersDAO.getAllCustomers();
                            customersTableView.setItems(customers);
                            customersTableView.refresh();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**This is the menu button. This will take the user back to the main menu.
     * @param event
     */
    @FXML
    void onActionCustomersMenuButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the button to update a selected customer. Takes the user to the update customer screen. If no customer is selected, this will throw and error.
     * @param event
     */
    @FXML
    void onActionCustomersUpdateButton(ActionEvent event) throws IOException {
        if (customersTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection error");
            alert.setContentText("Please select a customer to modify.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateCustomers.fxml"));
            loader.load();

            UpdateCustomersController UCController = loader.getController();
            try {
                UCController.sendCustomer(customersTableView.getSelectionModel().getSelectedItem());

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**This is the initialize method to fill the customers table
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customersTableView.setItems(CustomersDAO.getAllCustomers());
        customersIdCol.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        customersNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customersAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customersDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        customersCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        customersPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customersPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
    }
}
