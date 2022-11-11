package lapr.project.controller;

import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;

import java.util.LinkedList;

public class MostEfficiencyCircuitController {
    private final FreightNetwork freightNetwork;

    public MostEfficiencyCircuitController(){
        freightNetwork=App.getInstance().getCompany().getFreightNetwork();
    }

    /**
     *
     * @param vertexLocationInfo the name of the location
     * @param type
     * @return
     */
    public Pair<LinkedList<VertexLocation>,Double> getMostEfficiencyCircuit(String vertexLocationInfo,int type){
        VertexLocation vertexLocation;
        if (type==1){
            vertexLocation=getCityVertex(vertexLocationInfo);
            if (vertexLocation==null){
                throw new IllegalArgumentException("The system hasn't no city registered with name "+vertexLocationInfo);
            }
        }else
        if (type==0){
            vertexLocation=getPortVertex(vertexLocationInfo);
            if (vertexLocation==null){
                throw new IllegalArgumentException("The system hasn't no port registered with name "+vertexLocationInfo);
            }
        }
        else
            throw new IllegalArgumentException("Wrong option selection");

        return freightNetwork.getMostEfficiencyCircuit(vertexLocation);
    }

    private VertexLocation getCityVertex(String vertexLocationInfo){
        for (VertexLocation vertexLocation : freightNetwork.getFreightNetwork2().vertices())
            if (vertexLocation.getCountryName()==null&&vertexLocation.getLocationName().equals(vertexLocationInfo))
                    return vertexLocation;

        return null;
    }

    private VertexLocation getPortVertex(String vertexLocationInfo){
        for (VertexLocation vertexLocation : freightNetwork.getFreightNetwork2().vertices())
            if (vertexLocation.getCountryName()!=null&&vertexLocation.getLocationName().equals(vertexLocationInfo))
                    return vertexLocation;

        return null;
    }


}
