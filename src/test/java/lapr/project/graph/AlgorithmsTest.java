package lapr.project.graph;

import lapr.project.controller.CreateFreightNetworkController;
import lapr.project.graph.map.AdjacencyMapGraph;
import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.model.Colors;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class AlgorithmsTest {
    /**
     * Instance of CreateFreightNetworkController
     */
    CreateFreightNetworkController fnc = new CreateFreightNetworkController();
    /**
     * Adjacency Map Graph
     */
    AdjacencyMapGraph<VertexLocation, Double> map;
    /**
     * Instance of algorithms
     */
    Algorithms algorithms;

    AdjacencyMatrixGraph <String, Double> distanceMap = new AdjacencyMatrixGraph<>();

    @BeforeEach
    void setUp() {
        algorithms = new Algorithms();

        distanceMap.insertVertex("Porto");
        distanceMap.insertVertex("Braga");
        distanceMap.insertVertex("Vila Real");
        distanceMap.insertVertex("Aveiro");
        distanceMap.insertVertex("Coimbra");
        distanceMap.insertVertex("Leiria");

        distanceMap.insertVertex("Viseu");
        distanceMap.insertVertex("Guarda");
        distanceMap.insertVertex("Castelo Branco");
        distanceMap.insertVertex("Lisboa");
        distanceMap.insertVertex("Faro");
        distanceMap.insertVertex("Évora");


        distanceMap.insertEdge("Porto", "Aveiro", 75.0);
        distanceMap.insertEdge("Porto", "Braga", 60.0);
        distanceMap.insertEdge("Porto", "Vila Real", 100.0);
        distanceMap.insertEdge("Viseu", "Guarda", 75.0);
        distanceMap.insertEdge("Guarda",  "Castelo Branco", 100.0);
        distanceMap.insertEdge("Aveiro", "Coimbra", 60.0);
        distanceMap.insertEdge("Coimbra", "Lisboa", 200.0);
        distanceMap.insertEdge("Coimbra",  "Leiria",  80.0);
        distanceMap.insertEdge("Aveiro", "Leiria", 120.0);
        distanceMap.insertEdge("Leiria", "Lisboa", 150.0);


        distanceMap.insertEdge("Aveiro", "Viseu", 85.0);
        distanceMap.insertEdge("Leiria", "Castelo Branco", 170.0);
        distanceMap.insertEdge("Lisboa", "Faro", 280.0);
    }

    /**
     * Test that tests the method that colors the map with a freight network created
     */
    @Test
    void testMethodOfColoringMap() {
        //Arrange
        FreightNetwork freightNetwork = fnc.createFreightNetwork(0);
        map = freightNetwork.getFreightNetwork3();

        int[] expectedResult = new int[map.numVertices()];
        Arrays.fill(expectedResult, -1);

        //Act
        int[] result = algorithms.coloringMap(map);

        //Assert
        assertNotEquals(expectedResult[0], result[0]);
    }

    /**
     * Test that tests the method that colors the map created with 6 vertex locations
     */
    @Test
    void coloringMap2() {
        VertexLocation vl = new VertexLocation("Spain", "Madrid", 40.4, -3.683333, null, "Europe");
        VertexLocation vl2 = new VertexLocation("Portugal", "Lisbon", 40.5, -3.65, null, "Europe");
        VertexLocation vl3 = new VertexLocation("Spain2", "Madrid2", 41.4, -4.683333, null, "Europe");
        VertexLocation vl4 = new VertexLocation("Portugal2", "Lisbon2", 41.5, -4.65, null, "Europe");
        VertexLocation vl5 = new VertexLocation("Spain3", "Madri3", 42.4, -5.683333, null, "Europe");
        VertexLocation vl6 = new VertexLocation("Portugal3", "Lisbo3", 42.5, -5.65, null, "Europe");

        AdjacencyMapGraph<VertexLocation, Double> mapGraph = new AdjacencyMapGraph<>(false);

        mapGraph.addVertex(vl);
        mapGraph.addVertex(vl2);
        mapGraph.addVertex(vl3);
        mapGraph.addVertex(vl4);
        mapGraph.addVertex(vl5);
        mapGraph.addVertex(vl6);

        mapGraph.addEdge(vl, vl2, 1.0);
        mapGraph.addEdge(vl2, vl3, 1.0);
        mapGraph.addEdge(vl3, vl4, 1.0);
        mapGraph.addEdge(vl4, vl5, 1.0);
        mapGraph.addEdge(vl5, vl6, 1.0);
        mapGraph.addEdge(vl6, vl, 1.0);

        int[] colors = new int[mapGraph.numVertices()];
        colors[0] = 0;
        colors[1] = 1;
        colors[2] = 2;
        colors[3] = 3;
        colors[4] = 4;
        colors[5] = 5;

        //Act
        int[] result = algorithms.coloringMap(mapGraph);

        //Assert
        assertEquals(colors[0], result[0]);
        assertEquals(colors[1], result[1]);
        assertNotEquals(colors[2], result[2]);
        assertNotEquals(colors[3], result[3]);
        assertNotEquals(colors[4], result[4]);
        assertNotEquals(colors[5], result[5]);
    }

    /**
     * Test that tests the method that colors the map created with 2 vertex locations
     */
    @Test
    void coloringMap3() {
        VertexLocation vl = new VertexLocation("Spain", "Madrid", 40.4, -3.683333, null, "Europe");
        VertexLocation vl2 = new VertexLocation("Portugal", "Lisbon", 40.5, -3.65, null, "Europe");

        AdjacencyMapGraph<VertexLocation, Double> mapGraph = new AdjacencyMapGraph<>(false);

        mapGraph.addVertex(vl);
        mapGraph.addVertex(vl2);

        mapGraph.addEdge(vl, vl2, 1.0);

        int[] colors = new int[mapGraph.numVertices()];
        colors[0] = 0;
        colors[1] = 1;

        //Act
        int[] result = algorithms.coloringMap(mapGraph);

        //Assert
        assertEquals(colors[0], result[0]);
    }

    /**
     * Test that verifies if the adjacent vertex has the color. In this test, the vertex location sent to color with
     * color 1 has an adjacent vertex with that.
     */
    @Test
    void adjacentVertexHasTheColor() {
        VertexLocation vl = new VertexLocation("Spain", "Madrid", 40.4, -3.683333, null, "Europe");
        VertexLocation vl2 = new VertexLocation("Portugal", "Lisbon", 40.5, -3.65, null, "Europe");
        AdjacencyMapGraph<VertexLocation, Double> mapGraph = new AdjacencyMapGraph<>(false);
        mapGraph.addVertex(vl);
        mapGraph.addVertex(vl2);
        if (mapGraph.addEdge(vl, vl2, 1.0)) {
            vl2.setColor(Colors.BLUE);
        }
        assertFalse(algorithms.checkIfAdjVertsHaveTheColor(0, vl, mapGraph));
    }

    /**
     * Test that verifies if the adjacent vertex has the color. In this test, the vertex location sent to color with
     * color 1 has not an adjacent vertex with that.
     */
    @Test
    void adjacentVertexHasNotTheColor() {
        FreightNetwork freightNetwork = fnc.createFreightNetwork(0);
        map = freightNetwork.getFreightNetwork3();

        VertexLocation vl = null;
        for (VertexLocation vertexLocation : map.vertices()) {
            if (vertexLocation.getLocationID().equals("Portugal"))
                vl = vertexLocation;
            if (vertexLocation.getLocationID().equals("France"))
                vertexLocation.setColor(Colors.BLUE);
        }

        assertTrue(algorithms.checkIfAdjVertsHaveTheColor(0, vl, map));

    }

    /**
     * Test of shortestPath method by land path of class GraphAlgorithms.
     */
    @Test
    public void testShortestPathLand() {
        System.out.println("Test of shortest path");

        LinkedList<String> path = new LinkedList<>();

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "LX",  path, 0, -1) == -1, "Should be -1 if vertex does not exist");

        assertFalse(Algorithms.shortestPath(distanceMap, "Porto", "Évora", path, 0, -1)==-1, "Should be -1 if there is no path");

        assertTrue(Algorithms.shortestPath(distanceMap,  "Porto",  "Porto",  path, 0, -1) == 0, "Should be 0 if source and vertex are the same");

        assertTrue(path.size() == 1, "Path should be single vertex if source and vertex are the same");

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "Lisboa",  path, 0, -1) == 335, "Path between Porto and Lisboa should be 335 Km");


        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path, 0, -1) == 335, "Path between Porto and Castelo Branco should be 335 Km");
        assertFalse(path.size() == 5, "Path between Porto and Castelo Branco should be 5 cities");

        // Changing Viseu to Guarda should change shortest path between Porto and Castelo Branco

        distanceMap.removeEdge("Viseu", "Guarda");
        distanceMap.insertEdge("Viseu", "Guarda", 125.0);

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path, 0, -1) == 365, "Path between Porto and Castelo Branco should now be 365 Km");
        assertFalse(path.size() == 4, "Path between Porto and Castelo Branco should be 4 cities");


    }

    /**
     * Test of shortestPath method by sea path of class GraphAlgorithms.
     */
    @Test
    public void testShortestPathSea() {
        System.out.println("Test of shortest path");

        LinkedList<String> path = new LinkedList<>();

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "LX",  path, 1, -1) == -1, "Should be -1 if vertex does not exist");

        assertFalse(Algorithms.shortestPath(distanceMap, "Porto", "Évora", path, 1, -1)==-1, "Should be -1 if there is no path");

        assertTrue(Algorithms.shortestPath(distanceMap,  "Porto",  "Porto",  path, 1, -1) == 0, "Should be 0 if source and vertex are the same");

        assertTrue(path.size() == 1, "Path should be single vertex if source and vertex are the same");

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "Lisboa",  path, 1, -1) == 335, "Path between Porto and Lisboa should be 335 Km");


        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path, 1, -1) == 335, "Path between Porto and Castelo Branco should be 335 Km");
        assertFalse(path.size() == 5, "Path between Porto and Castelo Branco should be 5 cities");

        // Changing Viseu to Guarda should change shortest path between Porto and Castelo Branco

        distanceMap.removeEdge("Viseu", "Guarda");
        distanceMap.insertEdge("Viseu", "Guarda", 125.0);

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path, 1, -1) == 365, "Path between Porto and Castelo Branco should now be 365 Km");
        assertFalse(path.size() == 4, "Path between Porto and Castelo Branco should be 4 cities");


    }

    /**
     * Test of shortestPath method by land and sea path of class GraphAlgorithms.
     */
    @Test
    public void testShortestPathLandAndSea() {
        System.out.println("Test of shortest path");

        LinkedList<String> path = new LinkedList<>();

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "LX",  path, 2, -1) == -1, "Should be -1 if vertex does not exist");

        assertFalse(Algorithms.shortestPath(distanceMap, "Porto", "Évora", path, 2, -1)==-1, "Should be -1 if there is no path");

        assertTrue(Algorithms.shortestPath(distanceMap,  "Porto",  "Porto",  path, 2, -1) == 0, "Should be 0 if source and vertex are the same");

        assertTrue(path.size() == 1, "Path should be single vertex if source and vertex are the same");

        assertTrue(Algorithms.shortestPath(distanceMap,  "Porto",  "Lisboa",  path, 2, -1) == 335, "Path between Porto and Lisboa should be 335 Km");


        assertTrue(Algorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path, 2, -1) == 335, "Path between Porto and Castelo Branco should be 335 Km");
        assertTrue(path.size() == 5, "Path between Porto and Castelo Branco should be 5 cities");

        // Changing Viseu to Guarda should change shortest path between Porto and Castelo Branco

        distanceMap.removeEdge("Viseu", "Guarda");
        distanceMap.insertEdge("Viseu", "Guarda", 125.0);

        assertTrue(Algorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path, 2, -1) == 365, "Path between Porto and Castelo Branco should now be 365 Km");
        assertTrue(path.size() == 4, "Path between Porto and Castelo Branco should be 4 cities");


    }

    /**
     * Test of shortestPath method through n indicated places of class GraphAlgorithms.
     */
    @Test
    public void testShortestPathNIndicatedPlaces() {
        System.out.println("Test of shortest path");

        LinkedList<String> path = new LinkedList<>();

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "LX",  path, 3, 0) == -1, "Should be -1 if vertex does not exist");

        assertFalse(Algorithms.shortestPath(distanceMap, "Porto", "Évora", path, 3, 0)==-1, "Should be -1 if there is no path");

        assertTrue(Algorithms.shortestPath(distanceMap,  "Porto",  "Porto",  path, 3, 0) == 0, "Should be 0 if source and vertex are the same");

        assertFalse(path.size() == 1, "Path should be single vertex if source and vertex are the same");

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "Lisboa",  path, 3, 0) == 335, "Path between Porto and Lisboa should be 335 Km");


        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path, 3, 0) == 335, "Path between Porto and Castelo Branco should be 335 Km");
        assertFalse(path.size() == 5, "Path between Porto and Castelo Branco should be 5 cities");

        // Changing Viseu to Guarda should change shortest path between Porto and Castelo Branco

        distanceMap.removeEdge("Viseu", "Guarda");
        distanceMap.insertEdge("Viseu", "Guarda", 125.0);

        assertFalse(Algorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path, 3, 0) == 365, "Path between Porto and Castelo Branco should now be 365 Km");
        assertFalse(path.size() == 4, "Path between Porto and Castelo Branco should be 4 cities");
    }
}