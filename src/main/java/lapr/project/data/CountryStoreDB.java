package lapr.project.data;

import lapr.project.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryStoreDB {

    /**
     * Creates a Country in the database
     *
     * @param databaseConnection The database connection
     * @param info the information concerning the country
     * @return true if no issues were found
     */
    public boolean save(DatabaseConnection databaseConnection, String[] info) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from Country where countryID = ?";
        boolean returnValue = false;


        try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(
                sqlCommand)) {
            getCountryPreparedStatement.setString(1, info[3]);
            try (ResultSet countryResultSet = getCountryPreparedStatement.executeQuery()) {
                if (countryResultSet.next()) {
                    sqlCommand = "update Country set countryAlpha2Code = ?, countryAlpha3Code = ?, countryCapital = ?, continentID = ?, " +
                                 "countryPopulation = ?, countryLatitude = ?, countryLongitude = ?  where countryID = ?";
                } else {
                    sqlCommand = "insert into Country(countryAlpha2Code, countryAlpha3Code, countryCapital, continentID, " +
                                 "countryPopulation, countryLatitude, countryLongitude, countryID) values (?, ?, ?, ?, ?, ?, ?, ?)";
                }

                //Continent,Alpha2-Code,Alpha3-Code,Country,Population,Capital,Latitude,Longitude
                try (PreparedStatement saveCountryPreparedStatement = connection.prepareStatement(
                        sqlCommand)) {
                    saveCountryPreparedStatement.setString(1, info[1]);
                    saveCountryPreparedStatement.setString(2, info[2]);
                    saveCountryPreparedStatement.setString(3, info[5]);
                    saveCountryPreparedStatement.setString(4, info[0]);
                    saveCountryPreparedStatement.setDouble(5, Double.parseDouble(info[4]));
                    saveCountryPreparedStatement.setDouble(6, Double.parseDouble(info[6]));
                    saveCountryPreparedStatement.setDouble(7, Double.parseDouble(info[7]));
                    saveCountryPreparedStatement.setString(8, info[3]);

                    saveCountryPreparedStatement.executeUpdate();
                    returnValue = true;
                }
            }
        } catch (SQLException ex) {
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Get all the countries stored in the database
     *
     * @param databaseConnection The database connection
     * @return The list of countries stored in the database
     */
    public List<Country> getListCountries(DatabaseConnection databaseConnection) {
        Connection connection = databaseConnection.getConnection();

        List<Country> countryList = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM Country")) {

            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    String countryID = resultSet.getString("countryID");
                    String alpha2Code = resultSet.getString("countryAlpha2Code");
                    String alpha3Code = resultSet.getString("countryAlpha3Code");
                    String capital = resultSet.getString("countryCapital");
                    String continent = resultSet.getString("continentID");
                    Double population = resultSet.getDouble("countryPopulation");
                    Double latitude = resultSet.getDouble("countryLatitude");
                    Double longitude = resultSet.getDouble("countryLongitude");

                    Country country = new Country(countryID, alpha2Code, alpha3Code, capital, population, latitude, longitude, continent);


                    countryList.add(country);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryList;
    }
}
