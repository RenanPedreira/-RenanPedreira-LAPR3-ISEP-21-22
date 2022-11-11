package lapr.project.controller;

import lapr.project.data.LoadingCargoDB;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author Renan Pedreira
 */
public class LoadingCargoController {
    LoadingCargoDB lcdb;

    /**
     * Creates an instance of the controller
     */
    public LoadingCargoController(){
        lcdb = new LoadingCargoDB();
    }

    /**
     * Gets the list of containers to be loaded in the next port
     *
     * @param trip Current trip
     * @param currentPort Current port
     * @return List of containers to be loaded in the next port
     * @throws SQLException
     */
    public List<List<String>> getNextPortLoad(String trip, String currentPort) throws SQLException {
        return lcdb.getNextPortLoad(trip, currentPort);
    }
}
