package lapr.project.model;

import lapr.project.network.VertexLocation;

/**
 * The type Port.
 *
 * @author Diogo Ferreira
 */
public class Port extends VertexLocation implements Comparable<Port> {

    /**
     * Instantiates a new Port.
     *
     * @param identification the identification
     * @param name           the name
     * @param country        the country
     * @param latitude       the latitude
     * @param longitude      the longitude
     */
    public Port(Integer identification, String name, String country, double latitude, double longitude) {
        super(String.valueOf(identification), name, latitude, longitude, country, "unidentified");
    }

    /**
     * Instantiates a new Port.
     *
     * @param identification the identification
     * @param name           the name
     * @param country        the country
     * @param latitude       the latitude
     * @param longitude      the longitude
     * @param continent      the continent
     */
    public Port(Integer identification, String name, String country, double latitude, double longitude, String continent) {
        super(String.valueOf(identification), name, latitude, longitude, country, continent);
    }

    /**
     * Gets identification.
     *
     * @return the identification
     */
    public Integer getIdentification() {
        return Integer.valueOf(super.getLocationID());
    }

    /**
     * Sets identification.
     *
     * @param identification the identification
     */
    public void setIdentification(Integer identification) {
        super.setLocationID(String.valueOf(identification));
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return super.getLocationName();
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        super.setLocationName(name);
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return super.getCountryName();
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        super.setCountryName(country);
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    @Override
    public Double getLatitude() {
        return super.getLatitude();
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    @Override
    public void setLatitude(double latitude) {
        super.setLatitude(latitude);
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    @Override
    public Double getLongitude() {
        return super.getLongitude();
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    @Override
    public void setLongitude(double longitude) {
        super.setLongitude(longitude);
    }

    /**
     * Gets continent.
     *
     * @return the continent
     */
    @Override
    public String getContinent() {
        return super.getContinent();
    }

    /**
     * Sets continent.
     *
     * @param continent the continent
     */
    @Override
    public void setContinent(String continent) {
        super.setContinent(continent);
    }

    @Override
    public String toString() {
        return String.format("|%10d|%10s|%10s|%10s|%10f|%10f|", getIdentification(), getName(), getCountry(), getContinent(), getLatitude(), getLongitude());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof Port)) {
            return false;
        }

        Port port = (Port) o;

        return getIdentification().equals(port.getIdentification());
    }

    @Override
    public int hashCode() {
        return getIdentification().hashCode();
    }


    /**
     * The method will calculate the distance between the actual city instance with the another instance of
     * ClassesWithLocationValues(Port or City) received as parameter.
     *
     * @param instance2 to calculate the distance.
     * @return the distance between them.
     */
    public double calculateDistanceDifference(VertexLocation instance2){
        return super.distanceBetween(instance2);
    }

    /**
     * Compare to int.
     *
     * @param o the o
     * @return the int
     */
    public int compareTo(Port o) {
        return getIdentification().compareTo(o.getIdentification());
    }

}
