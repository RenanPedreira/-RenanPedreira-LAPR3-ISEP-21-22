package lapr.project.controller;

import lapr.project.data.ShipVoyagesAboveThresholdDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class ShipVoyagesAboveThresholdController {

    ShipVoyagesAboveThresholdDB svdb;
    List<List<String>> list;

    public ShipVoyagesAboveThresholdController() {
        svdb = new ShipVoyagesAboveThresholdDB();
        list = new ArrayList<>();
    }


    public List<List<String>> getShipVoyagesAboveThreshold(double threshold) throws SQLException {
        list = svdb.getShipVoyagesAboveThreshold(threshold);
        return list;
    }

}