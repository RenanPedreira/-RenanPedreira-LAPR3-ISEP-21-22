package lapr.project.ui;

import lapr.project.controller.OccupancyGivenMomentController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class OccupancyGivenMomentUI implements Runnable{
    public void run() {
        OccupancyGivenMomentController orc = new OccupancyGivenMomentController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Ship ID: ");
        String id = sc.next();
        Utils.print("Date (yyyy-mm-dd): ");
        String date = sc.next();
        Utils.print("Hour(hh:mm:ss): ");
        String hour = sc.next();

        try {
            Utils.print("Rate = ");
            Utils.print(orc.getOccupancyRate(id, Timestamp.valueOf(date + " " + hour)));
            Utils.print();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
