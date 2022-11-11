package lapr.project.ui;

import lapr.project.controller.ImportSeadistController;
import lapr.project.utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ImportSeadistUI implements Runnable{

    @Override
    public void run() {
        ImportSeadistController isc= new ImportSeadistController();

        Scanner sc3 = new Scanner(System.in);
        Utils.print("File name:");
        //Gets the file with the test
        String file = sc3.next();

        try {
            //Create ports form file in the database
            isc.importSeadistsFromFile(file);
            //Get the ports from the database
            Utils.print("Sea distances were successfully added.");
        } catch (IOException e) {
            Utils.print("File not found");
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
