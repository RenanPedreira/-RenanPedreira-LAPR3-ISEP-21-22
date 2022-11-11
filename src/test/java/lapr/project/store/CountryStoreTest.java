package lapr.project.store;

import lapr.project.model.Country;
import lapr.project.model.Ship;
import lapr.project.tree.AVL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class CountryStoreTest {
    private CountryStore countryStore;
    private AVL<Country> countryAVL = new AVL<>();

    @BeforeEach
    void setUp() {
        countryStore = new CountryStore(countryAVL);
    }

    /**
     * Test that verifies if the country is created. All the parameters of the expectedResult and result are the same
     */
    @Test
    public void createCountry1() {
        //Arrange
        Country result = new Country("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");

        //Act
        Country expectedResult = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");

        //Assert
        assertEquals(expectedResult.getName(), result.getName());
    }

    /**
     * Test that verifies if the country is created. The name of the expectedResult and result is not the same
     */
    @Test
    public void createCountry2() {
        //Arrange
        Country result = new Country("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");

        //Act
        Country expectedResult = countryStore.createCountry("Name2", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");

        //Assert
        assertNotEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getLatitude(), result.getLatitude());
    }

    /**
     * Test that verifies if a country with a certain name exists already. The name given to search exists
     */
    @Test
    void getCountryByName1() {
        //Arrange
        Country country = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");
        countryStore.saveCountry(country);

        //Act
        Country country2 = countryStore.getCountryByName("Name");

        //Assert
        assertNotNull(country2);
    }

    /**
     * Test that verifies if a country with a certain name exists already. The name given to search doesn´t exist
     */
    @Test
    public void getCountryByName2() {
        //Arrange
        Country country = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");
        countryStore.saveCountry(country);

        //Act
        Country country2 = countryStore.getCountryByName("Name2");

        //Assert
        assertNull(country2);
    }

    /**
     * Test that verifies if it possible to create a country with the same name of a country already stored
     */
    @Test
    public void newCountry1() {
        //Arrange
        Country country = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");
        countryStore.saveCountry(country);

        //Act
        Country country2=new Country("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");
        boolean expectedResult=countryStore.newCountry(country2);

        //Assert
        assertFalse(expectedResult);
    }

    /**
     * Test that verifies if it possible to create a country with a different name of a country already stored
     */
    @Test
    public void newCountry2() {
        //Arrange
        Country country = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");
        countryStore.saveCountry(country);

        //Act
        Country country2=new Country("Name2", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");
        boolean expectedResult=countryStore.newCountry(country2);

        //Assert
        assertTrue(expectedResult);
    }

    /**
     * Test that validates the created country
     */
    @Test
    public void validateCountry1() {
        //Arrange
        Country country = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");

        //Act
        boolean result = countryStore.validateCountry(country);
        boolean expectedResult = true;

        //Assert
        assertEquals(result, expectedResult);
    }

    /**
     * Test that validates the created country
     */
    @Test
    public void validateCountry2() {
        //Arrange
        Country country = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");
        countryAVL.insert(country);
        Country country2=new Country("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");

        //Act
        boolean result = countryStore.validateCountry(country2);
        boolean expectedResult = false;

        //Assert
        assertEquals(result, expectedResult);
    }

    /**
     * Test that validates the created country
     */
    @Test
    public void validateCountry3() {
        //Arrange
        Country country = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");
        countryAVL.insert(country);
        Country country2=null;

        //Act
        boolean result = countryStore.validateCountry(country2);
        boolean expectedResult = false;

        //Assert
        assertEquals(result, expectedResult);
    }

    /**
     * Test that saves the country if its valid
     */
    @Test
    public void saveCountry1() {
        //Arrange
        Country country = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");

        //Act
        boolean result = countryStore.saveCountry(country);
        boolean expectedResult = true;

        //Assert
        assertEquals(result, expectedResult);
    }

    /**
     * Test that saves the country if its valid
     */
    @Test
    public void saveCountry2() {
        //Arrange
        Country country = countryStore.createCountry("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");
        countryAVL.insert(country);
        Country country2=new Country("Name", "Alpha2Code", "Alpha3Code", "Capital", 10.0, 12.0, 14.0, "Continent");

        //Act
        boolean result = countryStore.saveCountry(country2);
        boolean expectedResult = false;

        //Assert
        assertEquals(result, expectedResult);
    }
}