package lapr.project.tree;

import lapr.project.model.Port;
import lapr.project.model.ShipDynamicData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo and Danilton Lopes
 */
class KDTreeTest {
    private KDTree<Port> kdTree;
    private Port info;
    private Port info2;

    @BeforeEach
    void setUp() {
        kdTree = new KDTree();
        info = new Port(123456789, "Name1", "Portugal", 35.0, 56.0);
        info2 = new Port(123456779, "Name1", "Portugal", 35.0, 56.0);
        kdTree.insert(info, info.getLatitude(), info.getLongitude());
        kdTree.insert(info2, info2.getLatitude(), info2.getLongitude());
    }

    @Test
    public void testBuildTree1() {
        Node<Port> node1;
        Node<Port> node2;
        Node<Port> node3;
        Node<Port> node4;
        Node<Port> node5;
        Port infoTest1, infoTest2, infoTest3, infoTest4, infoTest5;
        infoTest1 = new Port(123456789, "Name1", "Portugal", 10.0, 15.0);
        infoTest2 = new Port(483756913, "Name2", "Portugal", 15.0, 17.0);
        infoTest3 = new Port(859472931, "Name3", "Portugal", 8.0, 7.0);
        infoTest4 = new Port(573826413, "Name4", "Portugal", 18.0, 13.0);
        infoTest5 = new Port(459374914, "Name5", "Portugal", 11.0, 15.0);

        node1 = new Node<>(infoTest3, null, null, 8.0, 7.0);
        node2 = new Node<>(infoTest1, null, null, 10.0, 11.0);
        node3 = new Node<>(infoTest2, null, null, 15.0, 17.0);
        node4 = new Node<>(infoTest4, null, null, 20.0, 21.0);
        node5 = new Node<>(infoTest5, null, null, 26.0, 27.0);

        List<Node<Port>> nodes = new ArrayList<>();

        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);

        KDTree<Port> tree = new KDTree<>(nodes);

        Port port1 = tree.findNearestNeighbour(2.0, 1.0);
        assertEquals(node1.getInfo(), port1);

        Port port2 = tree.findNearestNeighbour(11.0, 12.0);
        assertEquals(node2.getInfo(), port2);

        Port port3 = tree.findNearestNeighbour(16.0, 18.0);
        assertEquals(node3.getInfo(), port3);

        Port port4 = tree.findNearestNeighbour(21.0, 22.0);
        assertEquals(node4.getInfo(), port4);

