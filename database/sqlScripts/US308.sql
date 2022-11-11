--Ship Trip
CREATE OR REPLACE TRIGGER trg_invalid_load_cargo_manifest_ship
BEFORE INSERT on ShipTripStop
FOR EACH ROW
DECLARE
    --Destination warehouse
vShip Ship.shipMMSI%type;

   --Number of containers to be unloaded
   vLoadCargoContainerCount INTEGER;

   --Ship's max occupancy
    vMaxOccupancy NUMBER(8,5);

    --Loading cargo manifest date time
    vDateTime TIMESTAMP;

    --Occupancy rate
    vRate NUMBER;

    --Percentage occupied by the load
    vNumber NUMBER;

    --Percentage after load
    vFinal NUMBER;

BEGIN
    --Ship
SELECT st.shipMMSI INTO vShip
FROM ShipTrip st
WHERE st.tripID = :new.tripID;

--Loading cargo manifest date time
SELECT cm.arrivalDate INTO vDateTime
FROM CargoManifest cm
WHERE cm.cargoManifestID = :new.loadingCargoManifestID;

--Curreny occupancy rate
SELECT fnc_ship_occupancy_rate_given_moment(vShip, vDateTime) INTO vRate FROM DUAL;

--Number of containers to be loaded
SELECT COUNT(*) INTO vLoadCargoContainerCount
FROM ShipCargoManifest
WHERE cargomanifestid = :new.loadingCargoManifestID;

--Ship's max occupancy
SELECT s.shipDimensionX * s.shipDimensionY * s.shipDimensionZ INTO vMaxOccupancy
FROM Ship s
WHERE s.shipMMSI = vShip;

vNumber := 100 * vLoadCargoContainerCount/vMaxOccupancy;
   vFinal := vNumber + vRate;

    IF vFinal > 100
        THEN raise_application_error(-20040, 'Cargo manifest has an invalid number of containers for this Ship');
END IF;
END;
/

--------------------------------------------------------------------------------

--Test
INSERT INTO Ship VALUES(123123123, 'IMO1231231', '123123', 'TestShip', 3, 100, 50, 1, 1, 1,2,2, 'Vtype1', 'FleetM1', 'Gen1');

--First stop
INSERT INTO CargoManifest VALUES('CargoManifest81', '2022-10-01 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest82', '2022-10-01 09:00:00', 'Unload');

--Second stop
INSERT INTO CargoManifest VALUES('CargoManifest83', '2022-10-03 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest84', '2022-10-03 09:00:00', 'Unload');

--4 containers(more than allowed)
INSERT INTO ShipCargoManifest VALUES('CargoManifest81', 'Cont1', 1, 1, 1, 123123123);
INSERT INTO ShipCargoManifest VALUES('CargoManifest81', 'Cont2', 2, 1, 1, 123123123);

INSERT INTO ShipCargoManifest VALUES('CargoManifest83', 'Cont3', 3, 1, 1, 123123123);
INSERT INTO ShipCargoManifest VALUES('CargoManifest83', 'Cont4', 4, 1, 1, 123123123);
INSERT INTO ShipCargoManifest VALUES('CargoManifest83', 'Cont5', 1, 2, 1, 123123123);


--Trip
INSERT INTO Trip VALUES('Trip35', '2021-12-10 09:00:00', '2022-12-10 20:00:00', '2022-12-10 20:00:00');

--ShipTrip
INSERT INTO ShipTrip VALUES('Trip35', '29002', '20351', 123123123, 'Crew1');

--ShipTripStop
INSERT INTO ShipTripStop VALUES('Trip35', '29002', '2022-12-10 20:00:00', '2022-12-10 20:00:00','2022-12-11 20:00:00', 'CargoManifest81', 'CargoManifest82');
INSERT INTO ShipTripStop VALUES('Trip35', '20351', '2022-12-10 20:00:00', '2022-12-10 20:00:00','2022-12-11 20:00:00', 'CargoManifest83', 'CargoManifest84');

delete from ShipTripStop where tripID = 'Trip35'