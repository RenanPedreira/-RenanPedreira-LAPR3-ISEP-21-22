CREATE OR REPLACE FUNCTION fnc_number_days_ship_idle_begining_year(p_fleetmanagerid fleetmanager.fleetmanagerID%TYPE)
RETURN SYS_REFCURSOR
AS
    v_currentYear INTEGER;

    v_count_fleet_manager INTEGER;
    v_ship_days_idle SYS_REFCURSOR;

BEGIN
    v_currentYear := EXTRACT(YEAR FROM SYSDATE);
    
    -- Checks if Fleet Manager exists
    SELECT COUNT(*) INTO v_count_fleet_manager
    FROM fleetmanager f
    WHERE f.fleetmanagerid = p_fleetmanagerid;
    
    IF (v_count_fleet_manager = 0)
    THEN raise_application_error(-20033, 'Fleet Manager ID does not exist');
    END IF;                   
    
    OPEN v_ship_days_idle FOR
    SELECT s.shipMMSI, (SELECT fnc_number_days_idle(shipMMSI, v_currentYear) FROM DUAL) DAYS_IDLE
    FROM Ship s
    WHERE s.fleetmanagerid = p_fleetmanagerid;

    RETURN v_ship_days_idle;
    END;
/

--SELECT fnc_number_days_ship_idle_begining_year('FleetM1') FROM DUAL;