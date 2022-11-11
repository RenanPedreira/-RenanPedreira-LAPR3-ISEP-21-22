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
public class UnloadingCargoDB {

    /**
     * Gets the list of containers to be unloaded in the next port
     *
     * @param trip Current trip
     * @param currentPort Current port
     * @return List of containers to be unloaded in the next port
     * @throws SQLException
     */
    public List<List<String>> getNextPortOffload(String trip, String currentPort) throws SQLException {
        try {
            DatabaseConnection databaseConnection  = ConnectionFactory.getInstance().getDatabaseConnection();

            Connection connection = databaseConnection.getConnection();
            connection.setAutoCommit(false);

            List<String> containerInformation = new ArrayList<>();
            List<List<String>> list = new ArrayList<>();

            try (CallableStatement callStmt = connection.prepareCall("{ ? = call  fnc_next_port_offload(?, ?)}")) {
                try {
                    callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                    callStmt.setString(2, trip);
                    callStmt.setString(3, currentPort);
                    callStmt.executeQuery();

                    ResultSet unload = (ResultSet) callStmt.getObject(1);

                    while (unload.next()) {
                        String nextPort = unload.getString(1);
                        String containerID = unload.getString(2);
                        String containerType = unload.getString(3);
                        Double containerPayload = unload.getDouble(4);
                        int containerPositionX = unload.getInt(5);
                        int containerPositionY = unload.getInt(6);
                        int containerPositionZ = unload.getInt(7);

                        containerInformation.add(nextPort);
                        containerInformation.add(containerID);
                        containerInformation.add(containerType);
                        containerInformation.add(String.valueOf(containerPayload));
                        containerInformation.add(String.valueOf(containerPositionX));
                        containerInformation.add(String.valueOf(containerPositionY));
                        containerInformation.add(String.valueOf(containerPositionZ));

                        list.add(new ArrayList<String>(containerInformation));
                        containerInformation.clear();
                    }
                    unload.close();
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