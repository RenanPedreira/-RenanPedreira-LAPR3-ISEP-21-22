package lapr.project.ui;

import lapr.project.controller.KilometersController;
import lapr.project.model.Ship;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;

/**
 *
 * @author Renan Pedreira
 */
public class KilometersUI implements Runnable{
    public void run() {
        KilometersController kfc = new KilometersController();
        Scanner sc = new Scanner(in);

        Utils.print("How many ships?");
        Integer topN = sc.nextInt();

        Utils.print("Starting date: ");
        String d1 = sc.next();
        Utils.print("Hour: ");
        String h1 = sc.next();

        Utils.print("Ending date: ");
        String d2 = sc.next();
        Utils.print("Hour: ");
        String h2 = sc.next();

        showInformation(kfc.getTopN(topN, d1+" "+h1, d2+" "+h2));

    }


    public void showInformation(Map<Integer, List<Pair<Ship, Pair<Double, Double>>>> map){
        for(Integer i : map.keySet()){
            for(Pair<Ship, Pair<Double, Double>> elemente : map.get(i)){
                Utils.print(String.format("Vessel Type: %s\n" +
                        "Ship: %s\n" +
                        "Distance: %f\n" +
                        "Mean SOG: %f\n\n", elemente.getFirst().getType(), elemente.getFirst().getMmsi(), elemente.getSecond().getFirst(), elemente.getSecond().getSecond()));
            }
        }
    }
}
