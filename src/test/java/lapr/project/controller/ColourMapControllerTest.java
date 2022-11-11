package lapr.project.controller;

import lapr.project.graph.Algorithms;
import lapr.project.graph.map.AdjacencyMapGraph;
import lapr.project.model.Country;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.store.CountryStore;
import lapr.project.tree.AVL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Luís Araújo
 */
class ColourMapControllerTest {
    /**
     * Instance of CreateFreightNetworkController
     */
    CreateFreightNetworkController fnc = new CreateFreightNetworkController();
    /**
     * Instance of ColourMapController
     */
    ColourMapController colourMapController;
    /**
     * Instance of CountryStore
     */
    CountryStore countryStore;
    /**
     * Instance of Algorithms
     */
    Algorithms algorithms;
    /**
     * AVL tree of type Country
     */
    AVL<Country> countryAVL;
    /**
     * Adjacency Map Graph
     */
    AdjacencyMapGraph<VertexLocation, Double> map;

    @BeforeEach
    void setUp() {
        colourMapController = new ColourMapController();
        countryStore = new CountryStore(countryAVL);
        algorithms = new Algorithms();
    }

    /**
     * Test that tests the method that returns the freight network
     */
    @Test
    void getFreightNetwork() {
        FreightNetwork expectedResult = fnc.createFreightNetwork(0);

        FreightNetwork result = colourMapController.getFreightNetwork();

        assertEquals(expectedResult, result);
    }

    /**
     * Test that tests the method that colors the map
     */
    @Test
    void testAlgorithmOfColoringMap() {
        //Arrange
        FreightNetwork freightNetwork = fnc.createFreightNetwork(0);
        map = freightNetwork.getFreightNetwork3();

        int[] expectedResult = new int[map.numVertices()];
        Arrays.fill(expectedResult, -1);

        //Act
        int[] result = colourMapController.colorMap(map);

        //Assert
        assertNotEquals(expectedResult[0], result[0]);
    }
}