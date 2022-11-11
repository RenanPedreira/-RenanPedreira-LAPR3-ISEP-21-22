package lapr.project.ui;

import lapr.project.controller.ShipsNextMondayController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;

public class ShipsNextMondayUI implements Runnable{
    @Override
    public void run() {
        ShipsNextMondayController asc = new ShipsNextMondayController();
        Utils.print("Next monday: ");

        try {
            List<List<String>> availableShips=asc.getAvailableShipsNextMonday();
            Utils.print();

            Utils.print("Ship MMSI|Port |Trip |");
            printAvailableShipsNextMonday(availableShips);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    
    public void printAvailableShipsNextMonday(List<List<String>> availableShips){
        for(List<String> l: availableShips) {
            for(String s: l){
                Utils.print(s +"|");
            }
            Utils.print();
        }
    }
}
