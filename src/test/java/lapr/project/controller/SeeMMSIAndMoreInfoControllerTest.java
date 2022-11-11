package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.store.ShipStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author
 */
class SeeMMSIAndMoreInfoControllerTest {
    SeeMMSIAndMoreInfoController controller = new SeeMMSIAndMoreInfoController();
    private List<Ship> shipList;

    @Test
    void getAllShips() {
        ShipStore shipStore = App.getInstance().getCompany().getShipStore();
        shipList= new ArrayList<>();
        //Ship's attributes
        Integer mmsi1 = 123456789;
        Integer mmsi2 = 123456781;
        Integer mmsi3 = 123456780;
        String imo1 = "IMO1234567";
        String imo2 = "IMO1234566";
        String imo3 = "IMO1234565";
        String callSign1 = "ABCD";
        String callSign2 = "ABDD";
        String callSign3 = "ABBD";

        //Ship
        Ship ship1=shipStore.createShip(mmsi1, imo1, callSign1);
        Ship ship2=shipStore.createShip(mmsi2, imo2, callSign2);
        Ship ship3=shipStore.createShip(mmsi3, imo3, callSign3);

        shipStore.saveShip(ship1);
        shipStore.saveShip(ship2);
        shipStore.saveShip(ship3);
        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);

        List<Ship> shipList1 = controller.getAllShips();
        assertFalse(shipList1.isEmpty());
        int size= shipList.size();
        int expected=3;
        assertEquals(expected,size);
    }
}