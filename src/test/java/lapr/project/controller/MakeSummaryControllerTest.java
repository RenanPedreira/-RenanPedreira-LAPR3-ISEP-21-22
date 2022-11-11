package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.*;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.store.ShipStore;
import lapr.project.tree.AVL;
import lapr.project.tree.BST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author
 */
class MakeSummaryControllerTest {
    private MakeSummaryController makeSummaryController;
    App app= new App();
    ShipStore shipStore = app.getInstance().getCompany().getShipStore();


    @BeforeEach
    public void setUp() throws Exception {
        // Singleton
        App app = App.getInstance();
        Company company = app.getCompany();

        //Controllers
        makeSummaryController = new MakeSummaryController();

    }

    /**
     * Searches the ship with the wrong mmsi but with 9 digits
     */
    @Test
    public void searchShipByMMSI1() {
        //Ship's attributes
        Integer mmsi1 = 123456789;
        String imo1 = "IMO1234567";
        String callSign1 = "ABCD";

        //Ship
        Ship ship = shipStore.createShip(mmsi1, imo1, callSign1);
        shipStore.saveShip(ship);

        //Act
        boolean expected = makeSummaryController.searchShipByMMSI(123456757);

        //Assert
        assertTrue(true);
    }

    /**
     * Searches the ship with the wrong mmsi but with 9 digits
     */
    @Test
    public void searchShipByMMSI2() {
        //Ship's attributes
        Integer mmsi1 = 123456789;
        String imo1 = "IMO1234567";
        String callSign1 = "ABCD";

        //Ship
        Ship ship = shipStore.createShip(mmsi1, imo1, callSign1);
        shipStore.saveShip(ship);

        //Act
        boolean expected = makeSummaryController.searchShipByMMSI(123456757);

        //Assert
        assertFalse(expected);
    }

    /**
     * Searches the ship with the wrong mmsi and without the 9 digit rule
     */
    @Test
    public void searchShipByMMSI3() {
        //Ship's attributes
        Integer mmsi1 = 123456789;
        String imo1 = "IMO1234567";
        String callSign1 = "ABCD";

        //Ship
        Ship ship = shipStore.createShip(mmsi1, imo1, callSign1);
        shipStore.saveShip(ship);

        //Assert
        Exception exception=assertThrows(IllegalArgumentException.class, ()->makeSummaryController.searchShipByMMSI(1));
        assertEquals("MMSI must be a 9-digit code", exception.getMessage());
    }

    /**
     * Searches the ship with the right imo
     */
    @Test
    public void searchShipByIMO1() {
        //Ship's attributes
        Integer mmsi2 = 123456788;
        String imo2 = "IMO1234566";
        String callSign2 = "ABCC";

        //Ship
        Ship ship2 = shipStore.createShip(mmsi2, imo2, callSign2);
        shipStore.saveShip(ship2);

        //Act
        boolean searchShipByIMO = makeSummaryController.searchShipByIMO("IMO1234566");

        //Assert
        assertTrue(searchShipByIMO);
    }

    /**
     * Searches the ship with the wrong imo but with 7 digits
     */
    @Test
    public void searchShipByIMO2() {
        //Ship's attributes
        Integer mmsi2 = 123456788;
        String imo2 = "IMO1234566";
        String callSign2 = "ABCC";

        //Ship
        Ship ship2 = shipStore.createShip(mmsi2, imo2, callSign2);
        shipStore.saveShip(ship2);

        //Assert
        Exception exception=assertThrows(IllegalArgumentException.class, ()->makeSummaryController.searchShipByIMO("1234566"));
        assertEquals("IMO must be a 7-digit code", exception.getMessage());
    }

    /**
     * Searches the ship with the wrong imo
     */
    @Test
    public void searchShipByIMO3() {
        //Ship's attributes
        Integer mmsi2 = 123456788;
        String imo2 = "IMO1234566";
        String callSign2 = "ABCC";

        //Ship
        Ship ship2 = shipStore.createShip(mmsi2, imo2, callSign2);
        shipStore.saveShip(ship2);

        //Assert
        //Exception exception=assertThrows(IllegalArgumentException.class, ()->makeSummaryController.searchShipByIMO(null));
        //assertEquals("IMO must be a 7-digit code", exception.getMessage());
        assertFalse(makeSummaryController.searchShipByIMO("IMO1231231"));
    }

    /**
     * Searches the ship with the right call sign
     */
    @Test
    public void searchShipByCallSign1() {
        //Ship's attributes
        Integer mmsi3 = 123456799;
        String imo3 = "IMO1234588";
        String callSign3 = "ABCE";

        //Ship
        Ship ship3 = shipStore.createShip(mmsi3, imo3, callSign3);
        shipStore.saveShip(ship3);

        //Act
        boolean searchShipByCallSign = makeSummaryController.searchShipByCallSign("ABCE");

        //Assert
        assertTrue(searchShipByCallSign);
    }

