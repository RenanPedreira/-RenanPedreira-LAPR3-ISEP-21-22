package lapr.project.ui;

import lapr.project.controller.MostEfficiencyCircuitController;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MostEfficiencyCircuitUI implements Runnable{
    MostEfficiencyCircuitController controller;

    public MostEfficiencyCircuitUI(){
        controller=new MostEfficiencyCircuitController();
    }
    @Override
    public void run() {
        List<String> options= new ArrayList<>();
        options.add("Port");
        options.add("City");

        int selected= Utils.showAndSelectIndex(options,"The initial point is a: ");
        if (selected==-1)
            (new TrafficManagerUI()).run();
        String name=Utils.readLineFromConsole("Insert the location's name");
        Utils.print();
        try {
            Pair<LinkedList<VertexLocation>,Double> result=controller.getMostEfficiencyCircuit(name,selected);
            Utils.print("/-INDEX-|-----LOCATIONNAME1-----|---LAT---|--LONG--\\");
            int index=1;
            for (VertexLocation vertexLocation : result.getFirst()){
                Utils.print(getInfo(index,vertexLocation));
                index++;
            }
            Utils.print("\\_______|_______________________|_________|________/");
        }catch (Exception e){
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(e.getMessage(),97)));
            Utils.print("\\_________________________________________________________________________________________________/");
            e.printStackTrace();
        }

        if(!Utils.confirm("Do you want to exit?"))
            this.run();
    }

    public String getInfo(int index,VertexLocation vertexLocation){
        return String.format("|%7d|%23s|%9.3f|%8.3f|",index,vertexLocation.getLocationName(),vertexLocation.getLatitude(),
                vertexLocation.getLongitude());
    }
}
