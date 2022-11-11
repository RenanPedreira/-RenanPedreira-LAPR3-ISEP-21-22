CREATE OR REPLACE TRIGGER trg_ship_cargo_manifest_control
BEFORE INSERT on ShipCargoManifest
FOR EACH ROW
DECLARE
    vDate CargoManifest.arrivalDate%type;
    vCount INTEGER; 
BEGIN
    SELECT cm.arrivalDate INTO vDate
    FROM CargoManifest cm
    WHERE cm.cargoManifestID = :new.cargoManifestID;
    
    SELECT COUNT(*) INTO vCount
    FROM Trip t
    WHERE t.tripID IN (SELECT st.tripID
                      FROM ShipTrip st
                      WHERE st.shipMMSI = :new.shipMMSI)
    AND t.tripStartDate <= vDate 
    AND t.tripEstimatedEndingDate >= vDate;

    IF vCount > 1
        THEN raise_application_error(-20070, 'Ship is unavailable');
    END IF; 
END;
/

--First Trip
INSERT INTO Trip VALUES('Trip40', '2023-01-01 09:00:00', '2023-01-15 20:00:00', '2023-01-15 20:00:00');
INSERT INTO ShipTrip VALUES('Trip40', '29002', '20351', 123123123, 'Crew1');

INSERT INTO CargoManifest VALUES('CargoManifest40', '2023-01-01 09:00:00', 'Load');
INSERT INTO ShipCargoManifest VALUES('CargoManifest40', 'Cont1', 1, 1, 1, 123123123);

--Invalid Trip
INSERT INTO Trip VALUES('Trip41', '2023-01-01 09:00:00', '2023-01-15 20:00:00', '2023-01-15 20:00:00');
INSERT INTO ShipTrip VALUES('Trip41', '29002', '20351', 123123123, 'Crew1');

INSERT INTO CargoManifest VALUES('CargoManifest41', '2023-01-01 09:00:00', 'Load');
INSERT INTO ShipCargoManifest VALUES('CargoManifest41', 'Cont1', 1, 1, 1, 123123123);
