package lapr.project.data;

import lapr.project.utils.Utils;

import java.io.IOException;
import java.sql.*;

/**
 * @Author Renan Pedreira
 */
public class OccupancyGivenMomentDB {

    /**
     * Gets the occupancy rate of a ship in a given moment
     *
     * @param shipMMSI The ship
     * @param date The moment
     * @return Occupancy rate of a ship in a given moment
     * @throws SQLException
     */
    public Double getOccupancyRateMoment(String shipMMSI, Timestamp date) throws SQLException {
        try {
            DatabaseConnection databaseConnection3 = ConnectionFactory.getInstance().getDatabaseConnection();
            Connection connection3 = databaseConnection3.getConnection();
            connection3.setAutoCommit(false);

            double rate3 = 0.0;

            try (CallableStatement callStmt = connection3.prepareCall("{ ? = call fnc_ship_occupancy_rate_given_moment(?, ?)}")) {
                try {
                    callStmt.registerOutParameter(1, Types.DOUBLE);
                    callStmt.setString(2, shipMMSI);
                    callStmt.setTimestamp(3, date);
                    callStmt.executeQuery();

                    rate3 = callStmt.getDouble(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return rate3;
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return 0.0;
        }
    }
}
