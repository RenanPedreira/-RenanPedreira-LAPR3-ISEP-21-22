package lapr.project.ui;

import lapr.project.controller.ContainerAlterationController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Renan Pedreira
 */
public class ContainerAlterationUI implements Runnable{
    String cargoManifest="Cargo Manifest ID: ";
    String container="Container ID: ";
    @Override
    public void run() {
        ContainerAlterationController cadb = new ContainerAlterationController();

        Scanner sc = new Scanner(System.in);

        Utils.print("1) Insert a container into a cargo manifest\n" +
                    "2) Update a container into a cargo manifest\n" +
                    "3) Delete container into a cargo manifest\n");

        int option = sc.nextInt();

        switch (option){
            case 1:
                Utils.print(cargoManifest);
                String cmID = sc.next();
                Utils.print(container);
                String cont = sc.next();
                Utils.print("Position X: ");
                Integer x = sc.nextInt();
                Utils.print("Position Y: ");
                Integer y = sc.nextInt();
                Utils.print("Position Z: ");
                Integer z = sc.nextInt();
                Utils.print("Ship: ");
                Integer ship = sc.nextInt();
                try {
                    cadb.alterContainerInformationCreate(cmID, cont, ship,x,y,z);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case 2:
                Utils.print(cargoManifest);
                cmID = sc.next();
                Utils.print(container);
                cont = sc.next();

                Utils.print("Position X: ");
                x = sc.nextInt();
                Utils.print("Position Y: ");
                y = sc.nextInt();
                Utils.print("Position Z: ");
                z = sc.nextInt();
                try {
                    cadb.alterContainerInformationUpdate(cmID, cont, x, y, z);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case 3:
                Utils.print(cargoManifest);
                cmID = sc.next();
                Utils.print(container);
                cont = sc.next();
                try {
                    cadb.alterContainerInformationDelete(cmID, cont);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            default:Utils.print("Invalid option");
                break;
        }
    }
}
