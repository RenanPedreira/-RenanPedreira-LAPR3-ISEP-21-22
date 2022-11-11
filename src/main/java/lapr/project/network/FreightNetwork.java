package lapr.project.network;

import lapr.project.controller.*;
import lapr.project.graph.Algorithms;
import lapr.project.graph.map.AdjacencyMapGraph;
import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.model.Country;
import lapr.project.model.Calculator;
import lapr.project.model.Port;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;

import java.util.*;
import java.util.function.BinaryOperator;

public class FreightNetwork {
    AdjacencyMatrixGraph<VertexLocation, Double> freightNetwork2;
    AdjacencyMapGraph<VertexLocation, Double> freightNetwork3;

    ImportCountryController icc;
    ImportPortDBController ipc;
    ImportBorderController ibc;
    ImportSeadistController isc;

    List<Port> portList;
    List<Country> countryList;

    public FreightNetwork() {
        freightNetwork2 = new AdjacencyMatrixGraph<>();
        freightNetwork3 = new AdjacencyMapGraph<>(false);
        icc = new ImportCountryController();
        ipc = new ImportPortDBController();
        ibc = new ImportBorderController();
        isc = new ImportSeadistController();
    }

    public AdjacencyMatrixGraph<VertexLocation, Double> getFreightNetwork2() {
        return this.freightNetwork2;
    }

    public boolean setFreightNetwork(AdjacencyMatrixGraph matrix){
        this.freightNetwork2 = matrix;
        return true;
    }

    public boolean saveCountry() {
        countryList = icc.getCountriesFromDB();

        for(Country country: countryList) {
            freightNetwork2.insertVertex(country);
            freightNetwork3.addVertex(country);
        }
        return true;
    }

    public AdjacencyMapGraph<VertexLocation, Double> getFreightNetwork3() {
        return this.freightNetwork3;
    }

    public boolean savePort() {
        portList = ipc.getPortsFromDBForGraph();

        for(Port port: portList) {
            //VertexLocation location = new VertexLocation(String.valueOf(port.getIdentification()), port.getName(), port.getLatitude(), port.getLongitude(), port.getCountry(), port.getContinent());
            freightNetwork2.insertVertex(port);
        }
        return true;
    }

