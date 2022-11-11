package lapr.project.ui;

import lapr.project.controller.LoadingCargoController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LoadingCargoUI implements Runnable{

    @Override
    public void run() {
        LoadingCargoController npc = new LoadingCargoController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Trip ID: ");
        String trip = sc.next();
        Utils.print("Current port: ");
        String port = sc.next();

        try {
            List<List<String>> nextPortLoad=npc.getNextPortLoad(trip, port);

            Utils.print("Port |Cont |Type  |Load  |X|Y|Z|");
            printNextPortLoad(nextPortLoad);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Prints the results
     *
     * @return true if no errors were found
     */
    public void printNextPortLoad(List<List<String>>nextPortLoad){
        for(List<String> l: nextPortLoad) {
            for (String s : l) {
                Utils.printNL(s + "|");
            }
            Utils.print();
        }
    }


}