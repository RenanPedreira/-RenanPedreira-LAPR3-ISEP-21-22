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
 * @author Renan Pedreira
 */
public class LeasedContainerLocationDB {
    public List<String> getLeasedContainerCurrentLocation(String clientID, String containerID) throws SQLException {
        DatabaseConnection dbConnection3 = null;
        try {
            dbConnection3 = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection connection = dbConnection3.getConnection();
        connection.setAutoCommit(false);

        List<String> infoContainer = new ArrayList<>();

        try (CallableStatement callStmt = connection.prepareCall("{ ? = call fnc_leased_container_situation(?,?)}")) {
            try {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, clientID);
                callStmt.setString(3, containerID);
                callStmt.executeQuery();

                ResultSet rs = (ResultSet) callStmt.getObject(1);

                rs.next();          //FETCHES next row from cursor
                String id = rs.getString(1);
                String type = rs.getString(2);
                String name = rs.getString(3);

                infoContainer.add(id);
                infoContainer.add(type);
                infoContainer.add(name);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return infoContainer;
    }

}
