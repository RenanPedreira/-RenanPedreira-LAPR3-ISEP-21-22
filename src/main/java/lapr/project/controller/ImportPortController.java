package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.PortStoreDB;
import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.store.PortStore;
import lapr.project.tree.KDTree;
import lapr.project.tree.Node;
import lapr.project.utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


/**
 * @author Renan Pedreira
 */
public class ImportPortController {
    /**
     * The company that records the ports
     */
    private Company company;

    /**
     * The port being registered
     */
    private Port p;

    /**
     * The PortStore used to create and save ports
     */
    private PortStore store;
    private KDTree kdTree = null;

    /**
     * Creates a controller getting the Company information from App
     */
    public ImportPortController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Creates a controller from the Company, having it as a parameter
     *
     * @param company The company responsible for registering ports
     */
    public ImportPortController(Company company) {
        this.company = company;
        this.store = this.company.getPortStore();
        this.p = null;
    }

    /**
     * Gets the list of nodes with ports from teh kdtree
     *
     * @return list of nodes
     */
    public List<Node<Port>> getList() {
        return store.getList();
    }

    /**
     * Gets the controller's associated KDTree
     *
     * @return 2D tree
     */
    public KDTree getKdTree() {
        return kdTree;
    }

    /**
     * Saves a KD tree from a list of nodes
     *
     * @param listOfNodes The nodes to be inserted into the kd tree
     * @return True if no issues were found while saving the tree
     * @throws IOException if IO exception was found
     * @throws ClassNotFoundException if company was not found
     */
    public boolean saveKDTree(List<Node<Port>> listOfNodes) throws IOException, ClassNotFoundException {
        kdTree = store.saveKDTree(listOfNodes);
        return !kdTree.isEmpty();
    }

    /**
     * Imports all ports from a csv file ito a 2d tree
     *
     * @param fileName File containing the ports
     * @return true if file was found
     * @throws FileNotFoundException
     */
    public boolean importPortFromFile(String fileName) throws FileNotFoundException {

        String[] info;

        //Gets the file containing ports
        try (FileInputStream fileIn = new FileInputStream(fileName)) {
            Scanner sc = new Scanner(fileIn);

            //First line has no port information
            sc.nextLine();

            //Port lines
            while (sc.hasNextLine()) {
                info = sc.nextLine().split(",");
                this.p = portFromLine(info);
                this.store.savePort(p);

            }
            sc.close();

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Create ports in teh database from the csv file containing port information
     *
     * @param fileName File containing ports
     * @return True if no issues were found while importing the ports
     * @throws FileNotFoundException if file was not found
     * @throws SQLException if tehre was an error in the database
     */
    public boolean importPortFromFile2(String fileName) throws FileNotFoundException, SQLException {
        try {
            DatabaseConnection databaseConnection = null;
            databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            connection.setAutoCommit(true);
            Utils.print("Connected to the database!");

            String[] info;

            PortStoreDB psdb = new PortStoreDB();
            //Gets the file containing ports
            try (FileInputStream fileIn = new FileInputStream(fileName)) {
                Scanner sc = new Scanner(fileIn);

                //First line has no port information
                sc.nextLine();

                //Port lines
                while (sc.hasNextLine()) {
                    info = sc.nextLine().split(",");
                    this.p = portFromLine(info);
                    psdb.save(databaseConnection, this.p);
                }
                sc.close();

            } catch (IOException e) {
                return false;
            }
            return true;
        } catch (IOException exception) {
            Utils.print("Connection failed");
            return false;
        }
    }

    /**
     * Get the ports from the database
     *
     * @return true if there were nbo issues importing the ports
     */
    public boolean portsFromDB() {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            Utils.print("Connection failed");
        }

        PortStoreDB psdb = new PortStoreDB();

        List<Port> portList = psdb.getListPorts(databaseConnection);

        for (Port po : portList)
            this.store.savePort(po);
        return true;
    }

    /*public List<Port> getPortsFromDBForGraph() {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            System.out.println("Connection failed");
        }

        PortStoreDB psdb = new PortStoreDB();

        return psdb.getListPorts(databaseConnection);
    }*/

    /**
     * Creates a port using the file's line information
     *
     * @param info The information read from a csv line
     * @return The port created from that information
     */
    public Port portFromLine(String[] info) {
        return this.store.createPort(Integer.parseInt(info[2]),
                info[3],
                info[1],
                Double.parseDouble(info[4]),
                Double.parseDouble(info[5]));
    }
}