package lapr.project.ui;

import lapr.project.controller.ContinentNClosenessPlaceController;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ContinentNClosenessPlaceUI implements Runnable{

    ContinentNClosenessPlaceController controller;

    public ContinentNClosenessPlaceUI() {
        this.controller = new ContinentNClosenessPlaceController();
    }

    @Override
    public void run() {
        try {
            String continent = Utils.readLineFromConsole("Type the continent that you want to know the n closeness places: ");
            int numPlaces = Utils.readIntegerFromConsole("Type the number of closeness places you want to see: ");
            Map<Integer,Pair<VertexLocation,Double>> map = controller.getNClosenessPlaceByContinent(continent, numPlaces);
            if (!map.isEmpty()) {
                Utils.print("/--CONTINENT1--|-----LOCATIONNAME1-----|---LAT---|--LONG--|-AVERAGE-DISTANCE-\\");
                ArrayList<Integer> a = new ArrayList<>(map.keySet());
                Collections.sort(a);
                for (Integer in : a){
                    Utils.print(getMapInfo(map.get(in)));
                }
                Utils.print("\\______________|_______________________|_________|________|__________________/");
            } else {
                Utils.print("/----------------------------------------------NOTE-----------------------------------------------\\");
                Utils.print(String.format("|%97s|", StringUtils.center("Nothing found according to the giving continent, please confirm the continent name, or n=0.",97)));
                Utils.print("\\_________________________________________________________________________________________________/");
            }
        }catch (NullPointerException e){
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center("Nothing found confirm if the freighted network was built.",97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }catch (Exception e){
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(e.getMessage(),97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }
        if(!Utils.confirm("Do you want to exit?"))
            this.run();
    }

    public String getMapInfo(Pair<VertexLocation,Double> m){
        String locationName;
        if (m.getFirst().getCountryName()!=null)
            locationName=String.format("Port: %s",m.getFirst().getLocationName());
        else
            locationName=String.format("City: %s",m.getFirst().getLocationName());

        return String.format("|%14s|%23s|%9.3f|%8.3f|%18.5f|",m.getFirst().getContinent(),
                    locationName,m.getFirst().getLatitude(),m.getFirst().getLongitude(),m.getSecond());
    }
}
