package lapr.project.ui;

import lapr.project.controller.SearchDetailsShipController;
import lapr.project.controller.SeeMMSIAndMoreInfoController;
import lapr.project.model.Ship;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DetermineTheShipsCenterMassUI implements Runnable{
    SeeMMSIAndMoreInfoController controllerAllShips;
    SearchDetailsShipController controllerOneShip;

    public DetermineTheShipsCenterMassUI(){
        controllerAllShips = new SeeMMSIAndMoreInfoController();
        controllerOneShip = new SearchDetailsShipController();
    }

    @Override
    public void run() {
        int selectedOption=showsAndReturnSelected();
        List<Ship> result= new ArrayList<>();
        try {
            if (selectedOption == 0) {
                result.add(getSpecificShip());
            }

            if (selectedOption == 1) {
                result=controllerAllShips.getAllShips();
            }

            Utils.print("/---MMSI---|-CallSign-|----IMO----|-WEIGHT(T)-|-Length-|-Width-|---xCM---|---zCM---\\");
            for (Ship ship :  result)
                Utils.print(getShipInfo(ship));
            Utils.print("\\__________|___________|__________|___________|________|_______|_________|_________/");

            if(selectedOption==0){
                String repository="/ShipsSkecth";
                if(Utils.confirm("Do you want to see the ship sketch=[Y/N]")){
                    System.out.println(result.get(0).getGeometricCode());
                    switch (result.get(0).getGeometricCode()){
                        case 1:
                            repository=String.format("%s\\TriangleSketch.png",repository);
                            new ShowSketchUI(repository);
                            break;
                        case 2:
                            repository=String.format("%s\\SemiCircleSketch.png",repository);
                            new ShowSketchUI(repository);
                            break;
                        case 3:
                            repository=String.format("%s\\SemiHexagonSketch.png",repository);
                            new ShowSketchUI(repository);
                            break;
                        default:
                            Utils.print("The ships has no info about it's shape format");
                    }
                }
            }

        }catch (Exception e){
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(e.getMessage(),97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }



        if(!Utils.confirm("Do you want to exit?"))
            this.run();
    }

    private int showsAndReturnSelected(){
        List<String> options= new ArrayList<>();
        options.add("A specific ships");
        options.add("All ships");

        return Utils.showAndSelectIndex(options,"Do you want to calculate the center of mass of: ");
    }

    public Ship getSpecificShip(){
        List<String> options = new ArrayList<>();
        options.add("MMSI");
        options.add("IMO");
        options.add("CALL SIGN");

        int selected=Utils.showAndSelectIndex(options,"Select the code you want to use to search about the ship.");
        if (selected==-1)
            (new ShipCaptainUI()).run();
        Ship ship;
        switch (selected){
            case 0:
                int mmsi=Utils.readIntegerFromConsole("Type the ship's MMSI code: ");
                Ship shipMMSI= controllerOneShip.getShipByMMSI(mmsi);
                if (shipMMSI==null)
                    throw new IllegalArgumentException("The system has no ship registered with this MMSI code: "+mmsi);
                ship=shipMMSI;
                break;
            case 1:
                String imo = Utils.readLineFromConsole("Type the ship's IMO code: ");
                Ship shipIMO = controllerOneShip.getShipByIMO(imo);
                if (shipIMO==null)
                    throw new IllegalArgumentException("The system has no ship registered with this IMO code: "+imo);
                ship=shipIMO;
                break;
            case 2:
                String callSign = Utils.readLineFromConsole("Type the ship's Call Sign code: ");
                Ship shipCallSign= controllerOneShip.getShipByCallSign(callSign);
                if (shipCallSign==null)
                    throw new IllegalArgumentException("The system has no ship registered with this Call Sign code: "+callSign);
                ship=shipCallSign;
                break;
            default:
                ship=getSpecificShip();
                break;
        }

        return ship;
    }

    public String getShipInfo(Ship ship){
        Pair<Double,Double> coordenatesCM=ship.calculateCM();
        return String.format("|%10d|%10s|%11s|%11.2f|%8d|%7d|%9.2f|%9.2f|",ship.getMmsi(),
                ship.getCallSign(),ship.getImo(),ship.calculateShipWeight(),ship.getLength(),ship.getWidth(),coordenatesCM.getFirst(),
                coordenatesCM.getSecond());
    }
}
