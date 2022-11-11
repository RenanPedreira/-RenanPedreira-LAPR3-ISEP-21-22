package lapr.project.data;

import lapr.project.model.Country;
import lapr.project.utils.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportBorderDB {
    public boolean save(DatabaseConnection databaseConnection, String[] info) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from Border where countryID1 = ? and countryID2 = ?";
        boolean returnValue = false;


        try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(
                sqlCommand)) {

            getCountryPreparedStatement.setString(1, info[0]);
            getCountryPreparedStatement.setString(2, info[1].substring(1));

            try (ResultSet countryResultSet = getCountryPreparedStatement.executeQuery()) {
                if (!countryResultSet.next())
                    sqlCommand = "insert into Border(countryID1, countryID2) values (?, ?)";
                }

                //Continent,Alpha2-Code,Alpha3-Code,Country,Population,Capital,Latitude,Longitude
                try (PreparedStatement saveCountryPreparedStatement = connection.prepareStatement(
                        sqlCommand)) {
                    saveCountryPreparedStatement.setString(1, info[0]);
                    saveCountryPreparedStatement.setString(2, info[1].substring(1));

                    saveCountryPreparedStatement.executeUpdate();
                    returnValue = true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
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
    public List<Pair<String, String>> getListBorders(DatabaseConnection databaseConnection) {
        Connection connection = databaseConnection.getConnection();

        List<Pair<String, String>> borderList = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM Border")) {

            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    String countryID1 = resultSet.getString("countryID1");
                    String countryID2 = resultSet.getString("countryID2");

                   Pair<String, String> border = new Pair<>(countryID1, countryID2);

                    borderList.add(border);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borderList;
    }
}
