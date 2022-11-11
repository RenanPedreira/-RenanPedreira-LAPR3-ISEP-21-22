package lapr.project.ui;

import lapr.project.controller.GetCriticalPortsController;
import lapr.project.network.VertexLocation;
import lapr.project.utils.Pair;
import lapr.project.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class GetCriticalPortsUI implements Runnable{
    public void run() {
        GetCriticalPortsController fnc = new GetCriticalPortsController();

        Scanner sc = new Scanner(System.in);
        Utils.print("Number of most critical ports:");
        int n = sc.nextInt();
        printResult(fnc.getCriticalPorts(n));

    }

    public boolean printResult(List<Pair<VertexLocation, Integer>> list){
        for (Pair<VertexLocation, Integer> l : list)
            Utils.print("\nPort: "+ l.getFirst()+" shortest paths: "+ l.getSecond());
        return true;
    }
}
