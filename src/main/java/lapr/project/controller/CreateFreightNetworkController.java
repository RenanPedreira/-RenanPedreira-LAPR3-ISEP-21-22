package lapr.project.controller;

import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.model.Company;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;

public class CreateFreightNetworkController {
    FreightNetwork freightNetwork;
    App app = App.getInstance();
    Company company = app.getCompany();

    public CreateFreightNetworkController() {
        this.freightNetwork = company.getFreightNetwork();
    }

    public FreightNetwork createFreightNetwork(int n) {
        freightNetwork.saveCountry();
        freightNetwork.savePort();
        freightNetwork.saveSeadists();
        freightNetwork.saveBorder();
        freightNetwork.connectPortsSameCountry();
        freightNetwork.connectClosestPortToCapital();
        freightNetwork.connectPortsWithNClosestPorts(n);

        return freightNetwork;
    }

    /**
     * Get freight network
     * @return adjency matrix
     */
    public AdjacencyMatrixGraph<VertexLocation, Double> getFreightNetwork() {
        return freightNetwork.getFreightNetwork2();
    }

}
