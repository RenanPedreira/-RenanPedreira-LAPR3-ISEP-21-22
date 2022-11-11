package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.store.ShipStore;

/**
 * @author Luís Araújo
 */
public class SearchDetailsShipController {
    /**
     * Company.
     */
    private final Company company;

    /**
     * Stores.
     */
    private final ShipStore shipStore;

    /**
     * Constructs an instance of the Controller,
     * using singleton to access the app instance;
     */
    public SearchDetailsShipController() {
        // Singleton
        App app = App.getInstance();
        company = app.getCompany();

        //Stores
        this.shipStore = company.getShipStore();
    }

    /**
     * Method that searches a ship by its mmsi
     *
     * @param mmsi
     * @return true if the ship is founded and false if it isn´t
     */
    public boolean searchShipByMMSI(Integer mmsi) {
        Ship ship = shipStore.getShipByMMSI(mmsi);
        return (ship != null);
    }

    /**
     * Method that searches a ship by its imo
     *
     * @param imo
     * @return true if the ship is founded and false if it isn´t
     */
    public boolean searchShipByIMO(String imo) {
        Ship ship = shipStore.getShipByIMO(imo);
        return (ship != null);
    }

    /**
     * Method that searches a ship by its call sign
     *
     * @param callSign
     * @return true if the ship is founded and false if it isn´t
     */
    public boolean searchShipByCallSign(String callSign) {
        Ship ship = shipStore.getShipByCallSign(callSign);
        return (ship!=null);
    }

    /**
     * Method that returns the ship using its mmsi
     *
     * @param mmsi
     * @return ship
     */
    public Ship getShipByMMSI(Integer mmsi) {
        return shipStore.getShipByMMSI(mmsi);
    }

    /**
     * Method that returns the ship using its imo
     *
     * @param imo
     * @return ship
     */
    public Ship getShipByIMO(String imo) {
        return shipStore.getShipByIMO(imo);
    }

    /**
     * Method that returns the ship using its callSign
     *
     * @param callSign
     * @return ship
     */
    public Ship getShipByCallSign(String callSign) {
        return shipStore.getShipByCallSign(callSign);
    }
}
