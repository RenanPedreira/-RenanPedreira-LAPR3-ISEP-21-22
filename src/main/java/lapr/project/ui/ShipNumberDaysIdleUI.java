package lapr.project.ui;

import lapr.project.controller.ShipNumberDaysIdleController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Renan Pedreira
 */
public class ShipNumberDaysIdleUI implements Runnable{

    @Override
    public void run() {
        ShipNumberDaysIdleController sndic = new ShipNumberDaysIdleController();
        Scanner sc = new Scanner(System.in);

        try {
            List<List<String>> result = sndic.getShipNumberDaysIdle();
            printShipNumberDaysIdle(result);
            System.out.println();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean printShipNumberDaysIdle(List<List<String>> result){
        for(List<String> l: result) {
            Utils.print("Ship: " + Integer.parseInt(l.get(0)));
            Utils.print("Days: " + Integer.parseInt(l.get(1)));
            System.out.println();
        }
        return true;
    }
}