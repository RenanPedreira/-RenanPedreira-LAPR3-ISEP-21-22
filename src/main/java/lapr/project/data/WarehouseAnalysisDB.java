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

public class WarehouseAnalysisDB {
    public List<List<String>> getWarehouseAnalysis(String portID) throws SQLException {
        DatabaseConnection dbc = null;
        try {
            dbc = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection connct = dbc.getConnection();
        connct.setAutoCommit(false);

        List<String> information = new ArrayList<>();
        List<List<String>> listString = new ArrayList<>();

        try (CallableStatement callStmt = connct.prepareCall("{ ? = call  fnc_warehouse_analysis(?)}")) {
            try {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, portID);
                callStmt.executeQuery();

                ResultSet rs = (ResultSet) callStmt.getObject(1);

                while (rs.next()) {                //FETCHES next row from cursor
                    String warehouse = rs.getString(2);
                    double rate = rs.getDouble(3);
                    int number = rs.getInt(4);

                    information.add(String.valueOf(warehouse));
                    information.add(String.valueOf(rate));
                    information.add(String.valueOf(number));

                    listString.add(new ArrayList<>(information));
                    information.clear();
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listString;
    }
}
