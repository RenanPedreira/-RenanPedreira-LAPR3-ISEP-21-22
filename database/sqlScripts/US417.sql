CREATE OR REPLACE FUNCTION fnc_center_of_mass(p_CargoManifestID CargoManifest.cargomanifestid%type)
RETURN SYS_REFCURSOR

AS
    v_manifestCount INTEGER;
    v_containers SYS_REFCURSOR;
    
    v_positionX INTEGER;
    v_positionY INTEGER;
    v_positionZ INTEGER;
    v_weight    Container.containergross%type;
    v_length NUMBER;
    v_width NUMBER;
    
    v_totalMass NUMBER;
    
    v_positionMassX NUMBER;
    v_positionMassY NUMBER;
    v_positionMassZ NUMBER;
    
    v_containerXAxis INTEGER;
    v_containerYAxis INTEGER;
    
    v_result SYS_REFCURSOR;

BEGIN

    --Checks if the given manifest is valid
    SELECT COUNT(*) INTO v_manifestCount
    FROM CargoManifest cm
    WHERE cm.cargoManifestID = p_CargoManifestID AND cm.cargomanifesttypeid = 'Load';
    IF v_manifestCount < 1
    THEN raise_application_error(-20014, 'Cargo manifest does not exist or is not associated to a Ship or it is not a Loading cargo manifest');
    END IF;
    
    --Containers position on the X axis furthest from the origin
    SELECT MAX(scm.containerpositionx) INTO v_containerXAxis
    FROM ShipCargoManifest scm
    WHERE scm.cargoManifestID = p_CargoManifestID;
    
    --Containers position on the Y axis furthest from the origin
    SELECT MAX(scm.containerpositionY) INTO v_containerYAxis
    FROM ShipCargoManifest scm
    WHERE scm.cargoManifestID = p_CargoManifestID;
        
    --Container position and weight
    OPEN v_containers FOR
    SELECT scm.containerPositionX, scm.containerPositionY, scm.containerPositionZ, c.containerGross, c.containerLength, c.containerWidth
    FROM ShipCargoManifest scm
    INNER JOIN Container c ON scm.containerID = c.containerID 
    AND scm.cargoManifestID = p_CargoManifestID
    GROUP BY c.containerGross , scm.containerPositionX, scm.containerPositionY, scm.containerPositionZ,  c.containerLength, c.containerWidth;
    
    
    --Calculates the centers of mass
    v_positionMassX := 0;
    v_positionMassY := 0;
    v_positionMassZ := 0;
    v_totalMass := 0;
    
    LOOP
        FETCH v_containers INTO v_positionX, v_positionY, v_positionZ, v_weight, v_length, v_width;
        -- position * length = actual position on teh referencial X axis
        v_positionMassX := v_positionMassX + (v_positionX *v_length  * v_weight);
        -- position * width = actual position on teh referencial Y axis
        v_positionMassY := v_positionMassY + (v_positionY *v_width * v_weight);
        v_positionMassZ := v_positionMassZ + (v_positionZ * v_weight);
        v_totalMass := v_totalMass + v_weight;

    EXIT WHEN v_containers%notfound;
    END LOOP;
    
    --Center of mass for each axis
    v_positionX := v_positionMassX/v_totalMass;
    v_positionY := v_positionMassY/v_totalMass;
    v_positionZ := v_positionMassZ/v_totalMass;
    
    --Insert the calculated values
    prc_insert_gravity_center(p_CargoManifestID, v_positionX, v_positionY, v_positionZ);

    
    OPEN v_result FOR
    SELECT vt.vesseltypename vessel, vt.vesseltypelength/2 x_axis, vt.vesseltypewidth/2 y_axis, gc.centerOfMassX x, gc.centerOfMassY y, gc.centerOfMassZ z
    FROM VesselType vt, GravityCenter gc
    WHERE gc.cargoManifestID = 'CargoManifest1'
    --Only vessel types capable of carring the containers
    AND v_containerXAxis*6 <= vt.vesseltypelength AND v_containerYAxis*2.3 <= vt.vesseltypewidth
    ORDER BY x_axis - x, y_axis - y;
    
    prc_delete_gravity_center(0);
    -- length / 2(position on X axis)
    -- width /  2(position on Y axis)
   -- SELECT vt.vesseltypename, vt.vesseltypelength/2, vt.vesseltypewidth/2
   --- FROM VesselType vt;
    
RETURN v_result;
END;


SELECT fnc_center_of_mass('CargoManifest1') FROM DUAL; --too big for fishing
SELECT fnc_center_of_mass('CargoManifest2') FROM DUAL; --invalid
SELECT fnc_center_of_mass('CargoManifest3') FROM DUAL; --fits in fishing

