package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.store.ShipStore;
import lapr.project.utils.CSVReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Renan Pedreira
 */
public class ImportShipController {
    /**
     * The company that tracks the ships
     */
    private Company company;

    int code=1;

    /**
     *  The ship being registered
     */
    private Ship s;

    /**
     * The String[] with information for this ship
     */
    private String[] shipInfo;

    /**
     *  The ShipStore used to create and save ships
     */
    private ShipStore store;

    /**
     * Creates a controller getting the Company information from App
     */
    public ImportShipController() throws IOException, ClassNotFoundException {
        this(App.getInstance().getCompany());
    }

    /**
     * Creates a controller from the Company, having it as a parameter
     *
     * @param company The company responsible for registering ships
     */
    public ImportShipController(Company company) {
        this.company = company;
        this.store = this.company.getShipStore();
        this.s = null;
    }

    /**
     * Gets information of a ship from a CSV file
     *
     * @param f The file
     * @return String[] with information
     */
    public boolean getShipLine(String f, int i){
        CSVReader csv = new CSVReader();
        String[] line = csv.createShipFromFile(f, i);

        if(line==null)
            return false;
        this.shipInfo = line;
        return true;
    }

    /**
     * Creates a ship if it's a new one
     *
     * @param info The line containing the ship's data
     */
    public Ship createNewShip(String[] info){
        this.s = this.store.createShip(Integer.parseInt(info[0]), info[8], info[9]);
        this.s.setVesselName(info[7]);
        this.s.setType(info[10]);
        this.s.setLength(Integer.parseInt(info[11]));
        this.s.setWidth(Integer.parseInt(info[12]));
        this.s.setDraft(Double.parseDouble(info[13]));

        this.s.setGeometricCode(code);
        if (code!=3)
            code++;
        else
            code=1;
        return this.s;
    }

    /**
     * Adds dynamic data to a ship
     *
     * @param info The line containing the ship's data
     */
    public void addDynamicData(String[] info){
        this.store.addDynamicData(this.s,
                info[1],
                Double.parseDouble(info[2]),
                Double.parseDouble(info[3]),
                Double.parseDouble(info[4]),
                Double.parseDouble(info[5]),
                Integer.parseInt(info[6]),
                info[14],
                info[15]);
    }

    /**
     * Creates a ship if it's a new one and adds dynamic data to the ship
     */
    public void importData(){
        Ship fileShip = new Ship(Integer.parseInt(shipInfo[0]), shipInfo[8], shipInfo[9]);

        if(this.store.validateShip(fileShip))
            createNewShip(this.shipInfo);
        else
            this.s = this.store.getShipByMMSI(Integer.parseInt(shipInfo[0]));

        addDynamicData(this.shipInfo);
    }


    public boolean importData2(String fileName){

        //Gets the file containing ports
        try (FileInputStream fileIn = new FileInputStream(fileName)) {
            Scanner sc = new Scanner(fileIn);

            //First line has no port information
            sc.nextLine();

            //Port lines
            while (sc.hasNextLine()){
                shipInfo = sc.nextLine().split(",");

                Ship fileShip = new Ship(Integer.parseInt(shipInfo[0]), shipInfo[8], shipInfo[9]);
                if(this.store.validateShip(fileShip))
                    createNewShip(this.shipInfo);
                else
                    this.s = this.store.getShipByMMSI(Integer.parseInt(shipInfo[0]));

                addDynamicData(this.shipInfo);

                addCreatedShip();
            }
            sc.close();

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Adds the ship created from the CSV file
     *
     * @return true if the ship was added
     */
    public boolean addCreatedShip(){
        return this.store.saveShip(this.s);
    }
}
