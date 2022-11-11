CREATE OR REPLACE FUNCTION fnc_daily_occupancy_rate(p_warehouseID Warehouse.warehouseID%type)
RETURN NUMBER
AS
    v_max_capacity NUMBER(8,5);
    v_container_count NUMBER(8,5);

BEGIN
    SELECT w.warehouseDimensionX * w.warehouseDimensionY * w.warehouseDimensionZ INTO v_max_capacity
    FROM Warehouse w
    WHERE w.warehouseID = p_warehouseID;

    SELECT COUNT(s.containerID) INTO v_container_count
    FROM Stay s
    WHERE s.containerArrivalDate <= SYSDATE AND s.containerDepartureDate >= SYSDATE AND s.warehouseID = p_warehouseID;

    RETURN (v_container_count * 100)/v_max_capacity;
END;
/