package lapr.project.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @Author Renan Pedreira && Luís Araújo
 */
public class CountryTest {
    private Country republicaDasBananas;

    @BeforeEach
    public void setUp() throws Exception {
        republicaDasBananas = new Country("Republic of Bananas",
                                        "RB",
                                        "ROB",
                                        "Bananaland",
                                        1111.0,
                                        11.0,
                                        11.0,
                                        "Oceania");
    }

    @Test
    public void TestCountryGetSet(){
        republicaDasBananas.setName("Republic of Papaya");
        String expectedName = "Republic of Papaya";
        String actualName = republicaDasBananas.getName();
        assertEquals(expectedName, actualName);

        republicaDasBananas.setAlpha2Code("RP");
        String expectedAlpha2 = "RP";
        String actualAlpha2 = republicaDasBananas.getAlpha2Code();
        assertEquals(expectedAlpha2, actualAlpha2);

        republicaDasBananas.setAlpha3Code("ROP");
        String expectedAlpha3 = "ROP";
        String actualAlpha3 = republicaDasBananas.getAlpha3Code();
        assertEquals(expectedAlpha3, actualAlpha3);

        republicaDasBananas.setContinent("America");
        String expectedContinent = "America";
        String actualContinent = republicaDasBananas.getContinent();
        assertEquals(expectedContinent, actualContinent);

        republicaDasBananas.setPopulation(1234.0);
        Double expectedPopulation = 1234.0;
        Double actualPopulation = republicaDasBananas.getPopulation();
        assertEquals(expectedPopulation, actualPopulation);

        republicaDasBananas.setLatitude(22.0);
        Double expectedLatitude = 22.0;
        Double actualLatitude = republicaDasBananas.getLatitude();
        assertEquals(expectedLatitude, actualLatitude);

        republicaDasBananas.setLongitude(22.0);
        Double expectedLongitude = 22.0;
        Double actualLongitude = republicaDasBananas.getLongitude();
        assertEquals(expectedLongitude, actualLongitude);

        republicaDasBananas.setCapital("Papaya Town");
        String expectedCapital = "Papaya Town";
        String actualCapital = republicaDasBananas.getCapital();
        assertEquals(expectedCapital, actualCapital);
    }

    /**
     * Test that checks the compareTo method
     */
    @Test
    public void compareTo() {
        Country country2=new Country("A",
                "RAB",
                "ROAB",
                "AA",
                1121.0,
                31.0,
                11.0,
                "A");;
        //Act
        int result1 = republicaDasBananas.compareTo(country2);
        int result2 = republicaDasBananas.compareTo(republicaDasBananas);

        //Assert
        assertEquals(result1, 17);
        assertEquals(result2, 0);
        assertTrue(result1 > 0, republicaDasBananas.getName() + " is not equal to " + country2.getName());
        assertTrue(result2 == 0, republicaDasBananas.getName() + " is equal to " + republicaDasBananas.getName());
    }

}
