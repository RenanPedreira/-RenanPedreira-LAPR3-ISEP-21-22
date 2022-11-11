CREATE OR REPLACE TRIGGER trg_ship_cargo_manifest_insert
AFTER INSERT on ShipCargoManifest
FOR EACH ROW
DECLARE
    vContainerOperationID           containeroperation.operationid%type;

BEGIN
    SELECT COUNT(*) INTO vContainerOperationID
    FROM ContainerOperation;

    vContainerOperationID := vContainerOperationID+1;

    INSERT INTO ContainerOperation VALUES(vContainerOperationID, 'user@lei.pt', SYSDATE, 'INSERT', :new.containerID, :new.cargoManifestID);

END;
/

CREATE OR REPLACE TRIGGER trg_ship_cargo_manifest_update
AFTER UPDATE on ShipCargoManifest
FOR EACH ROW
DECLARE
    vContainerOperationID           containeroperation.operationid%type;

BEGIN
    SELECT COUNT(*) INTO vContainerOperationID
    FROM ContainerOperation;

    vContainerOperationID := vContainerOperationID+1;

    INSERT INTO ContainerOperation VALUES(vContainerOperationID, 'user@lei.pt', SYSDATE, 'UPDATE', :new.containerID, :new.cargoManifestID);

END;
/

CREATE OR REPLACE TRIGGER trg_ship_cargo_manifest_delete
AFTER DELETE on ShipCargoManifest
FOR EACH ROW
DECLARE
    vContainerOperationID           containeroperation.operationid%type;

BEGIN
    SELECT COUNT(*) INTO vContainerOperationID
    FROM ContainerOperation;

    vContainerOperationID := vContainerOperationID+1;

    INSERT INTO ContainerOperation VALUES(vContainerOperationID, 'user@lei.pt', SYSDATE, 'DELETE', :old.containerID, :old.cargoManifestID);

END;
/

delete from containeroperation;


INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont20', 1, 1, 1, 222222222);
UPDATE ShipCargoManifest set containerpositionX = 2 WHERE cargomanifestid='CargoManifest1' and containerid='Cont20';
DELETE from ShipCargoManifest WHERE cargomanifestid='CargoManifest1' and containerid='Cont20';