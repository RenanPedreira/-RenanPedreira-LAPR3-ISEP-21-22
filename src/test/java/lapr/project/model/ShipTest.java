package lapr.project.model;

import lapr.project.auth.domain.model.Email;
import lapr.project.auth.domain.model.Password;
import lapr.project.auth.domain.model.User;
import lapr.project.controller.App;
import lapr.project.store.ShipStore;
import lapr.project.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author
 */
class ShipTest {
    Ship s = new Ship(111331640, "IMO9693305", "DFBN");
    Ship s2=new Ship(123456789, "IMO1234567", "ABCD");

    @Test
    public void shipSetMethods(){
        s.setMmsi(211331640);
        assertEquals(s.getMmsi(), 211331640);

        s.setVesselName("SEOUL EXPRESS");
        assertEquals(s.getVesselName(), "SEOUL EXPRESS");

        s.setImo("IMO9193305");
        assertEquals(s.getImo(), "IMO9193305");

        s.setCallSign("DHBN");
        assertEquals(s.getCallSign(), "DHBN");

        s.setType("70");
        assertEquals(s.getType(), "70");

        s.setLength(294);
        assertEquals(s.getLength(), 294);

        s.setWidth(32);
        assertEquals(s.getWidth(), 32);

        s.setDraft(13.6);
        assertEquals(s.getDraft(), 13.6);

        s.setCodeFind(1);
        assertEquals(s.getCodeFind(), 1);
    }

    @Test
    public void lengthInTheLimit(){
        s.setLength(0);
        assertEquals(s.getLength(), 0);
    }

    @Test
    public void widthInTheLimit(){
        s.setWidth(0);
        assertEquals(s.getWidth(), 0);
    }

