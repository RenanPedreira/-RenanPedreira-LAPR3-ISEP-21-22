package lapr.project.model;

import lapr.project.network.VertexLocation;

/**
 * @Author Renan Pedreira
 */
public class Country extends VertexLocation implements Comparable<Country> {

    private String alpha2Code;
    private String alpha3Code;
    private Double population;

    /**
     * @param name       Country's name
     * @param alpha2Code Country's 2 letters code
     * @param alpha3Code Country's 3 letters code
     * @param capital    Country's capital
     * @param population Country's population
     * @param latitude   Country's latitude
     * @param longitude  Country's longitude
     * @param continent  Country's continent
     */
    public Country(String name, String alpha2Code, String alpha3Code, String capital, Double population, Double latitude, Double longitude, String continent) {
        super(name, capital, latitude, longitude, null, continent);
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.population = population;
    }

    /**
     * Gets the Country's name
     *
     * @return the Country's name
     */
    public String getName() {
        return super.getLocationID();
    }

    /**
     * Gets the Country's name
     *
     * @return the Country's name
     */
    public void setName(String name) {
        super.setLocationID(name);
    }

    /**
     * Gets the Country's name
     *
     * @return the Country's name
     */
    public String getAlpha2Code() {
        return alpha2Code;
    }

    /**
     * Gets the Country's name
     *
     * @return the Country's name
     */
    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    /**
     * Gets the Country's name
     *
     * @return the Country's name
     */
    public String getAlpha3Code() {
        return alpha3Code;
    }

    /**
     * Gets the Country's name
     *
     * @return the Country's name
     */
    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    /**
     * Gets the Country's name
     *
     * @return the Country's name
     */
    public String getCapital() {
        return super.getLocationName();
    }

    /**
     * Gets the Country's name
     *
     * @return the Country's name
     */
    public void setCapital(String capital) {
        super.setLocationName(capital);
    }

    /**
     * +     * Gets the Country's name
     * +     *
     * +     * @return the Country's name
     * +
     */
    public Double getPopulation() {
        return population;
    }

    /**
     * Gets the Country's latitude
     *
     * @return the Country's latitude
     */
    public void setPopulation(Double population) {
        this.population = population;
    }

    @Override
    public Double getLatitude() {
        return super.getLatitude();
    }

    /**
     * Sets the Country's latitude
     */
    public void setLatitude(Double latitude) {
        super.setLatitude(latitude);
    }

    /**
     * Gets the Country's longitude
     *
     * @return the Country's longitude
     */
    @Override
    public Double getLongitude() {
        return super.getLongitude();
    }

    /**
     * Sets the Country's longitude
     */
    public void setLongitude(Double longitude) {
        super.setLongitude(longitude);
    }

    /**
     * Gets the Country's continent
     *
     * @return the Country's continent
     */
    @Override
    public String getContinent() {
        return super.getContinent();
    }

    /**
     * Sets the Country's continent
     */
    @Override
    public void setContinent(String continent) {
        super.setContinent(continent);
    }

    /**
     * Compares two countries
     *
     * @param o The other country
     * @return 0, 1 or -1 depending on the name compared
     */
    @Override
    public int compareTo(Country o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}