package lapr.project.controller;

import lapr.project.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImportCountryControllerTest {

    ImportCountryController icc = new ImportCountryController();

    @Test
    public void importCountriesFromDatabaseTest(){
        try {
            Assertions.assertTrue(icc.importCountriesFromFile("countries.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<Country> list =  icc.getCountriesFromDB();
        int expected = 68;
        int actual = list.size();

        assertEquals(expected, actual);
    }
}
