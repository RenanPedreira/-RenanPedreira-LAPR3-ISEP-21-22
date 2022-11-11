package lapr.project.ui;

import lapr.project.controller.KilometersController;
import lapr.project.controller.MakeSummaryController;
import lapr.project.controller.SeeOrganizedMessagesController;
import lapr.project.model.Ship;
import lapr.project.model.ShipDynamicData;
import lapr.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Luís Araújo
 */
public class MakeSummaryUI implements Runnable {
    MakeSummaryController makeSummaryController2;
    KilometersController kilometersController2;
    SeeOrganizedMessagesController seeOrganizedMessagesController2;
    TrafficManagerUI trafficManagerUI2;
    Ship ship2;
    boolean flagMMSI2;
    boolean flagIMO2;
    boolean flagCallSign2;

    public MakeSummaryUI() {
        makeSummaryController2 = new MakeSummaryController();
        kilometersController2 = new KilometersController();
        seeOrganizedMessagesController2 = new SeeOrganizedMessagesController();
        trafficManagerUI2 = new TrafficManagerUI();
    }

    @Override
    public void run() {
        Scanner sc2 = new Scanner(System.in);
        //array with all the 3 options of the codes: mmsi, imo or code sign
        List<String> options2 = new ArrayList<>();

        //ATRIBUTES
        Integer mmsi2;
        String imo2;
        String callSign2;
        int maxAttempts2 = 3;
        boolean success2;
        int option2;
        String answer2;


        // FILL THE ARRAY
        options2.add("MMSI");
        options2.add("IMO");
        options2.add("CALL SIGN");

        //process of selecting the type of code the traffic manager wants to use
        do {
            Utils.print("Select the code you want to use to get the ship.");
            // PRINTING THE ARRAY AND SELECT THE CODE
            option2 = Utils.showAndSelectIndex(options2, "\n\nTraffic Manager Menu");

            maxAttempts2--;
            success2 = false;

            answer2 = Utils.readLineFromConsole("Confirm type of code? [y/n]");

            switch (answer2) {
                case "y":
                case "Y":
                    switch (option2) {
                        case 3:
                            trafficManagerUI2.run();
                            break;
                        case 0:
                            Utils.print("Write the MMSI code of the ship");
                            mmsi2 = sc2.nextInt();
                            flagMMSI2 = makeSummaryController2.searchShipByMMSI(mmsi2);
                            if (flagMMSI2) {
                                ship2 = makeSummaryController2.getShipByMMSI(mmsi2);
                                makeSummary(ship2);
                            }
                            break;
                        case 1:
                            Utils.print("Write the IMO code of the ship");
                            imo2 = sc2.next();
                            flagIMO2 = makeSummaryController2.searchShipByIMO(imo2);
                            if (flagIMO2) {
                                ship2 = makeSummaryController2.getShipByIMO(imo2);
                                makeSummary(ship2);
                            }
                            break;
                        case 2:
                            Utils.print("Write the Call Sign code of the ship");
                            callSign2 = sc2.next();
                            flagCallSign2 = makeSummaryController2.searchShipByCallSign(callSign2);
                            if (flagCallSign2) {
                                ship2 = makeSummaryController2.getShipByCallSign(callSign2);
                                makeSummary(ship2);
                            }
                            break;
                        default:
                            Utils.print("Couldn´t get the ship.");
                    }
                    success2 = true;
                    trafficManagerUI2.run();
                    break;
                case "n":
                case "N":
                    Utils.print("Ship not selected.");
                    success2 = true;
                    break;
                default:
                    Utils.print(String.format("%nInvalid Answer. %d tries remaining%n", maxAttempts2));
            }
        }
        while (!success2 && maxAttempts2 > 0);
        tryAgain(flagMMSI2, flagIMO2, flagCallSign2);
    }

