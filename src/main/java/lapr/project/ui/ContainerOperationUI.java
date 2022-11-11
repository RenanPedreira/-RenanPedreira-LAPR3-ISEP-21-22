package lapr.project.ui;

import lapr.project.controller.ContainerOperationController;
import lapr.project.utils.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ContainerOperationUI implements Runnable{

    @Override
    public void run() {
        ContainerOperationController codb = new ContainerOperationController();
        Scanner sc = new Scanner(System.in);

        Utils.print("Cargo Manifest ID: ");
        String cmID = sc.next();
        Utils.print("Container ID: ");
        String cont = sc.next();

        try {
            List<List<String>> list = codb.getContainerAuditTrail(cmID, cont);
            Utils.print();

            Utils.print("ID         |DATE                 |TYPE  |CONT_ID  |CM_ID |");
            printContainerOperationList(list);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean printContainerOperationList(List<List<String>> list){
        for(List<String> l: list) {
            for(String s: l){
                Utils.print(s + "|");
            }
            Utils.print();
        }
        return true;
    }
}
