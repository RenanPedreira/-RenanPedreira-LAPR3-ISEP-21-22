package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author
 */
class AISMessageTest {
    private List<ShipDynamicData> actualPositions = new ArrayList<>();
    private List<Integer> mmsi = new ArrayList<>();
    private List<AISMessage> aisMessages = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception {
        mmsi.add(254687903);
        mmsi.add(254687901);
        mmsi.add(254687901);
        mmsi.add(254687901);
        mmsi.add(254687902);
        mmsi.add(254687902);
        mmsi.add(254687903);
        mmsi.add(254687904);
        actualPositions.add(new ShipDynamicData("31/12/2020 02:15", 28.08366, -88.50578, 11.5, 131.0, 131, "CMA CGM ALMAVIVA", "IMO9450648"));
        actualPositions.add(new ShipDynamicData("30/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044"));
        actualPositions.add(new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044"));
        actualPositions.add(new ShipDynamicData("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "VARAMO", "IMO9395044"));

        actualPositions.add(new ShipDynamicData("31/12/2020 19:37", 24.34573, -85.12394, 11.7, 119.9, 117, "SAITA I", "IMO9643544"));
        actualPositions.add(new ShipDynamicData("31/12/2020 21:49", 24.14301, -84.72268, 11.7, 116.6, 114, "SAITA I", "IMO9643544"));
        actualPositions.add(new ShipDynamicData("31/12/2020 00:34", 28.30354, -88.78563, 11.7, 129.9, 131, "CMA CGM ALMAVIVA", "IMO9450648"));

        actualPositions.add(new ShipDynamicData("31/12/2020 23:03", 55.09307, -167.63625, 3.5, -61.6, 232, "HYUNDAI SINGAPORE", "IMO9305685"));
        aisMessages.add(new AISMessage(mmsi.get(0), actualPositions.get(0)));
        aisMessages.add(new AISMessage(mmsi.get(1), actualPositions.get(1)));
        aisMessages.add(new AISMessage(mmsi.get(2), actualPositions.get(2)));
        aisMessages.add(new AISMessage(mmsi.get(3), actualPositions.get(3)));
        aisMessages.add(new AISMessage(mmsi.get(4), actualPositions.get(4)));
        aisMessages.add(new AISMessage(mmsi.get(5), actualPositions.get(5)));
        aisMessages.add(new AISMessage(mmsi.get(6), actualPositions.get(6)));
        aisMessages.add(new AISMessage(mmsi.get(7), actualPositions.get(7)));
    }

    @Test
    public void testCreator() {
        AISMessage message = new AISMessage(aisMessages.get(0));
        assertEquals(aisMessages.get(0), message, "The Positional Messages are diffent");
    }

    @Test
    public void getMmsi() {
        System.out.println("Test getMmmsi");
        Integer n1 = 254687903;
        Integer n2 = 254687901;
        Integer n3 = 254687902;
        Integer n4 = 254687904;
        assertEquals(n1, aisMessages.get(0).getMmsi());
        assertEquals(n2, aisMessages.get(1).getMmsi());
        assertEquals(n3, aisMessages.get(5).getMmsi());
        assertEquals(n4, aisMessages.get(7).getMmsi());
    }

    @Test
    public void setMmsi() {
        System.out.println("Test setMmmsi");
        Integer n1 = 254687903;
        Integer n2 = 254687901;
        aisMessages.get(0).setMmsi(n2);
        aisMessages.get(1).setMmsi(n1);
        assertEquals(n2, aisMessages.get(0).getMmsi());
        assertEquals(n1, aisMessages.get(1).getMmsi());

    }

    @Test
    public void getPositionAtMoment() {
        System.out.println("Test getPositionAtMoment");
        ShipDynamicData position1 = new ShipDynamicData("31/12/2020 02:15", 28.08366, -88.50578, 11.5, 131.0, 131, "CMA CGM ALMAVIVA", "IMO9450648");
        ShipDynamicData position2 = new ShipDynamicData("31/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044");
        assertTrue(position1.equals(aisMessages.get(0).getPositionAtMoment()));
        assertFalse(aisMessages.get(1).getPositionAtMoment().equals(position2));
    }

    @Test
    public void setPositionAtMoment() {
        System.out.println("Test setPositionAtMoment");
        ShipDynamicData position1 = new ShipDynamicData("31/12/2020 02:00", 28.08366, -88.50578, 11.5, 131.0, 131, "CMA CGM ALMAVIVA", "IMO9450648");
        aisMessages.get(6).setPositionAtMoment(position1);
        assertEquals(position1, aisMessages.get(6).getPositionAtMoment());
    }

    @Test
    public void testEquals() {
        System.out.println("Test equals");
        AISMessage position = new AISMessage();
        assertNull(position.getPositionAtMoment());
        assertTrue(aisMessages.get(0).equals(new AISMessage(mmsi.get(0), actualPositions.get(0))));
        assertTrue(aisMessages.get(0).equals(aisMessages.get(0)));
        assertFalse(aisMessages.get(0).equals("Testing for different type."));
        assertFalse(aisMessages.get(1).equals(new AISMessage(mmsi.get(0), actualPositions.get(0))));
    }
    @Test
    public void testToString1() {
    }

    @Test
    public void compareTo1() {
        System.out.println("Test equals");

        int expected = 0;
        int result1 = aisMessages.get(0).compareTo(aisMessages.get(0));
        assertEquals(expected, result1);


    }

    @Test
    public void testCompareTo2() {
        int expected2 = 2;
        assertEquals(expected2, aisMessages.get(1).compareTo(aisMessages.get(2)));
    }

    @Test
    public void testCompareTo3() {
        int expected3 = 2;
        assertEquals(expected3, aisMessages.get(2).compareTo(aisMessages.get(3)));
    }

    @Test
    public void testCompareTo4() {
        int expected4 = 1;
        assertEquals(expected4, aisMessages.get(0).compareTo(aisMessages.get(1)));
    }


    @Test
    public void testToString() {
        System.out.println("Test toString");
        AISMessage message = aisMessages.get(0);
        assertEquals(message.toString(), aisMessages.get(0).toString());
        assertNotEquals(message.toString(), aisMessages.get(1).toString());

    }

    @Test
    void testHashCode() {
        int expected =Math.addExact(aisMessages.get(0).getMmsi().hashCode(),23);
        int result = aisMessages.get(0).hashCode();
        assertEquals(expected,result);
    }
}