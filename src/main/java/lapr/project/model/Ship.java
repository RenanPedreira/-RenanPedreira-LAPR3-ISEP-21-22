package lapr.project.model;

import lapr.project.tree.AVL;
import lapr.project.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo/Renan Pedreira
 */
public class Ship implements Comparable<Ship> {
    // Instance Variables
    //---------------------------------------------------------------------

    private int codeFind = 1;

    private int geometricCode;

    /**
     * MMSI: unique 9-digit ship identification code.
     */
    private Integer mmsi;
    /**
     * Ship name.
     */
    private String vesselName;
    /**
     * IMO: unique 7-digit international identification number,
     */
    private String imo;
    /**
     * Call sign: ship's unique callsign.
     */
    private String callSign;
    /**
     * Vessel type: ship type, numerically coded.
     */
    private String type;
    /**
     * Length: ship length, in meters.
     */
    private Integer length;
    /**
     * Width: ship width, in meters.
     */
    private Integer width;

    /**
     * Draft: Vertical distance between the waterline and the bottom of the ship's hull, in meters.
     */
    private Double draft;
    /**
     * dynamicData: Contains information regarding the ship's location over time
     */
    private final AVL<ShipDynamicData> dynamicData;

    // Constructors
    //---------------------------------------------------------------------

    /**
     * Creates a ship object with its unique identifiers
     *
     * @param mmsi     The ship's MMSI
     * @param imo      The ship's IMO
     * @param callSign The ship's call sign
     */
    public Ship(Integer mmsi, String imo, String callSign) {
        setMmsi(mmsi);
        setImo(imo);
        setCallSign(callSign);
        dynamicData = new AVL<>();
    }

    /**
     * Creates a ship object from an existing ship
     *
     * @param s A ship
     */
    public Ship(Ship s) {
        this.mmsi = s.getMmsi();
        this.vesselName = s.getVesselName();
        this.imo = s.getImo();
        this.callSign = s.getCallSign();
        this.type = s.getType();
        this.length = s.getLength();
        this.width = s.getWidth();
        this.draft = s.getDraft();
        this.dynamicData = s.dynamicData;
        this.geometricCode=s.geometricCode;
    }

    // Getters & Setters
    //---------------------------------------------------------------------

    /**
     * Gets the ship's MMSI
     *
     * @return the ship's MMSI
     */
    public Integer getMmsi() {
        return mmsi;
    }

    /**
     * Set the ship's MMSI, a 9-digit code
     *
     * @param mmsi the ship's MMSI
     */
    public void setMmsi(Integer mmsi) {
        if (mmsi.toString().length() != 9)
            throw new IllegalArgumentException("MMSI must be a 9-digit code");
        this.mmsi = mmsi;
    }

    /**
     * Gets the ship's vessel name
     *
     * @return the ship's vessel name
     */
    public String getVesselName() {
        return vesselName;
    }

    /**
     * Sets the ship's vessel name
     *
     * @param vesselName the ship's vessel name
     */
    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    /**
     * Gets the ship's IMO
     *
     * @return the ship's IMO
     */
    public String getImo() {
        return imo;
    }

    /**
     * Sets the ship's IMO
     *
     * @param imo the ship's IMO
     */
    public void setImo(String imo) {
        if (imo.length() != 10)
            throw new IllegalArgumentException("IMO must be a 7-digit code");
        this.imo = imo;
    }

    /**
     * Gets the ship's call sign
     *
     * @return the ship's call sign
     */
    public String getCallSign() {
        return callSign;
    }

    /**
     * Sets the ship's call sign
     *
     * @param callSign the ship's call sign
     */
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    /**
     * Gets the ship's MMSI number
     *
     * @return the ship's MMSI number
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the ship's vessel type
     *
     * @param type the ship's vessel type
     */
    public void setType(String type) {
        this.type = type;

    }

    /**
     * Gets the ship's length
     *
     * @return the ship's length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Sets the ship's length
     *
     * @param length the ship's length
     */
    public void setLength(Integer length) {
        if (length < 0)
            throw new IllegalArgumentException("length can't be negative");
        this.length = length;
    }

