package lapr.project.ui;

import lapr.project.controller.App;
import lapr.project.controller.ColourMapController;
import lapr.project.data.CountryStoreDB;
import lapr.project.graph.map.AdjacencyMapGraph;
import lapr.project.model.Colors;
import lapr.project.model.Company;
import lapr.project.network.FreightNetwork;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Luís Araújo
 */
public class ColourMapUI implements Runnable {
    ColourMapController colourMapController;
    CountryStoreDB countryStoreDB;
    FreightNetwork freightNetwork;
    App app = App.getInstance();
    Company company = app.getCompany();

    public ColourMapUI() {
        colourMapController = new ColourMapController();
        countryStoreDB = new CountryStoreDB();
        freightNetwork = company.getFreightNetwork();
    }

    @Override
    public void run() {
        try {
            int[] finalColoredArray = new int[colourMapController.getFreightNetwork().getFreightNetwork3().numVertices()];
            Utils.print("\n\nTraffic Manager Menu\n\n");

            Utils.print("Map colored:\n");

            finalColoredArray = colourMapController.colorMap(colourMapController.getFreightNetwork().getFreightNetwork3());
            printFinalArray(finalColoredArray, colourMapController.getFreightNetwork().getFreightNetwork3());

        } catch (Exception e) {
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(e.getMessage(), 97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }
    }

    public void printFinalArray(int[] array, AdjacencyMapGraph<VertexLocation, Double> map2) {
        Utils.print("/-----Country-----|-----Color-----\\");
        Utils.print("|_________________|_______________|");
        // print the result
        for (int u = 0; u < colourMapController.getFreightNetwork().getFreightNetwork3().numVertices(); u++) {
            Utils.print(String.format("|%17s|%15s|", map2.vertices().get(u).getLocationID(), Colors.getColorByNumber(array[u])));
        }
        Utils.print("\\_________________|_______________/");
    }
}
