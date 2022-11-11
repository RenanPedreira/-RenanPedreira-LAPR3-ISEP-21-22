package lapr.project.graph.map;

import lapr.project.graph.CommonGraph;
import lapr.project.graph.Edge;

import java.util.*;

/**
 * @author Luís Araújo
 */
public class AdjacencyMapGraph<V, E> extends CommonGraph<V, E> {
    /**
     * Instance of map
     */
    final private Map<V, MapVertex<V, E>> mapVertices;  // all the Vertices of the graph
    /**
     * String for the exceptions
     */
    private static final String exception="Vertices cannot be null!";

    /**
     * Constructs an empty graph (either undirected or directed)
     * @param directed
     */
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        mapVertices = new LinkedHashMap<>();
    }

    /**
     * Constructs a graph sent by parameter
     * @param g
     */
    public AdjacencyMapGraph(CommonGraph<V,E> g) {
        this(g.isDirected());
        copy(g, this);
    }

    /**
     * Method that verifies if a vertex is valid
     * @param vert
     * @return true if it is valid and false otherwise
     */
    @Override
    public boolean validVertex(V vert) { return (mapVertices.get(vert) != null);   }

    /**
     * Method that returns a list of all the adjacent vertices of a certain vertex V
     * @param vert the vertex for which to find adjacent vertices
     * @return list of all the adjacent vertices of a certain vertex V
     */
    @Override
    public Collection<V> adjVertices(V vert) {
        MapVertex<V,E> mv = mapVertices.get(vert);
        if (mv==null)
            return null;
        return mv.getAllAdjVerts();
    }

    /**
     * Method that returns a list of edges
     * @return list of edges
     */
    @Override
    public Collection<Edge<V, E>> edges() {

        ArrayList<Edge<V, E>> le = new ArrayList<>(numEdges);

        for (MapVertex<V, E> mv : mapVertices.values())
            le.addAll(mv.getAllOutEdges());

        return le;
    }

    /**
     * Method that returns an edge between two vertices
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @return edge between two vertices
     */
    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vOrig);

        return mv.getEdge(vDest);
    }

    /**
     * Method that returns an edge between two keys
     * @param vOrigKey the key of vertex vOrig
     * @param vDestKey the key of vertex vDist
     * @return edge between two keys
     */
    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {
        V vOrig = vertex(vOrigKey);
        V vDest = vertex(vDestKey);

        return edge(vOrig, vDest);
    }

    /**
     * Method that returns the out degree of a certain vertex V
     * @param vert the vertex of interest
     * @return out degree of a certain vertex V
     */
    @Override
    public int outDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.numAdjVerts();
    }

    /**
     * Method that returns the in degree of a certain vertex V
     * @param vert the vertex of interest
     * @return in degree of a certain vertex V
     */
    @Override
    public int inDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        int degree = 0;
        for (V otherVert : mapVertices.keySet())
            if (edge(otherVert, vert) != null)
                degree++;

        return degree;
    }

    /**
     * Method that returns a list of outgoing edges of a certain vertex V
     * @param vert the vertex of interest
     * @return list of outgoing edges of a certain vertex V
     */
    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {

        if (!validVertex(vert))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.getAllOutEdges();
    }

    /**
     * Method that returns a list of incoming edges of a certain vertex V
     * @param vert the vertex of interest
     * @return list of incoming edges of a certain vertex V
     */
    @Override
    public Collection<Edge<V, E>> incomingEdges(V vert) {
        if (!validVertex(vert))
            return null;

        Collection<Edge<V,E>> incoming= new ArrayList<>();
        for (V other : mapVertices.keySet()){
            Edge<V,E> e=edge(other,vert);
            if (e!=null)
                incoming.add(e);
        }
        return incoming;
    }

    /**
     * Method that verifies if a certain vertex is added
     * @param vert the vertex to add
     * @return true if it is added and false otherwise
     */
    @Override
    public boolean addVertex(V vert) {

        if (vert == null) throw new RuntimeException(exception);
        if (validVertex(vert))
            return false;

        MapVertex<V, E> mv = new MapVertex<>(vert);
        vertices.add(vert);
        mapVertices.put(vert, mv);
        numVerts++;

        return true;
    }

    /**
     * Method that verifies if an edge between two vertices is added
     * @param vOrig  origin vertex
     * @param vDest  destination vertex
     * @param weight the weight of the edge
     * @return true if the edge is added and false otherwise
     */
    @Override
    public boolean addEdge(V vOrig, V vDest, E weight) {

        if (vOrig == null || vDest == null) throw new RuntimeException(exception);
        if (edge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            addVertex(vOrig);

        if (!validVertex(vDest))
            addVertex(vDest);

        MapVertex<V, E> mvo = mapVertices.get(vOrig);
        MapVertex<V, E> mvd = mapVertices.get(vDest);

        Edge<V, E> newEdge = new Edge<>(mvo.getElement(), mvd.getElement(), weight);
        mvo.addAdjVert(mvd.getElement(), newEdge);
        numEdges++;

        //if graph is not direct insert other edge in the opposite direction
        if (!isDirected)
            // if vDest different vOrig
            if (edge(vDest, vOrig) == null) {
                Edge<V, E> otherEdge = new Edge<>( mvd.getElement(), mvo.getElement(), weight);
                mvd.addAdjVert(mvo.getElement(), otherEdge);
                numEdges++;
            }

        return true;
    }

    /**
     * Test that verifies if a certain vertex is removed
     * @param vert the vertex to remove
     * @return true if the vertex is removed and false otherwise
     */
    @Override
    public boolean removeVertex(V vert) {

        if (vert == null) throw new RuntimeException(exception);
        if (!validVertex(vert))
            return false;

        //remove all edges that point to vert
        for (Edge<V, E> edge : incomingEdges(vert)) {
            removeEdge(edge.getVOrig(), vert);
        }

        MapVertex<V, E> mv = mapVertices.get(vert);

        //The edges that live from vert are removed with the vertex
        numEdges -= mv.numAdjVerts();
        mapVertices.remove(vert);
        vertices.remove(vert);

        numVerts--;

        return true;
    }

    /**
     * Method that verifies if an edge between two vertices is removed
     * @param vOrig vertex origin of the edge
     * @param vDest vertex destination of the edge
     * @return true if the edge is removed and false otherwise
     */
    @Override
    public boolean removeEdge(V vOrig, V vDest) {

        if (vOrig == null || vDest == null) throw new RuntimeException(exception);
        if (!validVertex(vOrig) || !validVertex(vDest))
            return false;

        Edge<V, E> edge = edge(vOrig, vDest);

        if (edge == null)
            return false;

        MapVertex<V, E> mvo = mapVertices.get(vOrig);

        mvo.remAdjVert(vDest);
        numEdges--;

        //if graph is not directed
        if (!isDirected) {
            edge = edge(vDest, vOrig);
            if (edge != null) {
                MapVertex<V, E> mvd = mapVertices.get(vDest);
                mvd.remAdjVert(vOrig);
                numEdges--;
            }
        }
        return true;
    }

    /**
     * Method that clones the graph
     * @return clone of the graph
     */
    @Override
    public AdjacencyMapGraph<V, E> clone() {

        AdjacencyMapGraph<V, E> g = new AdjacencyMapGraph<>(this.isDirected);

        copy(this,g);

        return g;
    }

    /**
     * ToString
     * @return string representation
     */

    @Override
    public String toString() {
        String s;
        if (numVerts == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + numVerts + " vertices, " + numEdges + " edges\n";
            for (MapVertex<V, E> mv : mapVertices.values())
                s += mv + "\n";
        }
        return s;
    }
}