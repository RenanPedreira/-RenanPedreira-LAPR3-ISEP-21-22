package lapr.project.model;

import lapr.project.auth.AuthFacade;
import lapr.project.auth.domain.store.UserRoleStore;
import lapr.project.network.FreightNetwork;
import lapr.project.store.AISMessageStore;
import lapr.project.store.CountryStore;
import lapr.project.store.PortStore;
import lapr.project.store.ShipStore;
import lapr.project.tree.AVL;
import lapr.project.tree.KDTree;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Luís Araújo
 */
public class Company {

    /**
     * Company designation.
     */
    private final String designation;

    /**
     * Auth facade.
     */
    private final AuthFacade authFacade;

    /**
     * User Role store.
     */
    private final UserRoleStore userRole;

    /**
     * Company's list of ships
     */
    private final AVL<Ship> shipList = new AVL<>();
    private final AVL<Ship> shipList2 = new AVL<>();
    private final AVL<Ship> shipList3 = new AVL<>();
    private final AVL<Country> countryList = new AVL<>();

    /**
     * Company's list of ports
     */
    private final KDTree<Port> portList = new KDTree<>();

    /**
     * Company's store of the positional messages.
     */
    private final AISMessageStore messageStore;

    private final FreightNetwork freightNetwork = new FreightNetwork();

    /**
     * Company's constructor
     *
     * @param designation
     */
    public Company(String designation) {
        if (StringUtils.isBlank(designation)) {
            throw new IllegalArgumentException("Designation cannot be blank.");
        }
        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.messageStore = new AISMessageStore();
        this.userRole = new UserRoleStore();
        //this.freightNetwork = new FreightNetwork();
    }

    /**
     * method that returns the designation
     *
     * @return desigation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * method that returns the auth facade
     *
     * @return auth facade
     */
    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * method that returns the store of the user roles
     *
     * @return user roles store
     */
    public UserRoleStore getUserRoles() {
        return this.userRole;
    }

    /**
     * Creates a ship store to manage the company's ships
     *
     * @return a ship store
     */
    public ShipStore getShipStore() {
        return new ShipStore(this.shipList, this.shipList2, this.shipList3);
    }

    public CountryStore getCountryStore() {
        return new CountryStore(countryList);
    }

    /**
     * Creates a port store to manage the company's ports
     *
     * @return a port store
     */
    public PortStore getPortStore() {
        return new PortStore(this.portList);
    }

    /**
     * Return the company's instance of AISMessageStore class
     *
     * @return messageStore
     */
    public AISMessageStore getMessageStore() {
        return this.messageStore;
    }

    public FreightNetwork getFreightNetwork() {
        return this.freightNetwork;
    }

    //endregion
}
