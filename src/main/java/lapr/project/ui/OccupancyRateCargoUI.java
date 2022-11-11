package lapr.project.ui;

import lapr.project.controller.OccupancyRateCargoController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.Scanner;

public class OccupancyRateCargoUI implements Runnable{

    public void run() {
        OccupancyRateCargoController orpcm = new OccupancyRateCargoController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Ship MMSI: ");
        String id = sc.next();
        Utils.print("Cargo Manifest ID: ");
        String cmid = sc.next();

        try {
            Utils.print("Rate = ");
            Utils.print(orpcm.getOccupancyRatePerCM(id, cmid));
            Utils.print();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
