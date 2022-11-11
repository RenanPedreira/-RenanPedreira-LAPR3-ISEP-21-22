CREATE OR REPLACE FUNCTION fnc_number_days_idle(p_shipID Ship.shipMMSI%type, p_year INTEGER)
RETURN INTEGER
AS
    v_ship_trip_list SYS_REFCURSOR;
    v_idle_days NUMBER;
    v_total_busy_days NUMBER;
        
    v_first_day DATE;
    v_start DATE;
    v_end DATE;
    
    v_result INTEGER;
BEGIN
    --Trips from a ship in this interval
    OPEN v_ship_trip_list FOR
    SELECT t.tripStartDate, t.tripActualEndingDate
    FROM Trip t
    WHERE t.tripID IN ( SELECT st.tripID
                        FROM ShipTrip st
                        WHERE st.shipMMSI = p_shipID)
    AND t.tripStartDate <= SYSDATE
    AND t.tripActualEndingDate >= SYSDATE;

    v_total_busy_days := 0;
    
    --first day of the year
    SELECT TRUNC(sysdate,'YEAR') INTO v_first_day FROM DUAL;
    
    LOOP
        FETCH v_ship_trip_list INTO v_start, v_end;
        EXIT WHEN v_ship_trip_list%NOTFOUND;

        --trip starts before this year and ends before today
        IF EXTRACT(YEAR FROM v_start) < p_year AND v_end < SYSDATE
        THEN
            v_total_busy_days := v_total_busy_days + (v_end - v_first_day);
        END IF; 
        
        
        --trip starts before this year and ends after today
        IF EXTRACT(YEAR FROM v_start) < p_year AND v_end > SYSDATE
        THEN
            v_total_busy_days := v_total_busy_days + (SYSDATE - v_first_day);
        END IF; 
        
        
        --trip starts this year and ends before today
        IF EXTRACT(YEAR FROM v_start) = p_year AND v_end < SYSDATE
        THEN
            v_total_busy_days := v_total_busy_days + (v_end - v_start);
        END IF; 


        --trip starts this year and ends after today
        IF EXTRACT(YEAR FROM v_start) = p_year AND v_end > SYSDATE
        THEN
            v_total_busy_days := v_total_busy_days + (SYSDATE - v_start);
        END IF;         
    END LOOP;
    
    v_idle_days := (SYSDATE - v_first_day) - v_total_busy_days;
    SELECT ROUND(v_idle_days) INTO v_result FROM DUAL;
    
    RETURN v_result;
    END;
/