    public void makeSummary(Ship ship) {
        //organized array with the dynamic data of the indicated ship
        List<ShipDynamicData> dynamicData;
        dynamicData = ship.getDynamicData();

        //variables for: travelled distance, distance between the first and last move, departure latitude, departure longitude,
        //arrival latitude and arrival longitude
        double travelledDistance = 0;
        double distanceFirstLast;
        double departureLat;
        double departureLon;
        double arrivalLat;
        double arrivalLon;

        //distance between the first and last move calculation
        distanceFirstLast = makeSummaryController2.calculateDistance(dynamicData.get(0).getLat(),
                dynamicData.get(0).getLon(), dynamicData.get(dynamicData.size() - 1).getLat(),
                dynamicData.get(dynamicData.size() - 1).getLon());

        //calculation of the departure latitude
        departureLat = dynamicData.get(0).getLat();

        //calculation of the departure longitude
        departureLon = dynamicData.get(0).getLon();

        //calculation of the arrival latitude
        arrivalLat = dynamicData.get(dynamicData.size() - 1).getLat();

        //calculation of the arrival longitude
        arrivalLon = dynamicData.get(dynamicData.size() - 1).getLon();

        //variables for the max sog, mean sog, max cog and mean cog
        double maxSog = 0;
        double meanSog = 0;
        double maxCog = 0;
        double meanCog = 0;

        //date 1 is the date of the arrival place
        LocalDateTime date1 = dynamicData.get(dynamicData.size() - 1).getBaseDateTime();

        //date 2 is the date of the departure place
        LocalDateTime date2 = dynamicData.get(0).getBaseDateTime();

        //date 3 is the conversion to Date of the date 1
        Date date3 = new Date(date1.getYear(), date1.getMonth().getValue(), date1.getDayOfMonth(), date1.getHour(), date1.getMinute(),
                date1.getSecond());

        //date 4 is the conversion to Date of the date 2
        Date date4 = new Date(date2.getYear(), date2.getMonth().getValue(), date2.getDayOfMonth(), date2.getHour(), date2.getMinute(),
                date2.getSecond());

        //duration of the trip in a long variable
        long duration = date3.getTime() - date4.getTime();

        //various types of differences of the 2 dates
        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);

        //for cycle to pass by all the elements of the array of the dynamic data
        for (int i = 0; i < dynamicData.size() - 1; i++) {
            //travelledDistance calculation
            travelledDistance += makeSummaryController2.calculateDistance(dynamicData.get(i).getLat(),
                    dynamicData.get(i).getLon(), dynamicData.get(i + 1).getLat(),
                    dynamicData.get(i + 1).getLon());

            //sum of all the sog. The mean sog is calculated while printing by dividing this sum by all the elements of the array
            meanSog += dynamicData.get(i).getSog();

            //max sog calculation
            if (maxSog < dynamicData.get(i).getSog()) {
                maxSog = dynamicData.get(i).getSog();
            }

            //sum of all the cog. The mean cog is calculated while printing by dividing this sum by all the elements of the array
            meanCog += dynamicData.get(i).getCog();

            //max cog calculation
            if (maxCog < dynamicData.get(i).getCog()) {
                maxCog = dynamicData.get(i).getCog();
            }
        }

        Utils.print("Summary");
        Utils.print();
        Utils.print("-----------------------------------------------------------------");
        Utils.print(String.format("| Ship %d is a %s type.", ship.getMmsi(), ship.getType()));
        Utils.print(String.format("| The ship started the journey in %s and ended the journey in %s.", dynamicData.get(0).getBaseDateTime().toString(),
                dynamicData.get(dynamicData.size() - 1).getBaseDateTime().toString()));
        Utils.print("| Journey Details:");
        Utils.print(String.format("|       -> Total Movement Time: %d hours.", diffInHours));
        Utils.print(String.format("|       -> Total Number of Movements: %d movements.", dynamicData.size()));
        Utils.print(String.format("|       -> Max SOG: %f knots.", maxSog));
        Utils.print(String.format("|       -> Mean SOG: %f knots.", meanSog / (dynamicData.size() - 1)));
        Utils.print(String.format("|       -> Max COG: %f degrees.", maxCog));
        Utils.print(String.format("|       -> Mean COG: %f degrees.", meanCog / (dynamicData.size() - 1)));
        Utils.print(String.format("|       -> Departure Latitude: %f.", departureLat));
        Utils.print(String.format("|       -> Departure Longitude: %f.", departureLon));
        Utils.print(String.format("|       -> Arrival Latitude: %f.", arrivalLat));
        Utils.print(String.format("|       -> Arrival Longitude: %f.", arrivalLon));
        Utils.print(String.format("|       -> Travelled Distance: %f km.", travelledDistance));
        Utils.print(String.format("|       -> Delta Distance: %f km.", distanceFirstLast));
        Utils.print("-----------------------------------------------------------------");
    }

    public void tryAgain(boolean flagMMSI2, boolean flagIMO2, boolean flagCallSign2) {
        String newAnswer2;
        if (!flagMMSI2 || !flagIMO2 || !flagCallSign2) {
            boolean suc = false;
            do {
                newAnswer2 = Utils.readLineFromConsole("Try again? [y/n]");

                switch (newAnswer2) {
                    case "y":
                    case "Y":
                        //To try again, the program reruns the UI.
                        this.run();
                        break;
                    case "n":
                    case "N":
                        Utils.print("Ship not selected.");
                        suc = true;
                        trafficManagerUI2.run();
                        break;
                    default:
                        Utils.print("Invalid answer.");
                        break;

                }
            } while (!suc);
        }
    }
}
