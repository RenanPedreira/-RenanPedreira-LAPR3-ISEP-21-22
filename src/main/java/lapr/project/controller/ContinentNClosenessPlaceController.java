package lapr.project.controller;

import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;

import java.util.Map;

public class ContinentNClosenessPlaceController {

    /**
     * The freighted network instance, that later will receive the value of the company's freighted network.
     */
    FreightNetwork companyFreightNetWork;

    /**
     * The constructor of the class, with no parameter.
     */
    public ContinentNClosenessPlaceController() {
        this.companyFreightNetWork = App.getInstance().getCompany().getFreightNetwork();
    }

    /**
     * The function that will return a map with the n closeness places.
     *
     * @param continent that it wanted to know the closeness places.
     * @param n, number of the closeness places.
     * @return the map.
     */
    public Map<Integer,Pair<VertexLocation,Double>> getNClosenessPlaceByContinent(String continent, int n){
        return companyFreightNetWork.getNClosenessPlaceByContinent(continent,n);
    }
}
