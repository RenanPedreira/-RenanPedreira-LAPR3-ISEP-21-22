package lapr.project.network;

import lapr.project.model.Colors;
import lapr.project.model.Calculator;

public class VertexLocation {

    /**
     * Port locationID:  Port's id number       Capital locationID: Capital's country name
     */
    String locationID;

    /**
     * Port locationName: Port's name           Capital locationName: Capital's name
     */
    String locationName;

    /**
     * Latitude in both cases
     */
    Double latitude;

    /**
     * Longitude in both cases
     */
    Double longitude;

    /**
     * Port countryName: port's country        Capital countryName: null(country is already specified in locationID attribute)
     */
    String countryName;

    /**
     * Continent in both cases
     */
    String continent;
    /**
     * Color of the country
     */
    Colors color;

    public VertexLocation(String locationID, String locationName, Double latitude, Double longitude, String countryName, String continent) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.countryName = countryName;
        this.continent = continent;
        this.color = null;
    }


    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        if (latitude>91||latitude<-90)
            throw new IllegalArgumentException("The latitude is out of the range.");
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if (longitude>181||longitude<-180)
            throw new IllegalArgumentException("The city longitude is out of the range.");
        this.longitude = longitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Double distanceBetween(VertexLocation l) {
        Double lat1 = this.latitude;
        Double lon1 = this.longitude;

        Double lat2 = l.getLatitude();
        Double lon2 = l.getLongitude();


        return Calculator.calculateDistance(lat1, lon1, lat2, lon2);
    }

    /**
     *
     * @param o
     * @return
     */
    //@Override
    public int compareTo(VertexLocation o) {
        return this.locationName.compareTo(o.locationName);
    }

    @Override
    public String toString() {
        return "VertexLocation{" +
                "locationID='" + locationID + '\'' +
                ", locationName='" + locationName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", countryName='" + countryName + '\'' +
                ", continent='" + continent + '\'' +
                ", color=" + color +
                '}';
    }
}