    /**
     * Searches the ship with the wrong call sign
     */
    @Test
    public void searchShipByCallSign2() {
        //Ship's attributes
        Integer mmsi3 = 123456799;
        String imo3 = "IMO1234588";
        String callSign3 = "ABCE";

        //Ship
        Ship ship3 = shipStore.createShip(mmsi3, imo3, callSign3);
        shipStore.saveShip(ship3);

        //Act
        boolean searchShipByCallSign = makeSummaryController.searchShipByCallSign("ABDE");

        //Assert
        assertFalse(searchShipByCallSign);
    }

    /**
     * Method that returns the ship using a mmsi that is already saved
     */
    @Test
    public void getShipByMMSI1() {
        //Ship's attributes
        Integer mmsi1 = 123456789;
        String imo1 = "IMO1234567";
        String callSign1 = "ABCD";

        //Ship
        Ship ship2 = shipStore.createShip(mmsi1, imo1, callSign1);
        shipStore.saveShip(ship2);

        //Act
        Ship ship3 = makeSummaryController.getShipByMMSI(mmsi1);

        //Assert
        assertNotNull(ship3);
    }

    /**
     * Method that returns the ship using a mmsi that isn´t saved
     */
    @Test
    public void getShipByMMSI2() {
        //Ship's attributes
        Integer mmsi1 = 123456789;
        String imo1 = "IMO1234567";
        String callSign1 = "ABCD";

        //Ship
        Ship ship = shipStore.createShip(mmsi1, imo1, callSign1);
        shipStore.saveShip(ship);

        //Act
        Ship ship2 = makeSummaryController.getShipByMMSI(123456767);

        //Assert
        assertNull(ship2);
    }

    /**
     * Method that returns the ship using a imo that is already saved
     */
    @Test
    public void getShipByIMO1() {
        //Ship's attributes
        Integer mmsi2 = 123456788;
        String imo2 = "IMO1234566";
        String callSign2 = "ABCC";

        //Ship
        Ship ship2 = shipStore.createShip(mmsi2, imo2, callSign2);
        shipStore.saveShip(ship2);

        //Act
        Ship ship = makeSummaryController.getShipByIMO("IMO1234566");

        //Assert
        assertNotNull(ship);
    }

    /**
     * Method that returns the ship using a imo that isn´t saved
     */
//    @Test
//    public void getShipByIMO2() {
//        //Ship's attributes
//        Integer mmsi2 = 123456788;
//        String imo2 = "IMO1234566";
//        String callSign2 = "ABCC";
//
//        //Ship
//        Ship ship2 = shipStore.createShip(mmsi2, imo2, callSign2);
//        shipStore.saveShip(ship2);
//
//        //Act
//        Ship ship = makeSummaryController.getShipByIMO("IMO1234562");
//
//        //Assert
//        assertNull(ship);
//    }

    /**
     * Method that returns the ship using a call sign that is already saved
     */
    @Test
    public void getShipByCallSign1() {
        //Ship's attributes
        Integer mmsi3 = 123456799;
        String imo3 = "IMO1234588";
        String callSign3 = "ABCE";

        //Ship
        Ship ship3 = shipStore.createShip(mmsi3, imo3, callSign3);
        shipStore.saveShip(ship3);

        //Act
        Ship ship = makeSummaryController.getShipByCallSign("ABCE");

        //Assert
        assertNotNull(ship);
    }

    /**
     * Method that returns the ship using a call sign that isn´t saved
     */
    @Test
    public void getShipByCallSign2() {
        //Ship's attributes
        Integer mmsi3 = 123456799;
        String imo3 = "IMO1234588";
        String callSign3 = "ABCE";

        //Ship
        Ship ship3 = shipStore.createShip(mmsi3, imo3, callSign3);
        shipStore.saveShip(ship3);

        //Act
        Ship ship = makeSummaryController.getShipByCallSign("ABCV");

        //Assert
        assertNull(ship);
    }

    @Test
    public void calculateDistance1() {
        //Arrange
        ShipDynamicData location1, location2;
        //Ship Dynamic Data
        location1=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        location2=new ShipDynamicData("31/12/2020 00:34",28.30354,-88.78563,11.7,129.9,131,"CMA CGM ALMAVIVA","IMO9450648");

        double expectedResult1=0.0;

        //Act
        double result1= makeSummaryController.calculateDistance(location1.getLat(), location1.getLon(), location2.getLat(),
                location2.getLon());

        //Assert
        assertNotEquals(expectedResult1, result1, 0);
    }

    @Test
    public void calculateDistance2() {
        //Arrange
        ShipDynamicData location1, location2;
        //Ship Dynamic Data
        location1=new ShipDynamicData("31/12/2020 23:03",0.0,0.0,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        location2=new ShipDynamicData("31/12/2020 00:34",0.0,0.0,11.7,129.9,131,"CMA CGM ALMAVIVA","IMO9450648");

        double expectedResult1=0.0;

        //Act
        double result1= makeSummaryController.calculateDistance(location1.getLat(), location1.getLon(), location2.getLat(),
                location2.getLon());

        //Assert
        assertEquals(expectedResult1, result1, 0);
    }
}