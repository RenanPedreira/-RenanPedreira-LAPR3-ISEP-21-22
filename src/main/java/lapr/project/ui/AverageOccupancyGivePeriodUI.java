package lapr.project.ui;

import lapr.project.controller.AverageOccupancyGivenPeriodController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 * @author Renan Pedreira
 */
public class AverageOccupancyGivePeriodUI implements Runnable{
    public void run() {
        AverageOccupancyGivenPeriodController aogp = new AverageOccupancyGivenPeriodController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Ship ID: ");
        String id = sc.next();
        Utils.print("\nInitial Date(yyyy-mm-dd hh:mm:ss): ");
        String date1 = sc.next();

        String hour1 = sc.next();

        Utils.print("\nFinal Date(yyyy-mm-dd hh:mm:ss): ");
        String date2 = sc.next();

        String hour2 = sc.next();



        try {
            Double result = aogp.getAverageOccupancy(id, Timestamp.valueOf(date1+ " " +hour1), Timestamp.valueOf(date2+ " " +hour2));
            Utils.print("Occupancy rate of ship "+id+" between "+date1+" "+hour1+" and "+date2+" "+hour2+"\n"
                        +"-> "+result+"%");

        } catch (SQLException throwables) {
            Utils.print("An error occurred");
        }
    }

}
