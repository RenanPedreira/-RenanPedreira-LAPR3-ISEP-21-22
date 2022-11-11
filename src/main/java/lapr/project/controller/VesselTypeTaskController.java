package lapr.project.controller;

import lapr.project.data.VesselTypeTaskDB;

import java.sql.SQLException;
import java.util.List;

public class VesselTypeTaskController {
    VesselTypeTaskDB vttdb = new VesselTypeTaskDB();
    List<List<String>> list;

    public List<List<String>> getVesselTypesForTask(String cargoManifest) throws SQLException {
        list = vttdb.getVesselTypesForTask(cargoManifest);
        return list;
    }

}
