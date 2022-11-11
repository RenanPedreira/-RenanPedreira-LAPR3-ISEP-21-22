package lapr.project.data;

import lapr.project.utils.Utils;

import java.io.IOException;
import java.sql.*;

/**
 * @Author Renan Pedreira
 */
public class OccupancyRateCargoDB {

    /**
     * Gets the occupancy rate of a ship considering the containers in a cargo manifest
     *
     * @param shipMMSI The ship to be analyzed
     * @param cargoManifestID The cargo manifest
     * @return The occupancy rate of a ship considering the containers in a cargo manifest
     * @throws SQLException
     */
    public Double getOccupancyRate(String shipMMSI, String cargoManifestID) throws SQLException {
        try {
            DatabaseConnection databaseConnection2 = ConnectionFactory.getInstance().getDatabaseConnection();

            Connection connection2 = databaseConnection2.getConnection();
            connection2.setAutoCommit(false);

            double rate2 = 0;

            try (CallableStatement callStmt = connection2.prepareCall("{ ? = call fnc_ship_occupancy_rate_for_cm(?, ?)}")) {
                try {
                    callStmt.registerOutParameter(1, Types.DOUBLE);
                    callStmt.setString(2, shipMMSI);
                    callStmt.setString(3, cargoManifestID);
                    callStmt.executeQuery();

                    rate2 = callStmt.getDouble(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return rate2;
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return 0.0;
        }

    }
}
