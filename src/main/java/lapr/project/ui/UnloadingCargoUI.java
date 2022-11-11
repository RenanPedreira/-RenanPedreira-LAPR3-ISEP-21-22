package lapr.project.ui;

import lapr.project.controller.UnloadingCargoController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UnloadingCargoUI implements Runnable{

    @Override
    public void run() {
        UnloadingCargoController ucla = null;
        ucla = new UnloadingCargoController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Trip ID: ");
        String trip = sc.next();
        Utils.print("Current port: ");
        String port = sc.next();

        try {
            List<List<String>> nextPortUnloading=ucla.getNextPortOffload(trip, port);
            Utils.print("Port |Cont |Type  |Load  |X|Y|Z|");
            printNextPortOffload(nextPortUnloading);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public boolean printNextPortOffload(List<List<String>> nextPortUnloading){
        for(List<String> l: nextPortUnloading) {
            for(String s: l){
                Utils.printNL(s +"|");
            }
            Utils.print();
        }
        return true;
    }

}