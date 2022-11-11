package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportBorderDB;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportBorderController {
    ImportBorderDB ibdb;

    public ImportBorderController(){
        ibdb = new ImportBorderDB();
    }

    public boolean importBordersFromFile(String fileName) throws FileNotFoundException, SQLException {
        try {

            DatabaseConnection databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            connection.setAutoCommit(true);
            Utils.print("Connected to the database!");
            String[] line;

            try (FileInputStream fileIn = new FileInputStream(fileName)) {
                Scanner sc = new Scanner(fileIn);
                sc.nextLine(); //Skip first line

                while (sc.hasNextLine()){
                    line = sc.nextLine().split(",");
                    ibdb.save(databaseConnection, line);
                }
                sc.close();

            } catch (IOException e) {
                return false;
            }
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }
        return true;
    }

    public List<Pair<String, String>> getBordersFromDB() {
        try {
            DatabaseConnection databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
            return ibdb.getListBorders(databaseConnection);
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return new ArrayList<>();
        }
    }
}
