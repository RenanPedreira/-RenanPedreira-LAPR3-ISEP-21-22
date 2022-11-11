package lapr.project.data;

import lapr.project.utils.Utils;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Renan Pedreira
 */
public class ShipsNextMondayDB {

    /**
     * Gets a list of available ships and their port location
     *
     * @return list of available ships and their port location
     * @throws SQLException
     */
    public List<List<String>> availableShipsNextMonday() throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection connection = databaseConnection.getConnection();
        connection.setAutoCommit(false);

        List<String> shipInfo = new ArrayList<>();
        List<List<String>> shipsList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT NEXT_DAY(SYSDATE,'Monday') \"Next_Monday\" FROM DUAL")){

            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp nextMonday = resultSet.getTimestamp(1);
                    Utils.print(nextMonday);

                    try (CallableStatement callStmt = connection.prepareCall("{ ? = call  fnc_available_ships_next_monday(?)}")) {
                        try {
                            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                            callStmt.setTimestamp(2, nextMonday);
                            callStmt.executeQuery();

                            ResultSet rs2 = (ResultSet) callStmt.getObject(1);

                            while (rs2.next()) {                //FETCHES next row from cursor
                                String shipID = rs2.getString(1);
                                String tripEndingPort = rs2.getString(2);
                                String tripID = rs2.getString(3);

                                shipInfo.add(shipID);
                                shipInfo.add(tripEndingPort);
                                shipInfo.add(tripID);

                                shipsList.add(new ArrayList<>(shipInfo));
                                shipInfo.clear();
                            }
                            rs2.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return shipsList;
    }
}