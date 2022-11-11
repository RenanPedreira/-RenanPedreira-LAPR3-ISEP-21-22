package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.store.ShipStore;
import java.util.List;

public class SeeMMSIAndMoreInfoController {
    private final ShipStore shipStore;

    /**
     * The constructor of the SeeMMSIAndMoreInfoController, no parameters needed.
     */
    public SeeMMSIAndMoreInfoController(){
        shipStore=App.getInstance().getCompany().getShipStore();
    }

    /**
     * The function will return the list of all ships on the company.
     * @return list of all ships.
     */
    public List<Ship> getAllShips(){
        return shipStore.getShips();
    }

}
