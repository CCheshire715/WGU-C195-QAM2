package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This class is where all the countries SQL queries are created.*/
public class CountriesDAO {
    /**This method is an observable list that gets all countries.
     * @return allCountries
     */
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> allCountries = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM COUNTRIES";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                Countries countries = new Countries(countryId, countryName);

                allCountries.add(countries);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allCountries;
    }
    /**This method is used to get all countries by their division ID.
     * @param divisionID
     * @return countries
     */
    public static Countries getCountryByDivisionId(int divisionID) throws SQLException {
        String sql = "SELECT C.* FROM COUNTRIES AS C INNER JOIN FIRST_LEVEL_DIVISIONS AS D ON C.Country_ID = D.Country_ID AND D.Division_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String country = rs.getString("Country");

            Countries countries = new Countries(countryId, country);
            return countries;
        }

        return null;
    }
    /**This method is an observable list that gets the total appointments by country.
     * @return countryTotal
     */
    public static ObservableList<Countries> getCountryTotal() {
        ObservableList<Countries> countryTotal = FXCollections.observableArrayList();

        try {
            String sql = "SELECT countries.country, COUNT(country) as total FROM countries, first_level_divisions, appointments, customers WHERE countries.Country_ID = first_level_divisions.Country_ID " +
                    "AND first_level_divisions.Division_ID = customers.Division_ID AND customers.Customer_ID = appointments.Customer_ID GROUP BY country";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String country = rs.getString("Country");
                int total = rs.getInt("Total");

                Countries countries = new Countries(country, total);

                countryTotal.add(countries);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryTotal;
    }
}
