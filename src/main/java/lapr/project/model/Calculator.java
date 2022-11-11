package lapr.project.model;

import lapr.project.utils.*;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class Calculator {

    /**
     * The time that the constructor is used.
     */
    private static int used=0;

    private  static final double GRAVITY=9.80665;

    /**
     * The constructor of the instance.
     */
    public Calculator(){
        used=Math.incrementExact(used);
    }

    /**
     * Method to calculate the distance according the latitude and the longitude.
     * @param lat1, the first latitude.
     * @param lon1, the first longitude.
     * @param lat2, the second latitude.
     * @param lon2, the second longitude.
     * @return the result of the calculation.
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        final double R = 6371;

        double latRad1 = Math.toRadians(lat1);
        double latRad2 = Math.toRadians(lat2);

        double varLat = Math.toRadians(lat2 - lat1);
        double varLon = Math.toRadians(lon2 - lon1);

        double aux = Math.sin(varLat / 2) * Math.sin(varLat / 2) + Math.cos(latRad1) * Math.cos(latRad2) * Math.sin(varLon / 2) * Math.sin(varLon / 2);

        double aux1 = 2 * Math.atan2(Math.sqrt(aux), Math.sqrt(1 - aux));

        return R * aux1;
    }

    /**
     *Return a pair of double that represent the value of the X and Z respectively
     *
     * @param data  list o pair that contains the area
     * @return a pair with the center of mass in
     */
    public static Pair<Double,Double> calculateCenterMass(List<Pair<Double,Pair<Double,Double>>> data){
        double xCM=0.0;
        double zCM=0.0;

        double total=0.0;

        for (Pair<Double,Pair<Double,Double>> pair : data)
            total=Double.sum(total,pair.getFirst());

        if(total!=0.0)
            for (Pair<Double,Pair<Double,Double>> pair : data){
                xCM=Double.sum(xCM,(pair.getFirst()*pair.getSecond().getFirst())/total);
                zCM=Double.sum(zCM,(pair.getFirst()*pair.getSecond().getSecond())/total);
            }

        return new Pair<>(xCM,zCM);
    }

    public static Double calculatePressure(double area, double mass){
        double force=mass*Calculator.GRAVITY;
        return force/area;
    }

    public static Double calculateAreaGeometric(double length, double width,int geometric){
        //for triangle
        if (geometric==1){
            return ((length*width)/2);
        }
        //for circle
        if (geometric==2){
            return (((Math.PI)*Math.pow((width/2),2))/2);
        }
        //for hexagon
        if (geometric==3){
            return 3*((width/2)*(length))/2;
        }
        //for quadrilateral
        if (geometric==4){
            return length*width;
        }

        throw new NotImplementedException("The geometric number introduced is not implemented");
    }

    /**
     * In ship context
     *
     * @param length
     * @param width
     * @param geometric
     * @param positionX
     * @param positionZ
     * @return
     */
    public static Pair<Double,Double> calculateGeometricCM(double length, double width,int geometric, double positionX, double positionZ){
        //for a triangle
        if (geometric==1){
            return new Pair<>(positionX+(length/3),(width/2));
        }
        //for semi-circle
        if (geometric==2){
            return new Pair<>(positionX+((4*(width/2))/(3*Math.PI)),(width/2));
        }
        //for hexagon
        if (geometric==3){
            return new Pair<>(positionX+(width*(length/2)*((length/2)/2)+((length/2)+length/3)*((length/2)*width)/2)/(calculateAreaGeometric(length,width,3)),(width/2));
        }
        //for quadrilaterals
        if (geometric==4){
            return new Pair<>(positionX+(length/2),positionZ+(width/2));
        }

        throw new NotImplementedException("The geometric number introduced is not implemented");
    }

    public static double calculateVolume(double length, double width,double height,int geometric){
        return Calculator.calculateAreaGeometric(length,width,geometric)*height;
    }


}
