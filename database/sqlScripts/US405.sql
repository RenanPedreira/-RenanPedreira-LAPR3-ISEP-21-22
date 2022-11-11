CREATE OR REPLACE FUNCTION fnc_average_occupancy_period(p_shipMMSI Ship.shipMMSI%type, p_Date_Start TIMESTAMP, p_Date_End TIMESTAMP)
RETURN NUMBER 
AS
    v_manifest_list SYS_REFCURSOR;
    v_manifest CargoManifest.cargoManifestID%type;
    v_manifestType CargoManifest.cargoManifestTypeID%type;
    v_ship_occupancy_rate NUMBER;
    v_total_occupancy NUMBER;
    v_manifest_rate NUMBER;
    v_manifest_count INTEGER;
    v_result NUMBER;
BEGIN
    -- Get initial ship occupancy rate from initial date from given period
    SELECT fnc_ship_occupancy_rate_given_moment(p_shipMMSI, p_date_start) INTO v_ship_occupancy_rate FROM DUAL;
    
    IF v_ship_occupancy_rate IS NULL
    THEN v_ship_occupancy_rate := 0;
    END IF;
 
    OPEN v_manifest_list FOR
    SELECT cm.cargoManifestID, cm.cargoManifestTypeID
    FROM CargoManifest cm
    WHERE cm.cargoManifestID IN (SELECT sts.loadingCargoManifestID
                                 FROM ShipTripStop sts
                                 WHERE sts.tripStopActualArrivalDate >= p_date_start
                                 AND sts.tripStopActualArrivalDate <= p_date_end 
                                 AND sts.tripID IN (SELECT tripID
                                                    FROM ShipTrip
                                                    WHERE shipMMSI = p_shipMMSI))                                                
      OR cm.cargoManifestID IN (SELECT sts.unloadingCargoManifestID
                                 FROM ShipTripStop sts
                                 WHERE sts.tripStopActualArrivalDate >= p_date_start
                                 AND sts.tripStopActualArrivalDate <= p_date_end 
                                 AND sts.tripID IN (SELECT tripID
                                                    FROM ShipTrip
                                                    WHERE shipMMSI = p_shipMMSI));
                                
    v_total_occupancy:=0;
    v_result :=0;
    v_manifest_rate:=3000;
    v_manifest_count :=0;
 
 
    LOOP
        FETCH v_manifest_list INTO v_manifest, v_manifestType;
        EXIT WHEN v_manifest_list%notfound;
        
        SELECT fnc_ship_occupancy_rate_for_cm(p_shipMMSI, v_manifest) INTO v_manifest_rate FROM DUAL;
        
        IF v_manifest_rate IS NULL
        THEN v_manifest_rate := 0;
        END IF;
        
        IF v_manifestType = 'Load' THEN
            v_ship_occupancy_rate := v_ship_occupancy_rate + v_manifest_rate;
        END IF;
        
        IF v_manifestType = 'Unload' THEN
            v_ship_occupancy_rate := v_ship_occupancy_rate - v_manifest_rate;
        END IF;
        
        v_manifest_count := v_manifest_count+1;
        v_total_occupancy := v_total_occupancy + v_ship_occupancy_rate;
    END LOOP;

   v_result := v_total_occupancy / v_manifest_count;
 
    RETURN v_result;
END;
/
