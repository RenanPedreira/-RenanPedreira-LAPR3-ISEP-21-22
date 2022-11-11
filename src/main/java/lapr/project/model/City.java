package lapr.project.model;

public class City extends ClassesWithLocationValues implements Comparable<City>{

    /**
     * City's country.
     */
    private String country;

    /**
     * City's name.
     */
    private String cityName;

    /**

    /**
     * The constructor of the city's class instance.
     *
     * @param cityName for the new city.
     * @param latitude of the city's position.
     * @param longitude of the city's position.
     */
    public City(String cityName, double latitude, double longitude){
        super(latitude, longitude);
        setCityName(cityName);
    }

    /**
     * The method will return the city's name.
     *
     * @return cityName.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * The method will define a new value to the city name.
     *
     * @param cityName of the city instance.
     */
    public void setCityName(String cityName) {
        if (cityName==null||cityName.equals(""))
            throw new IllegalArgumentException("The city name could not be empty.");
        this.cityName = cityName;
    }

    /**
     * The method will return the latitude of the city's position.
     *
     * @return latitude;
     */
    @Override
    public double getLatitude() {
        return super.getLatitude();
    }

    /**
     * The method will define a new value to the city's latitude.
     *
     * @param latitude of the city.
     */
    @Override
    public void setLatitude(double latitude) {
        super.setLatitude(latitude);
    }

    /**
     * The method will return the latitude of the city's position.
     *
     * @return longitude.
     */
    @Override
    public double getLongitude() {
        return super.getLongitude();
    }

    /**
     * The method will define a new value to the city's longitude.
     *
     * @param longitude od the city.
     */
    @Override
    public void setLongitude(double longitude) {
        super.setLongitude(longitude);
    }

    /**
     * The method to have the hashcode.
     *
     * @return the city's hash code.
     */
    @Override
    public int hashCode(){
        int hashCode=7;
        return hashCode*7*cityName.hashCode();
    }

    /**
     * The method will compare the actual instance with an object received as parameter and check if they are equals.
     *
     * @param obj received as parameter to compare.
     * @return true or false.
     */
    @Override
    public boolean equals(Object obj){
        if (obj==null)
            return false;
        if (!(obj instanceof City))
            return false;
        City city2=(City) obj;
        if (!city2.getCityName().equals(cityName))
            return false;
        if (city2.getLatitude()!=getLatitude())
            return false;
        return (city2.getLongitude()==getLongitude());
    }

    /**
     * The method will calculate the distance between the actual city instance with the another instance of
     * ClassesWithLocationValues(Port or City) received as parameter.
     *
     * @param instance2 to calculate the distance.
     * @return the distance between them.
     */
    @Override
    public double calculateDistanceDifference(ClassesWithLocationValues instance2){
        return super.calculateDistanceDifference(instance2);
    }

    /**
     * Compare the cities positions and return positive or negative value.
     *
     * @param o the second city.
     * @return -1, 0 or 1.
     */
    @Override
    public int compareTo(City o) {
        return (calculateDistanceDifference(o)>0)?1:-1;
    }
}
