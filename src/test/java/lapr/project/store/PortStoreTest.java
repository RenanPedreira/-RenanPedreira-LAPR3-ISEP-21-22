package lapr.project.store;

import lapr.project.model.Port;
import lapr.project.model.ShipDynamicData;
import lapr.project.tree.KDTree;
import lapr.project.tree.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortStoreTest {
    PortStore store = new PortStore(new KDTree<>());
    Port port1;
    Port port2;
    Port port3;

    @BeforeEach
    public void setUp() throws Exception {
        port1 = store.createPort(123456789, "Test", "Test", 44, 44);
        port2 = store.createPort(123456788, "TestTest", "TestTest", 55, 55);
        store.savePort(port1);
        store.savePort(port2);
    }

    @Test
    void saveKDTree(){
        //Arrange
        KDTree expectedResult=new KDTree();
        List<Node<Port>> listOfNodes=new ArrayList<>();

        //Act
        KDTree result=store.saveKDTree(listOfNodes);

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void getNearestPortFromPosition() {
        Port expected = port1;
        ShipDynamicData location1 = new ShipDynamicData("31/12/2020 23:03", 33.0, 33.0, 3.5, -61.6, 232, "HYUNDAI SINGAPORE", "IMO9305685");
        Port portResult = store.getNearestPortFromPosition(location1);
        assertEquals(expected, portResult);
    }

    @Test
    void savePort() {
        //Arrange
        Port port1 = store.createPort(123456789, "Test", "Test", 44, 44);
        boolean result = store.savePort(port1);

        //Assert
        assertTrue(result);
    }

    @Test
    public void getPorts() {
        //Arrange
        KDTree<Port> expectedResult=new KDTree<>();

        //Act
        KDTree<Port> result= store.getPorts();

        //Assert
        assertNotNull(result);
        assertNotEquals(expectedResult, result);
    }

    @Test
    public void getList() {
        //Arrange
        List<Node<Port>> expectedResult = new ArrayList<>();

        //Act
        List<Node<Port>> result=store.getList();

        //Assert
        assertNotEquals(expectedResult, result);
    }
}