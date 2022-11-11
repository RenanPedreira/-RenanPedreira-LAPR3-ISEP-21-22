package lapr.project.controller;

import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.store.PortStore;
import lapr.project.store.ShipStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindClosestPortControllerTest {
    FindClosestPortController controller;
    ShipStore shipStore;
    PortStore portStore;
    @BeforeEach
    void setUp() {
        controller=new FindClosestPortController();
        shipStore=App.getInstance().getCompany().getShipStore();
        portStore=App.getInstance().getCompany().getPortStore();
    }

    @Test
    void getClosestPort() {
        Ship ship1 = new Ship(111331640, "IMO9693305", "DFBN");
        Ship ship2 =new Ship(123456789, "IMO1234567", "ABCD");

        ship1.addDynamicData(new ShipDynamicData("31/12/2020 23:04",55.09307,55.29307,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9693305"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 23:03",55.09310,55.19307,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9693305"));
        ship1.addDynamicData(new ShipDynamicData("31/12/2020 23:02",55.09300,55.09307,3.5,-61.6,232,"HYUNDAI SINGAPORE","IMO9693305"));


        ship2.addDynamicData(new ShipDynamicData("31/12/2020 23:00",45.09307,44.63625,3.5,-61.6,232,"CMA CGM ALMAVIVA","IMO1234567"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 23:06",46.19307,44.63625,3.5,-61.6,232,"CMA CGM ALMAVIVA","IMO1234567"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 23:03",46.09307,44.63625,3.9,-61.6,232,"CMA CGM ALMAVIVA","IMO1234567"));
        ship2.addDynamicData(new ShipDynamicData("31/12/2020 23:09",47.09307,47.63625,3.5,-61.0,232,"CMA CGM ALMAVIVA","IMO1234567"));

        shipStore.saveShip(ship1);
        shipStore.saveShip(ship2);

        Port expect1 = portStore.createPort(123456789, "Test", "Test", 44.0, 44.0);
        Port expect2 = portStore.createPort(123456788, "TestTest", "TestTest", 55.0, 55.0);
        portStore.savePort(expect1);
        portStore.savePort(expect2);

        try {
            Port portResult1 = controller.getClosestPort("DFBN", "31/12/2020 23:04");
            //assertEquals(expect2,portResult1);
        }catch (IllegalArgumentException exception4){
            throw new RuntimeException("Something goes wrong!");
        }

        Exception exception1 = assertThrows(IllegalArgumentException.class,()->controller.getClosestPort("AB", "31/12/2020 23:05"));
        String expectedMessage1="The call sign introduced doesn't correspond with any of the ships registered.";
        assertEquals(expectedMessage1,exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class,()->controller.getClosestPort("DFBN", "31/12/2020 23:05"));
        String expectedMessage2="The date time introduced doesn't correspond with any of the positions registered in the system.";
        assertEquals(expectedMessage2,exception2.getMessage());

    }

}