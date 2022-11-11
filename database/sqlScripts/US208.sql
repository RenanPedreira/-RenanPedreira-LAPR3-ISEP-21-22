CREATE OR REPLACE FUNCTION fnc_ship_occupancy_rate_for_cm(p_shipMMSI Ship.shipMMSI%type, p_cargoManifestID ShipCargoManifest.cargoManifestID%type) 
RETURN NUMBER
AS
    v_rate NUMBER;  
BEGIN
    
    SELECT COUNT(scm.cargoManifestID)/ ( SELECT s.shipDimensionX * s.shipDimensionY * s.shipDimensionZ 
                                         FROM Ship s
                                         WHERE s.shipMMSI = p_shipMMSI) * 100 INTO v_rate
    FROM ShipCargoManifest scm
    WHERE scm.cargomanifestid = p_cargoManifestID
    GROUP BY 1;

    RETURN v_rate;                                                                                                                                                                           
END;

--TEST

--Expected: 0.000025
SELECT fnc_ship_occupancy_rate_for_cm(111111111, 'CargoManifest1') FROM DUAL;