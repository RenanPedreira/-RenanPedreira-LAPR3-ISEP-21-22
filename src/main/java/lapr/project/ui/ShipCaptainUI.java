package lapr.project.ui;

import lapr.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ShipCaptainUI implements Runnable{
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        //205
        options.add(new MenuItem("Check which containers will be unloaded in the next port", new UnloadingCargoUI()));
        //206
        options.add(new MenuItem("Check which containers will be loaded in the next port", new LoadingCargoUI()));
        //207
        options.add(new MenuItem("Check how many cargo manifests have been transported during a given year and the average number of containers per manifest ", new CargoManifestCountUI()));
        //208
        options.add(new MenuItem("Check the occupancy rate of a cargo manifest", new OccupancyRateCargoUI()));
        //209
        options.add(new MenuItem("Check the occupancy rate of a given moment", new OccupancyGivenMomentUI()));
        //304
        options.add(new MenuItem("Check the list of container changes in a manifest", new ContainerOperationUI()));
        options.add(new MenuItem("Change the information of container in a manifest", new ContainerAlterationUI()));
        //US417
        options.add(new MenuItem("Get 3 best vessel types for a manifest", new VesselTypeManifestUI()));
        //US418
        options.add(new MenuItem("Calcule the vessel Center of mass", new DetermineTheShipsCenterMassUI()));
        //US419
        options.add(new MenuItem("Position containers on the vessel", new PositionContainersUI()));
        //US420
        options.add(new MenuItem("Know how much a specific vessel sink",new VesselSinkUI()));


        int option;
        do {

            option = Utils.showAndSelectIndex(options, "\n\nShip Captain Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}

