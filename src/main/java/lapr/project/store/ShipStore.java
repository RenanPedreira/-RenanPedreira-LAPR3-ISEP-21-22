package lapr.project.store;

import lapr.project.controller.App;
import lapr.project.model.Calculator;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.tree.AVL;
import lapr.project.utils.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Store used add ships and manipulate registered ones
 *
 * @author Renan Pedreira
 */
public class ShipStore {
    private static final String DEFAULT_IMO = "IMO1234567";
    private static final String DEFAULT_CALLSIGN = "ABCD2";
    private static final Integer DEFAULT_MMSI = 123456789;
    /**
     * Binary Search Tree containing the ships
     */
    private final AVL<Ship> shipList;

    private final AVL<Ship> shipList2;

    private final AVL<Ship> shipList3;

    private final AISMessageStore messageStore;

    /**
     * Creates a ship store that contains all ships registered
     *
     * @param list A binary search(AVL) tree containing ships
     */
    public ShipStore(AVL<Ship> list, AVL<Ship> list2, AVL<Ship> list3) {
        this.shipList = list;
        this.shipList2 = list2;
        this.shipList3 = list3;
        messageStore = App.getInstance().getCompany().getMessageStore();
    }

    /**
     * Creates a ship object with its unique identifiers
     *
     * @param mmsi     The ship's MMSI
     * @param imo      The ship's IMO
     * @param callSign The ship's call sign
     * @return a new ship
     */
    public Ship createShip(Integer mmsi, String imo, String callSign) {
        return new Ship(mmsi, imo, callSign);
    }

    /**
     * Gets a ship using it's mmsi code
     *
     * @param mmsi Ship's MMSI code
     * @return The ship if it already exists
     */
    public Ship getShipByMMSI(Integer mmsi) {
        Ship ship = new Ship(mmsi, DEFAULT_IMO, DEFAULT_CALLSIGN);
        return shipList.findElement(ship);
    }

    /**
     * Gets a ship using it's imo code
     *
     * @param imo Ship's IMO code
     * @return The ship if it already exists
     */
    public Ship getShipByIMO(String imo) {
        Ship ship = new Ship(DEFAULT_MMSI, imo, DEFAULT_CALLSIGN);
        ship.setCodeFind(2);
        return shipList2.findElement(ship);
    }

    /**
     * Gets a ship using it's call sign
     *
     * @param callSign Ship's call sign
     * @return The ship if it already exists
     */
    public Ship getShipByCallSign(String callSign) {
        Ship ship = new Ship(DEFAULT_MMSI, DEFAULT_IMO, callSign);
        ship.setCodeFind(3);
        return shipList3.findElement(ship);
    }

    /**
     * Checks if the ship mmsi code is valid for creation
     *
     * @param s The ship to be validated
     * @return true if the mmsi is not already registered
     */
    public boolean newShip(Ship s) {
        return (getShipByMMSI(s.getMmsi()) == null);
    }

    /**
     * Validates the created ship
     *
     * @param s The ship to be validated
     * @return Validation if the ship does not already exists
     */
    public boolean validateShip(Ship s) {
        if (s == null)
            return false;
        return newShip(s);
    }

    /**
     * Saves the ship if it is valid
     *
     * @param s The ship to be added to the list of ships
     * @return true if the new ship was added successfully
     */
    public boolean saveShip(Ship s) {
        if (!validateShip(s))
            return false;
        Ship s1 = new Ship(s);
        this.shipList.insert(s1);

        Ship s2 = new Ship(s);
        s2.setCodeFind(2);
        this.shipList2.insert(s2);

        Ship s3 = new Ship(s);
        s3.setCodeFind(3);
        this.shipList3.insert(s3);
        return true;
    }

    /**
     * @param s                The ship
     * @param baseDateTime     current date and time
     * @param lat              current latitude
     * @param lon              current longitude
     * @param sog              current speed over ground
     * @param cog              current course over ground
     * @param heading          current heading
     * @param trailerId        current trailerID
     * @param transceiverClass current transceiver class
     */
    public void addDynamicData(Ship s, String baseDateTime,
                               Double lat,
                               Double lon,
                               Double sog,
                               Double cog,
                               Integer heading,
                               String trailerId,
                               String transceiverClass) {

        ShipDynamicData sdd = new ShipDynamicData(baseDateTime, lat, lon, sog, cog, heading, trailerId, transceiverClass);
        s.addDynamicData(sdd);
        messageStore.saveMessage(s.getMmsi(), sdd);
    }

