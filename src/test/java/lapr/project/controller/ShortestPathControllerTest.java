package lapr.project.controller;

import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class ShortestPathControllerTest {
    FreightNetwork freightNetworkP = App.getInstance().getCompany().getFreightNetwork();
    ShortestPathController shortestPathController;

    @BeforeEach
    void setUp() {
        shortestPathController = new ShortestPathController();
    }

    /**
     * Test that tests if the departure exists using one that is stored
     */
    @Test
    void checkIfDepartureExist1() {
        AdjacencyMatrixGraph<VertexLocation, Double> matrix = new AdjacencyMatrixGraph<>();
        matrix.insertVertex(new VertexLocation("Portugal", "Lisbon", 10.0, 10.0, "Bnana Land", "Europe"));

        String location = "Lisbon";

        assertTrue(shortestPathController.checkIfDepartureExist(location, matrix));
    }

    /**
     * Test that tests if the departure exists using one that is not stored
     */
    @Test
    void checkIfDepartureExist2() {
        String location = "Afdsds";

        assertFalse(shortestPathController.checkIfDepartureExist(location, freightNetworkP.getFreightNetwork2()));
    }

    /**
     * Test that tests if the arrival exists using one that is stored
     */
    @Test
    void checkIfArrivalExist1() {
        AdjacencyMatrixGraph<VertexLocation, Double> matrix = new AdjacencyMatrixGraph<>();
        matrix.insertVertex(new VertexLocation("Portugal", "Lisbon", 10.0, 10.0, "Bnana Land", "America"));

        String location = "Lisbon";

        assertTrue(shortestPathController.checkIfArrivalExist(location, matrix));
    }

    /**
     * Test that tests if the arrival exists using one that is not stored
     */
    @Test
    void checkIfArrivalExist2() {
        String location = "Afdsds";

        assertFalse(shortestPathController.checkIfArrivalExist(location, freightNetworkP.getFreightNetwork2()));
    }

    /**
     * Test that tests the method that redirects the flow of the app via the option sent
     */
    @Test
    void directByOptionChosen() {
        List<VertexLocation> finalList1 = new ArrayList<>();

        shortestPathController.directByOptionChosen(0, "Lisbon", "Madrid", freightNetworkP, freightNetworkP.getFreightNetwork2());

        finalList1 = shortestPathController.getFinalList();

        assertNotNull(finalList1);

        shortestPathController.directByOptionChosen(1, "ASDAS", "MadASDASrid", freightNetworkP, freightNetworkP.getFreightNetwork2());

        finalList1 = shortestPathController.getFinalList();

        assertNull(finalList1);

        shortestPathController.directByOptionChosen(2, "ASDAS", "MadASDASrid", freightNetworkP, freightNetworkP.getFreightNetwork2());

        finalList1 = shortestPathController.getFinalList();

        assertNull(finalList1);
    }

    /**
     * Test that tests the method that does the shortest path by land using two cities
     */
    @Test
    void testShortestPathByLandWithTwoCities() {
        List<VertexLocation> finalList1 = new ArrayList<>();

        finalList1 = shortestPathController.land("Lisbon", "Vienna", freightNetworkP, freightNetworkP.getFreightNetwork2(), 0);

        assertNotNull(finalList1);
    }

    /**
     * Test that tests the method that does the shortest path by land using two ports
     */
    @Test
    void testShortestPathByLandWithTwoPorts() {
        List<VertexLocation> finalList1 = new ArrayList<>();

        finalList1 = shortestPathController.land("Venice", "Gdansk", freightNetworkP, freightNetworkP.getFreightNetwork2(), 0);

        assertNotNull(finalList1);
    }

    /**
     * Test that tests the method that does the shortest path by sea using two ports (one of them is not stored)
     */
    @Test
    void testShortestPathBySeaWithTwoPorts() {
        List<VertexLocation> finalList1 = new ArrayList<>();

        finalList1 = shortestPathController.maritime("sdadasd", "Genoa", freightNetworkP, freightNetworkP.getFreightNetwork2(), 1);

        assertNull(finalList1);
    }

    /**
     * Test that tests the method that does the shortest path by land using two cities
     */
    @Test
    void testShortestPathBySeaWithTwoCities() {
        List<VertexLocation> finalList1 = new ArrayList<>();

        finalList1 = shortestPathController.maritime("Lisbon", "Madrid", freightNetworkP, freightNetworkP.getFreightNetwork2(), 1);

        assertNull(finalList1);
    }

    /**
     * Test that tests the method that does the shortest path going through n indicated places
     */
    @Test
    void testNIndicatedPlaces() {
        List<VertexLocation> finalList1 = new ArrayList<>();
        List<VertexLocation> listOfPlaces=new ArrayList<>();
        String[] places=new String[3];
        places[0]="Madrid";
        places[1]="Rome";
        places[2]="Vienna";
        listOfPlaces=shortestPathController.getVertexLocationByString(freightNetworkP, places, 3);

        assertNotEquals(finalList1, shortestPathController.nIndicatedPlaces("Lisbon", "Berlin", freightNetworkP, freightNetworkP.getFreightNetwork2(), 3, listOfPlaces, 3, 1));
    }

    /**
     * Test that tests the method that returns a list with vertex location got using a string
     */
    @Test
    void testGetVertexLocationByString() {
        List<VertexLocation> listOfPlaces=new ArrayList<>();
        String[] places=new String[3];
        places[0]="Madrid";
        places[1]="Rome";
        places[2]="Vienna";

        listOfPlaces=shortestPathController.getVertexLocationByString(freightNetworkP, places, 3);

        assertNotNull(listOfPlaces);
    }

    /**
     * Test that tests the method that returns a list with vertex location got using incorrect strings
     */
    @Test
    void testGetVertexLocationByString2() {
        List<VertexLocation> listOfPlaces=new ArrayList<>();
        List<VertexLocation> aux=new ArrayList<>();
        String[] places=new String[3];
        places[0]="AAA";
        places[1]="BBB";
        places[2]="CCC";

        listOfPlaces=shortestPathController.getVertexLocationByString(freightNetworkP, places, 3);

        assertEquals(aux, listOfPlaces);
    }

    /**
     * Test that tests the method that returns a list with vertex location got using incorrect strings
     */
    @Test
    void testOrderList() {
        LinkedList<VertexLocation> result = new LinkedList<>();
        VertexLocation startLocal = freightNetworkP.getLocationByName("Lisbon");
        VertexLocation location1 = freightNetworkP.getLocationByName("Madrid");
        VertexLocation location2 = freightNetworkP.getLocationByName("Paris");
        VertexLocation location3 = freightNetworkP.getLocationByName("Vienna");
        result.add(startLocal);
        List<VertexLocation> listOfPlaces=new ArrayList<>();
        listOfPlaces.add(location1);
        listOfPlaces.add(location2);
        listOfPlaces.add(location3);

        List<Pair<VertexLocation, Double>> finalList=new ArrayList<>();
        finalList.add(0, new Pair<VertexLocation, Double>(location1, 0.0));
        finalList.add(1, new Pair<VertexLocation, Double>(location2, 0.0));
        finalList.add(2, new Pair<VertexLocation, Double>(location3, 0.0));

        assertNotNull(shortestPathController.orderList(freightNetworkP.getFreightNetwork2(), startLocal,
                listOfPlaces, 3, result, 3, 1));
    }

//    @Test
//    void landOrSea() {
//        List<VertexLocation> finalList1 = new ArrayList<>();
//
//        finalList1 = shortestPathController.landOrSea("Lisbon", "Madrid", freightNetworkP, freightNetworkP.getFreightNetwork2(), 2);
//
//        assertNotNull(finalList1);
//    }

    /**
     * Test that gets the list of full path
     */
    @Test
    void getFinalList() {
        List<VertexLocation> expectedResult = new ArrayList<>();

        List<VertexLocation> result = shortestPathController.getFinalList();

        assertEquals(expectedResult, result);
    }

    /**
     * Test that tests the method that returns a country by using a correct port
     */
    @Test
    void getCountryByPort1() {
        AdjacencyMatrixGraph<VertexLocation, Double> matrix = new AdjacencyMatrixGraph<>();
        matrix.insertVertex(new VertexLocation("Portugal", "Lisbon", 10.0, 10.0, "Bnana Land", "Europe"));

        assertNotNull(shortestPathController.getCountryByPort(new VertexLocation("13012", "Leixoes", 41.18333333, -8.7, "Portugal", "Europe"), matrix));
    }

    /**
     * Test that tests the method that returns a country by using an incorrect port
     */
    @Test
    void getCountryByPort2() {
        AdjacencyMatrixGraph<VertexLocation, Double> matrix = new AdjacencyMatrixGraph<>();
        matrix.insertVertex(new VertexLocation("Portugal", "Lisbon", 10.0, 10.0, "Bnana Land", "Europe"));

        assertNull(shortestPathController.getCountryByPort(new VertexLocation("13012", "AAA", 41.18333333, -8.7, "Spain", "Europe"), matrix));
    }
}