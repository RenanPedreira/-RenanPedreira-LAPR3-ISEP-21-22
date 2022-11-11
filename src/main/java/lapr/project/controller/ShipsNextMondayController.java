package lapr.project.controller;

import lapr.project.data.ShipsNextMondayDB;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author Renan Pedreira
 */
public class ShipsNextMondayController {

    ShipsNextMondayDB asDB;

    /**
     * Creates an instance of the controller
     */
    public ShipsNextMondayController() {
        asDB = new ShipsNextMondayDB();
    }

    /**
     * Gets a list of available ships and their port location
     *
     * @return list of available ships and their port location
     * @throws SQLException
     */
    public List<List<String>> getAvailableShipsNextMonday() throws SQLException {
        return asDB.availableShipsNextMonday();
    }

}
