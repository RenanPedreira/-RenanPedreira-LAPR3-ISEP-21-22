package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.PortStoreDB;
import lapr.project.model.Port;
import lapr.project.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportPortDBController {

    public List<Port> getPortsFromDBForGraph() {

        try {
            DatabaseConnection databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
            PortStoreDB psdb = new PortStoreDB();
            return psdb.getListPorts(databaseConnection);
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return new ArrayList<>();
        }
    }
}
