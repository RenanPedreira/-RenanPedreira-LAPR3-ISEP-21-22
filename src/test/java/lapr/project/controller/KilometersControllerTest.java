package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.store.ShipStore;
import lapr.project.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Renan Pedreira
 */
class KilometersControllerTest {

    // Singleton
    App app = App.getInstance();
    Company company = app.getCompany();

    //Stores
    ShipStore shipStore = company.getShipStore();
    //Ships
    Ship ship1 = new Ship(111331611, "IMO2360001", "BOSS");
    Ship ship2 = new Ship(221331622, "IMO2360002", "XIAO");
    Ship ship3 = new Ship(331331633, "IMO2360003", "TAO");
    Ship ship4 = new Ship(441331644, "IMO2360004", "BENNY_BOY");
    Ship ship5 = new Ship(551331655, "IMO2360005", "MR_11");
    //Dynamic data
    ShipDynamicData sdd1 = new ShipDynamicData("01/12/2020 01:00", 42.97875, -66.97001, 12.9, 13.1, 131, "CMA CGM ALMAVIVA", "IMO9450648");
    ShipDynamicData sdd2 = new ShipDynamicData("02/12/2020 02:00", 42.92236, -66.97243, 12.5, 2.4, 131, "CMA CGM ALMAVIVA", "IMO9450648");
    ShipDynamicData sdd3 = new ShipDynamicData("01/12/2020 03:00", 40.97875, -60.97001, 12.9, 13.1, 131, "CMA CGM ALMAVIVA", "IMO9450648");
    ShipDynamicData sdd4 = new ShipDynamicData("05/12/2020 04:00", 42.92236, -66.97243, 12.5, 2.4, 131, "CMA CGM ALMAVIVA", "IMO9450648");
    ShipDynamicData sdd5 = new ShipDynamicData("01/12/2020 05:00", 40.97875, -69.97001, 12.9, 13.1, 131, "CMA CGM ALMAVIVA", "IMO9450648");
    ShipDynamicData sdd6 = new ShipDynamicData("02/12/2020 05:00", 43.97875, -66.97001, 12.9, 13.1, 131, "CMA CGM ALMAVIVA", "IMO9450648");
    ShipDynamicData sdd7 = new ShipDynamicData("05/12/2020 06:00", 42.92236, -66.97243, 12.5, 2.4, 131, "CMA CGM ALMAVIVA", "IMO9450648");
    //Dates for the time period
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime d1 = LocalDateTime.parse("01/12/2020 00:00", formatter);
    LocalDateTime d2 = LocalDateTime.parse("03/12/2020 23:00", formatter);
    //Controllers
    private KilometersController kfc = new KilometersController();

    @BeforeEach
    public void setUp() throws Exception {
        //BOSS has valid data for the time period
        ship1.addDynamicData(sdd1);
        ship1.addDynamicData(sdd2);
        ship1.setType("70");

        //XIAO has valid data for the time period
        ship2.addDynamicData(sdd1);
        ship2.addDynamicData(sdd6);
        ship2.setType("70");

        //TAO has 2 valid data entries and an invalid 1
        ship3.addDynamicData(sdd5);
        ship3.addDynamicData(sdd6);
        ship3.addDynamicData(sdd7);
        ship3.setType("80");

        //BENNY_BOY has only one data entry
        ship4.addDynamicData(sdd1);
        ship4.setType("80");

        //MR_11 has invalid data for the set time period
        ship5.addDynamicData(sdd3);
        ship5.addDynamicData(sdd4);
        ship5.setType("11");

        //All ships are in the store
        shipStore.saveShip(ship1);
        shipStore.saveShip(ship2);
        shipStore.saveShip(ship3);
        shipStore.saveShip(ship4);
        shipStore.saveShip(ship5);
    }

    @Test
    public void calculateDistanceTest() {
        DecimalFormat df = new DecimalFormat("#.##");

        double expected1 = 6.27337455592524;

        double result1 = shipStore.calculateDistance(sdd1.getLat(), sdd1.getLon(), sdd2.getLat(), sdd2.getLon());
        assertEquals(df.format(expected1), df.format(result1));

        double expected2 = 0.0;
        double result2 = shipStore.calculateDistance(0.0, 0.0, 0.0, 0.0);
        assertEquals(expected2, result2);
    }

//    @Test
//    public void getTemporalPeriodShipsTest(){
//        List<Pair<Ship, Pair<Double, Double>>> list= shipStore.getTemporalPeriodShips(d1, d2);
//
//        int expectedResult=3;
//        int result = list.size();
//
//        System.out.println(list.get(0).getFirst().getCallSign());
//        System.out.println(list.get(1).getFirst().getCallSign());
//        System.out.println(list.get(2).getFirst().getCallSign());
//
//        // All ships were added
//        assertEquals(expectedResult, result);
//
//        // All dynamic data is within the interval(BOSS)
//        boolean bossData = (list.get(0).getSecond().getFirst()>0.0 && list.get(0).getSecond().getSecond()>0.0);
//        assertTrue(bossData);
//
//        // Some dynamic data is within the interval(TAO)
//        boolean taoData = (list.get(2).getSecond().getFirst()>0.0 && list.get(2).getSecond().getSecond()>0.0);
//        assertTrue(taoData);
//    }

    @Test
    public void getMaxTest() {
        //Max distance ship
        Ship expected1 = ship3; //XIAO travelled the most
        Ship result1 = shipStore.getMax(shipStore.getTemporalPeriodShips(d1, d2)).getFirst();
        assertEquals(expected1, result1);
    }

    @Test
    public void groupByTypeTest() {
        List<Pair<Ship, Pair<Double, Double>>> shipList = shipStore.getTemporalPeriodShips(d1, d2);
        Map<Integer, List<Pair<Ship, Pair<Double, Double>>>> result = shipStore.groupByType(shipList);

        Ship expected1 = ship1; //BOSS
        Ship expected2 = ship3; //TAO

        Ship result1 = result.get(70).get(0).getFirst();
        Ship result2 = result.get(80).get(0).getFirst();

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test
    public void getTopNTest() {
        //The ship with most kilometers travelled
        Map<Integer, List<Pair<Ship, Pair<Double, Double>>>> result = kfc.getTopN(2, "01/12/2020 00:00", "03/12/2020 23:00");
        Ship expected1 = ship2;
        Ship result1 = result.get(70).get(0).getFirst();
        assertEquals(expected1, result1);

        Ship expected2 = ship3;
        Ship result2 = result.get(80).get(0).getFirst();
        assertEquals(expected2, result2);

    }

    @Test
    public void getTopNTest2() {
        //The ship with most kilometers travelled
        Map<Integer, List<Pair<Ship, Pair<Double, Double>>>> result = kfc.getTopN(0, "01/12/2020 00:00", "03/12/2020 23:00");
        assertTrue(result.isEmpty());
    }

}