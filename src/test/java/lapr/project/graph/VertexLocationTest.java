package lapr.project.graph;

import lapr.project.network.VertexLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VertexLocationTest {
    VertexLocation location1, location2;

    @BeforeEach
    public void setUp() throws Exception {
        location1 = new VertexLocation("Banana City", "Bnanaopolis", 10.0, 10.0, "Bnana Land", "America");
        location2 = new VertexLocation("Papaya Town", "Papaypolis", 10.0, 10.0, "PPaya Nation", "Asia");
    }

    @Test
    void getSetLocationIDTest() {
        location1.setLocationID("Avocado District");
        assertEquals("Avocado District", location1.getLocationID());
    }

    @Test
    void getSetLocationNameTest() {
        location1.setLocationName("Avocadopolis");
        assertEquals("Avocadopolis", location1.getLocationName());
    }

    @Test
    void getSetLatitudeTest() {
        location1.setLatitude(50.0);
        assertEquals(50.0, location1.getLatitude());
    }

    @Test
    void getSetLongitudeTest() {
        location1.setLongitude(50.0);
        assertEquals(50.0, location1.getLongitude());
    }

    @Test
    void getSetCountryName() {
        location1.setCountryName("Avcad Domain");
        assertEquals("Avcad Domain", location1.getCountryName());
    }

    @Test
    void getSetContinentTest() {
        location1.setContinent("Oceania");
        assertEquals("Oceania", location1.getContinent());
    }


    @Test
    void distanceBetween() {
        assertEquals(0.0, location1.distanceBetween(location2));
    }

}
