package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.store.ShipStore;

/**
 * @author Luís Araújo
 */
public class MakeSummaryController {
    /**
     * Company.
     */
    private final Company company2;

    /**
     * Stores.
     */
    private final ShipStore shipStore2;

    /**
     * Constructs an instance of the Controller,
     * using singleton to access the app instance;
     */
    public MakeSummaryController() {
        // Singleton
        App app2 = App.getInstance();
        company2 = app2.getCompany();

        //Stores
        this.shipStore2 = company2.getShipStore();
    }

    /**
     * Method that verifies if a ship with a certain mmsi exists by calling the method getShipByMMSI in the store
     *
     * @param mmsi2
     * @return true if ship exists (not null) and false otherwise
     */
    public boolean searchShipByMMSI(Integer mmsi2) {
        Ship ship2 = shipStore2.getShipByMMSI(mmsi2);
        return (ship2 != null);
    }

    /**
     * Method that verifies if a ship with a certain imo exists by calling the method getShipByIMO in the store
     *
     * @param imo2
     * @return true if ship exists (not null) and false otherwise
     */
    public boolean searchShipByIMO(String imo2) {
        Ship ship2 = shipStore2.getShipByIMO(imo2);
        return (ship2 != null);
    }

    /**
     * Method that verifies if a ship with a certain callSign exists by calling the method getShipByCallSign in the store
     *
     * @param callSign2
     * @return true if ship exists (not null) and false otherwise
     */
    public boolean searchShipByCallSign(String callSign2) {
        Ship ship2 = shipStore2.getShipByCallSign(callSign2);
        return (ship2 != null);
    }

    /**
     * Method that calculates the distance between two ships by calling the method calculateDistance in the store
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return the distance
     */
    public Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        return this.shipStore2.calculateDistance(lat1, lon1, lat2, lon2);
    }

    /**
     * Method that returns a Ship by searching it with a certain mmsi and by calling the method getShipByMMSI in the store
     *
     * @param mmsi
     * @return the ship if it exists and false otherwise
     */
    public Ship getShipByMMSI(Integer mmsi) {
        return shipStore2.getShipByMMSI(mmsi);
    }

    /**
     * Method that returns a Ship by searching it with a certain imo and by calling the method getShipByIMO in the store
     *
     * @param imo
     * @return the ship if it exists and false otherwise
     */
    public Ship getShipByIMO(String imo) {
        return shipStore2.getShipByIMO(imo);
    }

    /**
     * Method that returns a Ship by searching it with a certain callSign and by calling the method getShipByCallSign in the store
     *
     * @param callSign
     * @return the ship if it exists and false otherwise
     */
    public Ship getShipByCallSign(String callSign) {
        return shipStore2.getShipByCallSign(callSign);
    }
}
