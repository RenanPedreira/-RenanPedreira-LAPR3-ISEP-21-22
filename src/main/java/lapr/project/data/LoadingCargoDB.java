package lapr.project.data;

import lapr.project.utils.Utils;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Renan Pedreira
 */
public class LoadingCargoDB {

    /**
     * Gets the list of containers to be loaded in the next port
     *
     * @param trip Current trip
     * @param currentPort Current port
     * @return List of containers to be loaded in the next port
     * @throws SQLException
     */
    public List<List<String>> getNextPortLoad(String trip, String currentPort) throws SQLException {
        try {
            DatabaseConnection databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();

            Connection connection = databaseConnection.getConnection();
            connection.setAutoCommit(false);

            List<String> infoContainer = new ArrayList<>();
            List<List<String>> list = new ArrayList<>();

            try (CallableStatement callStmt = connection.prepareCall("{ ? = call  fnc_next_port_load(?, ?)}")) {
                try {
                    callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                    callStmt.setString(2, trip);
                    callStmt.setString(3, currentPort);
                    callStmt.executeQuery();

                    ResultSet loadResult = (ResultSet) callStmt.getObject(1);

                    while (loadResult.next()) {                //FETCHES next row from cursor
                        String nextPort = loadResult.getString(1);
                        String id = loadResult.getString(2);
                        String type = loadResult.getString(3);
                        Double payload = loadResult.getDouble(4);
                        int x = loadResult.getInt(5);
                        int y = loadResult.getInt(6);
                        int z = loadResult.getInt(7);

                        infoContainer.add(nextPort);
                        infoContainer.add(id);
                        infoContainer.add(type);
                        infoContainer.add(String.valueOf(payload));
                        infoContainer.add(String.valueOf(x));
                        infoContainer.add(String.valueOf(y));
                        infoContainer.add(String.valueOf(z));

                        list.add(new ArrayList<>(infoContainer));
                        infoContainer.clear();
                    }
                    loadResult.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return list;
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return new ArrayList<>();
        }

    }
}