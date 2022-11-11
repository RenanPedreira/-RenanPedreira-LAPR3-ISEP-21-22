package lapr.project.ui;

import lapr.project.controller.ContainerLocationController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ContainerLocationUI implements Runnable{
    @Override
    public void run() {
        ContainerLocationController ccp = new ContainerLocationController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Container ID: ");
        String id = sc.next();

        try {
            List<String> infoContainer=ccp.getContainerCurrentPosition(id);
            Utils.print();

            Utils.print("TYPE|NAME      |");
            printContainerCurrentPosition(infoContainer);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Prints the results
     *
     * @return true if no errors were found
     */
    public boolean printContainerCurrentPosition(List<String> infoContainer){
        for(String l: infoContainer) {
            Utils.print(l + "|");
        }
        return true;
    }
}
