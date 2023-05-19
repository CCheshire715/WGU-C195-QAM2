package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This class is where all the users SQL queries are created.*/
public class UsersDAO {
    /**This method is an observable list that gets all users from the database.
     * @return allUsers
     */
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> allUsers = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM USERS";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                Users users = new Users(userId, userName, password);

                allUsers.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }
    /**This method is used for the login validation. It takes the username and password and checks to make sure they are correct.
     * @param password
     * @param username
     * @return a value depending on whether the credentials match
     */
    public static int userValidation(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE user_name = '" + username + "' AND password = '" + password +"'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("User_Name").equals(username)) {
                    if (rs.getString("Password").equals(password)) {
                        return rs.getInt("User_ID");
                    }
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
    /**This method is used to select a user by their ID.
     * @param id
     * @return users
     */
    public static Users selectUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users where User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");

            Users users = new Users(userId, userName, password);

            return users;
        }
        return null;
    }
}
