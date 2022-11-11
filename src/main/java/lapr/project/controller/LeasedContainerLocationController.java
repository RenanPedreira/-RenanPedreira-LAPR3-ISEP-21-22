package lapr.project.controller;
import lapr.project.data.LeasedContainerLocationDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class LeasedContainerLocationController {
    LeasedContainerLocationDB lcpc;
    List<String> infoContainer = new ArrayList<>();

    public LeasedContainerLocationController() {
        lcpc = new LeasedContainerLocationDB();
    }

    public List<String> getLeasedContainerCurrentLocation(String clientID, String containerID) throws SQLException {
        infoContainer = lcpc.getLeasedContainerCurrentLocation(clientID, containerID);
        return infoContainer;
    }

}
