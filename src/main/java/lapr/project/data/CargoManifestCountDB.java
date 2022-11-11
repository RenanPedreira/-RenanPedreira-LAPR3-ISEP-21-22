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
public class CargoManifestCountDB {

    /**
     * Gets the number of cargo manifest carried by a ship and average container number per manifest
     *
     * @param shipMMSI The ship's identification
     * @param year Year to be analyzed
     * @return Number of cargo manifest carried by a ship and average container number per manifest
     * @throws SQLException
     */
    public List<String> getCountCargoManifest(String shipMMSI, int year) throws SQLException {
        try {
            DatabaseConnection databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            connection.setAutoCommit(false);

            List<String> infoCargoManifest = new ArrayList<>();

            try (CallableStatement callStmt = connection.prepareCall("{ ? = call fnc_count_cargo_manifest2(?, ?)}")) {
                try {
                    callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                    callStmt.setString(2, shipMMSI);
                    callStmt.setInt(3, year);
                    callStmt.executeQuery();

                    ResultSet rs = (ResultSet) callStmt.getObject(1);

                    while (rs.next()) {                //FETCHES next row from cursor
                        int nCargoManifest = rs.getInt(1);
                        double avgContainers = rs.getDouble(2);

                        infoCargoManifest.add(String.valueOf(nCargoManifest));
                        infoCargoManifest.add(String.valueOf(avgContainers));
                    }
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return infoCargoManifest;
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return new ArrayList<>();
        }
    }
}
