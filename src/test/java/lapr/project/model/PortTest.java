package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author
 */
class PortTest {
    private Port port1, port2, port3, test1, test2;

    @BeforeEach
    public void setUp() throws Exception {
        port1 = new Port(123456789, "Test", "Test", 44, 44);
        port2 = new Port(123456788, "TestTest", "TestTest", 55, 55);
        test1 = new Port(123456789, "Test", "Test", 44, 44, "Test");
        test2 = new Port(123456788, "TestTest", "TestTest", 55, 55, "TestTest");
    }

    @Test
    public void latTooSmall() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            port1.setLatitude(-180);
        });
    }

    @Test
    public void latTooBig() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            port1.setLatitude(180);
        });
    }

    @Test
    public void latInTheLimit() {
        port1.setLatitude(90);
        assertEquals(port1.getLatitude(), 90);
    }

    @Test
    public void latInTheLimit2() {
        port1.setLatitude(-90);
        assertEquals(port1.getLatitude(), -90);
    }

    @Test
    public void lonTooSmall() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            port1.setLongitude(-190);
            ;
        });
    }

    @Test
    public void lonTooBig() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            port1.setLongitude(190);
        });
    }

    @Test
    public void lonInTheLimit() {
        port1.setLongitude(180);
        assertEquals(port1.getLongitude(), 180);
    }

    @Test
    public void lonInTheLimit2() {
        port1.setLongitude(-180);
        assertEquals(port1.getLongitude(), -180);
    }

    /**
     * Test that gets the identification of the port
     */
    @Test
    public void getIdentification() {
        //Arrange
        Integer expectedResult = 123456789;
        Integer expectedResult2 = 123456788;

        //Act
        Integer result = port1.getIdentification();
        Integer result2 = port2.getIdentification();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
    }

    /**
     * Test that gets the name of the port
     */
    @Test
    public void getName() {
        //Arrange
        String expectedResult = "Test";
        String expectedResult2 = "TestTest";

        //Act
        String result = port1.getName();
        String result2 = port2.getName();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
    }

    /**
     * Test that gets the country of the port
     */
    @Test
    public void getCountry() {
        //Arrange
        String expectedResult = "Test";
        String expectedResult2 = "TestTest";

        //Act
        String result = port1.getCountry();
        String result2 = port2.getCountry();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
    }

    /**
     * Test that gets the latitude of the port
     */
    @Test
    public void getLatitude() {
        //Arrange
        double expectedResult = 44;
        double expectedResult2 = 55;

        //Act
        double result = port1.getLatitude();
        double result2 = port2.getLatitude();

        //Assert
        assertEquals(expectedResult, result, 0.01);
        assertEquals(expectedResult2, result2, 0.01);
    }

    /**
     * Test that gets the longitude of the port
     */
    @Test
    public void getLongitude() {
        //Arrange
        double expectedResult = 44;
        double expectedResult2 = 55;

        //Act
        double result = port1.getLongitude();
        double result2 = port2.getLongitude();

        //Assert
        assertEquals(expectedResult, result, 0.01);
        assertEquals(expectedResult2, result2, 0.01);
    }

    /**
     * Test that gets the country of the port
     */
    @Test
    public void getContinent() {
        //Arrange
        String expectedResult = "Test";
        String expectedResult2 = "TestTest";

        //Act
        String result = test1.getContinent();
        String result2 = test2.getContinent();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
    }

    /**
     * Test that checks the equals method
     */
    @Test
    public void testEquals() {
        Port port = null;
        assertTrue(port1.equals(port1));
        assertTrue(port2.equals(port2));
        assertFalse(port1.equals(port2));
        assertFalse(port1.equals(port));
        assertFalse(port1.equals("null"));
    }

    /**
     * Test that checks the compareTo method
     */
    @Test
    public void compareTo() {
        //Act
        int result1 = port1.compareTo(port1);
        int result2 = port1.compareTo(port2);

        //Assert
        assertEquals(result1, 0);
        assertEquals(result2, 1);
        assertTrue(result1 == 0, port1.getIdentification() + " is equal to " + port2.getIdentification());
        assertTrue(result2 > 0, port1.getIdentification() + " comes after " + port2.getIdentification());
    }

    /**
     * Test that tests the toString method
     */
    @Test
    void testToString() {
        //Act
        String expected = String.format("|%10d|%10s|%10s|%10s|%10f|%10f|", port1.getIdentification(), port1.getName()
                , port1.getCountry(), "unidentified", port1.getLatitude(), port1.getLongitude());
        String result = port1.toString();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void testHashCode() {
        Port port4 = new Port(123456788, "TestTest", "TestTest", 55, 55);
        int expected = port4.getIdentification();
        int result = port4.hashCode();
        assertEquals(expected, result);
    }

    @Test
    void calculateDistanceDifference() {
        Port port = new Port(1, "Port1", "County1", 25.0, 30.0);
        Port port2 = new Port(1, "Port1", "County1", 24.0, 30.0);
        City city = new City("Praia", 25.0, 30.0);
        City city2 = new City("Praia", 24.0, 30.0);
        double expectedResult = 0.0;
        double expectedResult2 = 111.19492664455873;
        assertEquals(expectedResult, port.calculateDistanceDifference(port));
        assertEquals(expectedResult2, port.calculateDistanceDifference(port2));
    }
}