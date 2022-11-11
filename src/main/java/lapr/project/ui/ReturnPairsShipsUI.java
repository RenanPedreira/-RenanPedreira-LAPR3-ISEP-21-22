package lapr.project.ui;

import lapr.project.controller.ReturnPairsShipsController;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;
import java.util.List;

/**
 * @author Diogo Ferreira
 */
public class ReturnPairsShipsUI implements Runnable {

    ReturnPairsShipsController controller;


    public ReturnPairsShipsUI() {
        controller = new ReturnPairsShipsController();
    }

    @Override
    public void run() {
        int n = 0;
        List<Pair<Ship, Ship>> closeShipsAndDistance;
        List<Ship> allShips = controller.getAllShips();

        closeShipsAndDistance = controller.getAllPairsClosePorts(allShips);

        Utils.print("/---MMSI1---|---LATI1---|---LONG1---|---LATI2---|---LONG2---|---MMSI2---|---LATI1---|---LONG1---|---LATI2---|---LONG2---|--Distance--\\");
        for (Pair p : closeShipsAndDistance) {
            Utils.print(getTheInfos(p));
        }
        Utils.print("\\___________|___________|___________|___________|___________|___________|___________|___________|___________|___________|____________/");

        while (n == 0) {
            String answer= Utils.readLineFromConsole("Do you want to leave? [Y/N]");

            if (answer.equalsIgnoreCase("Y")){
                n = 1;
            }
        }
    }

    public String getTheInfos(Pair<Ship,Ship> pairsShip){
        int firstPosition = 0;
        int last1 = Math.decrementExact(pairsShip.getFirst().getDynamicData().size());
        int last2 = Math.decrementExact(pairsShip.getSecond().getDynamicData().size());

        double distanceDifference = 0.0;
        Ship first = pairsShip.getFirst();
        Ship second = pairsShip.getSecond();

        List<ShipDynamicData> positions1 = first.getDynamicData();
        List<ShipDynamicData> positions2 = second.getDynamicData();

        double distanceArrivalArrival = controller.calculateDistance(positions1.get(firstPosition).getLat(),positions1.get(firstPosition).getLon(),
                positions2.get(firstPosition).getLat(),positions2.get(firstPosition).getLon());
        if (controller.validateShipsArrivalDeparturePoint(distanceArrivalArrival, controller.LIM_MIN_DISTANCE)) {
            distanceDifference = distanceArrivalArrival;
        }

        double distanceDepartureDeparture =controller.calculateDistance(positions1.get(last1).getLat(),positions1.get(last1).getLon(),
                positions2.get(last2).getLat(),positions2.get(last2).getLon());
        if (controller.validateShipsArrivalDeparturePoint(distanceDepartureDeparture, controller.LIM_MIN_DISTANCE)) {
            distanceDifference = distanceDepartureDeparture;
        }

        double distanceArrivalDeparture = controller.calculateDistance(positions1.get(firstPosition).getLat(),positions1.get(firstPosition).getLon(),
                positions2.get(last2).getLat(),positions2.get(last2).getLon());
        if (controller.validateShipsArrivalDeparturePoint(distanceArrivalDeparture, controller.LIM_MIN_DISTANCE)) {
            distanceDifference = distanceArrivalDeparture;
        }

        double distanceDepartureArrival =controller.calculateDistance(positions1.get(last1).getLat(),positions1.get(last1).getLon(),
                positions2.get(firstPosition).getLat(),positions2.get(firstPosition).getLon());
        if (controller.validateShipsArrivalDeparturePoint(distanceDepartureArrival, controller.LIM_MIN_DISTANCE)) {
            distanceDifference = distanceDepartureArrival;
        }

        return String.format("|%11d|%11.5f|%11.5f|%11.5f|%11.5f|%11d|%11.5f|%11.5f|%11.5f|%11.5f|%12.5f|", first.getMmsi(),
                first.getDynamicData().get(firstPosition).getLat(), first.getDynamicData().get(firstPosition).getLon(),
                first.getDynamicData().get(last1).getLat(), first.getDynamicData().get(last1).getLon(),second.getMmsi(),
                second.getDynamicData().get(firstPosition).getLat(), second.getDynamicData().get(firstPosition).getLon(),
                second.getDynamicData().get(last2).getLat(), second.getDynamicData().get(last2).getLon(), distanceDifference);
    }

}
