CREATE OR REPLACE FUNCTION fnc_warehouse_occupancy_rate_for_day(p_warehouseID Warehouse.warehouseID%type, p_day INTEGER, p_date DATE, p_WarehouseOccupancy INTEGER)
RETURN NUMBER
AS
    v_ContainerCount INTEGER;
    v_OccupancyRate NUMBER;

BEGIN
    SELECT COUNT (s.containerID) INTO v_ContainerCount
    FROM Stay s
    WHERE s.warehouseid = p_warehouseID AND EXTRACT (MONTH FROM s.containerarrivaldate)  <= EXTRACT (MONTH FROM TO_DATE(p_date)) 
                                        AND EXTRACT (YEAR FROM s.containerarrivaldate)   <= EXTRACT (YEAR FROM TO_DATE(p_date)) 
                                        AND EXTRACT (DAY FROM s.containerarrivaldate)    <=  p_day
                                        
                                        AND EXTRACT (MONTH FROM s.containerdeparturedate) >= EXTRACT (MONTH FROM TO_DATE(p_date)) 
                                        AND EXTRACT (YEAR FROM s.containerdeparturedate)  >= EXTRACT (YEAR FROM TO_DATE(p_date))
                                        AND EXTRACT (DAY FROM s.containerdeparturedate)   >  p_day;

    v_OccupancyRate := v_ContainerCount/p_WarehouseOccupancy;
        
RETURN v_OccupancyRate * 100;
END;
/

SELECT fnc_warehouse_occupancy_rate_for_day('Warehouse1', 19, '2020-12-01', 100) FROM DUAL;