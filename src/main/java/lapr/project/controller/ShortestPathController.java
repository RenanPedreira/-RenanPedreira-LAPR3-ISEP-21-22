package lapr.project.controller;

import lapr.project.graph.Algorithms;
import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.model.Country;
import lapr.project.model.Port;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class ShortestPathController {
    private static double distance = 0;
    private List<VertexLocation> finalList = new ArrayList<>();

    /**
     * Method that verifies if a certain local of departure exists
     * @param departureLocal
     * @param matrix
     * @return true if exists and false otherwise
     */
    public boolean checkIfDepartureExist(String departureLocal, AdjacencyMatrixGraph<VertexLocation, Double> matrix) {
        for (VertexLocation vl : matrix.vertices()) {
            if (vl.getLocationName().equals(departureLocal))
                return true;
        }
        return false;
    }

    /**
     * Method that verifies if a certain local of arrival exists
     * @param arrivalLocal
     * @param matrix
     * @return true if exists and false otherwise
     */
    public boolean checkIfArrivalExist(String arrivalLocal, AdjacencyMatrixGraph<VertexLocation, Double> matrix) {
        for (VertexLocation vl : matrix.vertices()) {
            if (vl.getLocationName().equals(arrivalLocal))
                return true;
        }
        return false;
    }

    /**
     * Method that directs to the certain method that discovers the shortest path through the option chosen: land, maritime or land or sea path
     *
     * @param optionChosen
     */
    public void directByOptionChosen(int optionChosen, String initial, String end, FreightNetwork fr, AdjacencyMatrixGraph<VertexLocation, Double> matrix) {
        switch (optionChosen) {
            case 0:
                finalList = land(initial, end, fr, matrix, optionChosen);
                break;
            case 1:
                finalList = maritime(initial, end, fr, matrix, optionChosen);
                break;
            case 2:
                finalList = landOrSea(initial, end, fr, matrix, optionChosen);
                break;
            default:
                break;
        }
    }

    /**
     * Method that gets the list with the land path from the initial local and the end local
     * @param initial
     * @param end
     * @param fr
     * @param matrix
     * @param option
     * @return land path
     */
    public List<VertexLocation> land(String initial, String end, FreightNetwork fr, AdjacencyMatrixGraph<VertexLocation, Double> matrix, int option) {
        LinkedList<VertexLocation> result = new LinkedList<>();

        VertexLocation startLocal = fr.getLocationByName(initial);
        VertexLocation endLocal = fr.getLocationByName(end);

        if (startLocal instanceof Port)
            startLocal = getCountryByPort(startLocal, matrix);

        if (endLocal instanceof Port)
            endLocal = getCountryByPort(endLocal, matrix);

        setDistance(Algorithms.shortestPath(matrix, startLocal, endLocal, result, option, -1));

        return result;
    }

    /**
     * Method that gets the list with the maritime path from the initial local and the end local
     * @param initial
     * @param end
     * @param fr
     * @param matrix
     * @param option
     * @return maritime path
     */
    public List<VertexLocation> maritime(String initial, String end, FreightNetwork fr, AdjacencyMatrixGraph<VertexLocation, Double> matrix, int option) {
        LinkedList<VertexLocation> result = new LinkedList<>();

        VertexLocation startLocal = fr.getLocationByName(initial);
        VertexLocation endLocal = fr.getLocationByName(end);

        if (!(startLocal instanceof Port) || !(endLocal instanceof Port))
            return null;

        setDistance(Algorithms.shortestPath(matrix, startLocal, endLocal, result, option, -1));

        return result;
    }

    /**
     * Method that gets the list with the land or sea path from the initial local and the end local
     * @param initial
     * @param end
     * @param fr
     * @param matrix
     * @param option
     * @return land or sea path
     */
    public List<VertexLocation> landOrSea(String initial, String end, FreightNetwork fr, AdjacencyMatrixGraph<VertexLocation, Double> matrix, int option) {
        LinkedList<VertexLocation> result = new LinkedList<>();

        VertexLocation startLocal = fr.getLocationByName(initial);
        VertexLocation endLocal = fr.getLocationByName(end);

        if (!((startLocal instanceof Port) || (startLocal instanceof Country) || (endLocal instanceof Port) || (endLocal instanceof Country))) {
            return null;
        }

        setDistance(Algorithms.shortestPath(matrix, startLocal, endLocal, result, option, -1));

        return result;
    }

    /**
     * Method that gets the list with the path passing through n indicated places
     * @param initial
     * @param end
     * @param fr
     * @param matrix
     * @param numberOfPlaces
     * @param listOfPlaces
     * @param opt
     * @param opt2
     * @return path passing through n indicated places
     */
    public List<VertexLocation> nIndicatedPlaces(String initial, String end, FreightNetwork fr, AdjacencyMatrixGraph<VertexLocation, Double> matrix, int numberOfPlaces, List<VertexLocation> listOfPlaces, int opt, int opt2) {
        LinkedList<VertexLocation> result = new LinkedList<>();
        LinkedList<VertexLocation> aux = new LinkedList<>();

        VertexLocation startLocal = fr.getLocationByName(initial);
        VertexLocation endLocal = fr.getLocationByName(end);

        if (!((startLocal instanceof Port) || (startLocal instanceof Country) || (endLocal instanceof Port) || (endLocal instanceof Country))) {
            return null;
        }

        //Adds the start location to the final list with the path so that this local is the first
        result.add(startLocal);

        //List with a Pair of a certain local and their distance from the start local to another local
        List<Pair<VertexLocation, Double>> localsAndTheirDistance = new ArrayList<>();

        //Counter to confirm that the number of iterations is not exceeded
        int counter = 0;
        //Index to guarantee that the closest vertex location and its distance are put in the next position in the final list
        int index=1;
        //Number of places that is going to be used in the cycle because the true value is being decremented
        int nPlacesAux=numberOfPlaces;
        //While the algorithm does not calculate all the distances between the start local and the rest
        while (counter < nPlacesAux) {
            //Sorting the list in ascending order of distance in order to know which is the closest place to put it in the final list
            localsAndTheirDistance = orderList(matrix, startLocal, listOfPlaces, numberOfPlaces, aux, opt, opt2);
            //Closest local is going to the second place on the final list
            result.add(index, localsAndTheirDistance.get(0).getFirst());
            //New start local is updated
            startLocal = localsAndTheirDistance.get(0).getFirst();
            //Number of iterations incremented
            counter++;
            //Number of places to verify is decremented
            numberOfPlaces--;
            //The local that went to the final list is being removed from the list of places that are not in the final list yet
            listOfPlaces.remove(localsAndTheirDistance.get(0).getFirst());
            //Index of the final list is incremented
            index++;
        }

        //End local is added to the final list and to the last place
        result.add(endLocal);
        finalList = result;
        return result;
    }

    /**
     * Method that orders the list of vertex locations in ascending order of their distance to the start local
     * @param matrix
     * @param startLocal
     * @param listOfPlaces
     * @param numPlaces
     * @param result
     * @param opt
     * @param opt2
     * @return list of vertex locations in ascending order of their distance to the start local
     */
    public List<Pair<VertexLocation, Double>> orderList(AdjacencyMatrixGraph<VertexLocation, Double> matrix, VertexLocation startLocal, List<VertexLocation> listOfPlaces, int numPlaces, LinkedList<VertexLocation> result, int opt, int opt2) {
        List<Pair<VertexLocation, Double>> localAndDistance = new ArrayList<>();

        for (int i = 0; i < numPlaces; i++) {
                localAndDistance.add(new Pair<VertexLocation, Double>(listOfPlaces.get(i),
                        Algorithms.shortestPath(matrix, startLocal, listOfPlaces.get(i), result, opt, opt2)));
        }

        localAndDistance.sort(new Comparator<Pair<VertexLocation, Double>>() {
            @Override
            public int compare(Pair<VertexLocation, Double> o1, Pair<VertexLocation, Double> o2) {
                if (o1.getSecond() > o2.getSecond()) {
                    return 1;
                } else if (o1.getSecond().equals(o2.getSecond())) {
                    return 0; // You can change this to make it then look at the
                    //words alphabetical order
                } else {
                    return -1;
                }
            }
        });
        return localAndDistance;
    }

    /**
     * Method that returns the final list with the path
     * @return final list with the path
     */
    public List<VertexLocation> getFinalList() {
        return finalList;
    }

    /**
     * Method that returns the distance of the path
     * @return distance of the path
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Method that modifies the distance of the path
     * @param distance
     */
    public static void setDistance(double distance) {
        ShortestPathController.distance = distance;
    }

    /**
     * Method that returns a vertex location of type Country after converting a Port
     * @param port
     * @param matrix
     * @return vertex location of type Country after converting a Port
     */
    public VertexLocation getCountryByPort(VertexLocation port, AdjacencyMatrixGraph<VertexLocation, Double> matrix) {
        String country = port.getCountryName();
        for (VertexLocation vl : matrix.vertices()) {
            if (vl.getLocationID().equals(country))
                return vl;
        }
        return null;
    }

    /**
     * Method that returns a list of vertex locations after searching for their location names in the string array of places
     * @param freightNetwork
     * @param places
     * @param numberOfPlaces
     * @return list of vertex locations after searching for their location names in the string array of places
     */
    public List<VertexLocation> getVertexLocationByString(FreightNetwork freightNetwork, String[] places, int numberOfPlaces) {
        List<VertexLocation> result = new ArrayList<>();
        int index = 0;
        while (index < numberOfPlaces) {
            for (VertexLocation vl : freightNetwork.getFreightNetwork2().vertices()) {
                if (vl.getLocationName().equals(places[index])) {
                    result.add(vl);
                    break;
                }
            }
            index++;
        }
        return result;
    }
}



