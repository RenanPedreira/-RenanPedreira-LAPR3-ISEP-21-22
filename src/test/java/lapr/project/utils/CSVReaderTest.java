package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author
 */
class CSVReaderTest {
    @Test
    public void fileNotFound() {
        CSVReader csv = new CSVReader();
        assertEquals(csv.createShipFromFile("fake.csv", 0), null);
    }

    @Test
    public void createShipFromFileTest() {
        CSVReader csv = new CSVReader();
        String[] result = csv.createShipFromFile("sships.csv", 0);
        assertTrue(result != null);
        String[] result2 = csv.createShipFromFile("sships.csv", 181);
        assertTrue(result2 == null);
    }
}