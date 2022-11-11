package lapr.project.controller;


import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.store.ShipStore;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Renan Pedreira
 */
class ImportShipControllerTest {
    // Singleton
    App app = App.getInstance();
    Company company = app.getCompany();
    //Stores
    ShipStore shipStore = company.getShipStore();
    //Controllers
    private ImportShipController kfc;

    {
        try {
            kfc = new ImportShipController();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void controllerCanBeCreated1() throws IOException, ClassNotFoundException {
        ImportShipController ctr = new ImportShipController();
    }

    @Test
    public void controllerCanBeCreated2() {
        ImportShipController ctr = new ImportShipController(new Company("a"));
    }

    @Test
    public void getShipLineTest() {
        String file = "sships.csv";

        boolean expected1 = kfc.getShipLine(file, 0);
        boolean expected2 = kfc.getShipLine(file, 181); //2 reads from third line(empty)

        assertTrue(expected1);
        assertFalse(expected2);
    }

    @Test
    public void createNewShipAddDynamicDataTest() {
        String lineFile = "210950000,31/12/2020 17:19,42.97875,-66.97001,12.9,13.1,355,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B";
        String info[] = lineFile.split(",");

        kfc.getShipLine("sships.csv", 0);


        //createNewShip test
        Ship resultS = kfc.createNewShip(info);

        Ship expectedS = new Ship(Integer.parseInt(info[0]), info[8], info[9]);
        expectedS.setVesselName(info[7]);
        expectedS.setType(info[10]);
        expectedS.setLength(Integer.parseInt(info[11]));
        expectedS.setWidth(Integer.parseInt(info[12]));
        expectedS.setDraft(Double.parseDouble(info[13]));

        String result1 = resultS.toString();
        String expected1 = expectedS.toString();

        assertEquals(expected1, result1);


        //addDynamicData test
        kfc.addDynamicData(info);
        ShipDynamicData sdd1 = resultS.getDynamicData().get(0);
        assertNotNull(App.getInstance().getCompany().getMessageStore().getAllMessages());
        ShipDynamicData sdd2 = new ShipDynamicData(info[1],
                Double.parseDouble(info[2]),
                Double.parseDouble(info[3]),
                Double.parseDouble(info[4]),
                Double.parseDouble(info[5]),
                Integer.parseInt(info[6]),
                info[14],
                info[15]);

        String result2 = sdd1.toString();
        String expected2 = sdd2.toString();

        System.out.println(result2);
        System.out.println(expected2);

        assertEquals(expected2, result2);

    }

    @Test
    public void importDataTest() {
        // First three lines for the same ship, last one refers to another ship
        kfc.getShipLine("sships.csv", 0);
        kfc.importData();
        kfc.addCreatedShip();

        kfc.getShipLine("sships.csv", 1);
        kfc.importData();
        kfc.addCreatedShip();

        kfc.getShipLine("sships.csv", 2);
        kfc.importData();
        kfc.addCreatedShip();

        kfc.getShipLine("sships.csv", 2);
        kfc.importData();
        kfc.addCreatedShip();

        kfc.getShipLine("sships.csv", 31);
        kfc.importData();
        kfc.addCreatedShip();

        /*int expectedShipCount = 2;
        int resultShipCount = shipStore.getShips().size();
        assertEquals(expectedShipCount, resultShipCount);*/

        /*int expectedDataCount1 = 3;
        int resultDataCount1 = shipStore.getShips().get(0).getDynamicData().size();
        assertEquals(expectedDataCount1, resultDataCount1);

        int expectedDataCount2 = 1;
        int resultDataCount2 = shipStore.getShips().get(1).getDynamicData().size();
        assertEquals(expectedDataCount2, resultDataCount2);*/
    }

    @Test
    public void importDataTest2() {
        // First three lines for the same ship, last one refers to another ship
        //Act
        assertTrue(kfc.importData2("sships.csv"));
        List<Ship> list = shipStore.getShips();

        //Assert
        assertTrue(list.size()>0);
        assertFalse(kfc.importData2("sshipsaa.csv"));
    }
}