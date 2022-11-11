package lapr.project.graph.map;

import lapr.project.graph.Edge;

import java.util.*;

/**
 * @param <V>
 * @param <E>
 * @author DEI-ESINF
 */
public class MapVertex<V, E> {
    /**
     * Instance of vertex
     */
    final private V element;
    /**
     * Adjacent vertices
     */
    final private Map<V, Edge<V, E>> outVerts;
    private int key;                     //Vertex key number

    /**
     * Constructs a map of vertices
     * @param vert
     */
    public MapVertex(V vert) {
        if (vert == null) throw new RuntimeException("Vertice information cannot be null!");
        element = vert;
        outVerts = new LinkedHashMap<>();
    }

    /**
     * Empty constructor
     */
    public MapVertex() {
        element = null;
        outVerts = new LinkedHashMap<>();
    }

    /**
     * Method that returns the vertex
     * @return vertex
     */
    public V getElement() {
        return element;
    }

    /**
     * Method that adds an adjacent vertex
     * @param vAdj
     * @param edge
     */
    public void addAdjVert(V vAdj, Edge<V, E> edge) {
        outVerts.put(vAdj, edge);
    }

    /**
     * Method that removes an adjacent vertex
     * @param vAdj
     */
    public void remAdjVert(V vAdj) {
        outVerts.remove(vAdj);
    }

    public V getAdjVert(Edge<V,E> edge){

        for (V vert : outVerts.keySet())
            if (edge.equals(outVerts.get(vert)))
                return vert;

        return null;
    }

    /**
     * Method that returns an edge
     * @param vAdj
     * @return edge
     */
    public Edge<V, E> getEdge(V vAdj) {
        return outVerts.get(vAdj);
    }

    /**
     * Method that returns the number of adjacent vertices
     * @return number of adjacent vertices
     */
    public int numAdjVerts() {
        return outVerts.size();
    }

    /**
     * Method that returns a list of all adjacent vertices
     * @return list of all adjacent vertices
     */
    public Collection<V> getAllAdjVerts() {
        return outVerts.keySet();
    }

    /**
     * Method that returns a list of all edges of adjacent vertices
     * @return list of all edges of adjacent vertices
     */
    public Collection<Edge<V, E>> getAllOutEdges() {
        return new ArrayList<>(outVerts.values());
    }

    public int getKey() { return key; }
    public void setKey(int k) { key = k; }

    /**
     * ToString
     * @return string representation
     */
    @Override
    public String toString() {
        String st = element + ": \n";
        if (!outVerts.isEmpty())
            for (V vert : outVerts.keySet())
                st += outVerts.get(vert);

        return st;
    }
}
