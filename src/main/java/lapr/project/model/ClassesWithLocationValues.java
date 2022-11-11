package lapr.project.model;

public abstract class ClassesWithLocationValues {


    /**
     * The latitude of the city's position.
     */
    private double latitude;

    /**
     * The longitude of the city's position.
     */
    private double longitude;

    public ClassesWithLocationValues(double latitude,double longitude){
        setLatitude(latitude);
        setLongitude(longitude);
    }


    /**
     * The method will return the latitude of the city's position.
     *
     * @return latitude;
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * The method will define a new value to the city's latitude.
     *
     * @param latitude of the city.
     */
    public void setLatitude(double latitude) {
        if (latitude>91||latitude<-90)
            throw new IllegalArgumentException("The city latitude is out of the range.");
        this.latitude = latitude;
    }

    /**
     * The method will return the latitude of the city's position.
     *
     * @return longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * The method will define a new value to the city's longitude.
     *
     * @param longitude od the city.
     */
    public void setLongitude(double longitude) {
        if (longitude>181||longitude<-180)
            throw new IllegalArgumentException("The city longitude is out of the range.");
        this.longitude = longitude;
    }

    /**
     * The method will calculate the distance between the actual city instance with the city received as parameter.
     *
     * @param instance2 to calculate the distance.
     * @return the distance between them.
     */
    public double calculateDistanceDifference(ClassesWithLocationValues instance2){
        return Calculator.calculateDistance(this.getLatitude(),this.getLongitude(),instance2.getLatitude(),instance2.getLongitude());
    }

}
