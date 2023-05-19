package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
/**This is the class where all the appointment SQL queries are created.*/
public class AppointmentsDAO {
    /**This method is an observable list to get all appointments.
     * @return allAppointments
     */
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM APPOINTMENTS";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                LocalDateTime appointmentStart = start.toLocalDateTime();
                Timestamp end = rs.getTimestamp("End");
                LocalDateTime appointmentEnd = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments appointments = new Appointments(appointmentId, title, description, location, type, appointmentStart, appointmentEnd, customerId, userId, contactId);

                allAppointments.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAppointments;
    }
    /**This is a method to add appointments to the database.
     * @param type
     * @param userId
     * @param title
     * @param start
     * @param location
     * @param end
     * @param description
     * @param customerId
     * @param contactId
     */
    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.executeUpdate();
    }
    /**This is a method to update existing appointments.
     * @param appointmentId
     * @param contactId
     * @param customerId
     * @param description
     * @param end
     * @param location
     * @param start
     * @param title
     * @param type
     * @param userId
     */
    public static void updateAppointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, appointmentId);

        ps.executeUpdate();
    }
    /**This is a method to delete an appointment.
     * @param appointmentId
     */
    public static void deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentId);

        ps.executeUpdate();
    }
    /**This is a method that deletes an appointment. Uses the customer ID to delete the appointment associated with that ID.
     * @param customerId
     */
    public static void deleteAppointmentsByCustomerId(int customerId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerId);

        ps.executeUpdate();
    }
    /**This method is an observable list to get appointments by the current month.
     * @return appointmentsCurrentMonth
     */
    public static ObservableList<Appointments> getAppointmentCurrentMonth() throws SQLException {
        ObservableList<Appointments> appointmentsCurrentMonth = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(curdate())";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                LocalDateTime appointmentStart = start.toLocalDateTime();
                Timestamp end = rs.getTimestamp("End");
                LocalDateTime appointmentEnd = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments appointments = new Appointments(appointmentId, title, description, location, type, appointmentStart, appointmentEnd, customerId, userId, contactId);

                appointmentsCurrentMonth.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentsCurrentMonth;
    }
    /**This method is an observable list that gets all appointments by current week.
     * @return appointmentsCurrentWeek
     */
    public static ObservableList<Appointments> getAppointmentCurrentWeek() throws SQLException {
        ObservableList<Appointments> appointmentsCurrentWeek = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE WEEK(Start) = WEEK(curdate())";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                LocalDateTime appointmentStart = start.toLocalDateTime();
                Timestamp end = rs.getTimestamp("End");
                LocalDateTime appointmentEnd = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments appointments = new Appointments(appointmentId, title, description, location, type, appointmentStart, appointmentEnd, customerId, userId, contactId);

                appointmentsCurrentWeek.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentsCurrentWeek;
    }
    /**This method is an observable list that gets all appointments by the Customer ID.
     * @param customerID
     * @return appointmentsById
     */
    public static ObservableList<Appointments> getAppointmentsByCustomerId(int customerID) {
        ObservableList<Appointments> appointmentsById = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                LocalDateTime appointmentStart = start.toLocalDateTime();
                Timestamp end = rs.getTimestamp("End");
                LocalDateTime appointmentEnd = end.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments appointments = new Appointments(appointmentId, title, description, location, type, appointmentStart, appointmentEnd, customerId, userId, contactId);

                appointmentsById.add(appointments);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentsById;
    }
    /**This method is an observable list that gets appointments by thier month and type.
     * @return appointmentsMonthType
     */
    public static ObservableList<Appointments> getAppointmentsMonthType() {
        ObservableList<Appointments> appointmentsMonthType = FXCollections.observableArrayList();

        try {
            String sql = "SELECT monthname(start) AS Month, Type, COUNT(*) AS Total FROM appointments GROUP BY Type, monthname(start) order by Month";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String appointmentMonth = rs.getString("Month");
                String appointmentType = rs.getString("Type");
                int appointmentTotal = rs.getInt("Total");

                Appointments appointments = new Appointments(appointmentMonth, appointmentType, appointmentTotal);

                appointmentsMonthType.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentsMonthType;
    }
}
