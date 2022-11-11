CREATE OR REPLACE FUNCTION fnc_get_container_location(p_containerID Container.containerID%TYPE) 
RETURN SYS_REFCURSOR
AS
    v_container_location SYS_REFCURSOR;
BEGIN
    OPEN  v_container_location FOR
        SELECT UNIQUE
            c.containerID container_id,
            CASE
                WHEN cm.cargoManifestID = sts.loadingCargoManifestID THEN 'SHIP'
                WHEN cm.cargoManifestID = sts.unloadingCargoManifestID THEN 'PORT'
                WHEN cm.cargoManifestID = tts.loadingCargoManifestID THEN 'TRUCK'
                WHEN cm.cargoManifestID = tts.unloadingCargoManifestID THEN 'WAREHOUSE'
                ELSE 'unknown'
                END AS TYPE,
            CASE
                WHEN cm.cargoManifestID = sts.loadingCargoManifestID THEN (
                    SELECT s.shipName
                    FROM ShipTrip st, Ship s
                    WHERE sts.tripID = st.tripId AND st.shipMMSI = s.shipMMSI)
                
                WHEN cm.cargoManifestID = sts.unloadingCargoManifestID THEN (
                    SELECT p.portName
                    FROM Port p
                    WHERE sts.currentPort = p.portID)
                    
                WHEN cm.cargoManifestID = tts.loadingCargoManifestID THEN (
                    SELECT t.truckID
                    FROM TruckTrip tt, Truck t
                    WHERE tts.tripID = tt.tripId AND tt.truckID = t.truckID)
                
                WHEN cm.cargoManifestID = tts.unloadingCargoManifestID THEN (
                    SELECT w.warehouseName
                    FROM Warehouse w
                    WHERE tts.currentWarehouse = w.warehouseID)
                
                ELSE 'unknown'
                END AS name
        FROM Container c, CargoManifest cm,
            ShipCargoManifest scm, ShipTripStop sts,
            TruckCargoManifest tcm, TruckTripStop tts 
        WHERE p_containerID = c.containerID
            AND (scm.containerID = c.containerID OR tcm.containerID = c.containerID)
            AND (scm.cargoManifestID = cm.cargoManifestID OR tcm.cargoManifestID = cm.cargoManifestID)
            AND (cm.cargoManifestID = sts.unloadingCargoManifestID OR cm.cargoManifestID = sts.loadingCargoManifestID
                OR cm.cargoManifestID = tts.unloadingCargoManifestID OR cm.cargoManifestID = tts.loadingcargoManifestID)
            AND cm.arrivalDate = (SELECT MAX(cm.arrivalDate)
                                  FROM CargoManifest cm
                                  WHERE cm.arrivalDate < SYSDATE
                                  AND (cm.cargomanifestid IN (SELECT scm.cargomanifestid
                                                             FROM ShipCargoManifest scm
                                                             WHERE scm.containerID = p_containerID)
                                    OR cm.cargomanifestid IN (SELECT tcm.cargomanifestid
                                                             FROM TruckCargoManifest tcm
                                                             WHERE tcm.containerID = p_containerID)));
 
    RETURN  v_container_location;
END;

--TEST

--Expected: CONTAINER_ID = Cont1, TYPE=PORT, NAME=New Jersey
SELECT fnc_get_container_location('Cont4') FROM DUAL;

--Expected: CONTAINER_ID = Cont2, TYPE=SHIP, NAME=Ship3
SELECT fnc_get_container_location('Cont2') FROM DUAL;




