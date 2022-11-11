package lapr.project.ui;

import lapr.project.controller.SearchDetailsShipController;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Luís Araújo
 */
public class SearchDetailsShipUI implements Runnable {
    SearchDetailsShipController searchDetailsShipController;
    TrafficManagerUI trafficManagerUI;
    Ship ship;
    boolean flagMMSI;
    boolean flagIMO;
    boolean flagCallSign;

    public SearchDetailsShipUI() {
        searchDetailsShipController = new SearchDetailsShipController();
        trafficManagerUI = new TrafficManagerUI();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        //array with all the 3 options of the codes: mmsi, imo or code sign
        List<String> options = new ArrayList<>();

        //ATRIBUTES
        Integer mmsi;
        String imo;
        String callSign;
        int maxAttempts = 3;
        boolean success;
        int option;
        String answer;


        // FILL THE ARRAY
        options.add("MMSI");
        options.add("IMO");
        options.add("CALL SIGN");

        //process of selecting the type of code the traffic manager wants to use
        do {
            Utils.print("Select the code you want to use to search about the ship.");
            // PRINTING THE ARRAY AND SELECT THE CODE
            option = Utils.showAndSelectIndex(options, "\n\nTraffic Manager Menu");

            maxAttempts--;
            success = false;

            answer = Utils.readLineFromConsole("Confirm type of code? [y/n]");

            switch (answer) {
                case "y":
                case "Y":
                    switch (option) {
                        case 3:
                            trafficManagerUI.run();
                            break;
                        case 0:
                            Utils.print("Write the MMSI code of the ship");
                            mmsi = sc.nextInt();
                            flagMMSI = searchDetailsShipController.searchShipByMMSI(mmsi);
                            if (flagMMSI) {
                                ship = searchDetailsShipController.getShipByMMSI(mmsi);
                                printingData(ship);
                            }
                            break;
                        case 1:
                            Utils.print("Write the IMO code of the ship");
                            imo = sc.next();
                            flagIMO = searchDetailsShipController.searchShipByIMO(imo);
                            if (flagIMO) {
                                ship = searchDetailsShipController.getShipByIMO(imo);
                                printingData(ship);
                            }
                            break;
                        case 2:
                            Utils.print("Write the Call Sign code of the ship");
                            callSign = sc.next();
                            flagCallSign = searchDetailsShipController.searchShipByCallSign(callSign);
                            if (flagCallSign) {
                                ship = searchDetailsShipController.getShipByCallSign(callSign);
                                printingData(ship);
                            }
                            break;
                        default:
                            Utils.print("Couldn´t search about the ship.");
                    }
                    success = true;
                    trafficManagerUI.run();
                    break;
                case "n":
                case "N":
                    Utils.print("Ship not seen.");
                    success = true;
                    break;
                default:
                    Utils.print(String.format("%nInvalid Answer. %d tries remaining%n", maxAttempts));
            }
        }
        while (!success && maxAttempts > 0);
        tryAgain(flagMMSI, flagIMO, flagCallSign);
    }

    public void printingData(Ship ship) {
        Utils.print("Ship Data");
        Utils.print();
        Utils.print("/------MMSI-----|--Vessel Name-|-----IMO------|---Call Sign--|-----Type-----|----Length----|----Width-----|----Draft-----|\\");
        Utils.print();
        Utils.print(ship);
        Utils.print("\\______________|______________|______________|______________|______________|______________|______________|______________/");
        Utils.print("Ship Dynamic Data");
        Utils.print();
        Utils.print("/-----------Date and Time-----------|---Latitude----|---Longitude---|---SOG----|---COG----|----Heading----|---Trailer ID--|----Transceiver Class----|\\");
        Utils.print("|___________________________________|_______________|_______________|__________|__________|_______________|_______________|_________________________|");
        List<ShipDynamicData> data = ship.getDynamicData();
        for (ShipDynamicData position : data)
            Utils.print(String.format("|%35s|%15f|%15f|%10f|%10f|%15d|%15s|%25s|", position.getBaseDateTime().toString(),
                    position.getLat(), position.getLon(), position.getSog(), position.getCog(), position.getHeading(), position.getTrailerId(),
                    position.getTranscieverClass()));

        Utils.print("\\___________________________________|_______________|_______________|__________|__________|_______________|_______________|_________________________/");
        Utils.print();
    }

    public void tryAgain(boolean flagMMSI, boolean flagIMO, boolean flagCallSign) {
        String newAnswer;
        if (!flagMMSI || !flagIMO || !flagCallSign) {
            boolean suc = false;
            do {
                newAnswer = Utils.readLineFromConsole("Try again? [y/n]");

                switch (newAnswer) {
                    case "y":
                    case "Y":
                        //To try again, the program reruns the UI.
                        this.run();
                        break;
                    case "n":
                    case "N":
                        Utils.print("Ship not seen.");
                        suc = true;
                        trafficManagerUI.run();
                        break;
                    default:
                        Utils.print("Invalid answer.");
                        break;

                }
            } while (!suc);
        }
    }
}

