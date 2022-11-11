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
 * @author Renan Pedreira
 */
public class LeasedContainerRouteDB {

    public  List<List<String>> getContainerRoute(String clientID, String containerID) throws SQLException {
        DatabaseConnection dbConnection2 = null;
        try {
            dbConnection2 = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection dbConnection2Connection = dbConnection2.getConnection();
        dbConnection2Connection.setAutoCommit(false);

        List<List<String>> listDB = new ArrayList<>();


        try (CallableStatement callStmt = dbConnection2Connection.prepareCall("{ ? = call fnc_leased_container_route(?, ?)}")) {
            try {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, clientID);
                callStmt.setString(3, containerID);
                callStmt.executeQuery();

                ResultSet rs = (ResultSet) callStmt.getObject(1);

                while (rs.next()) {                //FETCHES next row from cursor
                    String location = rs.getString(3);
                    String type = rs.getString(4);
                    String arrivalDate = String.valueOf(rs.getDate(5));
                    String departureDate = String.valueOf(rs.getDate(6));

                    List<String> infoContainer = new ArrayList<>();

                    infoContainer.add(location);
                    infoContainer.add(type);
                    infoContainer.add(arrivalDate);
                    infoContainer.add(departureDate);

                    listDB.add(infoContainer);
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listDB;
    }
}
