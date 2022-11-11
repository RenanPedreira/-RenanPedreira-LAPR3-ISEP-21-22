package lapr.project.ui;

import lapr.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class TrafficManagerUI implements Runnable {

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        //US101
        options.add(new MenuItem("Import ships from file", new ImportShipUI()));
        //US102
        options.add(new MenuItem("Search the details of a ship using any of its codes: MMSI, IMO or Call Sign", new SearchDetailsShipUI()));
        //US103
        options.add(new MenuItem("See organized positional messages", new SeeOrganizedMessagesUI()));
        //US104
        options.add(new MenuItem("Make a summary of a ship's movements", new MakeSummaryUI()));
        //US105
        options.add(new MenuItem("Check MMSI, the total number of movements, Travelled Distance and Delta Distance " +
                "of all the ships", new SeeMMSIAndMoreInfoUI()));
        //US106
        options.add(new MenuItem("Check top-N ships", new KilometersUI()));
        //US107
        options.add(new MenuItem("Return pairs of ships with routes with close departure/arrival coordinates (no more than 5 Kms away) and with different Travelled Distance", new ReturnPairsShipsUI()));
        //US202
        options.add(new MenuItem("Find the closest port of a ship", new FindClosestPortUI()));
        //US210
        options.add(new MenuItem("Check which ships will be available next monday", new ShipsNextMondayUI()));

        //US301
        options.add(new MenuItem("Import countries from csv file", new ImportCountryUI()));
        options.add(new MenuItem("Import borders from csv file", new ImportBorderUI()));
        options.add(new MenuItem("Import sea distances from csv file", new ImportSeadistUI()));
        options.add(new MenuItem("Create a freight network", new CreateFreightNetworkUI()));
        //US302
        options.add(new MenuItem("Colour the map using as few colours as possible", new ColourMapUI()));
        //US303
        options.add(new MenuItem("Know which places (cities or ports) are closest to all other places (closeness places).", new ContinentNClosenessPlaceUI()));

        //US401
        options.add(new MenuItem("Know which know which ports are more critical.", new GetCriticalPortsUI()));
        //US402
        options.add(new MenuItem("Find the shortest path between two locals (city and/or port)", new ShortestPathUI()));
        //US403
        options.add(new MenuItem("Find the most efficiency circuit", new MostEfficiencyCircuitUI()));
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

