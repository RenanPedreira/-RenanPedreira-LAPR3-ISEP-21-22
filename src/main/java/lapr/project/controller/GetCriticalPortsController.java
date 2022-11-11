package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;

import java.util.List;

public class GetCriticalPortsController {

    FreightNetwork freightNetwork;
    App app = App.getInstance();
    Company company = app.getCompany();

    public GetCriticalPortsController() {
        this.freightNetwork = company.getFreightNetwork();
    }

    public List<Pair<VertexLocation, Integer>> getCriticalPorts(Integer n){
        return  freightNetwork.getNCriticalPorts(n);
    }
}
