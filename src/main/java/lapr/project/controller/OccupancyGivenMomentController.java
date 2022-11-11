package lapr.project.controller;

import lapr.project.data.OccupancyGivenMomentDB;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @Author Renan Pedreira
 */
public class OccupancyGivenMomentController {
    OccupancyGivenMomentDB ordb;

    /**
     * Creates an instance of the controller
     */
    public OccupancyGivenMomentController(){
        ordb = new OccupancyGivenMomentDB();
    }

    /**
     * Gets the occupancy rate of a ship in a given moment
     *
     * @param shipMMSI The ship
     * @param date The moment
     * @return Occupancy rate of a ship in a given moment
     * @throws SQLException
     */
    public Double getOccupancyRate(String shipMMSI, Timestamp date) throws SQLException {
        return ordb.getOccupancyRateMoment(shipMMSI, date);
    }
}
