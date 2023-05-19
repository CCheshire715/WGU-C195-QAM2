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
import model.FirstLevelDivisions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**This is the controller for the add customers screen.*/
public class AddCustomersController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField addCustomerAddressTxt;

    @FXML
    private Button addCustomerCancelButton;

    @FXML
    private ComboBox<Countries> addCustomerCountryComboBox;

    @FXML
    private TextField addCustomerIdTxt;

    @FXML
    private TextField addCustomerNameTxt;

    @FXML
    private TextField addCustomerPhoneNumberTxt;

    @FXML
    private TextField addCustomerPostalCodeTxt;

    @FXML
    private Button addCustomerSaveButton;

    @FXML
    private ComboBox<FirstLevelDivisions> addCustomerStateComboBox;
    /**This is the combo box to add a country to a customer. Filters divisions by selected country
     * @param event
     */
    @FXML
    void onActionAddCustomerCountry(ActionEvent event) throws SQLException {
        Countries country = addCustomerCountryComboBox.getValue();
        addCustomerStateComboBox.setItems(FirstLevelDivisionsDAO.getDivisionsByCountry(country.getCountryId()));
        addCustomerStateComboBox.setValue(null);
    }

    @FXML
    void onActionAddCustomerState(ActionEvent event) {

    }
    /**This is the cancel button if the user doesn't want to add a customer. Takes the user back to the customer screen
     * @param event
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This saves the customer. Throws error dialog boxes if all the fields aren't filled out
     * @param event
     */
    @FXML
    void onActionSave(ActionEvent event) {
        try {
            String name = addCustomerNameTxt.getText();
            String address = addCustomerAddressTxt.getText();
            Countries country = addCustomerCountryComboBox.getValue();
            FirstLevelDivisions division = addCustomerStateComboBox.getValue();
            String postalCode = addCustomerPostalCodeTxt.getText();
            String phone = addCustomerPhoneNumberTxt.getText();

            if (name.isBlank() || address.isBlank() || postalCode.isBlank() || phone.isBlank() ||
                    (addCustomerCountryComboBox.getSelectionModel().getSelectedItem() == null) ||
                    (addCustomerStateComboBox.getSelectionModel().getSelectedItem() == null)
                    ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input error");
                alert.setContentText("All fields must be filled out");
                alert.showAndWait();
            } else {
                int divisionId = division.getDivisionId();
                CustomersDAO.addCustomer(name, address, postalCode, phone, divisionId);

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/customers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**This is the method to set the country and divisions combo boxes to the with the appropriate values
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountryComboBox.setItems(CountriesDAO.getAllCountries());
        addCustomerStateComboBox.setVisibleRowCount(5);
    }
}
