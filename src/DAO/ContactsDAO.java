package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This class is where all the contact SQL queries are created.*/
public class ContactsDAO {
    /**This method is an observable list that gets all contacts.
     * @return allContacts
     */
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM CONTACTS";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts contacts = new Contacts(contactId, contactName, email);

                allContacts.add(contacts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allContacts;
    }
    /**This method gets all contacts by their ID.
     * @param contactId
     * @return contacts
     */
    public static Contacts getContactById(int contactId) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contacts contacts = new Contacts(contactID, name, email);

            return contacts;
        }
        return null;
    }
}
