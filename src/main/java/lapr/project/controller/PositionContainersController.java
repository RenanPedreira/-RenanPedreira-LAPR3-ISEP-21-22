package lapr.project.controller;

import lapr.project.data.PositionContainersDB;
import lapr.project.utils.Pair;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class PositionContainersController {
    PositionContainersDB positionContainersDB = new PositionContainersDB();
    List<Pair<Pair<Double, Double>, Pair<Double, Double>>> list;

    public List<Pair<Pair<Double, Double>, Pair<Double, Double>>> getContainers(String mmsi, String cargoManifest) throws SQLException {
        list = positionContainersDB.getContainers(mmsi, cargoManifest);
        return list;
    }

    public int[][] getMatrixWithContainersPositioned(List<Pair<Pair<Double, Double>, Pair<Double, Double>>> list, int numberOfContainers) {
        int[][] finalMatrix;
        double containersLength = list.get(0).getSecond().getFirst();
        double containersWidth = list.get(0).getSecond().getSecond();
        double shipLength = list.get(0).getFirst().getFirst();
        double shipWidth = list.get(0).getFirst().getSecond();
        int counterX = 0;
        int counterZ = 0;
        int finalNumberOfContainers = numberOfContainers + list.size();

        String[] auxArray = new String[2];
        auxArray[0] = String.valueOf(shipLength / containersLength);
        String[] resultSplit=auxArray[0].split("\\.");
        counterX = Integer.parseInt(resultSplit[0]);

        auxArray[0] = String.valueOf(shipWidth / containersWidth);
        resultSplit=auxArray[0].split("\\.");
        counterZ = Integer.parseInt(resultSplit[0]);

        //final matrix with all elements initialized to 0
        finalMatrix = new int[counterX * 2][counterZ * 2];

        //final matrix with the axle z initialized to 2
        for (int i = 0; i < counterX * 2; i++) {
            finalMatrix[i][counterZ] = 2;
        }

        //final matrix with the axle x initialized to 2
        for (int i = 0; i < counterZ * 2; i++) {
            finalMatrix[counterX][i] = 2;
        }

        int counterAux = 0;
        while (!(finalNumberOfContainers % 4 == 0)) {
            finalNumberOfContainers--;
            counterAux++;
        }

        int aux1 = finalNumberOfContainers / 4;
        int aux2 = finalNumberOfContainers / 4;
        int aux3 = finalNumberOfContainers / 4;
        int aux4 = finalNumberOfContainers / 4;
        int axleZ = 0;
        int axleX = (counterZ * 2)-1;
        while (aux1 > 0) {
            for (int i = 0; i < counterX; i++) {
                finalMatrix[i][axleZ] = 1;
                aux1--;
            }
            axleZ++;
        }
        axleZ = 0;

        while (aux2 > 0) {
            for (int i = counterX + 1; i < counterX * 2; i++) {
                finalMatrix[i][axleZ] = 1;
                aux2--;
            }
            axleZ++;
        }

        while (aux3 > 0) {
            for (int i = 0; i < counterX; i++) {
                finalMatrix[i][axleX] = 1;
                aux3--;
            }
            axleX--;
        }
        axleX = (counterZ * 2)-1;

        while (aux4 > 0) {
            for (int i = counterX + 1; i < counterX * 2; i++) {
                finalMatrix[i][axleX] = 1;
                aux4--;
            }
            axleX--;
        }

        if (counterAux == 1) {
            finalMatrix[counterX - 1][counterZ - 1] = 1;
        }

        if (counterAux == 2) {
            finalMatrix[counterX - 1][counterZ - 1] = 1;
            finalMatrix[counterX - 1][counterZ + 1] = 1;
        }

        if (counterAux == 3) {
            finalMatrix[counterX - 1][counterZ - 1] = 1;
            finalMatrix[counterX - 1][counterZ + 1] = 1;
            finalMatrix[counterX + 1][counterZ - 1] = 1;
        }

        return finalMatrix;
    }
}

