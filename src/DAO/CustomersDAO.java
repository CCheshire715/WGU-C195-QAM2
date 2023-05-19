package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This class is where all the customer SQL queries are created.*/
public class CustomersDAO {
    /**This method is an observable list that gets all the customers.
     * @return allCustomers
     */
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, first_level_divisions.Division, first_level_divisions.Division_Id, countries.country from customers, first_level_divisions, countries\n" +
                    "WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phoneNumber = rs.getString("Phone");
                String division = rs.getString("Division");
                int divisionId = rs.getInt("Division_Id");
                String country = rs.getString("Country");

                Customers customers = new Customers(customerId, customerName, address, postalCode, phoneNumber, divisionId, division, country);

                allCustomers.add(customers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allCustomers;
    }
    /**This is the method that adds a customer to the database.
     * @param customerAddress
     * @param customerName
     * @param customerPhone
     * @param customerPostalCode
     * @param divisionId
     */
    public static void addCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, int divisionId) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, customerPostalCode);
        ps.setString(4, customerPhone);
        ps.setInt(5, divisionId);

        ps.executeUpdate();
    }
    /**This is the method that updates and existing customer.
     * @param customerPostalCode
     * @param divisionId
     * @param customerPhone
     * @param customerName
     * @param customerAddress
     * @param customerId */
    public static void updateCustomer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int divisionId) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, customerPostalCode);
        ps.setString(4, customerPhone);
        ps.setInt(5, divisionId);
        ps.setInt(6, customerId);

        ps.executeUpdate();
    }
    /**This is the method used to delete a customer.
     * @param customerId
     */
    public static void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerId);

        ps.executeUpdate();
    }

//    public static void selectCustomer() throws SQLException {
//        String sql = "SELECT * FROM CUSTOMERS";
//        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            int customerId = rs.getInt("Customer_ID");
//            String customerName = rs.getString("Customer_Name");
//            String customerAddress = rs.getString("Address");
//            String customerPostalCode = rs.getString("Postal_Code");
//            String customerPhone = rs.getString("Phone");
//            int divisionId = rs.getInt("Division_ID");
//        }
//    }
    /**This method is used to get a customer by their ID.
     * @param id
     * @return new Customer
     */
    public static Customers selectCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
           int customerId = rs.getInt("Customer_ID");
           String customerName = rs.getString("Customer_Name");
           String customerAddress = rs.getString("Address");
           String customerPostalCode = rs.getString("Postal_Code");
           String customerPhone = rs.getString("Phone");
           int divisionId = rs.getInt("Division_ID");

           return new Customers(customerId, customerName, customerAddress, customerPostalCode, customerPhone, divisionId);
        }
        return null;
    }
}
