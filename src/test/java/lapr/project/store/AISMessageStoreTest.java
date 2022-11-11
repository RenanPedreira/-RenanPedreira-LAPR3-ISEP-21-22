package lapr.project.store;

import lapr.project.controller.SeeOrganizedMessagesController;
import lapr.project.model.AISMessage;
import lapr.project.model.ShipDynamicData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;


/**
 * @author
 */
class AISMessageStoreTest {
    /**
     * The controller class' instance.
     */
    private AISMessageStore messageStore = new AISMessageStore();

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
    private List<AISMessage> aisMessages = new ArrayList<>();

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
        messageStore.saveMessage(new AISMessage(mmsi.get(0), actualPositions.get(0)));
        messageStore.saveMessage(new AISMessage(mmsi.get(1), actualPositions.get(1)));
        messageStore.saveMessage(new AISMessage(mmsi.get(2), actualPositions.get(2)));
        messageStore.saveMessage(new AISMessage(mmsi.get(5), actualPositions.get(5)));
        messageStore.saveMessage(new AISMessage(mmsi.get(6), actualPositions.get(6)));
        messageStore.saveMessage(mmsi.get(3), actualPositions.get(3));
        messageStore.saveMessage(mmsi.get(4), actualPositions.get(4));
        messageStore.saveMessage(mmsi.get(7), actualPositions.get(7));

        /**
         * adding messages to the list.
         */
        aisMessages.add(new AISMessage(mmsi.get(0), actualPositions.get(0)));
        aisMessages.add(new AISMessage(mmsi.get(1), actualPositions.get(1)));
        aisMessages.add(new AISMessage(mmsi.get(2), actualPositions.get(2)));
        aisMessages.add(new AISMessage(mmsi.get(3), actualPositions.get(3)));
        aisMessages.add(new AISMessage(mmsi.get(4), actualPositions.get(4)));
        aisMessages.add(new AISMessage(mmsi.get(5), actualPositions.get(5)));
        aisMessages.add(new AISMessage(mmsi.get(6), actualPositions.get(6)));
        aisMessages.add(new AISMessage(mmsi.get(7), actualPositions.get(7)));
    }

    /**
     * This test will test the createPositionalMessage method and validatePositionalMessage at once.
     */
    @Test
    public void testCreatePositionalMessage() {
        System.out.println("Test createPositionalMessage");
        AISMessage message = messageStore.createPositionalMessage(mmsi.get(0), actualPositions.get(0));
        assertEquals(aisMessages.get(0), message);
    }

    /**
     * This test will test the saveMessage method and others methods related and used in the saveMessage method.
     */
    @Test
    public void testSaveMessage() {
        System.out.println("Test saveMessage");

        Exception exception1 = assertThrows(Exception.class, () ->
        {
            messageStore.saveMessage(null, new ShipDynamicData());
        });
        String expectedMessage1 = "The argument is not valid.";
        String actualMessage1 = exception1.getMessage();
        assertTrue(actualMessage1.contains(expectedMessage1));

        //messageStore.createPositionalMessage(null,new ShipDynamicData());
        Exception exception2 = assertThrows(Exception.class, () ->
        {
            messageStore.saveMessage(0, null);
        });
        String expectedMessage2 = "The argument is not valid.";
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage2.contains(expectedMessage2));

        //messageStore.createPositionalMessage(null,new ShipDynamicData());
        Exception exception3 = assertThrows(Exception.class, () ->
        {
            messageStore.saveMessage(null);
        });
        String expectedMessage3 = "The argument is not valid.";
        String actualMessage3 = exception3.getMessage();
        assertTrue(actualMessage3.contains(expectedMessage3));
    }

    /**
     * This test will test the getAllMessages method.
     */
    @Test
    public void getAllMessages() {
        System.out.println("Test getAllMessages");
        SeeOrganizedMessagesController controller = new SeeOrganizedMessagesController();
        Collections.sort(aisMessages);
        List<AISMessage> messages = messageStore.getAllMessages();
        assertEquals(aisMessages, messages);
    }

    @Test
    void getAISMessagesByPeriod() {
        LocalDateTime day1;
        LocalDateTime day2;
        List<AISMessage> messages=new ArrayList<>();
        messages.add(new AISMessage(254687901,new ShipDynamicData("30/12/2020 17:03", 42.92236, -66.97243, 12.5, 2.4, 358, "VARAMO", "IMO9395044")));
        messages.add(new AISMessage(254687901,new ShipDynamicData("31/12/2020 16:20", 42.7698, -66.9759, 13.3, 3.7, 356, "VARAMO", "IMO9395044")));
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        day1 = LocalDateTime.parse("30/12/2020 16:00", formatter1);
        day2 = LocalDateTime.parse("31/12/2020 17:10", formatter1);
        List<AISMessage> messagesResult= messageStore.getAISMessagesByPeriod(254687901,day1,day2);
        Collections.sort(messages);
        assertEquals(messages,messagesResult);
    }
}