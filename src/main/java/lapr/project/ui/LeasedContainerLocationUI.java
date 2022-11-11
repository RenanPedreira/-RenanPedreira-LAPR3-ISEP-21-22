package lapr.project.ui;

import lapr.project.controller.LeasedContainerLocationController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Renan Pedreira
 */
public class LeasedContainerLocationUI implements Runnable{

    @Override
    public void run() {
        LeasedContainerLocationController lcpc = new LeasedContainerLocationController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Client ID: ");
        String id = sc.next();
        Utils.print("Container ID: ");
        String cont = sc.next();

        try {
            List<String> list = lcpc.getLeasedContainerCurrentLocation(id, cont);
            Utils.print();

            Utils.print("ID     |TYPE      |NAME       |");
            printLeasedContainerCurrentLocation(list);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean printLeasedContainerCurrentLocation(List<String> infoContainer){
        for(String l: infoContainer) {
            Utils.print(l + " | ");
        }
        return true;
    }
}
