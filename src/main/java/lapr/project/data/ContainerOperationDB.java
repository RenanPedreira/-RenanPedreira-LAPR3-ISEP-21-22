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
public class ContainerOperationDB {
    public List<List<String>> getContainerOperationList(String cargoManifestID, String containerID) throws SQLException {
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection c = dbConnection.getConnection();
        c.setAutoCommit(false);

        List<List<String>> listList = new ArrayList<>();

        try (CallableStatement callStmt = c.prepareCall("{ ? = call fnc_get_audit_trail(?,?)}")) {
            try {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, containerID);
                callStmt.setString(3, cargoManifestID);
                callStmt.executeQuery();

                ResultSet resultSets = (ResultSet) callStmt.getObject(1);

                while(resultSets.next()) {          //FETCHES next row from cursor
                    String id = resultSets.getString(2);
                    Timestamp date = resultSets.getTimestamp(3);
                    String type = resultSets.getString(4);
                    String contID = resultSets.getString(5);
                    String cmID = resultSets.getString(6);

                    List<String> infoContainer = new ArrayList<>();

                    infoContainer.add(id);
                    infoContainer.add(String.valueOf(date));
                    infoContainer.add(type);
                    infoContainer.add(contID);
                    infoContainer.add(cmID);

                    listList.add(infoContainer);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listList;
    }
}