    @Test
    public void invalidMMSI(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setMmsi(12345);
        });
    }

    @Test
    public void invalidIMO(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setImo("12345");
        });
    }

    @Test
    public void lenTooSmall(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setLength(-5);
        });
    }

    @Test
    public void widTooSmall(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setWidth(-5);
        });
    }

   /* @Test
    public void latTooSmall(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setLat(-360.0);
        });
    }

    @Test
    public void latTooBig(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setLat(360.0);
        });
    }

    @Test
    public void lonTooSmall(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setLon(-360.0);
        });
    }

    @Test
    public void lonTooBig(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setLon(181.0);
        });
    }

    @Test
    public void cogTooSmall(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setCog(-20.0);
        });
    }

    @Test
    public void cogTooBig(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setCog(360.0);
        });
    }

    @Test
    public void headingTooSmall(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setHeading(-20);
        });
    }

    @Test
    public void headingTooBig(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setHeading(360);
        });
    }*/

    /**
     * Test that checks the equals method
     */
    @Test
    public void testEquals() {
        Ship ship = null;
        assertFalse(s.equals(s2));
        assertTrue(s2.equals(s2));
        assertFalse(s.equals(s2));
        assertFalse(s.equals(ship));
        assertFalse(s.equals("null"));
    }

    @Test
    public void compareEqualsTest(){
        Ship s2 = new Ship(s);
        assertEquals(s2.compareTo(s), 0);
        s2.setCodeFind(3);
        assertEquals(s2.compareTo(s), 0);
        s2.setCodeFind(4);
        assertEquals(s2.compareTo(s), 0);
        assertEquals(s2.equals(s), true);
        assertEquals(s2.equals(null), false);
        assertEquals(s2.getMmsi().compareTo(s2.getMmsi()), 0);
    }

    @Test
    void testHashCode() {
        Ship s2 = new Ship(s);
        int expected = s2.getMmsi();
        int result =s2.hashCode();
        assertEquals(expected,result);
    }

    /**
     * Test that tests the toString method
     */
    @Test
    void testToString() {
        //Act
        String expected= String.format("|%14d|%14s|%14s|%14s|%14s|%14d|%14d|%14f|", s.getMmsi(), s.getVesselName(), s.getImo(), s.getCallSign(), s.getType()
                , s.getLength(), s.getWidth(), s.getDraft());
        String result = s.toString();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void calculateTravelledDistance() {
        DecimalFormat df = new DecimalFormat("#");
        double expected=16392.957963500736;
        Ship ship1=new Ship(123456789, "IMO1234567", "ABCD");
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 16:20",42.7698,-66.9759,13.3,3.7,356,"VARAMO","IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:03",42.92236,-66.97243,12.5,2.4,358,"VARAMO","IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044"));
        double result1=ship1.calculateTravelledDistance();
        assertEquals(df.format(expected),df.format(result1));
    }

    @Test
    void calculateDeltaDistance() {
        DecimalFormat df = new DecimalFormat("#");
        double expected1=8202.467119788273;
        Ship ship1=new Ship(123456789, "IMO1234567", "ABCD");
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 16:20",42.7698,-66.9759,13.3,3.7,356,"VARAMO","IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:03",42.92236,-66.97243,12.5,2.4,358,"VARAMO","IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044"));
        double result1=ship1.calculateDeltaDistance();
        assertEquals(df.format(expected1),df.format(result1));

        double expected2=0.0;
        Ship ship2=new Ship(123456789, "IMO1234567", "ABCD");
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 16:20",42.7698,-66.9759,13.3,3.7,356,"VARAMO","IMO9395044"));
        double result2=ship2.calculateDeltaDistance();
        assertEquals(expected2,result2);
    }

    @Test
    void calculateTotalMovements() {
        int expected1=2;
        Ship ship1=new Ship(123456789, "IMO1234567", "ABCD");
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 16:20",42.7698,-66.9759,13.3,3.7,356,"VARAMO","IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:03",42.92236,-66.97243,12.5,2.4,358,"VARAMO","IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044"));
        int result1=ship1.calculateTotalMovements();
        assertEquals(expected1,result1);
    }

    @Test
    void getShipDynamicDataByTime() {
        Ship ship1=new Ship(123456789, "IMO1234567", "ABCD");
        ShipDynamicData expected1 = new ShipDynamicData("31/12/2020 16:20",42.7698,-66.9759,13.3,3.7,356,"VARAMO","IMO9395044");
        ship1.addDynamicData(expected1);
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:03",42.92236,-66.97243,12.5,2.4,358,"VARAMO","IMO9395044"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044"));
        ShipDynamicData result1=ship1.getShipDynamicDataByTime("31/12/2020 16:20");

        assertEquals(expected1,result1);
    }

    @Test
    void getCodeFind() {
        //Arrange
        int expectedResult=1;

        //Act
        int result=s.getCodeFind();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void calculateCM() {
        s.setLength(294);
        s.setWidth(32);
        s2.setLength(300);
        s2.setWidth(40);
        double expectedXCM1=138.88280542986428;
        double expectedYCM1=16;
        double expectedXCM2=138.51826411341727;
        double expectedYCM2=20;
        s.setGeometricCode(3);
        Pair<Double,Double> result1=s.calculateCM();
        s2.setGeometricCode(2);
        Pair<Double,Double> result2=s2.calculateCM();

        assertEquals(result1.getFirst(),expectedXCM1);
        assertEquals(result2.getFirst(),expectedXCM2);
        assertEquals(result1.getSecond(),expectedYCM1);
        assertEquals(result2.getSecond(),expectedYCM2);
    }

    @Test
    void calculateShipArea() {
        s.setLength(294);
        s.setWidth(32);
        s2.setLength(300);
        s2.setWidth(40);
        double expected1=9172.800000000001;
        double expected2=11428.31853071796;
        s.setGeometricCode(3);
        s2.setGeometricCode(2);
        double result1=s.calculateShipArea();
        double result2=s2.calculateShipArea();
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }

    @Test
    void calculateShipWeight() {
        s.setLength(294);
        s.setWidth(32);
        s2.setLength(300);
        s2.setWidth(40);
        double expected1=64209.600000000006;
        double expected2=79998.22971502572;
        s.setGeometricCode(3);
        s2.setGeometricCode(2);
        double result1=s.calculateShipWeight();
        double result2=s2.calculateShipWeight();
        double result3=Ship.calculateShipWeight(s.getLength(),s.getWidth(),s.getGeometricCode());
        double result4=Ship.calculateShipWeight(s2.getLength(),s2.getWidth(),s2.getGeometricCode());
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
        assertEquals(expected1,result3);
        assertEquals(expected2,result4);
    }

    @Test
    void pressureMadeByShip() {
        s.setLength(294);
        s.setWidth(32);
        s2.setLength(300);
        s2.setWidth(40);
        double expected1=111.4105915140415;
        double expected2=154.4566363538314;
        s.setGeometricCode(3);
        s2.setGeometricCode(2);
        double result1=s.calculatePressureMadeByShip(80);
        double result2=s2.calculatePressureMadeByShip(200);
        double result3=Ship.calculatePressureMadeByShip(80,s.getLength(),s.getWidth(),s.getGeometricCode(),s.calculateShipWeight());
        double result4=Ship.calculatePressureMadeByShip(200,s2.getLength(),s2.getWidth(),s2.getGeometricCode(),s2.calculateShipWeight());
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
        assertEquals(expected1,result3);
        assertEquals(expected2,result4);
    }

    @Test
    void calculateHeightDifference() {s.setLength(294);
        s.setWidth(32);
        s2.setLength(300);
        s2.setWidth(40);
        double expected1=26.46067139825808;
        double expected2=8.495333620586209;
        s.setGeometricCode(3);
        s2.setGeometricCode(2);
        double result1=s.calculateHeightDifference(500);
        double result2=s2.calculateHeightDifference(200);
        double result3=Ship.calculateHeightDifference(500,s.getLength(),s.getWidth(),s.getGeometricCode());
        double result4=Ship.calculateHeightDifference(200,s2.getLength(),s2.getWidth(),s2.getGeometricCode());
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
        assertEquals(expected1,result3);
        assertEquals(expected2,result4);
    }
}