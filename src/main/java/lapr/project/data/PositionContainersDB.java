package lapr.project.data;

import lapr.project.utils.Pair;
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
 * @author Luís Araújo, Danilton Lopes and Renan Oliveira
 */
public class PositionContainersDB {
    public List<Pair<Pair<Double, Double>, Pair<Double, Double>>> getContainers(String mmsi, String cargoManifest) throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection connection = databaseConnection.getConnection();
        connection.setAutoCommit(false);

        List<Pair<Pair<Double, Double>, Pair<Double, Double>>> result = new ArrayList<>();

        try (CallableStatement callStmt = connection.prepareCall("{ ? = call  fnc_containers_on_ship(?, ?)}")) {
            try {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, mmsi);
                callStmt.setString(3, cargoManifest);
                callStmt.executeQuery();

                ResultSet rs = (ResultSet) callStmt.getObject(1);

                while (rs.next()) {                //FETCHES next row from cursor
                    String containerID = rs.getString(1);
                    double containerLength = rs.getDouble(2);
                    double containerWidth = rs.getDouble(3);
                    double shipLength = rs.getDouble(4);
                    double shipWidth = rs.getDouble(5);

                    result.add(new Pair<>(new Pair<>(shipLength, shipWidth), new Pair<>(containerLength, containerWidth)));
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
