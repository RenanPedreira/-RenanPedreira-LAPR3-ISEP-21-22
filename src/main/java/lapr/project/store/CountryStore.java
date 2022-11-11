package lapr.project.store;

import lapr.project.model.Country;
import lapr.project.tree.AVL;

/**
 * @author Luís Araújo && Danilton Lopes
 */
public class CountryStore {
    private static final String DEFAULT_STRING = "";
    private static final Double DEFAULT_DOUBLE = 0.0;

    /**
     * Binary Search Tree containing the ships
     */
    private final AVL<Country> countryAVL;

    public CountryStore(AVL<Country> countryAVL) {
        this.countryAVL = countryAVL;
    }

    public Country getCountryByName(String name) {
        Country country = new Country(name, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_DOUBLE, DEFAULT_DOUBLE, DEFAULT_DOUBLE, DEFAULT_STRING);
        return countryAVL.findElement(country);
    }

    /**
     * Creates a country object with its unique identifiers
     * @param name
     * @param alpha2Code
     * @param alpha3Code
     * @param capital
     * @param population
     * @param latitude
     * @param longitude
     * @param continent
     * @return a new country
     */
    public Country createCountry(String name, String alpha2Code, String alpha3Code, String capital, Double population, Double latitude, Double longitude, String continent) {
        return new Country(name, alpha2Code, alpha3Code, capital, population, latitude, longitude, continent);
    }

    /**
     * Checks if the country name is valid for creation
     *
     * @param c The country to be validated
     * @return true if the name is not already registered
     */
    public boolean newCountry(Country c) {
        return (getCountryByName(c.getName()) == null);
    }

    /**
     * Validates the created country
     *
     * @param c The country to be validated
     * @return Validation if the country does not already exists
     */
    public boolean validateCountry(Country c) {
        if (c == null)
            return false;
        return newCountry(c);
    }

    /**
     * Saves the country if it is valid
     *
     * @param c The country to be added to the list of countries
     * @return true if the new country was added successfully
     */
    public boolean saveCountry(Country c) {
        if (!validateCountry(c))
            return false;
        this.countryAVL.insert(c);
        return true;
    }
}
