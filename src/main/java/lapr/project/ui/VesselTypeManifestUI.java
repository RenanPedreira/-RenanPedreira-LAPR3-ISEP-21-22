package lapr.project.ui;


import lapr.project.controller.VesselTypeTaskController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class VesselTypeManifestUI implements Runnable{
    public void run() {
        VesselTypeTaskController vttc = new VesselTypeTaskController();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ship's center of mass is calculated considering a rectangular shape where Y=0");

        Utils.print("Manifest: ");
        String id = sc.next();

        try {
            List<List<String>> vesselTypes= vttc.getVesselTypesForTask(id);
            Utils.print();

            printCountCargoManifest(vesselTypes);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Prints the results
     *
     * @return true if no errors were found
     */
    public void printCountCargoManifest(List<List<String>> vesselTypes ){
        for(List<String> l: vesselTypes) {
            Utils.print("Vessel Type: "+l.get(0)+"\n");
            Utils.print("Vessel X center of mass: "+l.get(1)+"\n");
            Utils.print("Vessel Y center of mass: "+l.get(3)+"\n");
            Utils.print("Vessel Z center of mass: "+l.get(2)+"\n");
            Utils.print("Manifest X center of mass: "+l.get(4)+"\n");
            Utils.print("Manifest Y center of mass: "+l.get(5)+"\n");
            Utils.print("Manifest Z center of mass: "+l.get(6)+"\n");
            Utils.print("\n\n");
        }
    }
}
