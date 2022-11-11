package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.store.ShipStore;
import lapr.project.utils.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Diogo Ferreira
 */
public class ReturnPairsShipsController {

    private final ShipStore store;

    public static final double LIM_MIN_DISTANCE = 5.0;
    public static final double LIM_MIN = 10.0;

    /**
     * Instantiates a new Return pairs ships controller.
     */
    public ReturnPairsShipsController(){
        store = App.getInstance().getCompany().getShipStore();
    }

    /**
     * Calculate travelled distance double.
     *
     * @param ship the ship
     * @return the double
     */
    public double calculateTravelledDistance(Ship ship){
        return ship.calculateTravelledDistance();
    }

    /**
     * Calculate distance double.
     *
     * @param lat1 the lat 1
     * @param lon1 the lon 1
     * @param lat2 the lat 2
     * @param lon2 the lon 2
     * @return the double
     */
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        return store.calculateDistance(lat1,lon1,lat2,lon2);
    }

    /**
     * Check routes proximity boolean.
     *
     * @param ship1 the ship 1
     * @param ship2 the ship 2
     * @return the boolean
     */
    public boolean checkRoutesProximity(Ship ship1, Ship ship2) {
        boolean check = false;
        double calculateDistance1 = calculateTravelledDistance(ship1);
        double calculateDistance2 = calculateTravelledDistance(ship2);

        if (!validateTravelledDistance(calculateDistance1, LIM_MIN)||!validateTravelledDistance(calculateDistance2, LIM_MIN)) {
            return false;
        }

        List<ShipDynamicData> positions1= ship1.getDynamicData();
        List<ShipDynamicData> positions2= ship2.getDynamicData();

        int first=0;
        int last1 = Math.decrementExact(positions1.size());
        int last2 = Math.decrementExact(positions2.size());

        double distanceArrivalArrival = calculateDistance(positions1.get(first).getLat(),positions1.get(first).getLon(),
                                                            positions2.get(first).getLat(),positions2.get(first).getLon());
        if (validateShipsArrivalDeparturePoint(distanceArrivalArrival, LIM_MIN_DISTANCE)) {
            check = true;
        }

        double distanceDepartureDeparture =calculateDistance(positions1.get(last1).getLat(),positions1.get(last1).getLon(),
                                                                positions2.get(last2).getLat(),positions2.get(last2).getLon());
        if (validateShipsArrivalDeparturePoint(distanceDepartureDeparture, LIM_MIN_DISTANCE)) {
            check = true;
        }

        double distanceArrivalDeparture = calculateDistance(positions1.get(first).getLat(),positions1.get(first).getLon(),
                                                            positions2.get(last2).getLat(),positions2.get(last2).getLon());
        if (validateShipsArrivalDeparturePoint(distanceArrivalDeparture, LIM_MIN_DISTANCE)) {
            check = true;
        }

        double distanceDepartureArrival =calculateDistance(positions1.get(last1).getLat(),positions1.get(last1).getLon(),
                                                            positions2.get(first).getLat(),positions2.get(first).getLon());
        if (validateShipsArrivalDeparturePoint(distanceDepartureArrival, LIM_MIN_DISTANCE)) {
            check = true;
        }

        return check;
    }

    /**
     * Validate travelled distance boolean.
     *
     * @param travDistance the trav distance
     * @param limMin       the lim min
     * @return the boolean
     */
    public boolean validateTravelledDistance(double travDistance, double limMin){
        return (travDistance>=limMin);
    }

    /**
     * Validate ships arrival departure point boolean.
     *
     * @param distance the distance
     * @param limMin   the lim min
     * @return the boolean
     */
    public boolean validateShipsArrivalDeparturePoint(double distance, double limMin){
        return (distance<=limMin);
    }

    /**
     * Get all ships list.
     *
     * @return the list
     */
    public List<Ship> getAllShips(){
        return store.getShips();
    }

    /**
     * Gets all pairs close ports.
     *
     * @param allShips the all ships
     * @return the all pairs close ports
     */
    public  List<Pair<Ship,Ship>> getAllPairsClosePorts(List<Ship> allShips) {
        List<Pair<Ship, Ship>> closeShipsAndDistance = new ArrayList<>();
        int iSize = allShips.size();
        for (int i=0;i<iSize;i++){
            Ship ship1=allShips.get(i);
            Ship ship2;

            for (int j = Math.incrementExact(i); j < allShips.size(); j++){
                ship2 = allShips.get(j);
                if (checkRoutesProximity(ship1, ship2)){
                    closeShipsAndDistance.add(new Pair<>(ship1,ship2));
                }
            }
        }

        return closeShipsAndDistance;
    }


}
