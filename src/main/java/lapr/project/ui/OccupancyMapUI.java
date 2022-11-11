package lapr.project.ui;

import lapr.project.controller.OccupancyMapController;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Renan Pedreira
 */
public class OccupancyMapUI  implements Runnable{

    @Override
    public void run() {
        OccupancyMapController omc = new OccupancyMapController();

        Scanner sc = new Scanner(System.in);

        System.out.println("Port ID: ");
        String port = sc.next();
        System.out.println("Month: ");
        String month = sc.next();
        System.out.println("Year: ");
        String year = sc.next();

        try {
            omc.getOccupancyMap(port, month, year);
            System.out.println();

            System.out.println("DAY| W| P|");
            omc.printOccupancyMap();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
