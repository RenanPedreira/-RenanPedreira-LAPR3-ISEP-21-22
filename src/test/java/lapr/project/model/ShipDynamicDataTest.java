package lapr.project.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author
 */
class ShipDynamicDataTest {
    ShipDynamicData s = new ShipDynamicData();

    @Test
    public void testSetGetMethods(){
        s.setBaseDateTime("31/12/2020 01:25");
        assertEquals(s.getBaseDateTime().toString(), "2020-12-31T01:25");

        s.setLat(36.39094);
        assertEquals(s.getLat(), 36.39094);

        s.setLon(-122.71335);
        assertEquals(s.getLon(), -122.71335);

        s.setSog(19.7);
        assertEquals(s.getSog(), 19.7);

        s.setCog(145.5);
        assertEquals(s.getCog(), 145.5);

        s.setHeading(147);
        assertEquals(s.getHeading(), 147);

        s.setTrailerId("79");
        assertEquals(s.getTrailerId(), "79");

        s.setTranscieverClass("B");
        assertEquals(s.getTranscieverClass(), "B");
    }

    @Test
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
    public void cogTooBig(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setCog(360.0);
        });
    }

    @Test
    public void headingTooBig(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            s.setHeading(360);
        });
    }

    @Test
    public void latInTheLimit1(){
        s.setLat(90.0);
        assertEquals(s.getLat(), 90.0);
    }

    @Test
    public void latInTheLimit2(){
        s.setLat(-90.0);
        assertEquals(s.getLat(), -90.0);
    }

    @Test
    public void lonInTheLimit1(){
        s.setLon(180.0);
        assertEquals(s.getLon(), 180.0);
    }

    @Test
    public void lonInTheLimit2(){
        s.setLon(-180.0);
        assertEquals(s.getLon(), -180.0);
    }

    @Test
    public void cogInTheLimit2(){
        s.setCog(359.0);
        assertEquals(s.getCog(), 359.0);
    }

    @Test
    public void headingInTheLimit1(){
        s.setHeading(359);
        assertEquals(s.getHeading(), 359);
    }

    @Test
    public void headingInTheLimit2(){
        s.setHeading(0);
        assertEquals(s.getHeading(), 0);
    }

    @Test
    public void headingInTheLimit3(){
        s.setHeading(511);
        assertEquals(s.getHeading(), 511);
    }

    @Test
    public void testEquals(){
        ShipDynamicData location1=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        ShipDynamicData location2=new ShipDynamicData("31/12/2020 00:34",28.30354,-88.78563,11.7,129.9,131,"CMA CGM ALMAVIVA","IMO9450648");
        ShipDynamicData location3=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        ShipDynamicData location4=new ShipDynamicData("31/12/2020 23:03",55.09300,-167.63625,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        ShipDynamicData location5=new ShipDynamicData("31/12/2020 23:00",55.09307,-167.63625,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        ShipDynamicData location6=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63635,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        ShipDynamicData location7=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.9,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        ShipDynamicData location8=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.5,-61.0,232,"HYUNDAI SINGAPORE","IMO9305685");
        ShipDynamicData location9=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.5,-61.6,237,"HYUNDAI SINGAPORE","IMO9305685");
        ShipDynamicData location10=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.5,-61.6,232,"VARAMO","IMO9395044");
        ShipDynamicData location11=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9395044");

        assertTrue(location1.equals(location1));
        assertFalse(location1.equals("Tests for today"));
        assertFalse(location1.equals(location2));
        assertTrue(location1.equals(location3));
        assertFalse(location1.equals(location4));
        assertFalse(location1.equals(location5));
        assertFalse(location1.equals(location6));
        assertFalse(location1.equals(location7));
        assertFalse(location1.equals(location8));
        assertFalse(location1.equals(location9));
        assertFalse(location1.equals(location10));
        assertFalse(location1.equals(location11));
    }
    /**
     * Test that tests the toString method
     */
    @Test
    void testToString() {
        //Arrange
        ShipDynamicData location1=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        String expected= (", baseDateTime='" + location1.getBaseDateTime() + '\'' +
                ", lat=" + location1.getLat() +
                ", lon=" + location1.getLon() +
                ", sog=" + location1.getSog() +
                ", cog=" + location1.getCog() +
                ", heading=" + location1.getHeading() +
                ", trailerId=" + location1.getTrailerId() +
                ", transcieverClass='" + location1.getTranscieverClass());

        //Act
        String result = location1.toString();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void testHashCode() {
        ShipDynamicData location1=new ShipDynamicData("31/12/2020 23:03",55.09307,-167.63625,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9305685");
        int expected=location1.getTrailerId().hashCode()*7;
        int result=location1.hashCode();
        assertEquals(expected,result);
    }
}