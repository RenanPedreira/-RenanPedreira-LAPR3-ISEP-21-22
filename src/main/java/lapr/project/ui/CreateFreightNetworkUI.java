package lapr.project.ui;

import lapr.project.controller.CreateFreightNetworkController;
import lapr.project.network.FreightNetwork;
import lapr.project.utils.Utils;

import java.util.Scanner;

public class CreateFreightNetworkUI implements Runnable{
    public void run() {
        CreateFreightNetworkController fnc = new CreateFreightNetworkController();

        Scanner sc = new Scanner(System.in);
        Utils.print("Number of conncetions with closest foreign ports:");
        int n = sc.nextInt();

        FreightNetwork freightNetwork = fnc.createFreightNetwork(n);
        Utils.print("Vertices: " + freightNetwork.getFreightNetwork2().numVertices());
        Utils.print("Edges: " + freightNetwork.getFreightNetwork2().numEdges());
    }
}