    /**
     * Gets the ship's width
     *
     * @return the ship's width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Sets the ship's width
     *
     * @param width the ship's width
     */
    public void setWidth(Integer width) {
        if (width < 0)
            throw new IllegalArgumentException("width can't be negative");
        this.width = width;
    }

    /**
     * Gets the ship's draft
     *
     * @return the ship's draft
     */
    public Double getDraft() {
        return draft;
    }

    /**
     * Sets the ship's draft
     *
     * @param draft the ship's draft
     */
    public void setDraft(Double draft) {
        this.draft = draft;
    }

    /**
     * Gets the information concerning dynamic data of the ship
     *
     * @return The ship's dynamic data
     */
    public List<ShipDynamicData> getDynamicData() {
        return (List<ShipDynamicData>) this.dynamicData.inOrder();
    }

    /**
     * Gets the ShipDynamicData
     *
     * @return The ship's dynamic data
     */
    public ShipDynamicData getShipDynamicDataByTime(String dateTime) {
        ShipDynamicData shipDynamicData= new ShipDynamicData(dateTime,0.0,0.0,0.0,0.0,0,"No data","no Data");
        return this.dynamicData.findElement(shipDynamicData);
    }

    /**
     * Add new dynamic data to the ship
     *
     * @param newDynamicData New dynamic data to be recorded to the ship
     */
    public void addDynamicData(ShipDynamicData newDynamicData) {
        this.dynamicData.insert(newDynamicData);
    }

    // Equals, hashCode & toString
    //---------------------------------------------------------------------

    /**
     * Gets all information about the ship
     *
     * @return a string with all the ship's attributes
     */
    @Override
    public String toString() {
        return String.format("|%14d|%14s|%14s|%14s|%14s|%14d|%14d|%14f|", mmsi, vesselName, imo, callSign, type, length, width, draft);
    }

    /**
     * Compares two ships in order to see if they are the same
     *
     * @param obj the other object being compared
     * @return True if the ships are the same one
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (obj.getClass() != this.getClass())
            return false;

        Ship ship = (Ship) obj;

        return this.mmsi.equals(ship.mmsi);
    }

    public void setGeometricCode(int geometricCode) {
        this.geometricCode = geometricCode;
    }

    /**
     * Gets the hash code for the ship
     *
     * @return the ship's hash code
     */
    @Override
    public int hashCode() {
        return this.mmsi.hashCode();
    }

    public int getCodeFind() {
        return codeFind;
    }

    public void setCodeFind(int codeFind) {
        this.codeFind = codeFind;
    }

    /**
     * Compares two ships
     *
     * @param o The other ship
     * @return 0, 1 or -1 depending in the mmsi being compared
     */
    @Override
    public int compareTo(Ship o) {
        if (getCodeFind() == 2)
            return this.imo.compareTo(o.getImo());
        if (getCodeFind() == 3)
            return this.callSign.compareTo(o.getCallSign());

        return this.mmsi.compareTo(o.getMmsi());
    }

    /**
     * The function will call the function that calculate the distance between 2 points using latitude and longitude,
     * doing that in a loop according to the list of the Dynamic Data's size, and then know the travelled distance.
     *
     * @return the total distance travelled in km.
     */
    public double calculateTravelledDistance() {
        int nextPosition;
        double totalDistance = 0;
        List<ShipDynamicData> positions = getDynamicData();
        int lastPosition = Math.decrementExact(positions.size());
        for (int i = 0; i < lastPosition; i++) {
            nextPosition = Math.incrementExact(i);
            totalDistance = Double.sum(Calculator.calculateDistance(positions.get(i).getLat(), positions.get(i).getLon(),
                    positions.get(nextPosition).getLat(), positions.get(nextPosition).getLat()), totalDistance);
        }
        return totalDistance;
    }

    /**
     * The function will calculate the distance between the initial point and the final point of the ship.
     *
     * @return the delta distance in km.
     */
    public double calculateDeltaDistance() {
        ShipDynamicData smallest = dynamicData.smallestElement();
        ShipDynamicData biggest = dynamicData.biggestElement();
        if (dynamicData.size() > 1) {
            return Calculator.calculateDistance(smallest.getLat(), smallest.getLon(), biggest.getLat(), biggest.getLat());
        } else {
            return 0;
        }
    }

