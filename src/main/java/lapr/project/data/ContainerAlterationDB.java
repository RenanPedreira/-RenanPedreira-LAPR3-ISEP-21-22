package lapr.project.data;

import lapr.project.utils.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Renan Pedreira
 */
public class ContainerAlterationDB {
    private final static String message1 = "Connection failed";
    private final static String message2 = "update ContainerOperation set userID = ?  where cargoManifestID = ? and containerID = ? and operationDateTime = (SELECT MAX(operationDateTime) FROM ContainerOperation WHERE cargoManifestID = ? and containerID = ?)";

    //Create
    public boolean alterContainerInformationCreate(String cargoManifestID, String containerID, Integer ship, Integer x, Integer y, Integer z, String user) throws SQLException {
        boolean returnValue = false;

        DatabaseConnection dbConnection = null;
        try {
            dbConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print(message1);
        }

        Connection c = dbConnection.getConnection();
        c.setAutoCommit(true);

        String sqlCommand = "INSERT INTO ShipCargoManifest(cargoManifestID, containerID, containerPositionX, containerPositionY, containerPositionZ, shipMMSI) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement saveShipCargoManifestPreparedStatement = c.prepareStatement(
                sqlCommand)) {
            saveShipCargoManifestPreparedStatement.setString(1, cargoManifestID);
            saveShipCargoManifestPreparedStatement.setString(2, containerID);
            saveShipCargoManifestPreparedStatement.setInt(3, x);
            saveShipCargoManifestPreparedStatement.setInt(4, y);
            saveShipCargoManifestPreparedStatement.setInt(5, z);
            saveShipCargoManifestPreparedStatement.setInt(6, ship);

            saveShipCargoManifestPreparedStatement.executeUpdate();
            returnValue = true;
        } catch (SQLException ex) {
            dbConnection.registerError(ex);
            returnValue = false;
        }

        sqlCommand = message2;
        try (PreparedStatement savePreparedStatement2 = c.prepareStatement(
                sqlCommand)) {
            savePreparedStatement2.setString(1, user);
            savePreparedStatement2.setString(2, cargoManifestID);
            savePreparedStatement2.setString(3, containerID);
            savePreparedStatement2.setString(4, cargoManifestID);
            savePreparedStatement2.setString(5, containerID);
            savePreparedStatement2.executeUpdate();
            returnValue = true;
        }

        return returnValue;
    }


    //Update
    public boolean alterContainerInformationUpdate(String cargoManifestID, String containerID, Integer x, Integer y, Integer z, String user) throws SQLException {
        boolean returnValue = false;

        DatabaseConnection dbConnection = null;
        try {
            dbConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print(message1);
        }

        Connection c = dbConnection.getConnection();
        c.setAutoCommit(true);


        String sqlCommand = "update ShipCargoManifest set containerPositionX = ?, containerPositionY = ?, containerPositionZ = ? where cargoManifestID = ? and containerID = ?";

        try (PreparedStatement savePreparedStatement3 = c.prepareStatement(
                sqlCommand)) {
            savePreparedStatement3.setInt(1, x);
            savePreparedStatement3.setInt(2, y);
            savePreparedStatement3.setInt(3, z);
            savePreparedStatement3.setString(4, cargoManifestID);
            savePreparedStatement3.setString(5, containerID);
            savePreparedStatement3.executeUpdate();
            returnValue = true;
        }

        sqlCommand = "update ContainerOperation set userID = ?  where cargoManifestID = ? and containerID = ? and operationDateTime = (SELECT MAX(operationDateTime) FROM ContainerOperation WHERE cargoManifestID = ? and containerID = ?)";

        try (PreparedStatement savePreparedStatement = c.prepareStatement(
                sqlCommand)) {
            savePreparedStatement.setString(1, user);
            savePreparedStatement.setString(2, cargoManifestID);
            savePreparedStatement.setString(3, containerID);
            savePreparedStatement.setString(4, cargoManifestID);
            savePreparedStatement.setString(5, containerID);
            savePreparedStatement.executeUpdate();
            returnValue = true;
        }


        return returnValue;
    }


    // Delete
    public boolean alterContainerInformationDelete(String cargoManifestID, String containerID, String user) throws SQLException {
        boolean returnValue = false;

        DatabaseConnection dbConnection = null;
        try {
            dbConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print(message1);
        }

        Connection c = dbConnection.getConnection();
        c.setAutoCommit(true);


        String sqlCommand = "delete from ShipCargoManifest where cargoManifestID = ? and containerID = ?";

        try (PreparedStatement savePreparedStatement = c.prepareStatement(
                sqlCommand)) {
            savePreparedStatement.setString(1, cargoManifestID);
            savePreparedStatement.setString(2, containerID);
            savePreparedStatement.executeUpdate();
            returnValue = true;
        }

        sqlCommand = "update ContainerOperation set userID = ?  where cargoManifestID = ? and containerID = ? and operationDateTime = (SELECT MAX(operationDateTime) FROM ContainerOperation WHERE cargoManifestID = ? and containerID = ?)";

        try (PreparedStatement savePreparedStatement4 = c.prepareStatement(
                sqlCommand)) {
            savePreparedStatement4.setString(1, user);
            savePreparedStatement4.setString(2, cargoManifestID);
            savePreparedStatement4.setString(3, containerID);
            savePreparedStatement4.setString(4, cargoManifestID);
            savePreparedStatement4.setString(5, containerID);
            savePreparedStatement4.executeUpdate();
            returnValue = true;
        }


        return returnValue;
    }
}
