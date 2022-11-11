package lapr.project.data;

import lapr.project.utils.Utils;
import oracle.jdbc.internal.OracleTypes;

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
public class ContainerLocationDB {

    /**
     * Gets the current location of a container(ship or port)
     *
     * @param containerID Container to be found
     * @return Current location of a container(ship or port)
     * @throws SQLException
     */
    public List<String> getContainerCurrentPosition(String containerID) throws SQLException {
        try {
            DatabaseConnection databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();

            Connection connection = databaseConnection.getConnection();
            connection.setAutoCommit(false);

            List<String> infoContainer = new ArrayList<>();

            try (CallableStatement callStmt = connection.prepareCall("{ ? = call fnc_get_container_location(?)}")) {
                try {
                    callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                    callStmt.setString(2, containerID);
                    callStmt.executeQuery();

                    ResultSet rs = (ResultSet) callStmt.getObject(1);

                    while (rs.next()) {                //FETCHES next row from cursor
                        String type = rs.getString(2);
                        String name = rs.getString(3);

                        infoContainer.add(type);
                        infoContainer.add(name);
                    }
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return infoContainer;
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return new ArrayList<>();
        }
    }
}
