CREATE OR REPLACE FUNCTION fnc_port_occupancy_rate_for_day(p_portID Port.portID%type, p_day INTEGER, p_date DATE, p_PortCapacity INTEGER)
RETURN NUMBER
AS
    v_ShipCount INTEGER;
    v_OccupancyRate NUMBER;
BEGIN


                                             
                                             
SELECT COUNT(*) INTO v_ShipCount
FROM ShipTripStop sts
WHERE    sts.currentport = p_portID 
AND EXTRACT (MONTH FROM  sts.tripstopactualarrivaldate) <= EXTRACT (MONTH FROM TO_DATE(p_date)) 
AND EXTRACT (YEAR FROM  sts.tripstopactualarrivaldate)  <= EXTRACT (YEAR FROM TO_DATE(p_date))
AND EXTRACT (DAY FROM  sts.tripstopactualarrivaldate)   <=  p_day

AND EXTRACT (MONTH FROM  sts.tripstopdepartdate) >= EXTRACT (MONTH FROM TO_DATE(p_date)) 
AND EXTRACT (YEAR FROM  sts.tripstopdepartdate)  >= EXTRACT (YEAR FROM TO_DATE(p_date))
AND EXTRACT (DAY FROM  sts.tripstopdepartdate)   >=  p_day;
                                                                            
v_OccupancyRate := v_ShipCount/p_PortCapacity;
        
RETURN v_OccupancyRate * 100;
                                             
END;
/

SELECT fnc_port_occupancy_rate_for_day(14635, 3, '2021-12-01', 100) FROM DUAL;