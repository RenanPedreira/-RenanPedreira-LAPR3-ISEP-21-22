package lapr.project.controller;

import lapr.project.data.AverageOccupancyGivenPeriodDB;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Renan Pedreira
 */
public class AverageOccupancyGivenPeriodController {
    AverageOccupancyGivenPeriodDB aogpdb;

    public AverageOccupancyGivenPeriodController(){
        aogpdb = new AverageOccupancyGivenPeriodDB();
    }

    public Double getAverageOccupancy(String ship, Timestamp beginningDate, Timestamp endingDate) throws SQLException {
        return aogpdb.getAverageOccupancy(ship, beginningDate, endingDate);

    }
}

