package lapr.project.controller;

import lapr.project.data.CargoManifestCountDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Renan Pedreira
 */
public class CargoManifestCountController {
    CargoManifestCountDB cmdb;
    List<String> infoCargoManifest;

    /**
     * Creates an instance of the controller
     */
    public CargoManifestCountController(){
        cmdb = new CargoManifestCountDB();
        infoCargoManifest = new ArrayList<>();
    }

    /**
     * Gets the number of cargo manifest carried by a ship and average container number per manifest
     *
     * @param shipMMSI The ship's identification
     * @param year Year to be analyzed
     * @return Number of cargo manifest carried by a ship and average container number per manifest
     * @throws SQLException
     */
    public List<String> getCountCargoManifest(String shipMMSI, int year) throws SQLException {
        infoCargoManifest = cmdb.getCountCargoManifest(shipMMSI, year);
        return infoCargoManifest;
    }

}