    public List<Port> getPortList() {
        return portList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public boolean saveBorder() {
        List<Pair<String, String>> borderList = ibc.getBordersFromDB();
        Utils.print(borderList.size());

        for(Pair<String, String> p: borderList) {
            String locA = p.getFirst();
            String locB = p.getSecond();


            VertexLocation locationA = getLocationByID(locA);

            VertexLocation locationB = getLocationByID(locB);

            freightNetwork2.insertEdge(locationA, locationB, locationA.distanceBetween(locationB));
            freightNetwork3.addEdge(locationA, locationB, locationA.distanceBetween(locationB));
        }
        return true;
    }

    public boolean saveSeadists() {
        List<Pair<Pair<String, String>, Double>> seadistList = isc.getSeadistsFromDB();
        Utils.print(seadistList.size());

        for(Pair<Pair<String, String>, Double> l: seadistList){
            String locA = l.getFirst().getFirst();
            String locB = l.getFirst().getSecond();
            Double dist = l.getSecond();

            VertexLocation locationA = getLocationByID(locA);
            VertexLocation locationB = getLocationByID(locB);

            freightNetwork2.insertEdge(locationA, locationB, dist);
        }

        return true;
    }

    public VertexLocation getLocationByID(String locA){
        for(VertexLocation l: freightNetwork2.vertices()){
            if(l.getLocationID().equals(locA))
                return l;
        }
        return null;
    }

    public VertexLocation getLocationByName(String location){
        for(VertexLocation l: freightNetwork2.vertices()){
            if(l.getLocationName().equals(location))
                return l;
        }
        return null;
    }

    public boolean connectClosestPortToCapital() {
        double minDist = Double.MAX_VALUE;
        VertexLocation closestPort = null;

        for(Country c: countryList) {
            //get ports in the country
            for(VertexLocation l: freightNetwork2.vertices()) {
                if(l.getCountryName() != null && l.getCountryName().equals(c.getName())) {
                    double dist = Calculator.calculateDistance(c.getLatitude(), c.getLongitude(), l.getLatitude(), l.getLongitude());

                    if(dist < minDist) {
                        minDist = dist;
                        closestPort = l;
                    }
                }
            }
            freightNetwork2.insertEdge(getLocationByID(c.getName()), closestPort, minDist);
        }
        return true;
    }

    public boolean connectPortsWithNClosestPorts(int n) {
        List<Pair<VertexLocation, Double>> l = new ArrayList<>();
        double dist;

        for(VertexLocation l1: freightNetwork2.vertices()) {
            if(l1.getCountryName() != null) {
                for(VertexLocation l2: freightNetwork2.vertices()) {
                    if(l2.getCountryName() != null){
                        if(!l1.getCountryName().equals(l2.getCountryName())) {
                            dist = l1.distanceBetween(l2);

                            l.add(new Pair<>(l2, dist));
                        }
                    }
                }
            }

            Comparator<Pair<VertexLocation, Double>> comp = new Comparator<Pair<VertexLocation, Double>>() {
                @Override
                public int compare(Pair<VertexLocation, Double> o1, Pair<VertexLocation, Double> o2) {
                    return o1.getSecond().compareTo(o2.getSecond());
                }
            };

            Collections.sort(l, comp);

            if(l1.getCountryName() != null) {
                for (int i = 0; i < n; i++) {
                    freightNetwork2.insertEdge(l1, l.get(i).getFirst(), l.get(i).getSecond());
                }
            }
        }
        return true;
    }

    /**
     * The method will search for the n closeness places by the continent received as parameter.
     *
     * @param continent that the user want to know the n closeness places.
     * @param n represents the quantity of closeness places that the user want.
     * @return a map with n closeness places.
     */
    public Map<Integer,Pair<VertexLocation,Double>> getNClosenessPlaceByContinent(String continent, int n) {
        Map<Integer,Pair<VertexLocation,Double>> map= new HashMap<>();
        if(n<=0)
            return map;

        List<Pair<VertexLocation,Double>> allLocationContinent=new ArrayList<>();

        Comparator<Pair<VertexLocation,Double>> cp = new Comparator<Pair<VertexLocation,Double>>() {
            @Override
            public int compare(Pair<VertexLocation,Double> o1, Pair<VertexLocation,Double> o2) {
                return o1.getSecond().compareTo(o2.getSecond());
            }
        };

        fillTheListWithRequestedContinent(continent,freightNetwork2,allLocationContinent);

        if (allLocationContinent.size()==0)
            return map;

        allLocationContinent.sort(cp);

        fillMapWithTheResult(allLocationContinent,n, map);

        return map;
    }

    /**
     * This method will fill the map with the n closeness places receiving as parameter the n value and the source list.
     *
     * @param allLocationContinent represent the list of pairs by the continent.
     * @param n represents the quantity of closeness places that the user want.
     * @param map that wanted to get fill.
     */
    private void fillMapWithTheResult(List<Pair<VertexLocation,Double>> allLocationContinent,Integer n,Map<Integer,Pair<VertexLocation,Double>> map){
        int listSize=allLocationContinent.size();
        for (int i=0;i<n;i++) {
            if (n>listSize)
                break;
            map.put(i,allLocationContinent.get(i));
        }
    }

    /**
     * Fill a list with a pair of vertex that both have the same continent name that the user wanted.
     *
     * @param continent's name
     * @param p represents the matrix graph.
     * @param allLocationContinent represents the list to get fill.
     */
    private void fillTheListWithRequestedContinent(String continent, AdjacencyMatrixGraph<VertexLocation,Double> p,List<Pair<VertexLocation,Double>> allLocationContinent){
        LinkedList<VertexLocation> shortestPath=new LinkedList<>();
        for (VertexLocation vl1 : p.vertices()){
            if (vl1.getContinent().equalsIgnoreCase(continent)) {
                int index1 = p.toIndex(vl1);
                double totalDistance = 0.0;
                int totalVertexAcessible = 0;
                for (VertexLocation vl2 : p.vertices()) {
                    int index2 = p.toIndex(vl2);
                    if (index1 != index2 && vl2.getContinent().equalsIgnoreCase(continent)) {
                        totalDistance += Algorithms.shortestPath(freightNetwork2,vl1,vl2,shortestPath,2,0);
                        totalVertexAcessible = Math.incrementExact(totalVertexAcessible);
                    }
                }
                if (totalVertexAcessible!=0)
                    allLocationContinent.add(new Pair<>(vl1,totalDistance/totalVertexAcessible));
            }
        }
    }

    public Pair<LinkedList<VertexLocation>,Double> getMostEfficiencyCircuit(VertexLocation vertexLocation){
        if (freightNetwork2.adjVertices(vertexLocation).size()==1){
            throw new IllegalArgumentException("The vertexLocation has only one adjacent vertex, so it can not have a circuit.");
        }

        BinaryOperator<Double> sum= new BinaryOperator<Double>() {
            @Override
            public Double apply(Double aDouble, Double aDouble2) {
                if (aDouble==null)
                    return aDouble2;
                return Double.sum(aDouble,aDouble2);
            }
        };

        LinkedList<VertexLocation> resultFinal = new LinkedList<>();
        Pair<ArrayList<LinkedList<VertexLocation>>,Double> result = Algorithms.eulerianCircuit(freightNetwork2.clone(),vertexLocation,sum,0.0);
        for (LinkedList<VertexLocation> re : result.getFirst()){
            if (resultFinal.size()<re.size()){
                resultFinal=re;
            }
        }
        return new Pair<>(resultFinal,result.getSecond());
    }

    public boolean connectPortsSameCountry() {
        for(Port p1: portList) {
            for(Port p2: portList) {
                if(p1.getCountry().equals(p2.getCountry()) && !p1.getIdentification().equals(p2.getIdentification())){
                    VertexLocation l1 = getLocationByID(String.valueOf(p1.getIdentification()));
                    VertexLocation l2 = getLocationByID(String.valueOf(p2.getIdentification()));

                    if((freightNetwork2.getEdge(l1, l2) == null) && (freightNetwork2.getEdge(l2, l1) == null))
                        freightNetwork2.insertEdge(l1, l2, l1.distanceBetween(l2));
                }
            }
        }
        return true;
    }


////US401

    /**
     * Get the top N most critical ports
     *
     * @param n Top n central ports
     * @return List with top n central ports and how many times they appear in shortest paths
     */
    public List<Pair<VertexLocation, Integer>> getNCriticalPorts(Integer n){
        List<ArrayList<LinkedList<VertexLocation>>> shortestPaths = new ArrayList<>();

        List<Pair<VertexLocation, Integer>> portCount = new ArrayList<>();

        ArrayList<LinkedList<VertexLocation>> paths = new ArrayList<>();
        ArrayList<Double> dists = new ArrayList<>();


        //Gets the list of shortest paths from a location to all other locations
        for(VertexLocation location : freightNetwork2.vertices()){
                Algorithms.shortestPaths(freightNetwork2, location, Double::compare, Double::sum, 0.0, paths, dists);
                shortestPaths.add(new ArrayList<>(paths));

                paths.clear();
                dists.clear();
        }

        //Gets the amount of times a port appears in a shortest path
        Map<VertexLocation, Integer> portMap = new HashMap<>();
        for(ArrayList<LinkedList<VertexLocation>> listPaths : shortestPaths) {
            for (LinkedList<VertexLocation> path : listPaths) {
                for (VertexLocation vl : path) {
                    if(vl instanceof Port){
                        if (!portMap.keySet().contains(vl))
                            portMap.put(vl, 1);
                        else
                            portMap.put(vl, portMap.get(vl) + 1);
                    }
                }
            }
        }

        for(VertexLocation vl : portMap.keySet()){
            portCount.add(new Pair<>(vl, portMap.get(vl)));
        }

        //Top n
        Comparator<Pair<VertexLocation,Integer>> cp = new Comparator<Pair<VertexLocation,Integer>>() {
            @Override
            public int compare(Pair<VertexLocation,Integer> o1, Pair<VertexLocation,Integer> o2) {
                if (o1.getFirst()==null||o2.getSecond()==null)
                    return 10;
                return -(o1.getSecond().compareTo(o2.getSecond()));
            }
        };

        Collections.sort(portCount, cp);
        return portCount.subList(0, n);
    }

}