package lapr.project.ui;

import lapr.project.controller.ResourceMapNextWeekController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class ResourceMapNextWeekUI implements Runnable{

    @Override
    public void run() {
        ResourceMapNextWeekController rmc = new ResourceMapNextWeekController();

        try {
            List<List<String>> list = rmc.getResourceMapNextWeek();

            printResourceMapNextWeek(list);
            System.out.println();

        } catch (SQLException throwables) {
            System.out.println("An error has occurred");;
        }
    }

    public boolean printResourceMapNextWeek(List<List<String>> list){
        if(list.isEmpty())
            System.out.println("No container operations were found for next week");
        else {
            for (List<String> l : list) {
                Utils.print("\nContainer: " + l.get(0));
                Utils.print("Date: " + l.get(1));
                Utils.print("Port: " + l.get(2));
                Utils.print("Warehouse: " + l.get(3));
                Utils.print("Cargo Manifest: " + l.get(4));
                Utils.print("Manifest Type: " + l.get(5));
                Utils.print("Transport: " + l.get(6));
                Utils.print("Container Count: " + l.get(7));
                Utils.print("Container Position X: " + l.get(8));
                Utils.print("Container Position Y: " + l.get(9));
                Utils.print("Container Position Z: " + l.get(10));

                System.out.println();
            }
        }
        return true;
    }
}