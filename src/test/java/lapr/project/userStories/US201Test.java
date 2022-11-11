package lapr.project.userStories;

import lapr.project.controller.App;
import lapr.project.controller.ImportPortController;
import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.store.PortStore;
import lapr.project.tree.KDTree;
import lapr.project.tree.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Luís Araújo
 */
public class US201Test {
    @BeforeEach
    public void setUp() throws Exception {

    }

    /**
     * Test that verifies if the file name is wrong
     */
    @Test
    public void wrongFileName() {
        String filename = "notThisName";
        try {
            ImportPortController importPortController = new ImportPortController();
            assertFalse(importPortController.importPortFromFile(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that verifies if the file name is right
     */
    @Test
    public void rightFileName() {
        String filename = "sports.csv";
        try {
            ImportPortController importPortController = new ImportPortController();
            assertTrue(importPortController.importPortFromFile(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that verifies if the tree is balanced
     */
    @Test
    public void checkIfTreeIsBalanced() {
        String filename = "sports.csv";
        try {
            List<Node<Port>> listOfNodes = new ArrayList<>();
            ImportPortController importPortController = new ImportPortController();
            importPortController.importPortFromFile(filename);

            listOfNodes = importPortController.getList();
            KDTree kdTree1 = null;
            try {
                if (importPortController.saveKDTree(listOfNodes)) {
                    kdTree1 = importPortController.getKdTree();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for (Node<Port> node : listOfNodes) {
                if (kdTree1.balanceFactor2(node) < -1 || kdTree1.balanceFactor2(node) > 1) {
                    assertFalse(false);
                } else {
                    assertTrue(true);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
