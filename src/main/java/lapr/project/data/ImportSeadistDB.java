package lapr.project.data;

import lapr.project.utils.Pair;
import lapr.project.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportSeadistDB {

    /**
     * Creates a Seadist in the database
     *
     * @param databaseConnection database connection
     * @param line the information concerning the country
     * @return true if no issues were found
     */
    public boolean save(DatabaseConnection databaseConnection, String[] line) {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "select * from Seadist where fromPortID = ? and toPortID = ?";
        boolean returnValue = false;


        try (PreparedStatement getSeadistPreparedStatement = connection.prepareStatement(sqlCommand)) {

            getSeadistPreparedStatement.setString(1, line[1]);
            getSeadistPreparedStatement.setString(2, line[4]);

            try (ResultSet seadistResultSet = getSeadistPreparedStatement.executeQuery()) {
                if (seadistResultSet.next())
                    sqlCommand = "update Seadist set seadistDistance = ? where fromPortID = ? and toPortID = ?";
                else
                    sqlCommand = "insert into Seadist(seadistDistance, fromPortID, toPortID) values (?, ?, ?)";
            }

            try (PreparedStatement saveSeadistPreparedStatement = connection.prepareStatement(
                    sqlCommand)) {
                saveSeadistPreparedStatement.setDouble(1, Double.parseDouble(line[6]));
                saveSeadistPreparedStatement.setString(2, line[1]);
                saveSeadistPreparedStatement.setString(3, line[4]);

                saveSeadistPreparedStatement.executeUpdate();
                returnValue = true;
            }
        } catch (SQLException throwables) {
            Utils.print("Port not found!");
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Get Seadists from DB
     *
     * @param databaseConnection The database connection
     * @return list of seadists stored in the DB
     */
    public List<Pair<Pair<String, String>, Double>> getListSeadists(DatabaseConnection databaseConnection) {
        Connection connection = databaseConnection.getConnection();

        List<Pair<Pair<String, String>, Double>> seadistList = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM Seadist")) {

            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    String toPortID = resultSet.getString("toPortID");
                    String fromPortID = resultSet.getString("fromPortID");
                    Double dist = resultSet.getDouble("seadistDistance");

                    Pair<Pair<String, String>, Double> p = new Pair<>(new Pair<>(toPortID, fromPortID), dist);
                    seadistList.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seadistList;
    }
}
