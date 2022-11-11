package lapr.project.controller;

import lapr.project.model.AISMessage;
import lapr.project.model.ShipDynamicData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;


/**
 * @author
 */
class SeeOrganizedMessagesControllerTest {
    /**
     * The controller class' instance.
     */
    private SeeOrganizedMessagesController controller = new SeeOrganizedMessagesController();

    /**
     * The list of mmsi to use to create positional messages.
     */
    private List<Integer> mmsi = new ArrayList<>();

    /**
     * The list of ShipDynamicData's instances to create positional messages.
     */
    private List<ShipDynamicData> actualPositions = new ArrayList<>();

    /**
     * The list of AISMessage's instances to compare the result of the getAllMessages method.
     */
    private List<AISMessage> aISMessages = new ArrayList<>();


    @BeforeEach
    public void setUp() throws Exception {
        /**
         * The MMSI of the boat organized.
         */
        mmsi.add(254687903);
        mmsi.add(254687901);
        mmsi.add(254687901);
        mmsi.add(254687901);
        mmsi.add(254687902);
        mmsi.add(254687902);
        mmsi.add(254687903);
        mmsi.add(254687904);

        /**
         * Ship dynamic data organized.
         */
        actualPositions.add(new ShipDynamicData("31/12/2020 02:15", 28.08366, -88.50578, 11.5, 131.0, 131, "CMA CGM ALMAVIVA", "IMO9450648"));
        actualPositions.add(new ShipDynamicData("30/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044"));
        actualPositions.add(new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044"));
        actualPositions.add(new ShipDynamicData("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "VARAMO", "IMO9395044"));

        actualPositions.add(new ShipDynamicData("31/12/2020 19:37", 24.34573, -85.12394, 11.7, 119.9, 117, "SAITA I", "IMO9643544"));
        actualPositions.add(new ShipDynamicData("31/12/2020 21:49", 24.14301, -84.72268, 11.7, 116.6, 114, "SAITA I", "IMO9643544"));
        actualPositions.add(new ShipDynamicData("31/12/2020 00:34", 28.30354, -88.78563, 11.7, 129.9, 131, "CMA CGM ALMAVIVA", "IMO9450648"));

        actualPositions.add(new ShipDynamicData("31/12/2020 23:03", 55.09307, -167.63625, 3.5, -61.6, 232, "HYUNDAI SINGAPORE", "IMO9305685"));

        /**
         * adding Message to the store.
         */
        controller.getStore().saveMessage(new AISMessage(mmsi.get(0), actualPositions.get(0)));
        controller.getStore().saveMessage(new AISMessage(mmsi.get(1), actualPositions.get(1)));
        controller.getStore().saveMessage(new AISMessage(mmsi.get(2), actualPositions.get(2)));
        controller.getStore().saveMessage(new AISMessage(mmsi.get(5), actualPositions.get(5)));
        controller.getStore().saveMessage(new AISMessage(mmsi.get(6), actualPositions.get(6)));
        controller.getStore().saveMessage(mmsi.get(3), actualPositions.get(3));
        controller.getStore().saveMessage(mmsi.get(4), actualPositions.get(4));
        controller.getStore().saveMessage(mmsi.get(7), actualPositions.get(7));

        /**
         * adding messages to the list.
         */
        aISMessages.add(new AISMessage(mmsi.get(0), actualPositions.get(0)));
        aISMessages.add(new AISMessage(mmsi.get(1), actualPositions.get(1)));
        aISMessages.add(new AISMessage(mmsi.get(2), actualPositions.get(2)));
        aISMessages.add(new AISMessage(mmsi.get(3), actualPositions.get(3)));
        aISMessages.add(new AISMessage(mmsi.get(4), actualPositions.get(4)));
        aISMessages.add(new AISMessage(mmsi.get(5), actualPositions.get(5)));
        aISMessages.add(new AISMessage(mmsi.get(6), actualPositions.get(6)));
        aISMessages.add(new AISMessage(mmsi.get(7), actualPositions.get(7)));
    }

    @Test
    void getAISMessagesByPeriod1() {
        LocalDateTime day1;
        LocalDateTime day2;
        List<AISMessage> messages=new ArrayList<>();
        messages.add(new AISMessage(254687901,new ShipDynamicData("30/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044")));
        messages.add(new AISMessage(254687901,new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044")));
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        day1 = LocalDateTime.parse("30/12/2020 16:00", formatter1);
        day2 = LocalDateTime.parse("31/12/2020 17:10", formatter1);
        List<AISMessage> messagesResult= controller.getAISMessagesByPeriod(254687901,day1,day2);
        Collections.sort(messages);
        assertEquals(messages,messagesResult);
    }
    @Test
    void getAISMessagesByPeriod2() {
        LocalDateTime day1;
        LocalDateTime day2;
        List<AISMessage> messages=new ArrayList<>();
        messages.add(new AISMessage(254687901,new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044")));
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        day1 = LocalDateTime.parse("30/12/2020 17:10", formatter1);
        day2 = LocalDateTime.parse("31/12/2020 17:10", formatter1);
        List<AISMessage> messagesResult= controller.getAISMessagesByPeriod(254687901,day1,day2);
        Collections.sort(messages);
        assertEquals(messages,messagesResult);
    }

    @Test
    void getAISMessagesByPeriod3() {
        LocalDateTime day1;
        LocalDateTime day2;
        List<AISMessage> messages=new ArrayList<>();
        messages.add(new AISMessage(254687901,new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044")));
        messages.add(new AISMessage(254687901,new ShipDynamicData("30/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044")));
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        day2 = LocalDateTime.parse("31/12/2020 16:20", formatter1);
        day1 = LocalDateTime.parse("30/12/2020 17:03", formatter1);
        List<AISMessage> messagesResult= controller.getAISMessagesByPeriod(254687901,day1,day2);
        Collections.sort(messages);
        Collections.sort(messagesResult);
        assertEquals(messages,messagesResult);
    }
}