package lapr.project.controller;

import lapr.project.data.WarehouseAnalysisDB;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseAnalysisController {
        WarehouseAnalysisDB wadb;
        List<List<String>> list;

    public WarehouseAnalysisController() {
        wadb = new WarehouseAnalysisDB();
        list = new ArrayList<>();
    }

    public List<List<String>> getWarehouseAnalysis(String portID) throws SQLException {
        list = wadb.getWarehouseAnalysis(portID);
        return list;
    }

    public boolean printContainersLeavingNext30Days() {
        for (List<String> l : list) {
            for (String s : l) {
                Utils.print(s + "|");
            }
            Utils.print();
        }
        return true;
    }
}
