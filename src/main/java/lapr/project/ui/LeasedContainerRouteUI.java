package lapr.project.ui;

import lapr.project.controller.LeasedContainerRouteController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LeasedContainerRouteUI implements Runnable{

    @Override
    public void run() {
        LeasedContainerRouteController crc = new LeasedContainerRouteController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Client ID: ");
        String cid = sc.next();
        Utils.print("Container ID: ");
        String cont = sc.next();

        try {
            List<List<String>> list = crc.getLeasedContainerRoute(cid, cont);
            Utils.print();

            Utils.print("LOCATION|TYPE |ARRIVAL DATE |DEPARTURE DATE|");
            printLeasedContainerRoute(list);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean printLeasedContainerRoute( List<List<String>> listC){
        for(List<String> l: listC) {
            for(String s: l){
                Utils.print(s +" | ");
            }
            Utils.print();
        }
        return true;
    }
}