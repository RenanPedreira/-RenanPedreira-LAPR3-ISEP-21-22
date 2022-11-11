package lapr.project.ui;

import lapr.project.controller.CargoManifestCountController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CargoManifestCountUI implements Runnable{
    public void run() {
        CargoManifestCountController ccmc = new CargoManifestCountController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Ship ID: ");
        String id = sc.next();
        Utils.print("Year: ");
        int year = sc.nextInt();

        try {
            List<String> infoCargoManifest=ccmc.getCountCargoManifest(id, year);
            Utils.print();

            Utils.print("N|AVG |");
            printCountCargoManifest(infoCargoManifest);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Prints the results
     *
     * @return true if no errors were found
     */
    public void printCountCargoManifest(List<String> infoCargoManifest){
        for(String l: infoCargoManifest) {
            Utils.printNL(l + "|");
        }
    }
}
