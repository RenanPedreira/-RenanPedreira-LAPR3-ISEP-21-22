package lapr.project.ui;


import lapr.project.controller.ShipVoyagesAboveThresholdController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Renan Pedreira
 */
public class ShipVoyagesAboveThresholdUI  implements Runnable{

    @Override
    public void run() {
        ShipVoyagesAboveThresholdController rmc = new ShipVoyagesAboveThresholdController();
        Scanner sc = new Scanner(System.in);

        System.out.println("Threshold: ");
        Double threshold = sc.nextDouble();

        try {
            List<List<String>> result = rmc.getShipVoyagesAboveThreshold(threshold);
            printShipVoyagesAboveThreshold(result);
            System.out.println();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean printShipVoyagesAboveThreshold(List<List<String>> result){
        for(List<String> l: result) {
            Utils.print("Port: "+l.get(0));
            Utils.print("Date of origin: "+l.get(1));
            Utils.print("Date or destination: "+l.get(2));
            System.out.println();
        }
        return true;
    }
}