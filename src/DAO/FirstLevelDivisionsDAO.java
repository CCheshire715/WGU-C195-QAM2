package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This class is where all the first level divisions SQL queries are created.*/
public class FirstLevelDivisionsDAO {

//    public static ObservableList<FirstLevelDivisions> getAllDivisions() {
//        ObservableList<FirstLevelDivisions> allDivisions = FXCollections.observableArrayList();
//
//        try {
//            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
//            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                int divisionId = rs.getInt("Division_ID");
//                String divisionName = rs.getString("Division");
//                int countryId = rs.getInt("Country_ID");
//
//                FirstLevelDivisions firstLevelDivisions = new FirstLevelDivisions(divisionId, divisionName, countryId);
//
//                allDivisions.add(firstLevelDivisions);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return allDivisions;
//    }
    /**This method is an observable list that gets the divisions by the country.
     * @param countryId
     * @return allDivisions
     */
    public static ObservableList<FirstLevelDivisions> getDivisionsByCountry(int countryId) {
        ObservableList<FirstLevelDivisions> allDivisions = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Country_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
//                int countryId = rs.getInt("Country_ID");

                FirstLevelDivisions firstLevelDivisions = new FirstLevelDivisions(divisionId, divisionName, countryId);

                allDivisions.add(firstLevelDivisions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allDivisions;
    }
    /**This is the method used to get the divisions by their ID.
     * @param divisionId
     * @return firstLevelDivisions
     */
    public static FirstLevelDivisions getDivisionsById(int divisionId) {

        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, divisionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
//                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");

                FirstLevelDivisions firstLevelDivisions = new FirstLevelDivisions(divisionId, divisionName, divisionId);

                return firstLevelDivisions;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
