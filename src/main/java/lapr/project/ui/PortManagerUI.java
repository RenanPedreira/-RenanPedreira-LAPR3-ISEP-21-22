package lapr.project.ui;

import lapr.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class PortManagerUI implements Runnable{
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        //US201
        options.add(new MenuItem("Import ports from text file and create a 2D-tree", new ImportPortsUI()));
        //US304
        options.add(new MenuItem("Check occupancy rate and number of containers leaving in teh next 30 days", new WarehouseAnalysisUI()));
        //US310
        options.add(new MenuItem("Check occupancy map of the existing resources in the port during a given month", new OccupancyMapUI()));
        //US407
        options.add(new MenuItem("Check occupancy map of warehouses and ports next week", new ResourceMapNextWeekUI()));

        int option;
        do {

            option = Utils.showAndSelectIndex(options, "\n\nPort Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
