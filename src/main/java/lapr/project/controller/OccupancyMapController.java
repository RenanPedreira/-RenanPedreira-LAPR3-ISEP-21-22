package lapr.project.controller;

import lapr.project.data.OccupancyMapDB;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class OccupancyMapController {
    OccupancyMapDB ocdb;
    List<List<String>> list;

    public OccupancyMapController() {
        ocdb = new OccupancyMapDB();
        list = new ArrayList<>();
    }

    public List<List<String>> getOccupancyMap(String portID, String month, String year) throws SQLException {
        String date = year + "-" + month + "-01";
        list =  ocdb.getOccupancyMap(portID, date);
        return list;
    }

    public boolean printOccupancyMap(){
        for(int i = 0; i < list.size(); i++){
            List<String> l = list.get(i);
            if(i < 9) {
                Utils.print(" 0" + l.get(0) + "| ");
                Utils.print(l.get(1) + "| ");
                Utils.print(l.get(2) + "|");
            }else {
                Utils.print(" " + l.get(0) + "| " + l.get(1) +"| " + l.get(2) +"|");
            }
            Utils.print();
        }
        return true;
    }
}
