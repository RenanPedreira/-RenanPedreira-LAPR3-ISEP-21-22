package lapr.project.controller;

import lapr.project.data.ContainerOperationDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContainerOperationController {
    ContainerOperationDB codb;
    List<List<String>> list = new ArrayList<>();

    public ContainerOperationController() {
        codb = new ContainerOperationDB();
    }

    public List<List<String>> getContainerAuditTrail(String cargoManifestID, String containerID) throws SQLException {
        list = codb.getContainerOperationList(cargoManifestID, containerID);
        return list;
    }

}
