CREATE OR REPLACE PROCEDURE prc_insert_gravity_center(p_manifest CargoManifest.cargomanifestid%type, p_x NUMBER, p_y NUMBER, p_z NUMBER)
AS
    pragma autonomous_transaction;
    v_count INTEGER;
BEGIN
    SELECT COUNT(*)INTO v_count
    FROM GravityCenter gc
    WHERE gc.cargomanifestid = p_manifest;
    
    IF v_count = 0
    THEN INSERT INTO GravityCenter VALUES(p_manifest, p_x, p_y, p_z);
    END IF;
    
    IF v_count = 1
    THEN UPDATE GravityCenter set cargomanifestid=p_manifest, centerOfMassX=p_x, centerOfMassY=p_y, centerOfMassZ=p_z;
    END IF;
    
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE prc_delete_gravity_center(p_z NUMBER)
AS
    pragma autonomous_transaction;
BEGIN
    DELETE FROM GravityCenter;
    COMMIT;
END;
/