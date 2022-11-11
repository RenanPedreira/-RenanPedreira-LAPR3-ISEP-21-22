package lapr.project.controller;

import lapr.project.data.PositionContainersDB;
import lapr.project.model.Ship;
import lapr.project.utils.Pair;

import java.sql.SQLException;
import java.util.List;

public class VesselSinkController {
    PositionContainersDB positionContainersDB;

    public VesselSinkController(){
        positionContainersDB = new PositionContainersDB();
    }

    /**
     *
     * @param mmsi
     * @param cargoManifestID
     * @return
     * @throws SQLException
     */
    public Pair<Integer,Pair<Pair<Double,Double>,Pair<Double,Pair<Double,Double>>>> getTheCalculationResult(String mmsi,String cargoManifestID) throws SQLException {
        double containerWeight=500.0;
        List<Pair<Pair<Double, Double>, Pair<Double, Double>>> dbData= positionContainersDB.getContainers(mmsi,cargoManifestID);
        if (dbData==null||dbData.isEmpty())
            throw new IllegalArgumentException("The ship's MMSI or the CargoManifestID introduced are not registered in the system or in the DB.");

        int totalContainers=dbData.size();

        double totalMassPlaced=containerWeight*totalContainers;
        int geometricValue=0;
        int mmsiInt=Integer.parseInt(mmsi);
        if (mmsiInt%4==0){
            geometricValue=4;
        }else {
            if (mmsiInt%3==0){
                geometricValue=3;
            }else {
                if (mmsiInt%2==0){
                    geometricValue=2;
                }else {
                    geometricValue=1;
                }
            }
        }
        double shipLength=dbData.get(0).getFirst().getFirst();
        double shipWidth=dbData.get(0).getFirst().getSecond();
        double shipWeight=Ship.calculateShipWeight(shipLength,shipLength,geometricValue);
        double pressure= Ship.calculatePressureMadeByShip(totalContainers,shipLength,shipWidth,geometricValue,shipWeight);
        double heightDifference=Ship.calculateHeightDifference(totalContainers,shipLength,shipWidth,geometricValue);

        return new Pair<>(totalContainers,new Pair<>(new Pair<>(dbData.get(0).getFirst().getFirst(),dbData.get(0).getFirst().getSecond()),new Pair<>(totalMassPlaced,new Pair<>(pressure,heightDifference))));
    }
}
