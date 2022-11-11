package lapr.project.store;

import lapr.project.model.Port;
import lapr.project.model.ShipDynamicData;
import lapr.project.tree.KDTree;
import lapr.project.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class PortStore {

    /**
     * List of ports stored
     */
    private final KDTree<Port> portList;
    private List<Node<Port>> nodes = new ArrayList<>();

    /**
     * Creates a port store
     *
     * @param portList The list of ports
     */
    public PortStore(KDTree<Port> portList) {
        this.portList = portList;
    }

    public List<Node<Port>> getList() {
        nodes = portList.getNodes();
        return new ArrayList<>(nodes);
    }

    public KDTree saveKDTree(List<Node<Port>> nodes){
        return new KDTree(nodes);
    }

    /**
     * @param identification Port's id number
     * @param name           Port's name
     * @param country        Port's country
     * @param latitude       Port's latitude
     * @param longitude      Port's longitude
     * @return A port created with the given information
     */
    public Port createPort(Integer identification, String name, String country, double latitude, double longitude) {
        return new Port(identification, name, country, latitude, longitude);
    }

    /**
     * Saves the port in the list of stored ports
     *
     * @param p The port to be added
     * @return true if port was valid to be added
     */
    public boolean savePort(Port p) {
        this.portList.insert(p, p.getLatitude(), p.getLongitude());
        return true;
    }

    /**
     * Gets the list of stored ports
     *
     * @return the list of ports
     */
    public KDTree<Port> getPorts() {
        return this.portList;
    }

    /**
     * Get the nearest from the position received as parameter.
     *
     * @return the nearest Port
     */
    public Port getNearestPortFromPosition(ShipDynamicData position) {
        return portList.findNearestNeighbour(position.getLat(), position.getLon());
    }

}