    /**
     * Gets a list of the ships registered
     *
     * @return ship list
     */
    public List<Ship> getShips() {
        return ((List<Ship>) this.shipList.inOrder());
    }

    /**
     * Calculates the distance between two positions
     *
     * @param lat1 first ship's latitude
     * @param lon1 first ship's longitude
     * @param lat2 second ship's latitude
     * @param lon2 second ship's longitude
     * @return The distance
     */
    public double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        return Calculator.calculateDistance(lat1,lon1,lat2,lon2);
    }

    /**
     * Creates pairs of ships and their distance and mean sog
     *
     * @param d1 first date
     * @param d2 second date
     * @return list of pairs, ship/pair distance/mean sog
     */
    public List<Pair<Ship, Pair<Double, Double>>> getTemporalPeriodShips(LocalDateTime d1, LocalDateTime d2) {
        List<Ship> ships = getShips();
        List<Pair<Ship, Pair<Double, Double>>> validShips = new ArrayList<>();

        // Checks all ships
        for (Ship s : ships) {
            int infoCount = 0;
            double meanSOG = 0;
            double distance = 0;

            // For each ship gets the list of dynamic data
            for (int i = 0; i < s.getDynamicData().size() - 1; i++) {
                ShipDynamicData sdd1 = s.getDynamicData().get(i);
                ShipDynamicData sdd2 = s.getDynamicData().get(i + 1);

                // If the dynamic data's dates are within the given interval it sums its distance and the SOG
                if (sdd1.getBaseDateTime().isAfter(d1) && sdd1.getBaseDateTime().isBefore(d2) && sdd2.getBaseDateTime().isAfter(d1) && sdd2.getBaseDateTime().isBefore(d2)) {
                    distance =Double.sum(distance,calculateDistance(sdd1.getLat(), sdd1.getLon(), sdd2.getLat(), sdd2.getLon()));
                    meanSOG = Double.sum(meanSOG,sdd1.getSog());
                    infoCount = Math.incrementExact(infoCount);
                }
                // Distance and SOG of last dynamic
                if (i == s.getDynamicData().size() - 1 && sdd1.getBaseDateTime().isAfter(d1) && sdd1.getBaseDateTime().isBefore(d2)) {
                    meanSOG=Double.sum(meanSOG,sdd1.getSog());
                    infoCount = Math.incrementExact(infoCount);
                }
            }
            if (infoCount != 0)
                validShips.add(new Pair<>(s, new Pair<>(distance, meanSOG / infoCount)));
        }
        return validShips;
    }

    /**
     * Get the ship with most travelled distance
     *
     * @param list List of ships
     * @return A pair with he ship with most travelled distance and its mean sgo and distance
     */
    public Pair<Ship, Pair<Double, Double>> getMax(List<Pair<Ship, Pair<Double, Double>>> list) {
        double maxDistance = -1;
        int index = 0;
        Pair<Ship, Pair<Double, Double>> max = null;

        for (Pair<Ship, Pair<Double, Double>> elemente : list) {
            if (elemente.getSecond().getFirst() > maxDistance) {
                maxDistance = elemente.getSecond().getFirst();
                max = elemente;
                index = list.indexOf(elemente);
            }
        }
        list.remove(index);
        return max;
    }

    /**
     * Creates a map divided by vessel types
     *
     * @param shipList List with the top-N ships
     * @return a map divided by vessel types
     */
    public Map<Integer, List<Pair<Ship, Pair<Double, Double>>>> groupByType(List<Pair<Ship, Pair<Double, Double>>> shipList) {
        Map<Integer, List<Pair<Ship, Pair<Double, Double>>>> typeMap = new HashMap<>();

        // Creates a map using vesselType code as key
        for (Pair<Ship, Pair<Double, Double>> elemente : shipList) {
            Integer type = Integer.parseInt(elemente.getFirst().getType());
            if (!typeMap.containsKey(type)) {
                typeMap.put(type, new ArrayList<>());
            }
            typeMap.get(type).add(elemente);
        }

        return typeMap;
    }
}
