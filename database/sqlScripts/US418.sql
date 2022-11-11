CREATE OR REPLACE FUNCTION fnc_containers_on_ship(p_shipMMSI Ship.shipMMSI%type, p_cargoManifestID CargoManifest.cargoManifestID%type)
RETURN SYS_REFCURSOR

AS
    v_result SYS_REFCURSOR;

BEGIN
    OPEN v_result FOR
    SELECT c.containerID, c.containerlength, c.containerwidth, s.shipLength, s.shipWidth
    FROM Container c, Ship s
    WHERE c.containerID IN (SELECT scm.containerID
                            FROM ShipCargoManifest scm
                            WHERE scm.cargoManifestID = p_cargoManifestID
                            AND scm.shipMMSI = p_shipMMSI)
                            AND s.shipMMSI = p_shipMMSI;
    
RETURN v_result;
END;
/

select fnc_containers_on_ship(222222222, 'CargoManifest23') fROM DUAL;





