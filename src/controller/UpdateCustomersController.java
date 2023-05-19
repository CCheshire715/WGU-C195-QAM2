package controller;

import DAO.CountriesDAO;
import DAO.CustomersDAO;
import DAO.FirstLevelDivisionsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**This is the controller for the update customers screen.*/
public class UpdateCustomersController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField updateCustomerAddressTxt;

    @FXML
    private Button updateCustomerCancelButton;

    @FXML
    private ComboBox<Countries> updateCustomerCountryComboBox;

    @FXML
    private TextField updateCustomerIdTxt;

    @FXML
    private TextField updateCustomerNameTxt;

    @FXML
    private TextField updateCustomerPhoneNumberTxt;

    @FXML
    private TextField updateCustomerPostalCodeTxt;

    @FXML
    private Button updateCustomerSaveButton;

    @FXML
    private ComboBox<FirstLevelDivisions> updateCustomerStateComboBox;
    /**This is the cancel button. This cancels any updates and returns the user to the customers screen.
     * @param event
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This is the save button. This saves any changed values and throws errors if there are any logic or blank fields errors.
     * @param event
     */
    @FXML
    void onActionSave(ActionEvent event) {
        try {
            int id = Integer.parseInt(updateCustomerIdTxt.getText());
            String name = updateCustomerNameTxt.getText();
            String address = updateCustomerAddressTxt.getText();
            Countries country = updateCustomerCountryComboBox.getValue();
            FirstLevelDivisions division = updateCustomerStateComboBox.getValue();
            String postalCode = updateCustomerPostalCodeTxt.getText();
            String phone = updateCustomerPhoneNumberTxt.getText();

            if (name.isBlank() || address.isBlank() || (updateCustomerCountryComboBox.getSelectionModel().getSelectedItem() == null) || (updateCustomerStateComboBox.getSelectionModel().getSelectedItem() == null) ||
                    postalCode.isBlank() || phone.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input error");
                alert.setContentText("All fields must be filled out");
                alert.showAndWait();
            } else {
                int divisionId = division.getDivisionId();
                CustomersDAO.updateCustomer(id, name, address, postalCode, phone, divisionId);

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/customers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**This is the country combo box. Allows the user to select which country the customer is from
     * @param event
     */
    @FXML
    void onActionUpdateCustomerCountry(ActionEvent event) {
        Countries country = updateCustomerCountryComboBox.getValue();
        updateCustomerStateComboBox.setItems(FirstLevelDivisionsDAO.getDivisionsByCountry(country.getCountryId()));
        updateCustomerStateComboBox.setValue(null);
    }

    @FXML
    void onActionUpdateCustomerState(ActionEvent event) {

    }
    /**This method populates the update customer fields. This takes the information from the selected customer and populates it into each respective field.
     * @param customer
     */
    public void sendCustomer(Customers customer) throws SQLException {
        updateCustomerIdTxt.setText(String.valueOf(customer.getCustomerId()));
        updateCustomerNameTxt.setText(customer.getCustomerName());
        updateCustomerAddressTxt.setText(customer.getCustomerAddress());
        updateCustomerPostalCodeTxt.setText(customer.getCustomerPostalCode());
        updateCustomerPhoneNumberTxt.setText(customer.getCustomerPhoneNumber());
        Countries country = CountriesDAO.getCountryByDivisionId(customer.getDivisionId());
        updateCustomerCountryComboBox.setValue(country);
        updateCustomerStateComboBox.setItems(FirstLevelDivisionsDAO.getDivisionsByCountry(country.getCountryId()));
        updateCustomerStateComboBox.setValue(FirstLevelDivisionsDAO.getDivisionsById(customer.getDivisionId()));
    }
    /**This is the initialize method. This method fills the country and division combo boxes.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCustomerCountryComboBox.setItems(CountriesDAO.getAllCountries());
        updateCustomerStateComboBox.setVisibleRowCount(5);
    }
}
