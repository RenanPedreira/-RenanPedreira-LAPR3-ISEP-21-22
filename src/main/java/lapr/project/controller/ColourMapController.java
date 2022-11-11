package lapr.project.controller;

import lapr.project.graph.Algorithms;
import lapr.project.graph.map.AdjacencyMapGraph;
import lapr.project.model.Company;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;



/**
 * @author Luís Araújo
 */
public class ColourMapController {
    /**
     * Instance of App
     */
    App app = App.getInstance();
    /**
     * Instance of Company
     */
    Company company = app.getCompany();

    /**
     * Instance of FreightNetwork
     */
    FreightNetwork freightNetwork;

    /**
     * Instance of algorithms
     */
    Algorithms algorithms = new Algorithms();

    /**
     * Constructor that gets the country store
     */
    public ColourMapController() {
        freightNetwork = company.getFreightNetwork();
    }

    /**
     * Method that returns the freight network
     *
     * @return freight network
     */
    public FreightNetwork getFreightNetwork() {
        return freightNetwork;
    }

    /**
     * Method that calls the method in the class Algorithms that colours the map sent by parameter
     *
     * @param map2
     * @return int array with the color assigned to each country
     */
    public int[] colorMap(AdjacencyMapGraph<VertexLocation, Double> map2) {
        return algorithms.coloringMap(map2);
    }
}
