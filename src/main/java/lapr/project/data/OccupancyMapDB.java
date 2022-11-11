package lapr.project.data;

import lapr.project.utils.Utils;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class OccupancyMapDB {
    public List<List<String>>  getOccupancyMap(String portID, String date) throws SQLException {
        DatabaseConnection dbc5 = null;
        try {
            dbc5 = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection connct5 = dbc5.getConnection();
        connct5.setAutoCommit(false);

        List<String> information5 = new ArrayList<>();
        List<List<String>> listString5 = new ArrayList<>();
        try (CallableStatement callStmt = connct5.prepareCall("{ ? = call  fnc_occupancy_rate_for_month(?, ?)}")) {
            try {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, portID);
                callStmt.setString(3, date);
                callStmt.executeQuery();

                ResultSet rs = (ResultSet) callStmt.getObject(1);

                while (rs.next()) {                //FETCHES next row from cursor
                    String day = rs.getString(1);
                    String warehouseRate = rs.getString(2);
                    String portRate = rs.getString(3);

                    information5.add(day);
                    information5.add(warehouseRate);
                    information5.add(portRate);

                    listString5.add(new ArrayList<>(information5));
                    information5.clear();
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listString5;
    }
}
