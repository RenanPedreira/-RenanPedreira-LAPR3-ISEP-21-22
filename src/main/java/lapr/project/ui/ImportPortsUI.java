package lapr.project.ui;

import lapr.project.controller.ImportPortController;
import lapr.project.model.Port;
import lapr.project.tree.KDTree;
import lapr.project.tree.Node;
import lapr.project.utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Luís Araújo
 */
public class ImportPortsUI implements Runnable {
    private List<Node<Port>> listOfNodes;

    public ImportPortsUI() {
        listOfNodes = new ArrayList<>();
    }

    @Override
    public void run() {

        ImportPortController ipc = new ImportPortController();


        Scanner sc3 = new Scanner(System.in);
        Utils.print("File name:");
        //Gets the file with the test
        String file = sc3.next();

        try {
            //Create ports form file in the database
            ipc.importPortFromFile2(file);
            //Get the ports from the database
            ipc.portsFromDB();
            Utils.print("Ports were successfully added.");
        } catch (IOException e) {
            Utils.print("File not found");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
        listOfNodes=ipc.getList();
        KDTree kdTree1 = null;
        try {
            if(ipc.saveKDTree(listOfNodes)){
                kdTree1=ipc.getKdTree();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Utils.print();
        Utils.print("Tree´s height:");
        Utils.print(kdTree1.height2());
        Utils.print();
        Utils.print("Number of nodes:");
        Utils.print(listOfNodes.size());
        Utils.print();
        Utils.print("Balance factor for every node:");
        Utils.print("------------------------------");
        for (Node<Port> node : listOfNodes) {
            Utils.print("|BF " + node.getInfo().getIdentification() + "- " + kdTree1.balanceFactor2(node) + "|");
        }
        Utils.print("------------------------------");
    }
}
