package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.store.PortStore;
import lapr.project.tree.KDTree;
import lapr.project.tree.Node;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImportPortControllerTest {
    // Singleton
    App app = App.getInstance();
    Company company = app.getCompany();
    //Stores
    PortStore portStore = company.getPortStore();
    //Controllers
    private ImportPortController ipc= new ImportPortController();
    
    @Test
    public void controllerCanBeCreated1() throws IOException, ClassNotFoundException {
        ImportPortController ipc = new ImportPortController();
    }

    @Test
    public void controllerCanBeCreated2() {
        ImportPortController ipc = new ImportPortController(new Company("a"));
    }

    @Test
    public void getList(){
        //Act
        List<Node<Port>> result=ipc.getList();

        //Assert
        assertNotNull(result);
    }

    @Test
    public void getKDTree(){
        //Arrange
        List<Node<Port>> listOfNodes=new ArrayList<>();
        KDTree expectedResult=new KDTree(listOfNodes);

        //Act
        KDTree result=ipc.getKdTree();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    public void saveKDTree1() throws IOException, ClassNotFoundException {
        //Arrange
        List<Node<Port>> listOfNodes=new ArrayList<>();

        //Assert
        assertFalse(ipc.saveKDTree(listOfNodes));
    }

    @Test
    public void saveKDTree2() throws IOException, ClassNotFoundException {
        //Arrange
        List<Node<Port>> listOfNodes=null;

        //Assert
        assertFalse(ipc.saveKDTree(listOfNodes));
    }

    @Test
    public void portFromLine(){
        //Arrange
        String[] info=new String[10];
        Port port=new Port(123456788, "TestTest", "TestTest", 55.0, 55.0);
        info[0]="TestTest";
        info[1]="TestTest";
        info[2]="123456788";
        info[3]="TestTest";
        info[4]="55.0";
        info[5]="55.0";

        //Act
        Port result=ipc.portFromLine(info);

        //Assert
        assertEquals(port, result);
    }

    @Test
    public void testImportPortFromFile() throws FileNotFoundException {
        ipc.importPortFromFile("sports.csv");

        int expected = 22;
        int result=portStore.getPorts().getNodes().size();

        //checks if all 22 ports were imported
        assertTrue(expected<=result);
        //assertTrue(result>0);

        //return false if file does not exist
        boolean result2 = ipc.importPortFromFile("mr11.csv");
        assertTrue(!result2);
    }

    @Test
    public void importDataTest2() throws SQLException, FileNotFoundException {
        // First three lines for the same ship, last one refers to another ship
        //Act
        ipc.importPortFromFile2("bports.csv");
        ipc.portsFromDB();
        int result=portStore.getPorts().getNodes().size();

        //Assert
        assertTrue(result>0);
        assertFalse(ipc.importPortFromFile2("sposrts.csv"));
    }
}
