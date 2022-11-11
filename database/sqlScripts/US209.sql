CREATE OR REPLACE FUNCTION fnc_ship_occupancy_rate_given_moment(p_shipMMSI Ship.shipMMSI%type, p_date TIMESTAMP) 
RETURN NUMBER
AS
    v_rate_final NUMBER;
    v_rate_load NUMBER;
    v_rate_unload NUMBER;
    v_loadCargoManifestList SYS_REFCURSOR;
    v_unloadCargoManifestList SYS_REFCURSOR;
    v_cargoManifestID VARCHAR(20);
    
    
BEGIN 

    v_rate_final := 0;
    --Load cargo manifest
    OPEN v_loadCargoManifestList FOR
    SELECT UNIQUE(cm.cargomanifestid)
    FROM CargoManifest cm
    WHERE cm.arrivalDate < p_date AND cm.cargoManifestID IN ( SELECT scm.cargoManifestID
                                                              FROM ShipCargoManifest scm
                                                              WHERE scm.shipMMSI = p_shipMMSI)
    AND cm.cargomanifesttypeid = 'Load';
    
    --Rates sum (LOOP)
    LOOP
        FETCH v_loadCargoManifestList INTO v_cargoManifestID;
        
        SELECT fnc_ship_occupancy_rate_for_cm(p_shipMMSI, v_cargoManifestID) INTO v_rate_load 
        FROM DUAL;
        
        v_rate_final := v_rate_final + v_rate_load;
        
        EXIT WHEN v_loadCargoManifestList%notfound;
    END LOOP;


    --Unload cargo manifest
    OPEN v_unloadCargoManifestList FOR
    SELECT UNIQUE(cm.cargomanifestid)
    FROM CargoManifest cm
    WHERE cm.arrivalDate < p_date AND cm.cargoManifestID IN ( SELECT scm.cargoManifestID
                                                                             FROM ShipCargoManifest scm
                                                                             WHERE scm.shipMMSI = p_shipMMSI)
    AND cm.cargomanifesttypeid = 'Unload';

    --Rates sum (LOOP)
    LOOP
        FETCH v_unloadCargoManifestList INTO v_cargoManifestID;
        
        SELECT fnc_ship_occupancy_rate_for_cm(p_shipMMSI, v_cargoManifestID) INTO v_rate_unload 
        FROM DUAL;
        
        v_rate_final := v_rate_final - v_rate_unload;
        
        EXIT WHEN v_unloadCargoManifestList%notfound;
    END LOOP;

    RETURN v_rate_final;                                                                                                                                                                           
END;


--TEST

--eXPECTED: 0
SELECT fnc_ship_occupancy_rate_given_moment(111111111, '2020-11-13 06:00:00') FROM DUAL;


