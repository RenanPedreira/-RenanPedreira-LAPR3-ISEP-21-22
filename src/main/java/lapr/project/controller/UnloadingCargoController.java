package lapr.project.controller;

import lapr.project.data.UnloadingCargoDB;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author Renan Pedreira
 */
public class UnloadingCargoController {

    UnloadingCargoDB ucla;

    /**
     * Creates an instance of the controller
     */
    public UnloadingCargoController(){
        ucla = new UnloadingCargoDB();
    }

    /**
     * Gets the list of containers to be unloaded in the next port
     *
     * @param trip Current trip
     * @param currentPort Current port
     * @return List of containers to be unloaded in the next port
     * @throws SQLException
     */
    public List<List<String>> getNextPortOffload(String trip, String currentPort) throws SQLException {
        return ucla.getNextPortOffload(trip, currentPort);
    }


}