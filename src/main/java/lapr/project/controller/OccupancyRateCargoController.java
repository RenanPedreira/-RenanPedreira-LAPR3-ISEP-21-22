package lapr.project.controller;

import lapr.project.data.OccupancyRateCargoDB;

import java.sql.SQLException;

/**
 * @Author Renan Pedreira
 */
public class OccupancyRateCargoController {
    OccupancyRateCargoDB ordb;

    /**
     * Creates an instance of the controller
     */
    public OccupancyRateCargoController(){
        ordb = new OccupancyRateCargoDB();
    }

    /**
     * Gets the occupancy rate of a ship considering the containers in a cargo manifest
     *
     * @param shipMMSI The ship to be analyzed
     * @param cargoManifestID The cargo manifest
     * @return The occupancy rate of a ship considering the containers in a cargo manifest
     * @throws SQLException
     */
    public Double getOccupancyRatePerCM(String shipMMSI, String cargoManifestID) throws SQLException {
        return ordb.getOccupancyRate(shipMMSI, cargoManifestID);
    }
}
