package lapr.project.data;

import lapr.project.utils.Utils;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.*;

/**
 * @author Renan Pedreira
 */
public class AverageOccupancyGivenPeriodDB {
    public Double getAverageOccupancy(String ship, Timestamp beginningDate, Timestamp endingDate) throws SQLException {
            try {
                DatabaseConnection databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
                Connection connection = databaseConnection.getConnection();
                connection.setAutoCommit(false);

                Double rate=0.0;

                try (CallableStatement callStmt = connection.prepareCall("{ ? = call fnc_average_occupancy_period(?, ?, ?)}")) {
                    try {
                        callStmt.registerOutParameter(1, OracleTypes.DOUBLE);
                        callStmt.setString(2, ship);
                        callStmt.setTimestamp(3, beginningDate);
                        callStmt.setTimestamp(4, endingDate);
                        callStmt.executeQuery();

                        rate  = callStmt.getDouble(1);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return rate;
            } catch (IOException exception) {
                Utils.print("Connection failed");
                return null;
            }
    }
}
