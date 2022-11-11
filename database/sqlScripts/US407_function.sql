CREATE OR REPLACE FUNCTION fnc_resource_map_manifests(p_portManagerID Employee.employeeid%type, p_next_monday TIMESTAMP)
RETURN INTEGER

AS
    v_manifestList SYS_REFCURSOR;
    v_containers SYS_REFCURSOR;
    
    v_count         INTEGER;
    v_count2        INTEGER;
    
    v_manifestID CargoManifest.cargoManifestID%type;
    v_manifestType CargoManifest.cargoManifestTypeID%type;
    
    v_containerCount INTEGER;
    
    v_container     Container.containerID%type;
    v_port          Port.portID%type;
    v_warehouse     Warehouse.warehouseID%type;
    v_day           TIMESTAMP;
    
    v_transport     VARCHAR(20);
    
    v_positionX     INTEGER;
    v_positionY     INTEGER;
    v_positionZ     INTEGER;

BEGIN
    --Manifest List
    OPEN v_manifestList FOR
    SELECT cm.cargoManifestID
    FROM CargoManifest cm
    WHERE cm.cargoManifestID IN (SELECT sts.loadingCargoManifestID
                                 FROM ShipTripStop sts
                                 WHERE sts.tripStopActualArrivalDate >= p_next_monday 
                                 AND sts.tripStopActualArrivalDate <= p_next_monday+7 
                                 AND sts.currentPort IN (SELECT p.portID
                                                         FROM Port p
                                                         WHERE p.portmanagerid = p_portManagerID))
                                                         
    OR cm.cargoManifestID IN (SELECT sts.unloadingCargoManifestID
                                 FROM ShipTripStop sts
                                 WHERE sts.tripStopActualArrivalDate >= p_next_monday 
                                 AND sts.tripStopActualArrivalDate <= p_next_monday+7 
                                 AND sts.currentPort IN (SELECT p.portID
                                                         FROM Port p
                                                         WHERE p.portmanagerid = p_portManagerID))
    
    OR cm.cargoManifestID IN (SELECT tts.loadingCargoManifestID
                              FROM TruckTripStop tts
                              WHERE tts.tripStopActualArrivalDate >= p_next_monday 
                              AND tts.tripStopActualArrivalDate <= p_next_monday+7
                              AND tts.currentWarehouse IN (SELECT w.warehouseID
                                                           FROM Warehouse w
                                                           WHERE w.portID IN(SELECT portID
                                                                             FROM Port p
                                                                             WHERE p.portmanagerid = p_portManagerID)))
                                                                             
    OR cm.cargoManifestID IN (SELECT tts.unloadingCargoManifestID
                              FROM TruckTripStop tts
                              WHERE tts.tripStopActualArrivalDate >= p_next_monday 
                              AND tts.tripStopActualArrivalDate <= p_next_monday+7
                              AND tts.currentWarehouse IN (SELECT w.warehouseID
                                                           FROM Warehouse w
                                                           WHERE w.portID IN(SELECT portID
                                                                             FROM Port p
                                                                             WHERE p.portmanagerid = p_portManagerID)));
                                                                             
    
    LOOP  
        FETCH v_manifestList INTO v_manifestID;
        --Day
        SELECT arrivalDate INTO v_day FROM CargoManifest WHERE cargoManifestID = v_manifestID;
        
        --manifest type
        SELECT cm.cargoManifestTypeID INTO v_manifestType FROM cargomanifest cm WHERE cm.cargoManifestID = v_manifestID;
        
        --manifest transport
        SELECT COUNT(cm.cargoManifestID) INTO v_count  FROM CargoManifest cm WHERE cm.cargoManifestID IN (SELECT cargoManifestID FROM ShipCargoManifest WHERE cargoManifestID = v_manifestID);
        SELECT COUNT(cm.cargoManifestID) INTO v_count2 FROM cargomanifest cm WHERE cm.cargoManifestID IN (SELECT cargoManifestID FROM TruckCargoManifest WHERE cargoManifestID = v_manifestID);
        
        --Ship
        IF v_count = 1      
        THEN
            --Transport
            SELECT s.shipMMSI INTO v_transport
            FROM Ship s
            WHERE s.shipMMSI IN (SELECT scm.shipMMSI FROM ShipCargoManifest scm WHERE scm.cargoManifestID = v_manifestID);
             
            --Count 
            SELECT COUNT(*) INTO v_containerCount
            FROM ShipCargoManifest
            WHERE cargoManifestID = v_manifestID;
             
            --Port
            SELECT currentPort INTO v_port FROM ShipTripStop WHERE loadingcargomanifestid = v_manifestID OR unloadingcargomanifestid = v_manifestID;
                     
            --Container positions
            OPEN v_containers FOR
            SELECT scm.containerID, scm.containerPositionX, scm.containerPositionY, scm.containerPositionZ
            FROM ShipCargoManifest scm
            WHERE scm.cargoManifestID = v_manifestID;
        
            LOOP
                FETCH v_containers INTO v_container, v_positionX, v_positionY, v_positionZ;
                prc_insert_resource_map(v_day, v_port, null, v_manifestID, v_manifestType, v_transport, v_containerCount , v_positionX, v_positionY, v_positionZ, v_container);
            EXIT WHEN v_containers%notfound;   
            END LOOP;
        END IF;
        
        
        --Truck
        IF v_count2 = 1
        THEN
            --Transport
            SELECT t.truckID INTO v_transport
            FROM Truck t
            WHERE t.truckID = (SELECT tcm.truckID FROM TruckCargoManifest tcm WHERE tcm.cargoManifestID = v_manifestID);
             
            --Count 
            SELECT COUNT(*) INTO v_containerCount
            FROM TruckCargoManifest
            WHERE cargoManifestID = v_manifestID;
        
            --Warehouse
             SELECT currentWarehouse INTO v_warehouse FROM TruckTripStop WHERE loadingcargomanifestid = v_manifestID OR unloadingcargomanifestid = v_manifestID;
            
            --Port
            SELECT portID INTO v_port FROM Warehouse WHERE warehouseID = v_warehouse;
            
            --Container positions
            OPEN v_containers FOR
            SELECT scm.containerID, scm.containerPositionX, scm.containerPositionY, scm.containerPositionZ
            FROM ShipCargoManifest scm
            WHERE scm.cargoManifestID = v_manifestID;
        
            LOOP
                FETCH v_containers INTO v_container, v_positionX, v_positionY, v_positionZ;
                prc_insert_resource_map(v_day, v_port, v_warehouse, v_manifestID, v_manifestType, v_transport, v_containerCount, 0, 0, 0, v_container);
            EXIT WHEN v_containers%notfound;   
            END LOOP;
        END IF;
           
    EXIT WHEN v_manifestList%notfound;
    END LOOP;
    CLOSE v_manifestList;
    
RETURN 1;
END;
/


delete from resourcemap;
SELECT fnc_resource_map_manifests('PortM1', SYSDATE) FROM DUAL;

