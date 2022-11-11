--Ship Trip
CREATE OR REPLACE TRIGGER trg_invalid_unload_cargo_manifest_ship
BEFORE INSERT on ShipTripStop
FOR EACH ROW
DECLARE
    --Destination warehouse
    vWarehouseID Warehouse.warehouseID%type;
    
   --Curreny container count
   vContainerCount INTEGER;
   
   --Number of containers to be unloaded
   vUnloadCargoContainerCount INTEGER;
   
   --Warehouse's max occupancy
    vMaxOccupancy NUMBER(8,5);
    
BEGIN
    SELECT w.warehouseID INTO vWarehouseID
    FROM Warehouse w
    WHERE w.portID = (SELECT p.portID
                      FROM Port p
                      WHERE p.PortID = :new.currentPort);
                                        


     --Curreny container count
    SELECT COUNT(s.containerID) INTO vContainerCount
    FROM Stay s
    WHERE s.containerArrivalDate <= SYSDATE AND s.containerDepartureDate >= SYSDATE AND s.warehouseID= vWarehouseID;

    --Number of containers to be unloaded
    SELECT COUNT(*) INTO vUnloadCargoContainerCount
    FROM ShipCargoManifest
    WHERE cargomanifestid = :new.unloadingCargoManifestID;
    
    --Warehouse's max occupancy
    SELECT w.warehouseDimensionX * w.warehouseDimensionY * w.warehouseDimensionZ INTO vMaxOccupancy
    FROM Warehouse w
    WHERE w.warehouseID = vWarehouseID;
    
    IF vUnloadCargoContainerCount + vContainerCount > vMaxOccupancy
        THEN raise_application_error(-20030, 'Cargo manifest has an invalid number of containers for this warehouse');
    END IF;    
END;

--------------------------------------------------------------------------------

--Truck Trip
CREATE OR REPLACE TRIGGER trg_invalid_unload_cargo_manifest_truck
BEFORE INSERT on TruckTripStop
FOR EACH ROW
DECLARE
    --Destination warehouse
    vWarehouseID Warehouse.warehouseID%type;
    
   --Curreny container count
   vContainerCount INTEGER;
   
   --Number of containers to be unloaded
   vUnloadCargoContainerCount INTEGER;
   
   --Warehouse's max occupancy
    vMaxOccupancy NUMBER(8,5);
    
BEGIN
    SELECT w.warehouseID INTO vWarehouseID
    FROM Warehouse w
    WHERE w.warehouseID = :new.currentWarehouse;
                                        

     --Curreny container count
    SELECT COUNT(s.containerID) INTO vContainerCount
    FROM Stay s
    WHERE s.containerArrivalDate <= SYSDATE AND s.containerDepartureDate >= SYSDATE AND s.warehouseID= vWarehouseID;

    --Number of containers to be unloaded
    SELECT COUNT(*) INTO vUnloadCargoContainerCount
    FROM TruckCargoManifest
    WHERE cargomanifestid = :new.unloadingCargoManifestID;
    
    --Warehouse's max occupancy
    SELECT w.warehouseDimensionX * w.warehouseDimensionY * w.warehouseDimensionZ INTO vMaxOccupancy
    FROM Warehouse w
    WHERE w.warehouseID = vWarehouseID;
    
    IF vUnloadCargoContainerCount + vContainerCount > vMaxOccupancy
        THEN raise_application_error(-20040, 'Cargo manifest has an invalid number of containers for this warehouse');
    END IF;    
END;


--Test
--Warehouse 
INSERT INTO Warehouse VALUES('Warehouse75', 'War75', 1, 2, 2, 'WarehouseM1', '246265');

--Initial stay
INSERT INTO Stay VALUES('Cont1', 'Warehouse75', '2021-11-29 06:00:00', '2022-10-30 06:00:00');


INSERT INTO CargoManifest VALUES('CargoManifest91', '2022-10-01 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest92', '2022-10-01 09:00:00', 'Unload');

--4 containers(more than allowed)
INSERT INTO ShipCargoManifest VALUES('CargoManifest92', 'Cont1', 1, 1, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest92', 'Cont2', 2, 1, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest92', 'Cont3', 3, 1, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest92', 'Cont4', 4, 1, 1, 111111111);

--Trip
INSERT INTO Trip VALUES('Trip75', '2021-12-10 09:00:00', '2022-12-10 20:00:00', '2022-12-10 20:00:00');

--ShipTrip
INSERT INTO ShipTrip VALUES('Trip75', '29002', '20351', 111111111, 'Crew1');  

--ShipTripStop
INSERT INTO ShipTripStop VALUES('Trip75', '246265', '2022-12-10 20:00:00', '2022-12-10 20:00:00','2022-12-11 20:00:00', 'CargoManifest91', 'CargoManifest92');