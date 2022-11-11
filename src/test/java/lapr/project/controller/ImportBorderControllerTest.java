package lapr.project.controller;

import lapr.project.utils.Pair;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImportBorderControllerTest {

    ImportBorderController ibc = new ImportBorderController();

    @Test
    public void importBordersFromDatabaseTest(){
        try {
            assertTrue(ibc.importBordersFromFile("borders.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<Pair<String, String>> list =  ibc.getBordersFromDB();
        int expected = 121;
        int actual = list.size();

        assertEquals(expected, actual);
    }
}
