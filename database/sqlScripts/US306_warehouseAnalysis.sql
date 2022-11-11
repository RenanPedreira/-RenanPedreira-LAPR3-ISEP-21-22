CREATE OR REPLACE FUNCTION fnc_warehouse_analysis(p_portID Port.portID%type)
RETURN SYS_REFCURSOR
AS
    v_result SYS_REFCURSOR;
    v_warehouse_list SYS_REFCURSOR;
    v_warehouseID Warehouse.warehouseID%type;
    v_warehouse_capacity INTEGER;
    v_rate NUMBER;
    v_containers_leaving INTEGER;
    v_count INTEGER;

BEGIN
    --List of warehouses
    OPEN v_warehouse_list FOR
    SELECT warehouseID
    FROM Warehouse w
    WHERE w.portID = p_portID;
    
    LOOP
        FETCH v_warehouse_list INTO v_warehouseID;

        --Warehouse max capacity
        SELECT w.warehouseDimensionX * w.warehouseDimensionY * w.warehouseDimensionZ INTO v_warehouse_capacity
        FROM Warehouse w
        WHERE w.warehouseID = v_warehouseID;
        
        --For each warehouse calculate ocupation
        SELECT fnc_daily_occupancy_rate(v_warehouseID) INTO v_rate FROM DUAL;

        --For each warehouse calculate containers leaving in the next 30 days
        SELECT COUNT(*) INTO v_containers_leaving
        FROM Stay s
        WHERE s.warehouseID = v_warehouseID 
        AND s.containerDepartureDate > SYSDATE
        AND s.containerDepartureDate < SYSDATE + 30;
    
        SELECT COUNT(*) INTO v_count
        FROM ContainersLeavingWarehouse30days 
        WHERE warehouseID = v_warehouseID;
    
        IF v_count = 0
        THEN prc_record_containers_leaving_next_30_days(v_warehouseID, v_rate, v_containers_leaving);
        END IF;
        
        EXIT WHEN v_warehouse_list%notfound;
    END LOOP;
    
    OPEN v_result FOR
    SELECT RECORDID, WAREHOUSEID ,OCCUPATIONRATE ,CONTAINERSLEAVING  
    FROM ContainersLeavingWarehouse30days;
    
    prc_delete_containers_leaving_next_30_days(0);

    RETURN v_result;
END;
/
