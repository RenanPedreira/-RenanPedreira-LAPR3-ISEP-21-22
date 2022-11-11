package lapr.project.ui;

import lapr.project.controller.WarehouseAnalysisController;

import java.sql.SQLException;
import java.util.Scanner;

public class WarehouseAnalysisUI implements Runnable{
    @Override
    public void run() {
        WarehouseAnalysisController wac = new WarehouseAnalysisController();

        Scanner sc = new Scanner(System.in);

        System.out.println("Port ID: ");
        String port = sc.next();

        try {
            wac.getWarehouseAnalysis(port);
            System.out.println();

            System.out.println("WAREHOUSE|RATE|N|");
            wac.printContainersLeavingNext30Days();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
