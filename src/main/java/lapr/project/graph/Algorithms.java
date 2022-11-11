package lapr.project.graph;

import lapr.project.graph.map.AdjacencyMapGraph;
import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.model.Colors;
import lapr.project.model.Country;
import lapr.project.model.Port;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * @author Luís Araújo
 */
public class Algorithms {
    /**
     * Empty constructor
     */
    public Algorithms() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                              ALGORITHMS FOR CONDITION 1 (US402)- LAND PATH

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    protected static <V, E> void shortestPathLengthForLand(AdjacencyMatrixGraph<V, E> g, V vOrig, V[] vertices,
                                                           boolean[] visited, int[] pathKeys, double[] dist, V vDest) {
        int vkey = g.key(vOrig);
        dist[vkey] = 0;
        while (vkey != -1) {
            vOrig = vertices[vkey];
            visited[vkey] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                if (vAdj instanceof Country || vAdj == vDest) {
                    int vkeyAdj2 = g.key(vAdj);
                    Double edge2 = (Double) g.privateGet(vkey,vkeyAdj2);
                    if (!visited[vkeyAdj2] && dist[vkeyAdj2] > dist[vkey] + edge2) {
                        dist[vkeyAdj2] = dist[vkey] + edge2;
                        pathKeys[vkeyAdj2] = vkey;
                    }
                }
            }
            double minDist2 = Double.MAX_VALUE;
            vkey = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && dist[i] < minDist2) {
                    minDist2 = dist[i];
                    vkey = i;
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                              ALGORITHMS FOR CONDITION 2 (US402)- MARITIME PATH

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    protected static <V, E> void shortestPathLengthForMaritime(AdjacencyMatrixGraph<V, E> g, V vOrig, V[] vertices,
                                                               boolean[] visited, int[] pathKeys, double[] dist) {
        int vkey = g.key(vOrig);
        dist[vkey] = 0;
        while (vkey != -1) {
            vOrig = vertices[vkey];
            visited[vkey] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                if (vAdj instanceof Port) {
                    int vkeyAdj3 = g.key(vAdj);
                    Double edge = (Double) g.privateGet(vkey,vkeyAdj3);
                    if (!visited[vkeyAdj3] && dist[vkeyAdj3] > dist[vkey] + edge) {
                        dist[vkeyAdj3] = dist[vkey] + edge;
                        pathKeys[vkeyAdj3] = vkey;
                    }
                }
            }
            double minDist3 = Double.MAX_VALUE;
            vkey = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && dist[i] < minDist3) {
                    minDist3 = dist[i];
                    vkey = i;
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                              ALGORITHMS FOR CONDITION 3 (US402)- LAND AND MARITIME PATH

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    protected static <V, E> void shortestPathLength(AdjacencyMatrixGraph<V, E> g, V vOrig, V[] vertices,
                                                    boolean[] visited, int[] pathKeys, double[] dist) {
        int vkey = g.key(vOrig);
        dist[vkey] = 0;
        while (vkey != -1) {
            vOrig = vertices[vkey];
            visited[vkey] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                int vkeyAdj4 = g.key(vAdj);
                Double edge4 = (Double) g.privateGet(vkey,vkeyAdj4);
                if (!visited[vkeyAdj4] && dist[vkeyAdj4] > dist[vkey] + edge4) {
                    dist[vkeyAdj4] = dist[vkey] + edge4;
                    pathKeys[vkeyAdj4] = vkey;
                }
            }
            double minDist4 = Double.MAX_VALUE;
            vkey = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && dist[i] < minDist4) {
                    minDist4 = dist[i];
                    vkey = i;
                }
            }
        }
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(AdjacencyMatrixGraph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {
        if (!vOrig.equals(vDest)) {
            path.push(vDest);

            int vKey = g.key(vDest);
            int prevVKey = pathKeys[vKey];
            vDest = verts[prevVKey];

            getPath(g, vOrig, vDest, verts, pathKeys, path);
        } else {
            path.push(vOrig);
        }
    }

    //shortest-path between voInf and vdInf
    public static <V, E> double shortestPath(AdjacencyMatrixGraph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath, int opt, int opt2) {
        if (g.validVertex(vOrig) && g.validVertex(vDest)) {
            boolean[] knownVertices = new boolean[g.numVertices()];
            int[] precedences = new int[g.numVertices()];
            double[] minDist = new double[g.numVertices()];
            V vertices[] = g.allkeyVerts();
            shortPath.clear();

            for (V v : g.vertices()) {
                minDist[g.key(v)] = Double.MAX_VALUE;
                precedences[g.key(v)] = -1;
                knownVertices[g.key(v)] = false;
            }

            if (opt == 0) {
                shortestPathLengthForLand(g, vOrig, vertices, knownVertices, precedences, minDist, vDest);

                double lengthPath1 = minDist[g.key(vDest)];

                if (lengthPath1 != Double.MAX_VALUE) {
                    getPath(g, vOrig, vDest, vertices, precedences, shortPath);
                    return lengthPath1;
                }
            }

            if (opt == 1) {
                shortestPathLengthForMaritime(g, vOrig, vertices, knownVertices, precedences, minDist);

                double lengthPath2 = minDist[g.key(vDest)];

                if (lengthPath2 != Double.MAX_VALUE) {
                    getPath(g, vOrig, vDest, vertices, precedences, shortPath);
                    return lengthPath2;
                }
            }

            if (opt == 2) {

                shortestPathLength(g, vOrig, vertices, knownVertices, precedences, minDist);

                double lengthPath3 = minDist[g.key(vDest)];

                if (lengthPath3 != Double.MAX_VALUE) {
                    getPath(g, vOrig, vDest, vertices, precedences, shortPath);
                    return lengthPath3;
                }
            }

            if (opt == 3) {
                if(opt2==0)
                    shortestPathLengthForLand(g, vOrig, vertices, knownVertices, precedences, minDist, vDest);
                if(opt2==1)
                    shortestPathLengthForMaritime(g, vOrig, vertices, knownVertices, precedences, minDist);
                if(opt2==2)
                    shortestPathLength(g, vOrig, vertices, knownVertices, precedences, minDist);

                double lengthPath4 = minDist[g.key(vDest)];

                if (lengthPath4 != Double.MAX_VALUE) {
                    return lengthPath4;
                }
            }
        }
        return 0;
    }

    /** Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(AdjacencyMatrixGraph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                        LinkedList<V> path, List<LinkedList<V>> paths) {
        path.add(vOrig);
        visited[g.key(vOrig)]=true;
        for (V vAdj : g.adjVertices(vOrig)){
            if (vAdj.equals(vDest)){
                path.add(vDest);
                paths.add(new LinkedList<>(path));
                V v =path.pollLast();
                visited[g.key(v)]=false;
            }else
            if (!visited[g.key(vAdj)]){
                allPaths(g,vAdj,vDest,visited,path,paths);
            }
        }
        V v =path.pollLast();
        visited[g.key(v)]=false;
    }

    /**
     * Assign colors (starting from 0) to all vertices and
     *
     * @param map2
     * @return array with all the colors assigned
     */
    public int[] coloringMap(AdjacencyMapGraph<VertexLocation, Double> map2) {
        //Array with all the colors assigned to each vertex
        int[] aux = new int[map2.numVertices()];

        //Find the first available color
        int y;
        int cr;
        int numberOfColors;

        numberOfColors = Colors.getNumberOfColors();
        for (y = 0; y < map2.numVertices(); y++) {
            //Find one out of six colors to assign to a vertex
            for (cr = 0; cr < numberOfColors; cr++) {
                //Check if the adjacent vertices have that color assigned
                if (checkIfAdjVertsHaveTheColor(cr, map2.vertices().get(y), map2)) {
                    aux[y] = cr; // Assign the found color
                    map2.vertices().get(y).setColor(Colors.getColorByNumber(cr));
                    break;
                }
            }
        }
        return aux;
    }

    /**
     * Method that verifies if the adjacent vertices of a vertex (Vertex Location) have the color (num)
     *
     * @param num
     * @param vertexLocation
     * @return false if one of the adjacent vertices has that color and true otherwise
     */
    public boolean checkIfAdjVertsHaveTheColor(int num, VertexLocation
            vertexLocation, AdjacencyMapGraph<VertexLocation, Double> map2) {
        Iterator<VertexLocation> it = map2.adjVertices(vertexLocation).iterator();
        VertexLocation aux2;
        while (it.hasNext()) {
            aux2 = it.next();
            if (aux2.getColor() != null) {
                if (aux2.getColor().getColorNum() == num) {
                    return false;
                }
            }
        }
        return true;
    }



    public static <V, E> boolean shortestPaths(AdjacencyMatrixGraph<VertexLocation, Double> g, VertexLocation vOrig, Comparator<Double> ce, BinaryOperator<Double> sum, Double zero, ArrayList<LinkedList<VertexLocation>> paths, ArrayList<Double> dists) {
        paths.clear();
        if (!g.validVertex(vOrig)) return false;
        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];

        VertexLocation[] pathKeys = new VertexLocation[nverts];

        Double[] dist = new Double[nverts];
        Double maxValue = Double.MAX_VALUE;

        for (int i = 0; i < nverts; i++) {
            dist[i] = maxValue;
            pathKeys[i] = null;
        }

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);
        dists.clear();
        paths.clear();
        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(maxValue);
        }
        for (VertexLocation vDst : g.vertices()) {
            int i = g.key(vDst);
            if (dist[i] != maxValue) {
                LinkedList<VertexLocation> shortPath = new LinkedList<>();
                getPath(g, vOrig, vDst, pathKeys, shortPath);
                paths.set(i, shortPath);
                dists.set(i, dist[i]);
            } else {
                dists.set(i, null);
            }
        }
        return true;
    }

    private static <V, E> void shortestPathDijkstra(AdjacencyMatrixGraph<V, Double> g, V vOrig, Comparator<Double> ce, BinaryOperator<Double> sum, Double zero, boolean[] visited, V[] pathKeys, Double[] dist) {
        int nverts = g.numVertices();
        Double maxValue = Double.MAX_VALUE;
        int vKey = g.key(vOrig);
        dist[vKey] = zero;
        pathKeys[vKey] = vOrig;
        while (vOrig != null) {
            vKey = g.key(vOrig);
            visited[vKey] = true;
            for (V vAdj : g.directConnections(vOrig)) {
                int vKeyAdj = g.key(vAdj);
                Double edge1 = g.getEdge(vOrig, vAdj);
                if (!visited[vKeyAdj]) {
                    if (ce.compare(dist[vKeyAdj], (sum.apply(dist[vKey], edge1))) > 0) {
                        dist[vKeyAdj] = sum.apply(dist[vKey], edge1);
                        pathKeys[vKeyAdj] = vOrig;
                    }
                }
            }

            Double minDist = maxValue;
            vOrig = null;
            for (V vert : g.vertices()) {
                int i = g.key(vert);
                if (!visited[i] && (ce.compare(dist[i], minDist) < 0)) {
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }
    }

    private static <V, E> void getPath(AdjacencyMatrixGraph<V, E> g, V vOrig, V vDest, V[] pathKeys, LinkedList<V> path) {
        if (vOrig.equals(vDest))
            path.push(vOrig);
        else {
            path.push(vDest);
            int vKey = g.key(vDest);
            vDest = pathKeys[vKey];
            getPath(g, vOrig, vDest, pathKeys, path);
        }
    }
    public static <V,E> Pair<ArrayList<LinkedList<V>>,E> eulerianCircuit(AdjacencyMatrixGraph<V,E> g, V vOrig, BinaryOperator<E> sum, E zero){
        LinkedList<V> result= new LinkedList<>();
        ArrayList<LinkedList<V>> resultTotal=new ArrayList<>();
        int countOddVertex=g.countOddVertices();
        boolean[] isVertexVisited = new boolean[g.numVertices()];
        if (countOddVertex==2&&g.adjVertices(vOrig).size()%2!=1)
            throw new IllegalArgumentException("The Initial Vertex it's not acceptable to start a cycle because the graph is semi-eulerian");

        getEulerianCircuit(g,vOrig,vOrig,result,isVertexVisited,sum, zero,resultTotal);

        return new Pair<>(resultTotal,zero);
    }

    private static <V,E> void getEulerianCircuit(AdjacencyMatrixGraph<V,E> g, V vOrig, V vDest,LinkedList<V> path, boolean[] isVertexVisited, BinaryOperator<E> sum, E zero,ArrayList<LinkedList<V>> resultTotal){
        int adjSize=g.adjVertices(vOrig).size();
        ArrayList<V> adjVertices=((ArrayList<V>)g.adjVertices(vOrig));
        path.add(vOrig);

        isVertexVisited[g.toIndex(vOrig)]=true;
        for (int i=0;i<adjSize;i++){
            V vAdj = adjVertices.get(i);
            if (isNextEdgeValid(g,vOrig,vAdj,isVertexVisited)){
                zero=sum.apply(g.removeEdge(vOrig,vAdj),zero);
                if (!vAdj.equals(vDest)) {
                    getEulerianCircuit(g, vAdj, vDest, path, isVertexVisited, sum, zero, resultTotal);
                }else {
                    path.add(vDest);
                    resultTotal.add(new LinkedList<>(path));
                    V v =path.pollLast();
                    //isVertexVisited[g.toIndex(v)]=false;
                }
            }
        }
        V v =path.pollLast();
        //isVertexVisited[g.toIndex(v)]=false;

    }

    private static <V,E> boolean isNextEdgeValid(AdjacencyMatrixGraph<V,E>g, V vOrig, V vAdj, boolean[] isVertexVisited){
        if (g.adjVertices(vOrig).size()==1)
            return true;
        int count1 = dfsCount(g,vOrig, isVertexVisited);
        E value=g.removeEdge(vOrig,vAdj);
        int count2 = dfsCount(g,vAdj, isVertexVisited);
        g.insertEdge(vOrig, vAdj,value);
        return (count1 <= count2);
    }

    private static <V,E> int dfsCount(AdjacencyMatrixGraph<V,E> g, V vOrig, boolean[] isVisited){
        isVisited[g.toIndex(vOrig)]=true;
        int count = 1;
        for (V vAdj : g.adjVertices(vOrig)){
            if (!isVisited[g.toIndex(vAdj)]){
                count=Math.addExact(count,dfsCount(g,vAdj,isVisited));
            }
        }
        return count;
    }
}
