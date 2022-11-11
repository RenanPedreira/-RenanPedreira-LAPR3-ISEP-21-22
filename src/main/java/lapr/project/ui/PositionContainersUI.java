package lapr.project.ui;

import lapr.project.controller.PositionContainersController;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Luís Araújo
 */
public class PositionContainersUI implements Runnable {
    PositionContainersController positionContainersController;

    public PositionContainersUI() {
        positionContainersController = new PositionContainersController();
    }

    @Override
    public void run() {
        try {
            String seeSketch;
            String mmsi;
            String cargoManif;
            int containers;
            mmsi = Utils.readLineFromConsole("Insert the mmsi of the boat you want to position the containers.");
            cargoManif = Utils.readLineFromConsole("Insert the manifest.");
            List<Pair<Pair<Double, Double>, Pair<Double, Double>>> finalList = positionContainersController.getContainers(mmsi, cargoManif);
            containers = chooseNumberOfContainersToPosition();
            int[][] matrix = positionContainersController.getMatrixWithContainersPositioned(finalList, containers);
            printMatrix(matrix);
            Utils.print("\n\n\n");
            seeSketch = Utils.readLineFromConsole("Do you want to see the sketch of the distribution and loading on the vessel? [y/n]");
            redirectToShowImage(seeSketch);

        } catch (Exception exception) {
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(exception.getMessage(), 97)));
            exception.printStackTrace();
            Utils.print("\\_________________________________________________________________________________________________/");
        }
    }

    public int chooseNumberOfContainersToPosition() {
        int numberOfContainers = 0;
        int maxAttempts2 = 3;
        boolean success2;
        String answer4;

        do {
            try {
                numberOfContainers = Utils.readIntegerFromConsole("Insert the number of containers to position [0-100].");
            } catch (Exception exception) {
                Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
                Utils.print(String.format("|%97s|", StringUtils.center(exception.getMessage(), 97)));
                Utils.print("\\_________________________________________________________________________________________________/");
                run();
            }

            maxAttempts2--;
            success2 = false;

            answer4 = Utils.readLineFromConsole("Confirm number of containers? [y/n]");

            switch (answer4) {
                case "y":
                case "Y":
                    if (numberOfContainers > 100) {
                        Utils.print("Number of containers exceed the limit. Try again.");
                        run();
                    }
                    success2 = true;
                    break;
                case "n":
                case "N":
                    Utils.print("Number of containers not chosen.");
                    success2 = true;
                    break;
                default:
                    Utils.print(String.format("%nInvalid Answer. %d tries remaining%n", maxAttempts2));
            }
        }
        while (!success2 && maxAttempts2 > 0);
        return numberOfContainers;
    }

    public void printMatrix(int[][] matrix) {
        // Loop through all rows
        //for (int[] row : matrix)

        // converting each row as string
        // and then printing in a separate line
        //Utils.print(Arrays.toString(row));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Utils.printNL(matrix[i][j] + " ");
            }
            Utils.print();
        }
    }

    public void redirectToShowImage(String answerToShowImage) {
        int maxAttempts3 = 3;
        boolean success3;

        do {
            maxAttempts3--;
            success3 = false;

            switch (answerToShowImage) {
                case "y":
                case "Y":
                    try {
                        String filename = Utils.readLineFromConsole("Enter the name of the file.");
                        ShowSketchUI showSketchUI = new ShowSketchUI(filename);
                    } catch (Exception e) {
                        Utils.print(e.getMessage());
                    }
                    success3 = true;
                    break;
                case "n":
                case "N":
                    Utils.print("Image not seen.");
                    success3 = true;
                    break;
                default:
                    Utils.print(String.format("%nInvalid Answer. %d tries remaining%n", maxAttempts3));
            }
        }
        while (!success3 && maxAttempts3 > 0);
    }
}
