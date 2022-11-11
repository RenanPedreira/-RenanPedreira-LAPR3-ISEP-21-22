package lapr.project.ui;

import lapr.project.controller.ImportCountryController;
import lapr.project.utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ImportCountryUI implements Runnable{

    @Override
    public void run() {
        ImportCountryController icc= new ImportCountryController();

        Scanner sc3 = new Scanner(System.in);
        Utils.print("File name:");
        //Gets the file with the test
        String file = sc3.next();

        try {
            //Create ports form file in the database
            icc.importCountriesFromFile(file);
            //Get the ports from the database
            Utils.print("Countries were successfully added.");
        } catch (IOException e) {
            Utils.print("File not found");
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
