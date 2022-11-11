--TESTS
--------------------------------------------------------------
--US404
SET SERVEROUTPUT ON;
DECLARE
    v_ship         Ship.shipMMSI%type;
    v_days_idle    NUMBER;
    
    v_result        SYS_REFCURSOR;
    
BEGIN
    v_result := fnc_number_days_ship_idle_begining_year('FleetM1');
    LOOP
        FETCH v_result INTO v_ship, v_days_idle;
        EXIT WHEN v_result%NOTFOUND;
        dbms_output.put_line('Ship: ' || v_ship);
        dbms_output.put_line('Days idle: ' || v_days_idle);
        
        dbms_output.put_line('------------------');
    END LOOP;
    CLOSE v_result;
END;
/


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--****************************************************************************************************************************************************************************--
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT fnc_ship_occupancy_rate_given_moment(111111111, '2021-12-01 12:00:00') FROM DUAL;
--US405
SET SERVEROUTPUT ON;
DECLARE
    v_result        NUMBER;
    
BEGIN
    v_result := fnc_average_occupancy_period(111111111, '2021-12-01 06:00:00', '2021-12-05 12:00:00');
    dbms_output.put_line('Average Occupancy Rate: ' || v_result);  
END;
/

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--****************************************************************************************************************************************************************************--
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--US406
SET SERVEROUTPUT ON;
DECLARE
    v_port          ShipTripStop.currentPort%type;
    v_dateArr       shiptripstop.tripstopactualarrivaldate%type;
    v_dateDep       shiptripstop.tripstopdepartdate%type;
    
    v_result        SYS_REFCURSOR;
    
BEGIN
    v_result :=  fnc_ship_voyages_above_threshold(66.00);
    LOOP
        FETCH v_result INTO v_port, v_dateArr, v_dateDep;
        EXIT WHEN v_result%NOTFOUND;
        dbms_output.put_line('Port: ' || v_port);
        dbms_output.put_line('Arrival Date: ' || v_dateArr);
        dbms_output.put_line('Departure Date: ' || v_dateDep);
        
        dbms_output.put_line('------------------');
    END LOOP;
    CLOSE v_result;
END;
/

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--****************************************************************************************************************************************************************************--
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--US407
SET SERVEROUTPUT ON;
DECLARE
    v_date          TIMESTAMP;
    v_port          VARCHAR(20);
    v_warehouse     VARCHAR(20);
    v_cm            VARCHAR(20);
    v_cmtype        VARCHAR(30);
    v_transport     VARCHAR(30);
    v_count         INTEGER;
    v_x             INTEGER;
    v_y             INTEGER;
    v_z             INTEGER;
    v_container     VARCHAR(20);
    v_result        SYS_REFCURSOR;
    
BEGIN
    v_result := fnc_resource_map_next_week('PortM1', '2021-12-01 09:00:00');
    LOOP
        FETCH v_result INTO v_container, v_date, v_port, v_warehouse, v_cm, v_cmtype, v_transport, v_count, v_x, v_y, v_z;
        EXIT WHEN v_result%NOTFOUND;
        dbms_output.put_line('Date: ' || v_date);
        dbms_output.put_line('Port: ' || v_port);
        dbms_output.put_line('Warehouse: ' || v_warehouse);
        dbms_output.put_line('Cargo Manifest: ' || v_cm);
        dbms_output.put_line('Cargo Manifest Type: ' || v_cmtype);
        dbms_output.put_line('Transport: ' || v_transport);
        dbms_output.put_line('Container Count: ' || v_count);
        dbms_output.put_line('Container: ' || v_container);
        dbms_output.put_line('X: ' || v_x);
        dbms_output.put_line('Y: ' || v_y);
        dbms_output.put_line('Z: ' || v_z);
        dbms_output.put_line('------------------');
    END LOOP;
    CLOSE v_result;
END;
/

--test exception
SELECT fnc_resource_map_next_week('PortM2','2021-12-01 09:00:00') FROM DUAL;

