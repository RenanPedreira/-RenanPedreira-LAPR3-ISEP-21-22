package lapr.project.ui;

import lapr.project.controller.VesselSinkController;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

public class VesselSinkUI implements Runnable{
    VesselSinkController controller;
    public VesselSinkUI(){
        controller= new VesselSinkController();
    }
    @Override
    public void run() {
        String mmsi= Utils.readLineFromConsole("Type the ship's MMSI: ");
        String cargoManifestID = Utils.readLineFromConsole("Type the CargoManifestID to know how many container is on the ship: ");
        try{
            Pair<Integer,Pair<Pair<Double,Double>,Pair<Double,Pair<Double,Double>>>> dataResult=controller.getTheCalculationResult(mmsi,cargoManifestID);
            Utils.print("/---MMSI---|--SHIPLENGTH--|--SHIPWIDTH--|-NUMCONT-|-WEIGHTPLACED-|-SHIPPRESSURE-|-VESSELSINKPOSCONT-\\");
            Utils.print(getInfo(dataResult, mmsi));
            Utils.print("\\__________|______________|_____________|_________|______________|______________|___________________/");
        }catch (Exception e){
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(e.getMessage(),97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }

        if(!Utils.confirm("Do you want to exit?"))
            this.run();
    }

    public String getInfo(Pair<Integer,Pair<Pair<Double,Double>,Pair<Double,Pair<Double,Double>>>> data, String mmsi){
        return String.format("|%10s|%14.2f|%13.2f|%9d|%14.2f|%14.2f|%19.2f|",mmsi,data.getSecond().getFirst().getFirst(),data.getSecond().getFirst().getSecond(),
                data.getFirst(),data.getSecond().getSecond().getFirst(),data.getSecond().getSecond().getSecond().getFirst(),
                data.getSecond().getSecond().getSecond().getSecond());
    }
}
