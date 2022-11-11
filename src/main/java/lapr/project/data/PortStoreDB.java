package lapr.project.data;

import lapr.project.model.Port;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class PortStoreDB {

    /**
     * Creates a Port in the database
     *
     * @param databaseConnection The database connection
     * @param object the port
     * @return true if no issues were found
     */
    public boolean save(DatabaseConnection databaseConnection, Object object) {

        Connection connection = databaseConnection.getConnection();
        Port port = (Port) object;

        String sqlCommand = "select * from Port where portID = ?";
        boolean returnValue = false;


        try (PreparedStatement getPortPreparedStatement = connection.prepareStatement(
                sqlCommand)) {
            getPortPreparedStatement.setInt(1, port.getIdentification());
            try (ResultSet addressesResultSet = getPortPreparedStatement.executeQuery()) {
                if (addressesResultSet.next()) {
                    sqlCommand = "update Port set portName = ?, countryID = ?, portLatitude = ?, portLongitude = ? where portID = ?";
                } else {
                    sqlCommand = "insert into Port(portName, countryID, portLatitude, portLongitude, portID, portManagerID, portDimensionX, portDimensionY, portDimensionZ) values (?, ?, ?, ?, ?, 'PortM1', 5, 5, 4)";
                }

                try (PreparedStatement savePortPreparedStatement = connection.prepareStatement(
                        sqlCommand)) {
                    savePortPreparedStatement.setString(1, port.getName());
                    savePortPreparedStatement.setString(2, port.getCountry());
                    savePortPreparedStatement.setDouble(3, port.getLatitude());
                    savePortPreparedStatement.setDouble(4, port.getLongitude());
                    savePortPreparedStatement.setString(5, String.valueOf(port.getIdentification()));
                    savePortPreparedStatement.executeUpdate();
                    returnValue = true;
                }
            }
        } catch (SQLException ex) {
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Get all the ports stored in the database
     *
     * @param databaseConnection The database connection
     * @return The list of ports stored in the database
     */
    public List<Port> getListPorts(DatabaseConnection databaseConnection) {
        Connection connection = databaseConnection.getConnection();

        List<Port> portList = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM Port")) {

            //For each port
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    String continentID="";
                    //Gets the port's continent using its country
                    try (PreparedStatement getContinent = connection.prepareStatement("SELECT ContinentID FROM Country WHERE CountryID = ?")) {
                        getContinent.setString(1, resultSet.getString("CountryID"));

                        try (ResultSet continent = getContinent.executeQuery()) {
                            while(continent.next())
                                continentID = continent.getString("ContinentID");
                        }

                        Integer portID = Integer.valueOf(resultSet.getString("PortID"));
                        String portName = resultSet.getString("PortName");
                        String portCountry = resultSet.getString("CountryID");
                        Double portLatitude = resultSet.getDouble("PortLatitude");
                        Double portLongitude = resultSet.getDouble("PortLongitude");

                        Port port = new Port(portID, portName, portCountry, portLatitude, portLongitude, continentID);


                        portList.add(port);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return portList;
    }

}