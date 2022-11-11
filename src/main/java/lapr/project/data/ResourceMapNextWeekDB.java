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
public class ResourceMapNextWeekDB {


    public List<List<String>> getResourceMapNextWeek(String id) throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        Connection connection = databaseConnection.getConnection();
        connection.setAutoCommit(false);

        List<String> info = new ArrayList<>();
        List<List<String>> list = new ArrayList<>();

        //get next monday
        try (PreparedStatement ps = connection.prepareStatement("SELECT NEXT_DAY(SYSDATE,'Monday') \"Next_Monday\" FROM DUAL")){

            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp nextMonday = resultSet.getTimestamp(1);

                    try (CallableStatement callStmt = connection.prepareCall("{ ? = call  fnc_resource_map_next_week(?,?)}")) {
                        try {
                            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                            callStmt.setString(2, id);
                            callStmt.setTimestamp(3, nextMonday);
                            callStmt.executeQuery();

                            ResultSet rs2 = (ResultSet) callStmt.getObject(1);

                            while (rs2.next()) {                //FETCHES next row from cursor
                                String container =  rs2.getString(1);
                                String date =  rs2.getString(2);
                                String port =  rs2.getString(3);
                                String warehouse =  rs2.getString(4);
                                String cm =  rs2.getString(5);
                                String cmtype =  rs2.getString(6);
                                String transport =  rs2.getString(7);
                                String count =  rs2.getString(8);
                                String x =  rs2.getString(9);
                                String y =  rs2.getString(10);
                                String z =  rs2.getString(11);

                                info.add(container);
                                info.add(date);
                                info.add(port);
                                info.add(warehouse);
                                info.add(cm);
                                info.add(cmtype);
                                info.add(transport);
                                info.add(count);
                                info.add(x);
                                info.add(y);
                                info.add(z);

                                list.add(new ArrayList<String>(info));
                                info.clear();
                            }
                            rs2.close();
                        } catch (SQLException e) {
                            Utils.print("No container operations found for next week");
                        }
                    }
                }
            }
        }
        return list;
    }

}