package lapr.project.controller;

import lapr.project.graph.Algorithms;
import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import static org.testng.Assert.assertTrue;

class ContinentNClosenessPlaceControllerTest {
    CreateFreightNetworkController fnc = new CreateFreightNetworkController();
    ContinentNClosenessPlaceController controller = new ContinentNClosenessPlaceController();

    @Test
    void getNClosenessPlaceByContinent() {
        fnc.createFreightNetwork(1);

        ArrayList<String> centryNames = new ArrayList<>();
        centryNames.add("Mexico City");
        centryNames.add("Ottawa");
        centryNames.add("Washington");

        Map<Integer, Pair<VertexLocation, Double>> result1 = controller.getNClosenessPlaceByContinent("America", 3);

        for (Integer in : result1.keySet())
            assertTrue(centryNames.contains(result1.get(in).getFirst().getLocationName()));
    }

}