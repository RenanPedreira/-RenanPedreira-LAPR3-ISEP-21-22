package lapr.project.graph;

import lapr.project.graph.map.MapVertex;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;

/**
 * @author Danilton Lopes
 */
public abstract class CommonGraph<V, E> implements Graph<V, E> {
    protected final boolean isDirected;
    protected int numVerts;
    protected int numEdges;
    protected ArrayList<V> vertices;       // Used to maintain a numeric key to each vertex
    private Map<V,MapVertex<V,E>> mapOfVerts;  //all Vertices of the graph

    public CommonGraph(boolean directed) {
        numVerts = 0;
        numEdges = 0;
        isDirected = directed;
        vertices = new ArrayList<>();
        mapOfVerts = new LinkedHashMap<>();
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int numVertices() {
        return numVerts;
    }

    @Override
    public ArrayList<V> vertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    public boolean validVertex(V vert) {
        return vertices.contains(vert);
    }

    @Override
    public int key(V vert) {
        return vertices.indexOf(vert);
    }

    @Override
    public V vertex(int key) {
        if ((key < 0) || (key >= numVerts)) return null;
        return vertices.get(key);
    }

    @Override
    public V vertex(Predicate<V> p) {
        for (V v : vertices) {
            if (p.test(v)) return v;
        }
        return null;
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    /**
     * Copy graph from to graph to
     *
     * @param from graph from which to copy
     * @param to   graph for which to copy
     */
    protected void copy(Graph<V, E> from, Graph<V, E> to) {
        //insert all vertices
        for (V v : from.vertices()) {
            to.addVertex(v);
        }

        //insert all edges
        for (Edge<V, E> e : from.edges()) {
            to.addEdge(e.getVOrig(), e.getVDest(), e.getWeight());
        }
    }

    /* equals implementation compares graphs, independently of their representation
     * @param the other graph to test for equality
     * @return true if both objects represent the same graph
     */
    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj)
            return true;

        if (!(otherObj instanceof Graph<?, ?>))
            return false;

        @SuppressWarnings("unchecked") Graph<V, E> otherGraph = (Graph<V, E>) otherObj;

        if (numVerts != otherGraph.numVertices() || numEdges != otherGraph.numEdges() || isDirected() != otherGraph.isDirected())
            return false;

        // graph must have same vertices
        Collection<V> tvc = this.vertices();
        tvc.removeAll(otherGraph.vertices());
        if (tvc.size() > 0) return false;

        // graph must have same edges
        Collection<Edge<V, E>> tec = this.edges();
        tec.removeAll(otherGraph.edges());
        return (tec.size() == 0);
    }

    @Override
    public V[] allkeyVerts() {

        //V[] keyverts = (V[]) new Object[numVert];
        V  vertElem = null;
        for (MapVertex<V,E> vert : mapOfVerts.values())
            vertElem = vert.getElement() ;            // To get type

        V[] keyverts = (V[]) Array.newInstance(vertElem.getClass(), numVerts);

        for (MapVertex<V,E> vert : mapOfVerts.values())
            keyverts[vert.getKey()]=vert.getElement();

        return keyverts;
    }

    public V opposite(V vert, Edge<V,E> edge){

        if (!validVertex(vert))
            return null;

        MapVertex<V,E> vertex = mapOfVerts.get(vert);

        return vertex.getAdjVert(edge);
    }

    public Edge<V,E> getEdge(V vOrig, V vDest){

        if (!validVertex(vOrig) || !validVertex(vDest))
            return null;

        MapVertex<V,E> vorig = mapOfVerts.get(vOrig);

        return vorig.getEdge(vDest);
    }

    public abstract Graph<V, E> clone();

    @Override
    public int hashCode() {
        return Objects.hash(numVerts, numEdges, isDirected, vertices);
    }
}
