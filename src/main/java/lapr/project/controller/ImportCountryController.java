package lapr.project.controller;

import lapr.project.data.CountryStoreDB;
import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Country;
import lapr.project.utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class ImportCountryController {
    CountryStoreDB csdb;

    public ImportCountryController(){
        csdb = new CountryStoreDB();
    }

    public boolean importCountriesFromFile(String fileName3) throws FileNotFoundException, SQLException {
        try {
            DatabaseConnection databaseConnection3 = ConnectionFactory.getInstance().getDatabaseConnection();
            Connection connection3 = databaseConnection3.getConnection();
            connection3.setAutoCommit(true);
            Utils.print("Connected to the database!");

            String[] line3;

            try (FileInputStream fileIn3 = new FileInputStream(fileName3)) {
                Scanner sc3 = new Scanner(fileIn3);
                sc3.nextLine(); //Skip first line

                while (sc3.hasNextLine()){
                    line3 = sc3.nextLine().split(",");
                    csdb.save(databaseConnection3, line3);
                }
                sc3.close();

            } catch (IOException e) {
                return false;
            }
            return true;
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return false;
        }
    }

    public List<Country> getCountriesFromDB() {
        try {
            DatabaseConnection databaseConnection3 = ConnectionFactory.getInstance().getDatabaseConnection();
            return csdb.getListCountries(databaseConnection3);
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return new ArrayList<>();
        }
    }
}
