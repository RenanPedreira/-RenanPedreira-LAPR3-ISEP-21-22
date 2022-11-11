package lapr.project.ui;

import lapr.project.controller.App;
import lapr.project.controller.ImportShipController;
import lapr.project.utils.Utils;

import java.io.IOException;
import java.util.Scanner;

public class ImportShipUI implements Runnable {

    public void run() {
        Scanner sc3 = new Scanner(System.in);
        Utils.print("File name:");
        //Gets the file with the ships
        String file = sc3.next();

        try {
            ImportShipController ipc = new ImportShipController();
            ipc.importData2(file);
            Utils.print("Ships were successfully added.");
        } catch (IOException e) {
            Utils.print("File not found");
        } catch (ClassNotFoundException e) {
            Utils.print("Company not found");
        }
    }
}
