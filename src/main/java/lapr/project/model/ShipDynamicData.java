package lapr.project.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class regarding dynamic information about a ship
 *
 * @author Renan Pedreira
 */
public class ShipDynamicData implements Comparable<ShipDynamicData>{
    /**
     * Base Date Time: date/time of AIS message.
     */
    private LocalDateTime baseDateTime;
    /**
     * Latitude: ship latitude.
     */
    private Double lat;
    /**
     * Longitude: ship longitude.
     */
    private Double lon;
    /**
     * Speed over ground (SOG).
     */
    private Double sog;
    /**
     * Course over ground (COG): direction relative to absolute North.
     */
    private Double cog;
    /**
     * Heading: ship heading.
     */
    private Integer heading;
    private String trailerId;
    /**
     * Transceiver Class: class to transceiver used when sending data.
     */
    private String transceiverClass;

    /**
     * Creates a dynamic data with no attributes
     */
    public ShipDynamicData(){

    }

    /**
     * Creates a dynamic data group
     *
     * @param baseDateTime current date and time
     * @param lat current latitude
     * @param lon current longitude
     * @param sog current speed over ground
     * @param cog current course over ground
     * @param heading current heading
     * @param trailerId current trailerID
     * @param transceiverClass current transceiver class
     */
    public ShipDynamicData(String baseDateTime,
                           Double lat,
                           Double lon,
                           Double sog,
                           Double cog,
                           Integer heading,
                           String trailerId,
                           String transceiverClass)
    {
        setBaseDateTime(baseDateTime);
        setLat(lat);
        setLon(lon);
        setSog(sog);
        setCog(cog);
        setHeading(heading);
        setTrailerId(trailerId);
        setTranscieverClass(transceiverClass);
    }

    /**
     *
     * Gets the ship's base date and time
     *
     * @return the ship's base date and time
     */
    public LocalDateTime getBaseDateTime() {
        return baseDateTime;
    }

    /**
     *
     * Sets the ship's base date and time
     *
     * @param baseDateTime the ship's base date and time
     */
    public void setBaseDateTime(String baseDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.baseDateTime = LocalDateTime.parse(baseDateTime, formatter);
    }

    /**
     *
     * Gets the ship's latitude
     *
     * @return the ship's latitude
     */
    public Double getLat() {
        return lat;
    }

    /**
     *
     * Sets the ship's latitude
     *
     * @param lat the ship's latitude
     */
    public void setLat(Double lat){
        if(lat<-90 || lat>90)
            throw new IllegalArgumentException("latitude must be contained in [-90; 90]");
        this.lat = lat;
    }

    /**
     *
     * Gets the ship's longitude
     *
     * @return the ship's longitude
     */
    public Double getLon() {
        return lon;
    }

    /**
     *
     * Sets the ship's longitude
     *
     * @param lon the ship's longitude
     */
    public void setLon(Double lon){
        if(lon<-180 || lon>180)
            throw new IllegalArgumentException("longitude must be contained in [-180; 180]");
        this.lon = lon;
    }

    /**
     *
     * Gets the ship's SOG
     *
     * @return the ship's SOG
     */
    public Double getSog() {
        return sog;
    }

    /**
     *
     * Sets the ship's SOG
     *
     * @param sog the ship's SOG
     */
    public void setSog(Double sog) {
        this.sog = sog;
    }

    /**
     *
     * Gets the ship's COG
     *
     * @return the ship's COG
     */
    public Double getCog() {
        return cog;
    }

    /**
     *
     * Sets the ship's COG
     *
     * @param cog the ship's COG
     */
    public void setCog(Double cog){
        if(cog>359)
            throw new IllegalArgumentException("cog must be contained in [0; 359]");
        this.cog = cog;
    }

    /**
     *
     * Gets the ship's heading
     *
     * @return the ship's heading
     */
    public Integer getHeading() {
        return heading;
    }

    /**
     *
     * Sets the ship's heading
     *
     * @param heading the ship's heading
     */
    public void setHeading(Integer heading) {
        if(heading>359 && heading!=511)
            throw new IllegalArgumentException("heading must be contained in [0; 359]");
        this.heading = heading;
    }

    /**
     *
     * Gets the ship's trailer id
     *
     * @return the ship's trailer id
     */
    public String getTrailerId() {
        return trailerId;
    }

    /**
     *
     * Sets the ship's trailer id
     *
     * @param trailerId the ship's trailer id
     */
    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    /**
     *
     * Gets the ship's transciever class
     *
     * @return the ship's transciever class
     */
    public String getTranscieverClass() {
        return transceiverClass;
    }

    /**
     *
     * Sets the ship's transciever class
     *
     * @param transcieverClass the ship's transciever class
     */
    public void setTranscieverClass(String transcieverClass) {
        this.transceiverClass = transcieverClass;
    }

    @Override
    public String toString(){
        return (", baseDateTime='" + baseDateTime + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", sog=" + sog +
                ", cog=" + cog +
                ", heading=" + heading +
                ", trailerId=" + trailerId +
                ", transcieverClass='" + transceiverClass);
    }

    @Override
    public int hashCode() {
        int hashCode=7;
        hashCode=trailerId.hashCode()*hashCode;
        return hashCode;
    }

    /**
     * Compare the actual object with the Object received as parameter.
     * @param obj
     * @return boolean value
     */
    @Override
    public boolean equals(Object obj){
        if (obj==this)
            return true;
        if (!(obj instanceof ShipDynamicData))
            return false;

        ShipDynamicData otherShipDynamicaData= (ShipDynamicData) obj;

        if (!(baseDateTime.equals(otherShipDynamicaData.baseDateTime)))
            return false;
        if (!(lat.equals(otherShipDynamicaData.lat)))
            return false;
        if (!(lon.equals(otherShipDynamicaData.lon)))
            return false;
        if (!(sog.equals(otherShipDynamicaData.sog)))
            return false;
        if (!(cog.equals(otherShipDynamicaData.cog)))
            return false;
        if (!(heading.equals(otherShipDynamicaData.heading)))
            return false;
        if (!(trailerId.equals(otherShipDynamicaData.trailerId)))
            return false;

        return (transceiverClass.equals(otherShipDynamicaData.transceiverClass));
    }

    /**
     * Compares dynamic data based on the time
     *
     * @param o Other dynamic data
     * @return a number to identify the dynamic data's order
     */
    @Override
    public int compareTo(ShipDynamicData o) {
        return(this.getBaseDateTime().compareTo(o.getBaseDateTime()));
    }
}
