package lapr.project.ui;

import lapr.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class FleetManagerUI implements Runnable {

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        //US404
        options.add(new MenuItem("Check number of days a ship has been idle this year", new ShipNumberDaysIdleUI()));
        //US405
        options.add(new MenuItem("Check occupancy of a ship for a given period", new AverageOccupancyGivePeriodUI()));
        //US406
        options.add(new MenuItem("Check ship voyages had an occupancy rate below a certain threshold", new ShipVoyagesAboveThresholdUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nTraffic Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
