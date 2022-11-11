package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    City city;

    @BeforeEach
    void setUp() {
        city= new City("Praia",25.0,30.0);
    }

    @Test
    void getCityName() {
        String expectedName="Praia";
        assertEquals(expectedName,city.getCityName());
    }

    @Test
    void setCityName() {
        City city2=new City("Lisboa",80.0,54.0);
        city2.setCityName("Praia");
        assertEquals(city.getCityName(),city2.getCityName());
        Exception exception1 = assertThrows(IllegalArgumentException.class,()->city2.setCityName(null));
        Exception exception2 = assertThrows(IllegalArgumentException.class,()->city2.setCityName(""));
        String expectedMessage1="The city name could not be empty.";

        assertEquals(expectedMessage1,exception1.getMessage());
        assertEquals(expectedMessage1,exception2.getMessage());
    }

    @Test
    void getLatitude() {
        double expectedLatitude=25.0;
        assertEquals(expectedLatitude,city.getLatitude());
    }

    @Test
    void setLatitude() {
        City city2=new City("Lisboa",80.0,54.0);
        assertEquals(80.0,city2.getLatitude());
        city2.setLatitude(25.0);
        assertEquals(city.getLatitude(),city2.getLatitude());
        city2.setLatitude(-90);
        assertEquals(-90,city2.getLatitude());
        city2.setLatitude(91);
        assertEquals(91,city2.getLatitude());

        String expectedMessage1="The city latitude is out of the range.";
        String expectedMessage2="The city longitude is out of the range.";
        Exception exception1 = assertThrows(IllegalArgumentException.class,()->city2.setLongitude(-189));
        Exception exception2 = assertThrows(IllegalArgumentException.class,()->city2.setLongitude(189));
        Exception exception3 = assertThrows(IllegalArgumentException.class,()->city2.setLatitude(99));
        Exception exception4 = assertThrows(IllegalArgumentException.class,()->city2.setLatitude(-99));

        assertEquals(expectedMessage2,exception1.getMessage());
        assertEquals(expectedMessage2,exception2.getMessage());
        assertEquals(expectedMessage1,exception3.getMessage());
        assertEquals(expectedMessage1,exception4.getMessage());
    }

    @Test
    void getLongitude() {
        double expectedLongitude=30.0;
        assertEquals(expectedLongitude,city.getLongitude());
    }

    @Test
    void setLongitude() {
        City city2=new City("Lisboa",80.0,54.0);
        assertEquals(54.0,city2.getLongitude());
        city2.setLongitude(30.0);
        assertEquals(city.getLongitude(),city2.getLongitude());
        city2.setLongitude(-180.0);
        assertEquals(-180.0,city2.getLongitude());
        city2.setLongitude(181.0);
        assertEquals(181.0,city2.getLongitude());
    }

    @Test
    void testHashCode() {
        int expectHasCode=7*7*city.getCityName().hashCode();
        assertEquals(expectHasCode,city.hashCode());
    }

    @Test
    void testEquals() {
        assertNotEquals(city,null);
        assertNotEquals(city,new Object());
        City city2=new City("Lisbon",26.0,30.0);
        City city3=new City("Praia",26.0,30.0);
        City city4=new City("Praia",25.0,40.0);
        City city5=new City("Praia",25.0,30.0);


        assertNotEquals(city,city2);
        assertNotEquals(city,city3);
        assertNotEquals(city,city4);
        assertEquals(city,city5);
    }

    @Test
    void calculateDistanceDifference() {
        City city5=new City("Praia",25.0,30.0);
        City city6=new City("Praia",24.0,30.0);
        double expectedResult=0.0;
        double expectedResult2=111.19492664455873;
        assertEquals(expectedResult,city.calculateDistanceDifference(city5));
        assertEquals(expectedResult2,city.calculateDistanceDifference(city6));
    }

    @Test
    void testCalculateDistanceDifference() {
        Port port=new Port(1,"Port1","County1",25.0,30.0);
        Port port2=new Port(1,"Port1","County1",24.0,30.0);
        double expectedResult=0.0;
        double expectedResult2=111.19492664455873;
    }

    @Test
    void compareTo() {
        City city5=new City("Praia",25.0,30.0);
        City city6=new City("Praia",24.0,30.0);
        int expectedResult=-1;
        int expectedResult2=1;
        assertEquals(expectedResult,city.compareTo(city5));
        assertEquals(expectedResult2,city.compareTo(city6));
    }
}