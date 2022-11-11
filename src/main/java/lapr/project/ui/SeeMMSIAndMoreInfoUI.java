package lapr.project.ui;

import lapr.project.controller.SeeMMSIAndMoreInfoController;
import lapr.project.model.Ship;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SeeMMSIAndMoreInfoUI implements Runnable{

    SeeMMSIAndMoreInfoController controller;

    public SeeMMSIAndMoreInfoUI(){
        controller=new SeeMMSIAndMoreInfoController();
    }

    @Override
    public void run() {
        try {
            List<Ship> shipList = controller.getAllShips();
            Collections.sort(shipList, new SortByTravDistAndNumberMovements());

            Utils.print("/---MMSI---|-Moviments-|-Travelled Distance-|-Delta Distance-\\");
            Utils.print("|__________|___________|____________________|________________|");
            for (Ship ship : shipList)
                Utils.print(getAllInfos(ship));
            Utils.print("\\__________|___________|____________________|________________/");
            Utils.print();
        }catch (Exception e){
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(e.getMessage(),97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }
        if(!Utils.confirm("Do you want to exit?"))
            this.run();
    }

    /**
     * An anonymous class that implement a comparator that permit organization of the list of ship by the travelled
     * distance and total movements.
     */
    class SortByTravDistAndNumberMovements implements Comparator<Ship> {
        @Override
        public int compare(Ship o1, Ship o2) {
            double diff= o1.calculateTravelledDistance()- o2.calculateTravelledDistance();
            if (diff==0){
                return (o1.calculateTotalMovements()-o2.calculateTotalMovements());
            }else{
                return (diff>0)?(-1):(1);
            }
        }
    }


    /**
     * The function will calculate all the infos needed of the ship and return it.
     *
     * @param ship, Ships
     */
    public String getAllInfos(Ship ship){
        return (String.format("|%10d|%11d|%20.5f|%16.5f|",ship.getMmsi(), ship.calculateTotalMovements(),
                ship.calculateTravelledDistance(),ship.calculateDeltaDistance()));
    }

}
