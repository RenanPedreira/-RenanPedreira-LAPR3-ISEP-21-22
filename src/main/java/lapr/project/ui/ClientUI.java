package lapr.project.ui;

import lapr.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClientUI implements Runnable{
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        //204
        options.add(new MenuItem("Check a container's location", new ContainerLocationUI()));
        options.add(new MenuItem("Check a leased container's route", new LeasedContainerRouteUI()));
        options.add(new MenuItem("Check a leased container's location", new LeasedContainerLocationUI()));



        int option;
        do {

            option = Utils.showAndSelectIndex(options, "\n\nClient Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