        Port port5 = tree.findNearestNeighbour(27.0, 28.0);
        assertEquals(node5.getInfo(), port5);

    }


    @Test
    public void testFindNearestNeighbours() {
        List<Port> ports = new ArrayList<>();
        ports.add(new Port(10000, "Lisboa", "Portugal", 25.0, 85.0));
        ports.add(new Port(10001, "Praia", "Portugal", 1.0, 179.0));
        ports.add(new Port(10002, "New York", "EUA", 0.0, 24.0));
        ports.add(new Port(10003, "Miami", "EUA", 10.0, 58.0));
        ports.add(new Port(10004, "Veneza", "Italia", 15.0, 68.0));
        List<Node<Port>> portNodes = new ArrayList<>();
        for (Port port : ports)
            portNodes.add(new Node<>(port, null, null, port.getLatitude(), port.getLongitude()));
        KDTree<Port> tree = new KDTree(portNodes);

        Port nearest = tree.findNearestNeighbour(9.89, 57.88);
        Port nearest2 = tree.findNearestNeighbour(1, -179.0);
        assertEquals(ports.get(3), nearest);
        assertEquals(ports.get(1), nearest2);
    }


    /**
     * Test that gets the info of the point 2d
     */
    @Test
    public void getInfo() {
        //Act
        Port result = kdTree.root().getInfo();

        //Assert
        assertEquals(info, result);
    }

    /**
     * Test that gets the info of the point 2d
     */
    @Test
    public void getLeft1() {
        //Act
        Node<Port> result = kdTree.root().getLeft();

        //Assert
        assertNull(result);
    }

    /**
     * Test that gets the info of the point 2d
     */
    @Test
    public void getLeft2() {
        //Act
        Port info3 = new Port(123456788, "Name1",  "Portugal", 30.0, 56.0);
        kdTree.insert(info3, info3.getLatitude(), info3.getLongitude());
        Node<Port> result = kdTree.root().getLeft();

        //Assert
        assertNotNull(result);

        Port info4 = new Port(123456787, "Name1", "Portugal", 29.0, 55.0);
        kdTree.insert(info4, info4.getLatitude(), info4.getLongitude());
        Node<Port> result2 = kdTree.root().getRight();

        //Assert
        assertNull(result2);
    }

    @Test
    public void getLeftNgetRigth() {
        //Act
        Port info2 = new Port(123456788, "Name1",  "Portugal", 30.0, 56.0);
        kdTree.insert(info2, info2.getLatitude(), info2.getLongitude());
        Node<Port> result = kdTree.root().getLeft();

        //Assert
        assertNotNull(result);

        Port info3 = new Port(123456790, "Name1",  "Portugal", 40.0, 56.0);
        kdTree.insert(info3, info3.getLatitude(), info3.getLongitude());
        Node<Port> result2 = kdTree.root().getRight();

        //Assert
        assertNotNull(result2);
    }

    /**
     * Test that gets the info of the point 2d
     */
    @Test
    public void getRight1() {
        //Act
        Node<Port> result = kdTree.root().getRight();

        //Assert
        assertNull(result);
    }

    /**
     * Test that gets the info of the point 2d
     */
    @Test
    public void getRight2() {
        //Act
        Port info3 = new Port(123456790, "Name1", "Portugal", 40.0, 56.0);
        kdTree.insert(info3, info3.getLatitude(), info3.getLongitude());
        Node<Port> result = kdTree.root().getRight();

        //Assert
        assertNotNull(result);

        Port info4 = new Port(123456791, "Name1", "Portugal", 41.0, 57.0);
        kdTree.insert(info4, info4.getLatitude(), info4.getLongitude());
        Node<Port> result2 = kdTree.root().getLeft();

        //Assert
        assertNull(result2);
        assertEquals(info, kdTree.root().getInfo());
    }

    @Test
    public void kdTreeSetMethods() {
        //Act
        Port info2 = new Port(123456788, "Name1", "Portugal", 30.0, 56.0);
        kdTree.root().setInfo(info2);

        assertEquals(kdTree.root().getInfo(), info2);

        kdTree.root().setCoords(25.0, 50.0);
        assertEquals(kdTree.root().getCoords().getX(), 25.0);
        assertEquals(kdTree.root().getCoords().getY(), 50.0);
    }

    @Test
    void isEmpty() {
        boolean flag = kdTree.isEmpty();
        assertFalse(flag);
    }

    @Test
    void isEmpty2() {
        //Arrange
        KDTree kdTree2=new KDTree();

        //Act
        boolean flag = kdTree2.isEmpty();

        //Assert
        assertTrue(flag);
    }

    @Test
    void insert() {
        KDTree<ShipDynamicData> instance = new KDTree<>();
        instance.insert(new ShipDynamicData(), 30.0, 40.0); //x
        instance.insert(new ShipDynamicData(), 45.0, 50.0); //y
        instance.insert(new ShipDynamicData(), 20.0, 35.0); //y
        instance.insert(new ShipDynamicData(), 25.0, 30.0); //x
        instance.insert(new ShipDynamicData(), 24.0, 36.0); //x
        instance.insert(new ShipDynamicData(), 35.0, 45.0); //x
        instance.insert(new ShipDynamicData(), 34.0, 51.0); //x
        instance.insert(new ShipDynamicData(), 34.0, 51.0); //equals
        //testar o equals
        boolean flag = instance.isEmpty();
        assertFalse(flag);
    }

    @Test
    void size() {
        int size = 1;
        int result = kdTree.size();
        assertEquals(size, result);
    }

    @Test
    void root() {
        Node<Port> portRoot = kdTree.root();
        Port portResult = portRoot.getInfo();
        assertEquals(info, portResult);
        assertNull(portRoot.getLeft());
        assertNull(portRoot.getRight());
        portRoot.setInfo(portResult);
    }

    @Test
    void inOrder() {
        List<Port> list =(ArrayList)kdTree.inOrder();
        System.out.println(list);
    }

    @Test
    void height() {
        //Arrange
        KDTree kdTree2=new KDTree();

        //Act
        int result = kdTree2.height2();

        //Assert
        assertEquals(-1, result);
    }

    @Test
    void getNodes() {
        //Arrange
        List<Node<Port>> expectedResult = new ArrayList<>();

        //Act
        List<Node<Port>> result=kdTree.getNodes();

        //Assert
        assertNotEquals(expectedResult, result);
    }
}