    /**
     * The function will return the total of the movements made by the ships. So it verify the size of the list of
     * dynamic data.
     *
     * @return total movements
     */
    public int calculateTotalMovements() {
        return Math.decrementExact(dynamicData.size());
    }

    /**
     * By now we are considerate that all the ship's shape is rectangle format.
     *
     * @return the CM of the ship.
     */
    public Pair<Double,Double> calculateCM(){
        List<Pair<Double,Pair<Double,Double>>> data = new ArrayList<>();
        double area= ((width * length));

        double xCM= ((double)length*(0.9))/2;
        double yCM= ((double)width)/2;

        data.add(new Pair<>(area, new Pair<>(xCM,yCM)));

        data.add(new Pair<>(Calculator.calculateAreaGeometric(length*0.05,width*0.6,4), Calculator.calculateGeometricCM(length*0.05,width*0.6,4,4,width*0.2)));

        data.add(new Pair<>(Calculator.calculateAreaGeometric(length*0.1,width,geometricCode),Calculator.calculateGeometricCM(length*0.1,width,geometricCode,length*0.9,0.0)));

        return Calculator.calculateCenterMass(data);
    }

    public int getGeometricCode(){
        return this.geometricCode;
    }

    public double calculateShipArea(){
        return calculateShipArea(length,width,geometricCode);
    }

    private static double calculateShipArea(double length, double width, int geometricCode){
        return Calculator.calculateAreaGeometric(length*0.9,width,4)+Calculator.calculateAreaGeometric(length*0.1,width,geometricCode);
    }

    public double calculateShipWeight(){
        return calculateShipWeight(length,width,geometricCode);
    }

    public static double calculateShipWeight(double length, double width, int geometricCode){
        double WEIGHT_PER_SQUARE_METER = 7.0;
        return WEIGHT_PER_SQUARE_METER*Ship.calculateShipArea(length,width,geometricCode);
    }

    public double calculatePressureMadeByShip(int totalCointers){
        return Ship.calculatePressureMadeByShip(totalCointers,length,width,geometricCode,calculateShipWeight());
    }

    public static double calculatePressureMadeByShip(int totalCointers, double lenght, double width,int geometricCode, double shipWeight){
        double WEIGHT_ONE_CONTAINER = 500.0;
        double totalWeight=shipWeight+totalCointers*WEIGHT_ONE_CONTAINER;
        return Calculator.calculatePressure(calculateShipArea(lenght,width,geometricCode),totalWeight);
    }

    public double calculateHeightDifference(int totalContainers){
        return calculateHeightDifference(totalContainers,length,width,geometricCode);
    }

    public static double calculateHeightDifference(int totalContainers, double length, double width, int geometricCode){
        //Considering that they are static
        double containerHeight=2.7432;
        double containerLength=6.0;
        double containerWidth=2.3;
        double WEIGHT_ONE_CONTAINER = 500.0;
        double seaWaterDensity=1.03;
        double shipHeight=length*0.075;

        double volume1=Calculator.calculateVolume(length*0.9,width,shipHeight,4)+Calculator.calculateVolume(length*0.1,width,shipHeight,geometricCode);
        double density1=Ship.calculateShipWeight(length,width,geometricCode)/volume1;

        double volumeSubmerse=(density1/seaWaterDensity)*volume1;

        double heightSubmerse1=volumeSubmerse/calculateShipArea(length,width,geometricCode);

        double volume2=Calculator.calculateVolume(length*0.9,width,shipHeight,4)+Calculator.calculateVolume(length*0.1,width,shipHeight,geometricCode)+totalContainers*Calculator.calculateVolume(containerLength,containerWidth,containerHeight,4);

        double density2=(calculateShipWeight(length,width,geometricCode)+(WEIGHT_ONE_CONTAINER*totalContainers))/volume2;

        volumeSubmerse=(density2/seaWaterDensity)*volume2;
        double heightSubmerse2=volumeSubmerse/calculateShipArea(length,width,geometricCode);

        return heightSubmerse2-heightSubmerse1;
    }

}

