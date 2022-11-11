package lapr.project.ui;

import lapr.project.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class MainMenuUI {

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Do Login", new AuthUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
