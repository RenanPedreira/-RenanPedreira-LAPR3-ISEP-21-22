package lapr.project.controller;

import lapr.project.data.ContainerLocationDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Renan Pedreira
 */
public class ContainerLocationController {
    ContainerLocationDB cpdb;
    List<String> infoContainer;

    /**
     * Creates an instance of the controller
     */
    public ContainerLocationController(){
        cpdb = new ContainerLocationDB();
        infoContainer = new ArrayList<>();
    }

    /**
     * Gets the current location of a container(ship or port)
     *
     * @param containerID Container to be found
     * @return Current location of a container(ship or port)
     * @throws SQLException
     */
    public List<String> getContainerCurrentPosition(String containerID) throws SQLException {
        infoContainer = cpdb.getContainerCurrentPosition(containerID);
        return infoContainer;
    }
}
