package lapr.project.controller;

import lapr.project.data.LeasedContainerRouteDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class LeasedContainerRouteController {

    LeasedContainerRouteDB lcrdb;
    List<List<String>> list = new ArrayList<>();

    public LeasedContainerRouteController() {
        lcrdb = new LeasedContainerRouteDB();
    }

    public List<List<String>> getLeasedContainerRoute(String clientID, String containerID) throws SQLException {
        list = lcrdb.getContainerRoute(clientID, containerID);
        return list;
    }
}