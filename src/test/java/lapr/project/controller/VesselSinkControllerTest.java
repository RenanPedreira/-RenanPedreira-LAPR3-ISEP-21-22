package lapr.project.controller;

import lapr.project.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class VesselSinkControllerTest {
    VesselSinkController controller= new VesselSinkController();

    @Test
    void getTheCalculationResult() throws SQLException {
        Pair<Integer,Pair<Pair<Double,Double>,Pair<Double,Pair<Double,Double>>>> result= controller.getTheCalculationResult("222222222","CargoManifest23");
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, ()->controller.getTheCalculationResult("123456789","CargoManifest23"));
        assertEquals(0.009957679860591817,result.getSecond().getSecond().getSecond().getSecond());
        assertEquals("The ship's MMSI or the CargoManifestID introduced are not registered in the system or in the DB.",e.getMessage());
    }
}