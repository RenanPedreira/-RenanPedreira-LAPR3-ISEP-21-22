CREATE OR REPLACE PROCEDURE prc_insert_resource_map(p_day TIMESTAMP, p_port Port.portID%type, p_warehouse Warehouse.warehouseID%type, p_manifestID CargoManifest.cargoManifestID%type, p_manifestType CargoManifest.cargoManifestTypeID%type, p_transport VARCHAR, p_containerCount INTEGER, p_positionX INTEGER, p_positionY INTEGER, p_positionZ INTEGER, p_container Container.containerID%type)
AS
    pragma autonomous_transaction;
    v_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM ResourceMap;

    INSERT INTO ResourceMap VALUES(v_count+1, p_day, p_port, p_warehouse, p_manifestID, p_manifestType, p_transport, p_containerCount, p_positionX, p_positionY, p_positionZ, p_container);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE prc_delete_resource_map(p_del INTEGER)
AS
    pragma autonomous_transaction;
BEGIN
    DELETE FROM ResourceMap;
    COMMIT;
END;
/