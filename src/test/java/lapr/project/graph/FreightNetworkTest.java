package lapr.project.graph;

import lapr.project.controller.App;
import lapr.project.controller.CreateFreightNetworkController;
import lapr.project.controller.GetCriticalPortsController;
import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.model.Colors;
import lapr.project.model.Country;
import lapr.project.model.Port;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import sun.security.provider.certpath.Vertex;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FreightNetworkTest {
    CreateFreightNetworkController fnc = new CreateFreightNetworkController();
    FreightNetwork freightNetworkP = App.getInstance().getCompany().getFreightNetwork();
    FreightNetwork freightNetwork2 = new FreightNetwork();

    @BeforeEach
    void setUp() {
        freightNetwork2.saveCountry();
        freightNetwork2.savePort();
        freightNetwork2.saveBorder();
        freightNetwork2.saveSeadists();
        freightNetwork2.connectPortsSameCountry();
        freightNetwork2.connectClosestPortToCapital();
        freightNetwork2.connectPortsWithNClosestPorts(0);
    }

    @Test
    public void CreateNetworkTest(){
        assertTrue(freightNetwork2.getFreightNetwork2().numVertices() == 146);
        assertTrue(freightNetwork2.getFreightNetwork2().numEdges() == 3027);
    }

    @Test
    public void CreateNetworkTest2() {
        fnc.createFreightNetwork(10);
        assertTrue(freightNetworkP.getFreightNetwork2().numEdges() > 3027);
    }

    @Test
    public void testGetFreightedNetwork(){
        AdjacencyMatrixGraph<VertexLocation,Double> freightNetwork2=fnc.getFreightNetwork();
        assertEquals(freightNetworkP.getFreightNetwork2(),freightNetwork2);
    }

    @Test
    public void testGetCriticalPortsFreightNetwork(){
        freightNetworkP.setFreightNetwork(freightNetwork2.getFreightNetwork2());
        GetCriticalPortsController gc = new GetCriticalPortsController();
        List<Pair<VertexLocation, Integer>> portCount = gc.getCriticalPorts(3);

        assertEquals("London", portCount.get(0).getFirst().getLocationName());
        assertEquals(12461, +portCount.get(0).getSecond());

        assertEquals("Monaco", portCount.get(1).getFirst().getLocationName());
        assertEquals(11507, +portCount.get(1).getSecond());

        assertEquals("Stockholm", portCount.get(2).getFirst().getLocationName());
        assertEquals(8393, +portCount.get(2).getSecond());

    }

    @Test
    public void testGetCriticalPorts(){
        AdjacencyMatrixGraph<VertexLocation, Double> matrix = new AdjacencyMatrixGraph<>();

        //There are 7 ports
        Port v1 = new Port(10000, "loc 1", "Country 1", 10.0, 10.0, "Continent 1");
        Port v2 = new Port(20000, "loc 2", "Country 2", 20.0, 20.0, "Continent 1");
        Port v3 = new Port(30000, "loc 3", "Country 3", 30.0, 30.0, "Continent 2");
        Port v4 = new Port(40000, "loc 4", "Country 4", 40.0, 40.0, "Continent 2");
        Port v5 = new Port(50000, "loc 5", "Country 4", 50.0, 50.0, "Continent 3");
        Port v6 = new Port(60000, "loc 6", "Country 4", 60.0, 60.0, "Continent 3");
        Port v7 = new Port(70000, "loc 7", "Country 4", 80.0, 70.0, "Continent 4");
        Country v8 = new Country("Country 1", "CO", "COU", "Capital 1", 70.0, 80.0, 80.0, "Continent 1");

        //Insert the vertices
        matrix.insertVertex(v1);
        matrix.insertVertex(v2);
        matrix.insertVertex(v3);
        matrix.insertVertex(v4);
        matrix.insertVertex(v8); //Capital
        matrix.insertVertex(v5);
        matrix.insertVertex(v6);
        matrix.insertVertex(v7);


        //Port 1 has 6 connection
        matrix.insertEdge(v1, v2, 10.0);
        matrix.insertEdge(v1, v3, 10.0);
        matrix.insertEdge(v1, v4, 10.0);
        matrix.insertEdge(v1, v5, 10.0);
        matrix.insertEdge(v1, v6, 10.0);
        matrix.insertEdge(v1, v7, 10.0);
        matrix.insertEdge(v1, v8, 10.0);

        //Port 2 has 5 connection
        matrix.insertEdge(v2, v3, 15.0);
        matrix.insertEdge(v2, v4, 15.0);
        matrix.insertEdge(v2, v5, 15.0);
        matrix.insertEdge(v2, v6, 15.0);
        matrix.insertEdge(v2, v7, 15.0);
        matrix.insertEdge(v2, v8, 15.0);

        matrix.insertEdge(v3, v4, v3.calculateDistanceDifference(v4));
        matrix.insertEdge(v3, v5, v3.calculateDistanceDifference(v5));
        matrix.insertEdge(v3, v6, v3.calculateDistanceDifference(v6));
        matrix.insertEdge(v3, v7, v3.calculateDistanceDifference(v7));
        matrix.insertEdge(v3, v8, v3.calculateDistanceDifference(v8));

        matrix.insertEdge(v4, v5, v4.calculateDistanceDifference(v5));
        matrix.insertEdge(v4, v6, v4.calculateDistanceDifference(v6));
        matrix.insertEdge(v4, v7, v4.calculateDistanceDifference(v7));
        matrix.insertEdge(v4, v8, v4.calculateDistanceDifference(v8));

        matrix.insertEdge(v5, v6, v5.calculateDistanceDifference(v6));
        matrix.insertEdge(v5, v7, v5.calculateDistanceDifference(v7));
        matrix.insertEdge(v5, v8, v5.calculateDistanceDifference(v8));

        matrix.insertEdge(v6, v7, v6.calculateDistanceDifference(v7));
        matrix.insertEdge(v6, v8, v6.calculateDistanceDifference(v8));

        matrix.insertEdge(v7, v8, v7.calculateDistanceDifference(v8));

        freightNetwork2.setFreightNetwork(matrix);
        List<Pair<VertexLocation, Integer>> portCount = freightNetwork2.getNCriticalPorts(7);
        System.out.println(portCount.get(0).getFirst()+" : "+portCount.get(0).getSecond());
        System.out.println(portCount.get(1).getFirst()+" : "+portCount.get(1).getSecond());
        System.out.println(portCount.get(2).getFirst()+" : "+portCount.get(2).getSecond());
        System.out.println(portCount.get(3).getFirst()+" : "+portCount.get(3).getSecond());
        System.out.println(portCount.get(4).getFirst()+" : "+portCount.get(4).getSecond());
        System.out.println(portCount.get(5).getFirst()+" : "+portCount.get(5).getSecond());
        System.out.println(portCount.get(6).getFirst()+" : "+portCount.get(6).getSecond());

        assertEquals("10000", portCount.get(0).getFirst().getLocationID());
        assertEquals(45, portCount.get(0).getSecond());
    }

    @Test
    public void testGetCriticalPortsUniquePath(){
        AdjacencyMatrixGraph<VertexLocation, Double> matrix = new AdjacencyMatrixGraph<>();

        //There are 7 ports
        Port v1 = new Port(10000, "loc 1", "Country 1", 10.0, 10.0, "Continent 1");
        Port v2 = new Port(20000, "loc 2", "Country 2", 20.0, 20.0, "Continent 1");
        Port v3 = new Port(30000, "loc 3", "Country 3", 30.0, 30.0, "Continent 2");
        Port v4 = new Port(40000, "loc 4", "Country 4", 40.0, 40.0, "Continent 2");
        Port v5 = new Port(50000, "loc 5", "Country 4", 50.0, 50.0, "Continent 3");
        Port v6 = new Port(60000, "loc 6", "Country 4", 60.0, 60.0, "Continent 3");
        Port v7 = new Port(70000, "loc 7", "Country 4", 80.0, 70.0, "Continent 4");


        //Insert the vertices
        matrix.insertVertex(v1);
        matrix.insertVertex(v2);
        matrix.insertVertex(v3);
        matrix.insertVertex(v4);
        matrix.insertVertex(v5);
        matrix.insertVertex(v6);
        matrix.insertVertex(v7);

        //Port 1 has 6 connection
        matrix.insertEdge(v1, v2, v1.calculateDistanceDifference(v2));
        matrix.insertEdge(v1, v3, v1.calculateDistanceDifference(v3));
        matrix.insertEdge(v1, v4, v1.calculateDistanceDifference(v4));
        matrix.insertEdge(v1, v5, v1.calculateDistanceDifference(v5));
        matrix.insertEdge(v1, v6, v1.calculateDistanceDifference(v6));
        matrix.insertEdge(v1, v7, v1.calculateDistanceDifference(v7));



        freightNetwork2.setFreightNetwork(matrix);
        List<Pair<VertexLocation, Integer>> portCount = freightNetwork2.getNCriticalPorts(7);
        System.out.println(portCount.get(0).getFirst()+" : "+portCount.get(0).getSecond());
        System.out.println(portCount.get(1).getFirst()+" : "+portCount.get(1).getSecond());
        System.out.println(portCount.get(2).getFirst()+" : "+portCount.get(2).getSecond());
        System.out.println(portCount.get(3).getFirst()+" : "+portCount.get(3).getSecond());
        System.out.println(portCount.get(4).getFirst()+" : "+portCount.get(4).getSecond());
        System.out.println(portCount.get(5).getFirst()+" : "+portCount.get(5).getSecond());
        System.out.println(portCount.get(6).getFirst()+" : "+portCount.get(6).getSecond());

        assertEquals("10000", portCount.get(0).getFirst().getLocationID());
        assertEquals(43, portCount.get(0).getSecond());

    }

    @Test
    public void testGetPortList() {
        List<Port> expectedResult=new ArrayList<>();

        List<Port> result=freightNetworkP.getPortList();

        assertNotEquals(expectedResult, result);
    }

    @Test
    public void testGetCountryList() {
        List<Country> expectedResult=new ArrayList<>();

        List<Country> result=freightNetworkP.getCountryList();

        assertNotEquals(expectedResult, result);
    }

    @Test
    public void testGetLocationUsingTheRightName(){
        String location="Lisbon";

        VertexLocation result=freightNetworkP.getLocationByName(location);

        assertNotEquals(result, new VertexLocation(null, null, 0.0, 0.0, null, null));
    }

    @Test
    public void testGetLocationUsingTheWrongName(){
        String location="Abababab";
        VertexLocation expectedResult=null;
        for(VertexLocation l: freightNetworkP.getFreightNetwork2().vertices()){
            if(l.getLocationName().equals(location))
                expectedResult=l;
        }

        assertNull(expectedResult);
    }
}
