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
public class ShipNumberDaysIdleDB {

    public List<List<String>> getShipNumberDaysIdle(String fleetManagerID) throws SQLException {
        DatabaseConnection dbc4 = null;
        try {
            dbc4 = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection connct4 = dbc4.getConnection();
        connct4.setAutoCommit(false);

        List<String> information4 = new ArrayList<>();
        List<List<String>> listString4 = new ArrayList<>();

        try (CallableStatement callStmt = connct4.prepareCall("{ ? = call fnc_number_days_ship_idle_begining_year(?)}")) {
            try {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, fleetManagerID);
                callStmt.executeQuery();

                ResultSet rs = (ResultSet) callStmt.getObject(1);

                while (rs.next()) {                //FETCHES next row from cursor
                    String id = rs.getString(1);
                    String days = rs.getString(2);

                    information4.add(id);
                    information4.add(String.valueOf(days));

                    listString4.add(new ArrayList<>(information4));
                    information4.clear();
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listString4;
    }
}