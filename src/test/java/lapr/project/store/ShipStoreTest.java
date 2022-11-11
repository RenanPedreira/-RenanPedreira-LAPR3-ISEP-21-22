package lapr.project.store;

import static org.junit.jupiter.api.Assertions.*;
import lapr.project.controller.App;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.tree.AVL;
import lapr.project.tree.BST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author
 */
class ShipStoreTest {
    private ShipStore shipStore;
    private AVL<Ship> shipList = new AVL();
    private AVL<Ship> shipList2 = new AVL();
    private AVL<Ship> shipList3 = new AVL();

    @BeforeEach
    public void setUp() throws Exception {
        App app = App.getInstance();
        Company company = app.getCompany();
        shipStore = company.getShipStore();

        shipStore = new ShipStore(shipList, shipList2, shipList3);
    }

    /**
     * Test that verifies if the ship is created. All the parameters of the expectedResult and result are the same
     */
    @Test
    public void createShip1() {
        //Arrange
        Ship result = new Ship(123456789, "IMO1234567", "ABCD");

        //Act
        Ship expectedResult = shipStore.createShip(123456789, "IMO1234567", "ABCD");

        //Assert
        assertEquals(expectedResult.getMmsi(), result.getMmsi());
        assertEquals(expectedResult.getImo(), result.getImo());
        assertEquals(expectedResult.getCallSign(), result.getCallSign());
    }

    /**
     * Test that verifies if the ship is created. The mmsi of the expectedResult and result is the same but the imo and call
     * sign are different
     */
    @Test
    public void createShip2() {
        //Arrange
        Ship result = new Ship(123456789, "IMO1234566", "ABCC");

        //Act
        Ship expectedResult = shipStore.createShip(123456789, "IMO1234567", "ABCD");

        //Assert
        assertEquals(expectedResult.getMmsi(), result.getMmsi());
        assertNotEquals(expectedResult.getImo(), result.getImo());
        assertNotEquals(expectedResult.getCallSign(), result.getCallSign());
    }

    /**
     * Test that verifies if a ship with a certain mmsi exists already. The mmsi given to search exists
     */
    @Test
    public void getShipByMMSI1() {
        //Arrange
        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");
        ship.setCodeFind(1);
        shipStore.saveShip(ship);

        //Act
        Ship ship2 = shipStore.getShipByMMSI(123456789);
        int result=ship2.getCodeFind();

        //Assert
        assertNotNull(ship2);
        assertEquals(1, result);
    }

    /**
     * Test that verifies if a ship with a certain mmsi exists already. The mmsi given to search doesn´t exist
     */
    @Test
    public void getShipByMMSI2() {
        //Arrange
        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCC");
        ship.setCodeFind(1);
        shipStore.saveShip(ship);

        //Act
        Ship ship2 = shipStore.getShipByMMSI(123456788);

        //Assert
        assertNull(ship2);
    }

    /**
     * Test that verifies if a ship with a certain imo exists already. The imo given to search exists
     */
    @Test
    public void getShipByIMO1() {
        //Arrange
        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");
        ship.setCodeFind(2);
        shipStore.saveShip(ship);

        //Act
        Ship ship2 = shipStore.getShipByIMO("IMO1234567");
        int result=ship2.getCodeFind();

        //Assert
        assertNotNull(ship2);
        assertEquals(2, result);
    }

    /**
     * Test that verifies if a ship with a certain imo exists already. The imo given to search doesn´t exist
     */
//    @Test
//    public void getShipByIMO2() {
//        //Arrange
//        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");
//        ship.setCodeFind(2);
//        shipStore.saveShip(ship);
//
//        //Act
//        Ship ship2 = shipStore.getShipByIMO("IMO1234566");
//
//        //Assert
//        assertNull(ship2);
//    }

    /**
     * Test that verifies if a ship with a certain call sign exists already. The call sign given to search exists
     */
    @Test
    public void getShipByCallSign1() {
        //Arrange
        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");
        ship.setCodeFind(3);
        shipStore.saveShip(ship);

        //Act
        Ship ship2 = shipStore.getShipByCallSign("ABCD");
        int result=ship2.getCodeFind();

        //Assert
        assertNotNull(ship2);
        assertEquals(3, result);
    }

    /**
     * Test that verifies if a ship with a certain imo exists already. The call sign given to search doesn´t exist
     */
//    @Test
//    public void getShipByCallSign2() {
//        //Arrange
//        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");
//        ship.setCodeFind(3);
//        shipStore.saveShip(ship);
//
//        //Act
//        Ship ship2 = shipStore.getShipByCallSign("ABCC");
//
//        //Assert
//        assertNull(ship2);
//    }

    /**
     * Test that verifies if it possible to create a ship with the same mmsi of a ship already stored
     */
    @Test
    public void newShip1() {
        //Arrange
        Ship ship1 = shipStore.createShip(123456789, "IMO1234567", "ABCD");
        shipStore.saveShip(ship1);

        //Act
        Ship ship2=new Ship(123456789, "IMO1234567", "ABCD");
        boolean expectedResult=shipStore.newShip(ship2);

        //Assert
        assertFalse(expectedResult);
    }

    /**
     * Test that verifies if it possible to create a ship with a different mmsi of a ship already stored
     */
    @Test
    public void newShip2() {
        //Arrange
        Ship ship1 = shipStore.createShip(123456789, "IMO1234567", "ABCD");
        shipStore.saveShip(ship1);

        //Act
        Ship ship2=new Ship(123456788, "IMO1234567", "ABCD");
        boolean expectedResult=shipStore.newShip(ship2);

        //Assert
        assertTrue(expectedResult);
    }

    /**
     * Test that validates the created ship
     */
    @Test
    public void validateShip1() {
        //Arrange
        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");

        //Act
        boolean result = shipStore.validateShip(ship);
        boolean expectedResult = true;

        //Assert
        assertEquals(result, expectedResult);
    }

    /**
     * Test that validates the created ship
     */
    @Test
    public void validateShip2() {
        //Arrange
        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");
        shipList.insert(ship);
        Ship ship2=new Ship(123456789, "IMO1234567", "ABCD");

        //Act
        boolean result = shipStore.validateShip(ship2);
        boolean expectedResult = false;

        //Assert
        assertEquals(result, expectedResult);
    }

    /**
     * Test that validates the created ship
     */
    @Test
    public void validateShip3() {
        //Arrange
        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");
        shipList.insert(ship);
        Ship ship2=null;

        //Act
        boolean result = shipStore.validateShip(ship2);
        boolean expectedResult = false;

        //Assert
        assertEquals(result, expectedResult);
    }

    /**
     * Test that saves the ship if its valid
     */
    @Test
    public void saveShip1() {
        //Arrange
        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");

        //Act
        boolean result = shipStore.saveShip(ship);
        boolean expectedResult = true;
        int result2=ship.getCodeFind();

        //Assert
        assertEquals(result, expectedResult);
        assertEquals(1, result2);
    }

    /**
     * Test that saves the ship if its valid
     */
    @Test
    public void saveShip2() {
        //Arrange
        Ship ship = shipStore.createShip(123456789, "IMO1234567", "ABCD");
        shipList.insert(ship);
        Ship ship2=new Ship(123456789, "IMO1234567", "ABCD");

        //Act
        boolean result = shipStore.saveShip(ship2);
        boolean expectedResult = false;

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void addDynamicData() {
    }

    @Test
    public void getShips() {
    }

    @Test
    public void calculateDistance() {
    }

    @Test
    public void getTemporalPeriodShips() {
    }

    @Test
    public void getMax() {
    }

    @Test
    public void groupByType() {
    }
}