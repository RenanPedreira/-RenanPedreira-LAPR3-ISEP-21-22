package lapr.project.controller;

import lapr.project.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImportSeadistControllerTest {

    ImportSeadistController isc = new ImportSeadistController();

    @Test
    public void importCountriesFromDatabaseTest(){
        try {
            Assertions.assertTrue(isc.importSeadistsFromFile("seadists.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<Pair<Pair<String, String>, Double>> list =  isc.getSeadistsFromDB();
        int expected = 2834;
        int actual = list.size();

        assertEquals(expected, actual);
    }
}
