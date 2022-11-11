CREATE OR REPLACE FUNCTION fnc_occupancy_rate_for_month(p_portID Port.portID%type, p_date VARCHAR)
RETURN SYS_REFCURSOR
AS
    v_occupancyMap SYS_REFCURSOR;
    v_warehouseCapacity INTEGER;
    v_portCapacity INTEGER;
    v_warehouseID Warehouse.warehouseID%type;
    
    v_day INTEGER;
    v_monthLastDay INTEGER;
    v_warehouseDailyRate NUMBER;
    v_portDailyRate NUMBER;

BEGIN
    --warehouseID
    SELECT w.warehouseID INTO v_warehouseID
    FROM Warehouse w
    WHERE w.portID = p_portID;

    --warehouse dimensions
    SELECT w.warehouseDimensionX * w.warehouseDimensionY * w.warehouseDimensionZ INTO v_warehouseCapacity
    FROM Warehouse w
    WHERE w.portID = p_portID;
    
    --port dimensions
    SELECT p.portDimensionX * p.portDimensionY * p.portDimensionZ INTO v_portCapacity
    FROM Port p
    WHERE p.portID = p_portID;

    v_day := 1;
    
    --number of days of the month
    SELECT EXTRACT(DAY FROM LAST_DAY(TO_DATE(p_date, 'YYYY-MM-DD'))) INTO v_monthLastDay FROM DUAL;

    --for each day of the month calculates occupancy rate
    LOOP
        --calculates daily rate for the warehouse
        SELECT fnc_warehouse_occupancy_rate_for_day(v_warehouseID, v_day, TO_DATE(p_date, 'YYYY-MM-DD'), v_warehouseCapacity) INTO v_warehouseDailyRate FROM DUAL;
        --calculates daily rate for the port
        SELECT fnc_port_occupancy_rate_for_day(p_portID, v_day, TO_DATE(p_date, 'YYYY-MM-DD'), v_portCapacity) INTO v_portDailyRate FROM DUAL;
        
        --records calculated data on the table
        prc_record_daily_rate(v_day, v_warehouseDailyRate, v_portDailyRate);
       
        --increments day
        v_day := v_day + 1;

        EXIT WHEN v_day = v_monthLastDay + 1;
    END LOOP;

    --selects all rows of the table into a cursor
    OPEN v_occupancyMap FOR
    SELECT * 
    FROM DailyOccupancyRate d
    ORDER BY d.day;

    --deletes all rows from the table
    prc_delete_daily_rate(0);

RETURN v_occupancyMap;
END;
/

--TEST
SET SERVEROUTPUT ON;
DECLARE
    v_day INTEGER;
    v_warehouseDailyRate NUMBER;
    v_portDailyRate NUMBER;
    v_occupancyMap SYS_REFCURSOR;
    
BEGIN
    v_occupancyMap := fnc_occupancy_rate_for_month(14635, '2021-12-01');
    LOOP
        FETCH v_occupancyMap INTO v_day, v_warehouseDailyRate, v_portDailyRate;
        EXIT WHEN v_occupancyMap%NOTFOUND;
        dbms_output.put_line('Day: ' || v_day);
        dbms_output.put_line('Warehouse rate: ' || v_warehouseDailyRate);
        dbms_output.put_line('Port rate: ' || v_portDailyRate);
        dbms_output.put_line('------------------');
    END LOOP;
    CLOSE v_occupancyMap;
END;