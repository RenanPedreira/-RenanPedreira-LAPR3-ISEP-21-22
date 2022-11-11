package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.store.ShipStore;
import lapr.project.tree.AVL;
import lapr.project.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Diogo Ferreira
 */
public class ReturnPairsShipsControllerTest {

    ReturnPairsShipsController controller = new ReturnPairsShipsController();
    private Ship ship1;
    private Ship ship2;
    private Ship ship3;
    private Ship ship4;
    private Ship ship5;
    private Ship ship6;
    private List<Ship> shipList;
    private List<Pair<Ship, Ship>> closeShipsAndDistance;

    @BeforeEach
    public void setUp() throws Exception {
        ShipStore shipStore = App.getInstance().getCompany().getShipStore();
        shipList = new ArrayList<>();
        closeShipsAndDistance = new ArrayList<>();
        //assertTrue();     //Ship's attributes
        Integer mmsi1 = 123456789;
        Integer mmsi2 = 123456781;
        Integer mmsi3 = 123456780;
        Integer mmsi4 = 123456778;
        Integer mmsi5 = 123456777;
        Integer mmsi6 = 123456776;
        String imo1 = "IMO1234567";
        String imo2 = "IMO1234566";
        String imo3 = "IMO1234565";
        String imo4 = "IMO1234564";
        String imo5 = "IMO1234563";
        String imo6 = "IMO1234562";
        String callSign1 = "ABCD";
        String callSign2 = "ABDD";
        String callSign3 = "ABBD";
        String callSign4 = "ABBB";
        String callSign5 = "ACBB";
        String callSign6 = "ACCB";

        //Ship
        ship1 = shipStore.createShip(mmsi1, imo1, callSign1);
        ship2 = shipStore.createShip(mmsi2, imo2, callSign2);
        ship3 = shipStore.createShip(mmsi3, imo3, callSign3);
        ship4 = shipStore.createShip(mmsi4, imo4, callSign4);
        ship5 = shipStore.createShip(mmsi5, imo5, callSign5);
        ship6 = shipStore.createShip(mmsi6, imo6, callSign6);

        ship1.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "VARAMO", "IMO9395044"));

        ship2.addDynamicData(new ShipDynamicData("31/12/2020 02:15", 28.08366, -88.50578, 11.5, 131.0, 131, "CMA CGM ALMAVIVA", "IMO9450648"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 02:30", 18.08366, -68.50578, 11.5, 131.0, 131, "CMA CGM ALMAVIVA", "IMO9450648"));

        ship3.addDynamicData(new ShipDynamicData("31/12/2020 02:15", 28.08566, -88.50678, 11.5, 130.0, 131, "CMA CGM ALMAVIVA", "IMO9450649"));
        ship3.addDynamicData(new ShipDynamicData("31/12/2020 02:45", 38.08566, -78.50678, 11.5, 130.0, 131, "CMA CGM ALMAVIVA", "IMO9450649"));
        ship3.addDynamicData(new ShipDynamicData("31/12/2020 03:15", 42.97875, -66.97001, 11.5, 130.0, 131, "CMA CGM ALMAVIVA", "IMO9450649"));

        ship4.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "VARAMO", "IMO9395044"));
        ship4.addDynamicData(new ShipDynamicData("31/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044"));
        ship4.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044"));

        ship5.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044"));

        shipStore.saveShip(ship1);
        shipStore.saveShip(ship2);
        shipStore.saveShip(ship3);

        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);

        closeShipsAndDistance.add(new Pair<>(ship1, ship2));
        closeShipsAndDistance.add(new Pair<>(ship1, ship3));
        closeShipsAndDistance.add(new Pair<>(ship2, ship3));
        closeShipsAndDistance.add(new Pair<>(ship1, ship4));
    }

    @Test
    void calculateTravelledDistance() {

        //Arrange
        double expectedResult = 0;

        //Act
        double result = controller.calculateTravelledDistance(ship1);

        //Assert
        assertNotEquals(expectedResult, result);

    }

    @Test
    void calculateDistance() {
        //Arrange
        double expectedResult = 0;

        //Act
        double result = controller.calculateDistance(ship1.getDynamicData().get(0).getLat(), ship1.getDynamicData().get(0).getLon(),
                ship2.getDynamicData().get(0).getLat(), ship2.getDynamicData().get(0).getLon());

        //Assert
        assertNotEquals(expectedResult, result);

        //Arrange
        ship1.getDynamicData().get(0).setLat(0.0);
        ship1.getDynamicData().get(0).setLon(0.0);
        ship2.getDynamicData().get(0).setLat(0.0);
        ship2.getDynamicData().get(0).setLon(0.0);

        //Act
        double result2 = controller.calculateDistance(ship1.getDynamicData().get(0).getLat(), ship1.getDynamicData().get(0).getLon(),
                ship2.getDynamicData().get(0).getLat(), ship2.getDynamicData().get(0).getLon());

        //Assert
        assertEquals(expectedResult, result2);
    }

    @Test
    void validateTravelledDistance1() {
        //Arrange
        double travelledDistance = 150;
        double min = 10;

        //Act
        boolean result = controller.validateTravelledDistance(travelledDistance, min);

        //Assert
        assertTrue(result);
    }

    @Test
    void validateTravelledDistance2() {
        //Arrange
        double travelledDistance = 2;
        double min = 10;

        //Act
        boolean result = controller.validateTravelledDistance(travelledDistance, min);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateTravelledDistance3() {
        //Arrange
        double travelledDistance = 10;
        double min = 10;

        //Act
        boolean result = controller.validateTravelledDistance(travelledDistance, min);

        //Assert
        assertTrue(result);
    }

    @Test
    void validateShipsArrivalDeparturePoint1() {
        //Arrange
        double distance = 150;
        double min = 10;

        //Act
        boolean result = controller.validateShipsArrivalDeparturePoint(distance, min);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateShipsArrivalDeparturePoint2() {
        //Arrange
        double distance = 5;
        double min = 10;

        //Act
        boolean result = controller.validateShipsArrivalDeparturePoint(distance, min);

        //Assert
        assertTrue(result);
    }

    @Test
    void validateShipsArrivalDeparturePoint3() {
        //Arrange
        double distance = 10;
        double min = 10;

        //Act
        boolean result = controller.validateShipsArrivalDeparturePoint(distance, min);

        //Assert
        assertTrue(result);
    }

    @Test
    void getAllShips1() {
        //Act
        shipList = controller.getAllShips();

        //Assert
        assertNotNull(shipList);
    }

    @Test
    void checkRoutesProximity1() {
        Ship ship1 = new Ship(123456789, "IMO1234567", "ABCD");
        Ship ship2 = new Ship(123456788, "IMO1234568", "ABCC");

        ship1.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "VARAMO", "IMO9395044"));

        ship2.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 34.7698, 66.9759, 13.3, 3.7, 356, "VARAMA", "IMO9395045"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 34.92236, 66.97243, 12.5, 2.4, 358, "VARAMA", "IMO9395045"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 34.97875, 66.97001, 12.9, 13.1, 355, "VARAMA", "IMO9395045"));

        boolean result1 = controller.checkRoutesProximity(ship1, ship2);
        boolean result2 = controller.checkRoutesProximity(ship5, ship2);
        boolean result3 = controller.checkRoutesProximity(ship1, ship5);

        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    void checkRoutesProximity2() {
        Ship ship1 = new Ship(123456789, "IMO1234567", "ABCD");
        Ship ship2 = new Ship(123456788, "IMO1234568", "ABCC");

        ship1.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 0.0, 0.0, 13.3, 3.7, 356, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:03", 1.0, 1.0, 12.5, 2.4, 358, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 1.1, 1.1, 12.9, 13.1, 355, "VARAMO", "IMO9395044"));

        ship2.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 0.0, 0.0, 13.3, 3.7, 356, "VARAMA", "IMO9395045"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 1.0, 1.0, 12.5, 2.4, 358, "VARAMA", "IMO9395045"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 1.1, 1.1, 12.9, 13.1, 355, "VARAMA", "IMO9395045"));

        boolean result = controller.checkRoutesProximity(ship1, ship2);

        assertTrue(result);
    }

    @Test
    void getAllPairsClosePorts() {
        Ship ship1 = new Ship(123456789, "IMO1234567", "ABCD");
        Ship ship2 = new Ship(123456788, "IMO1234568", "ABCC");
        Ship ship3 = new Ship(123456787, "IMO1234569", "ABCB");

        ship1.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "VARAMO", "IMO9395044"));

        ship2.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 34.7698, 66.9759, 13.3, 3.7, 356, "VARAMA", "IMO9395045"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 17:03", 34.92236, 66.97243, 12.5, 2.4, 358, "VARAMA", "IMO9395045"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 34.97875, 66.97001, 12.9, 13.1, 355, "VARAMA", "IMO9395045"));

        ship3.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMS", "IMO9395044"));
        ship3.addDynamicData(new ShipDynamicData("31/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMS", "IMO9395044"));
        ship3.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "VARAMS", "IMO9395044"));

        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);

        boolean result2 = controller.checkRoutesProximity(ship1, ship3);
        assertTrue(result2);

        closeShipsAndDistance.add(new Pair<>(ship1, ship3));

        List<Pair<Ship, Ship>> expected3 = closeShipsAndDistance;
        List<Pair<Ship, Ship>> result3 = controller.getAllPairsClosePorts(shipList);
        assertNotEquals(expected3, result3);
        assertFalse(result3.isEmpty());
    }

    @Test
    void getAllShips() {
        assertTrue(controller.getAllShips().containsAll(shipList));
    }

    @Test
    void getAllPairsClosePorts2() {
        List<Ship> ships = new ArrayList<>();
        int expected = 2;
        int result = ships.size();
        assertNotEquals(expected, result);

        assertTrue(ships.isEmpty());

        List result2 = controller.getAllPairsClosePorts(ships);
        assertTrue(result2.isEmpty());
    }

    @Test
    void getAllPairsClosePorts3() {
        List<Ship> listShips = new ArrayList<>();

        Ship ship1 = new Ship(123456789, "IMO1234567", "ABCD");
        Ship ship2 = new Ship(123456788, "IMO1234568", "ABCC");

        ship1.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "VARAMO", "IMO9395044"));

        ship2.addDynamicData(new ShipDynamicData("31/12/2020 16:20", 0.7698, 66.9759, 13.3, 3.7, 356, "VARAMA", "IMO9395045"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 17:03", 0.92236, 66.97243, 12.5, 2.4, 358, "VARAMA", "IMO9395045"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 17:19", 0.97875, 66.97001, 12.9, 13.1, 355, "VARAMA", "IMO9395045"));

        listShips.add(ship1);
        listShips.add(ship2);

        List result = controller.getAllPairsClosePorts(listShips);
        assertTrue(result.isEmpty());
    }

}
