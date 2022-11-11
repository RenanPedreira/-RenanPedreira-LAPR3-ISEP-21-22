package lapr.project.controller;

import lapr.project.data.ContainerOperationDB;
import lapr.project.data.WarehouseAnalysisDB;
import lapr.project.utils.Pair;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBControllersTest {

    List<String> resultSet;
    Boolean printResult;
    List<List<String>> resultList;

    //204
    ContainerLocationController clc = new ContainerLocationController();

    //205
    UnloadingCargoController ucc = new UnloadingCargoController();

    //206
    LoadingCargoController lcc = new LoadingCargoController();

    //207
    CargoManifestCountController cmcc = new CargoManifestCountController();

    //208
    OccupancyRateCargoController orcc = new OccupancyRateCargoController();

    //209
    OccupancyGivenMomentController oggc = new OccupancyGivenMomentController();

    //210
    ShipsNextMondayController snmc = new ShipsNextMondayController();

    //304
    ContainerOperationDB codb = new ContainerOperationDB();

    //305
    LeasedContainerRouteController lcrc = new LeasedContainerRouteController();

    //306
    WarehouseAnalysisController wac = new WarehouseAnalysisController();

    //310
    OccupancyMapController omc = new OccupancyMapController();

    //312
    LeasedContainerLocationController lclc = new LeasedContainerLocationController();

    //404
    ShipNumberDaysIdleController sndic = new ShipNumberDaysIdleController();

    //405
    AverageOccupancyGivenPeriodController aogpc = new AverageOccupancyGivenPeriodController();

    //406
    ShipVoyagesAboveThresholdController svtc = new ShipVoyagesAboveThresholdController();

    //407
    ResourceMapNextWeekController rmnwc = new ResourceMapNextWeekController();

    //417
    VesselTypeTaskController vttc = new VesselTypeTaskController();

    //419
    PositionContainersController positionContainersController=new PositionContainersController();

    //US 204
    @Test
    public void ContainerLocationController() throws SQLException {
        resultSet = clc.getContainerCurrentPosition("Cont1");
        assertTrue(resultSet != null);
        //assertEquals("PORT", resultSet.get(0));
        //assertEquals("New Jersey", resultSet.get(1));

        resultSet = clc.getContainerCurrentPosition("Cont2");
        assertTrue(resultSet != null);
        //assertEquals("SHIP", resultSet.get(0));
        //assertEquals("Ship3", resultSet.get(1));
    }


    //US 205
    @Test
    public void UnloadingCargoControllerTest() throws SQLException {
        List<List<String>> result = ucc.getNextPortOffload("Trip1", "29002");
        assertTrue(result != null);
//        assertEquals("25007", result.get(0).get(0));
//        assertEquals("Cont6", result.get(0).get(1));
//        assertEquals("ContT2", result.get(0).get(2));
//        assertEquals("1255.0", result.get(0).get(3));
//        assertEquals("3", result.get(0).get(4));
//        assertEquals("1", result.get(0).get(5));
//        assertEquals("7", result.get(0).get(6));
    }

    //US 206
    @Test
    public void LoadingCargoControllerTest() throws SQLException {
        resultList = lcc.getNextPortLoad("Trip1", "29002");
        assertTrue(resultList != null);
//        assertEquals("25007", resultList.get(0).get(0));
//        assertEquals("Cont5", resultList.get(0).get(1));
//        assertEquals("ContT2", resultList.get(0).get(2));
//        assertEquals("1254.0", resultList.get(0).get(3));
//        assertEquals("1", resultList.get(0).get(4));
//        assertEquals("4", resultList.get(0).get(5));
//        assertEquals("1", resultList.get(0).get(6));
    }

    //US 207
    @Test
    public void CargoManifestCountControllerTest() throws SQLException {
        ContainerAlterationController cac = new ContainerAlterationController();
        cac.alterContainerInformationCreate("CargoManifest20", "Cont20", 444444444, 1,1,1);
        cac.alterContainerInformationUpdate("CargoManifest20", "Cont20",1, 1,1);
        cac.alterContainerInformationDelete("CargoManifest20", "Cont20");
        resultSet = cmcc.getCountCargoManifest("111111111", 2020);
        assertTrue(resultSet != null);

    }

    //US 208
    @Test
    public void OccupancyRateCargoControllerTest() throws SQLException {
        Double result = orcc.getOccupancyRatePerCM("111111111", "CargoManifest1");
        //assertTrue(result != null);
        //assertNotEquals(0.000025, result);
    }

    //US 209
    @Test
    public void OccupancyGivenMomentControllerTest() throws SQLException {
        Double result = oggc.getOccupancyRate("111111111", Timestamp.valueOf("2020-11-13 06:00:00"));
        assertTrue(result != null);
        assertEquals(0, result);
    }

    //US210
    @Test
    public void ShipsNextMondayControllerTest() throws SQLException {
        //resultList = snmc.getAvailableShipsNextMonday();
        //assertTrue(resultList != null);
    }

    //US304
    @Test
    public void ContainerOperationDBTest() throws SQLException {
        resultList = codb.getContainerOperationList("CargoManifest1", "Cont20");
        /*assertEquals(resultList.get(0).get(1), "2021-12-19 18:44:59.0");
        assertEquals(resultList.get(0).get(2), "INSERT");
        assertEquals(resultList.get(0).get(3), "Cont20");
        assertEquals(resultList.get(0).get(4), "CargoManifest1");

        assertEquals(resultList.get(1).get(1), "2021-12-19 18:47:38.0");
        assertEquals(resultList.get(1).get(2), "UPDATE");
        assertEquals(resultList.get(1).get(3), "Cont20");
        assertEquals(resultList.get(1).get(4), "CargoManifest1");

        assertEquals(resultList.get(2).get(1), "2021-12-19 18:48:03.0");
        assertEquals(resultList.get(2).get(2), "DELETE");
        assertEquals(resultList.get(2).get(3), "Cont20");
        assertEquals(resultList.get(2).get(4), "CargoManifest1");*/
    }

    //US305
    @Test
    public void LeasedContainerRouteControllerTest() throws SQLException{
        resultList = lcrc.getLeasedContainerRoute("Client1", "Cont4");
        assertEquals(resultList.get(0).get(0), "Warehouse7");
        assertEquals(resultList.get(0).get(1), "Truck");
        assertEquals(resultList.get(0).get(2), "2021-11-30");
        assertEquals(resultList.get(0).get(3), "2021-11-30");

        assertEquals(resultList.get(1).get(0), "Warehouse1");
        assertEquals(resultList.get(1).get(1), "Truck");
        assertEquals(resultList.get(1).get(2), "2021-11-30");
        assertEquals(resultList.get(1).get(3), "2021-11-30");

        assertEquals(resultList.get(2).get(0), "29002");
        assertEquals(resultList.get(2).get(1), "Ship");
        assertEquals(resultList.get(2).get(2), "2021-12-01");
        assertEquals(resultList.get(2).get(3), "2021-12-01");

        assertEquals(resultList.get(3).get(0), "20301");
        assertEquals(resultList.get(3).get(1), "Ship");
        assertEquals(resultList.get(3).get(2), "2021-12-07");
        assertEquals(resultList.get(3).get(3),"2021-12-07");
    }

    //US306
    @Test
    public void WarehouseAnalysisTest() throws SQLException{
        resultList = wac.getWarehouseAnalysis("246265");

        /*
        assertEquals("Warehouse100", resultList.get(1).get(0));
        assertEquals("100.0", resultList.get(1).get(1));
        assertEquals("4", resultList.get(1).get(2));

        assertEquals("Warehouse101", resultList.get(2).get(0));
        assertEquals("50.0", resultList.get(2).get(1));
        assertEquals("2", resultList.get(2).get(2));

        assertEquals("Warehouse75", resultList.get(0).get(0));
        assertEquals("25.0", resultList.get(0).get(1));
        assertEquals("0", resultList.get(0).get(2));

         */
    }

    //US310
    @Test
    public void OccupancyMapControllerTest() throws SQLException{
        resultList = omc.getOccupancyMap("14635", "12", "2021");

        assertEquals("1", resultList.get(0).get(0));
        assertEquals("2", resultList.get(0).get(1));
        assertEquals("0", resultList.get(0).get(2));

        assertEquals("2", resultList.get(1).get(0));
        assertEquals("2", resultList.get(1).get(1));
        assertEquals("0", resultList.get(1).get(2));

        assertEquals("3", resultList.get(2).get(0));
        assertEquals("3", resultList.get(2).get(1));
        assertEquals("1", resultList.get(2).get(2));

        assertEquals("4", resultList.get(3).get(0));
        assertEquals("3", resultList.get(3).get(1));
        assertEquals("1", resultList.get(3).get(2));

        assertEquals("5", resultList.get(4).get(0));
        assertEquals("0", resultList.get(4).get(1));
        assertEquals("0", resultList.get(4).get(2));
    }

    //US312
    @Test
    public void LeasedContainerLocationControllerTest() throws SQLException{
        resultSet = lclc.getLeasedContainerCurrentLocation("Client1", "Cont4");

        assertEquals(resultSet.get(0), "Cont4");
//        assertEquals(resultSet.get(1), "WAREHOUSE");
//      assertEquals(resultSet.get(2), "War8");
    }

    //US404
    @Test
    public void ShipNumberDaysIdleControllerTest() throws SQLException {
        resultList = sndic.getShipNumberDaysIdle();
        assertTrue(Integer.parseInt(resultList.get(0).get(1)) > 21);
        assertTrue(Integer.parseInt(resultList.get(1).get(1)) > 21);
        assertTrue(Integer.parseInt(resultList.get(2).get(1)) > 21);
        assertTrue(Integer.parseInt(resultList.get(3).get(1)) > 21);
        assertTrue(Integer.parseInt(resultList.get(4).get(1)) > 21);
    }

    //US405
    @Test
    public void AverageOccupancyGivenPeriodController() throws SQLException {
        Double result = aogpc.getAverageOccupancy("111111111", Timestamp.valueOf("2021-11-30 09:00:00"), Timestamp.valueOf("2021-12-10 15:00:00"));
        assertTrue(result > 0.0);
    }

    //US406
    @Test
    public void ShipVoyagesAboveThresholdController() throws SQLException {
        resultList = svtc.getShipVoyagesAboveThreshold(66.6);

        assertEquals("25007", resultList.get(0).get(0));
        assertEquals("2021-12-05 09:00:00", resultList.get(0).get(1));
        assertEquals("2021-12-05 10:00:00", resultList.get(0).get(2));

        assertEquals("29002", resultList.get(1).get(0));
        assertEquals("2021-12-01 09:00:00", resultList.get(1).get(1));
        assertEquals("2021-12-01 10:00:00", resultList.get(1).get(2));

        assertEquals("20351", resultList.get(2).get(0));
        assertEquals("2021-12-09 09:00:00", resultList.get(2).get(1));
        assertEquals("2021-12-09 10:00:00", resultList.get(2).get(2));
    }

    //407
//    @Test
    public void ResourceMapNextWeekController() throws SQLException {
        resultList = rmnwc.getResourceMapNextWeek();
        if(!resultList.isEmpty()) {

            assertEquals("Cont1", resultList.get(0).get(0));
            assertEquals("2021-12-01 09:00:00", resultList.get(0).get(1));
            assertEquals("29002", resultList.get(0).get(2));
            assertEquals(null, resultList.get(0).get(3));
            assertEquals("CargoManifest1", resultList.get(0).get(4));
            assertEquals("Load", resultList.get(0).get(5));
            assertEquals("111111111", resultList.get(0).get(6));
            assertEquals("10", resultList.get(0).get(7));
            assertEquals("1", resultList.get(0).get(8));
            assertEquals("1", resultList.get(0).get(9));
            assertEquals("1", resultList.get(0).get(10));
        }
    }

    //US417
    @Test
    public void VesselTypeTaskTest() throws SQLException{
        resultList = vttc.getVesselTypesForTask("CargoManifest1");
        String vessel = resultList.get(0).get(0);
        assertEquals("Cargo", vessel);
    }

    //US419
    @Test
    public void PositionContainersControllerTest() throws SQLException{
        List<Pair<Pair<Double, Double>, Pair<Double, Double>>> result = positionContainersController.getContainers("222222222", "CargoManifest23");

        assertEquals(result.get(0).getFirst().getFirst(), 500.0);
        assertEquals(result.get(1).getFirst().getSecond(), 200.0);
        assertEquals(result.get(0).getSecond().getFirst(), 6.0);
        assertEquals(result.get(1).getSecond().getSecond(), 2.3);
    }

}