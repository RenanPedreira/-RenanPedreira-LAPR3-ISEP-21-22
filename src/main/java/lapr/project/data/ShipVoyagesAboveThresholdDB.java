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
public class ShipVoyagesAboveThresholdDB {

    public List<List<String>> getShipVoyagesAboveThreshold(Double threshold) throws SQLException {
        DatabaseConnection dbc3 = null;
        try {
            dbc3 = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection connct3 = dbc3.getConnection();
        connct3.setAutoCommit(false);

        List<String> information3 = new ArrayList<>();
        List<List<String>> listString3 = new ArrayList<>();

        try (CallableStatement callStmt = connct3.prepareCall("{ ? = call  fnc_ship_voyages_above_threshold(?)}")) {
            try {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setDouble(2, threshold);
                callStmt.executeQuery();

                ResultSet rs = (ResultSet) callStmt.getObject(1);

                while (rs.next()) {                //FETCHES next row from cursor
                    String port = rs.getString(1);
                    String arrDate = rs.getString(2);
                    String depDate = rs.getString(3);

                    information3.add(port);
                    information3.add(arrDate);
                    information3.add(depDate);
                    listString3.add(new ArrayList<>(information3));
                    information3.clear();
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listString3;
    }

}