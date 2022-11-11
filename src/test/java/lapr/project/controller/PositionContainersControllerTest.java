package lapr.project.controller;

import lapr.project.data.PositionContainersDB;
import lapr.project.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class PositionContainersControllerTest {
    PositionContainersDB positionContainersDB;
    PositionContainersController positionContainersController;

    @BeforeEach
    void setUp() {
        positionContainersDB=new PositionContainersDB();
        positionContainersController=new PositionContainersController();
    }

    @Test
    void getMatrixWithContainersPositioned() throws SQLException {
        List<Pair<Pair<Double, Double>, Pair<Double, Double>>> list=positionContainersDB.getContainers("222222222", "CargoManifest23");
        int numberOfContainers=5;

        assertNotNull(positionContainersController.getMatrixWithContainersPositioned(list, numberOfContainers));
    }
}