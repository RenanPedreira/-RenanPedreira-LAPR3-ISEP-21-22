package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportSeadistDB;
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

public class ImportSeadistController {

    public boolean importSeadistsFromFile(String fileName) throws FileNotFoundException, SQLException {

        try {
            DatabaseConnection databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();

            Connection connection = databaseConnection.getConnection();
            connection.setAutoCommit(true);
            Utils.print("Connected to the database!");

            ImportSeadistDB seadistsStoreDB = new ImportSeadistDB();
            String[] line2;
            try(FileInputStream fileIn = new FileInputStream(fileName)) {
                Scanner sc = new Scanner(fileIn);
                sc.nextLine(); //Skip first line

                while (sc.hasNextLine()) {
                    line2 = sc.nextLine().split(",");
                    seadistsStoreDB.save(databaseConnection, line2);
                }
                sc.close();
            }catch (IOException exception){
                return false;
            }
            return true;
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return false;
        }
    }

    public List<Pair<Pair<String, String>, Double>> getSeadistsFromDB() {
        try {
            DatabaseConnection databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();

            ImportSeadistDB isdb = new ImportSeadistDB();

            return isdb.getListSeadists(databaseConnection);
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return new ArrayList<>();
        }
    }
}
