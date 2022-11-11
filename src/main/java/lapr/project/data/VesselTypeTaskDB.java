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

public class VesselTypeTaskDB {
    public List<List<String>> getVesselTypesForTask(String cargoManifest) throws SQLException {
        DatabaseConnection dbc2 = null;
        try {
            dbc2 = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection connct2 = dbc2.getConnection();
        connct2.setAutoCommit(false);

        List<String> information2 = new ArrayList<>();
        List<List<String>> listString2 = new ArrayList<>();

        try (CallableStatement callStmt = connct2.prepareCall("{ ? = call  fnc_center_of_mass(?)}")) {
            try {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, cargoManifest);
                callStmt.executeQuery();

                ResultSet rs = (ResultSet) callStmt.getObject(1);

                while (rs.next()) {                //FETCHES next row from cursor
                    String type = rs.getString(1);
                    double shipX = rs.getDouble(2);
                    double shipY = rs.getDouble(3);
                    double manifestX = rs.getDouble(4);
                    double manifestY = rs.getDouble(5);
                    double manifestZ = rs.getDouble(6);

                    information2.add(type);
                    information2.add(String.valueOf(shipX));
                    information2.add(String.valueOf(shipY));
                    information2.add("0.0");
                    information2.add(String.valueOf(manifestX));
                    information2.add(String.valueOf(manifestY));
                    information2.add(String.valueOf(manifestZ));

                    listString2.add(new ArrayList<>(information2));
                    information2.clear();
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listString2;
    }
}
