package lapr.project.ui;

import lapr.project.controller.App;
import lapr.project.controller.ShortestPathController;
import lapr.project.model.Company;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class ShortestPathUI implements Runnable {
    TrafficManagerUI ui;
    ShortestPathController shortestPathController;
    FreightNetwork freightNetwork;
    App app = App.getInstance();
    Company company = app.getCompany();

    public ShortestPathUI() {
        shortestPathController = new ShortestPathController();
        ui = new TrafficManagerUI();
        freightNetwork = company.getFreightNetwork();
    }

    @Override
    public void run() {
        try {
            selectIndexOfThePath();

        } catch (Exception exception) {
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(exception.getMessage(), 97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }
    }

    public int tryAgainYesOrNo(List<String> list) {
        int opt;
        int opt2 = -1;
        int maxAttempts2 = 3;
        boolean success2;
        String answer3;
        String departure;
        String arrival;

        //process of selecting the type of code the traffic manager wants to use
        do {
            Utils.print("Select the type of the path.\n");
            // PRINTING THE ARRAY AND SELECT THE CODE
            opt = Utils.showAndSelectIndex(list, "\n\nTraffic Manager Menu");

            maxAttempts2--;
            success2 = false;

            answer3 = Utils.readLineFromConsole("Confirm type of path? [y/n]");

            switch (answer3) {
                case "y":
                case "Y":
                    //if the traffic manager chose one of the 4 options
                    if (opt < 4) {
                        departure = Utils.readLineFromConsole("Indicate the place of departure.");
                        arrival = Utils.readLineFromConsole("Indicate the place of arrival.");
                        if (!shortestPathController.checkIfDepartureExist(departure, freightNetwork.getFreightNetwork2())) {
                            Utils.print("Couldn´t find the departure local. Try again.");
                            run();
                        }

                        if (!shortestPathController.checkIfArrivalExist(arrival, freightNetwork.getFreightNetwork2())) {
                            Utils.print("Couldn´t find the arrival local. Try again.");
                            run();
                        }

                        if (opt == 3) {
                            Utils.print("Select the type of the path passing through n places.\n");
                            opt2 = Utils.showAndSelectIndex(list.subList(0, 3), "\n\nTraffic Manager Menu");
                            if (opt2 > 2) {
                                ui.run();
                                break;
                            }
                        }

                        Utils.print(String.format("Shortest path by %s between %s and %s:", list.get(opt), departure, arrival));

                        printAllData(opt, departure, arrival, opt2);
                    }

                    //if the traffic manager chose to cancel
                    if (opt == 4) {
                        ui.run();
                        break;
                    }

                    success2 = true;
                    break;
                case "n":
                case "N":
                    Utils.print("Type of path not selected.");
                    success2 = true;
                    break;
                default:
                    Utils.print(String.format("%nInvalid Answer. %d tries remaining%n", maxAttempts2));
            }
        }
        while (!success2 && maxAttempts2 > 0);
        return opt;
    }

    public int selectIndexOfThePath() {
        List<String> listOfOptions = new ArrayList<>();
        listOfOptions.add("Land path");
        listOfOptions.add("Maritime path");
        listOfOptions.add("Land or sea path");
        listOfOptions.add("Passing through n indicated places");

        return tryAgainYesOrNo(listOfOptions);
    }

    public void printAllData(int opt, String departure, String arrival, int opt2) {
        //calls the controller that directs to the certain method through the option chosen
        shortestPathController.directByOptionChosen(opt, departure, arrival, freightNetwork, freightNetwork.getFreightNetwork2());

        if (opt == 0) {
            ifListIsEmptyOrNull();
            printLocationInfo();
        }

        if (opt == 1) {
            ifListIsEmptyOrNull();
            printLocationInfo();
        }

        if (opt == 2) {
            ifListIsEmptyOrNull();
            printLocationInfo();
        }

        if (opt == 3) {
            int nPlaces;
            nPlaces = Utils.readIntegerFromConsole("Insert how many places (number) to pass through.");
            int index = 0;
            String[] places = new String[nPlaces];
            List<VertexLocation> listOfPlaces = new ArrayList<>();
            while (index < nPlaces) {
                places[index] = Utils.readLineFromConsole("Insert the name of the local.");
                index++;
            }

            listOfPlaces = shortestPathController.getVertexLocationByString(freightNetwork, places, nPlaces);

            shortestPathController.nIndicatedPlaces(departure, arrival, freightNetwork, freightNetwork.getFreightNetwork2(), nPlaces, listOfPlaces, opt, opt2);

            ifListIsEmptyOrNull();
            printLocationInfo();
        }
    }

    public void printList() {
        for (VertexLocation vl : shortestPathController.getFinalList()) {
            Utils.print(String.format("|%22s|%23s|", vl.getLocationID(), vl.getLocationName()));
        }
    }

    public void printLocationInfo() {
        DecimalFormat df = new DecimalFormat("#.####");
        Utils.print("/-----Location ID------|-----Location Name-----\\");
        Utils.print("|______________________|_______________________|");
        printList();
        Utils.print("\\______________________|_______________________/");
        Utils.print("\n\n");
        Utils.print("/-----------Length Of The Path (Km)------------\\");
        Utils.print("                   " + df.format(shortestPathController.getDistance()));
        Utils.print("\\______________________________________________/");
    }

    public void ifListIsEmptyOrNull() {
        if (shortestPathController.getFinalList().isEmpty() || shortestPathController.getFinalList() == null) {
            Utils.print("Error in finding the location. Try again.");
            run();
        }
    }
}

