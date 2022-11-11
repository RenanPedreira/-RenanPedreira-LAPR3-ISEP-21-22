package lapr.project.controller;

import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.store.PortStore;
import lapr.project.store.ShipStore;

public class FindClosestPortController {
    /**
     * The company ships' store.
     */
    ShipStore shipStore;

    /**
     * The company port's store.
     */
    PortStore portStore;

    /**
     * The constructor of the FindClosestController instance.
     */
    public FindClosestPortController(){
        shipStore=App.getInstance().getCompany().getShipStore();
        portStore=App.getInstance().getCompany().getPortStore();
    }

    /**
     * The method return the closest port of a ship
     * @param callSign of the ship.
     * @param dateTime, to get the positional infos of the ship.
     * @return the closest port
     */
    public Port getClosestPort(String callSign, String dateTime){
        Ship ship=shipStore.getShipByCallSign(callSign);
        if (ship==null)
            throw new IllegalArgumentException("The call sign introduced doesn't correspond with any of the ships registered.");
        ShipDynamicData position=ship.getShipDynamicDataByTime(dateTime);
        if (position==null)
            throw  new IllegalArgumentException("The date time introduced doesn't correspond with any of the positions registered in the system.");
        return portStore.getNearestPortFromPosition(position);
    }
}
