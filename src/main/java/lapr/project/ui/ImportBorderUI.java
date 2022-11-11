package lapr.project.ui;

import lapr.project.controller.ImportBorderController;
import lapr.project.utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ImportBorderUI implements Runnable{

    @Override
    public void run() {
        ImportBorderController icc= new ImportBorderController();

        Scanner sc3 = new Scanner(System.in);
        Utils.print("File name:");
        //Gets the file with the test
        String file = sc3.next();

        try {
            //Create ports form file in the database
            icc.importBordersFromFile(file);
            //Get the ports from the database
            Utils.print("Borders were successfully added.");
        } catch (IOException e) {
            Utils.print("File not found");
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
