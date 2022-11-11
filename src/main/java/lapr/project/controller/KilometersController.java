package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.store.ShipStore;
import lapr.project.utils.Pair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Renan Pedreira
 */
public class KilometersController {

    private Company company;
    private ShipStore store;

    public KilometersController(){
        this(App.getInstance().getCompany());
    }

    public KilometersController(Company company) {
        this.company = company;
        this.store = this.company.getShipStore();
    }


    public Map<Integer, List<Pair<Ship, Pair<Double, Double>>>> getTopN(Integer n, String date1, String date2){
        // Get the dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime d1 = LocalDateTime.parse(date1, formatter);
        LocalDateTime d2 = LocalDateTime.parse(date2, formatter);

        List<Pair<Ship, Pair<Double, Double>>> list = this.store.getTemporalPeriodShips(d1, d2);
        List<Pair<Ship, Pair<Double, Double>>> topN = new ArrayList<>();

        // Get the top n distances
        for(int i=0; i<n; i++)
            topN.add(store.getMax(list));


        return this.store.groupByType(topN);
    }
}
