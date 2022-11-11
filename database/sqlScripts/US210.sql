CREATE OR REPLACE FUNCTION fnc_available_ships_next_monday(p_next_monday TIMESTAMP) 
RETURN SYS_REFCURSOR
AS
    v_shipList SYS_REFCURSOR;  -- variável de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
    OPEN v_shipList FOR  
    SELECT s.shipMMSI, st1.tripEndingPort, st1.tripID
    FROM Ship s
    INNER JOIN ShipTrip st1 ON st1.shipMMSI = s.shipMMSI 
    AND st1.tripID = ( SELECT MAX(st2.tripID)
                       FROM ShipTrip st2
                       WHERE s.shipMMSI = st2.shipMMSI
                       AND st2.tripID IN ( SELECT(tripID)
                                           FROM Trip t
                                           WHERE t.tripEstimatedEndingDate < p_next_monday))
    GROUP BY s.shipMMSI, st1.tripEndingPort, st1.tripID
    ORDER BY 1 ASC;

    RETURN (v_shipList);                                                                                                                                                                           
END;

--------
--TEST

--Expected: SHIPMMSI=111111111, TRIPENDINGPORT= Port7, TRIPID=4
--          SHIPMMSI=222222222, TRIPENDINGPORT= Port4, TRIPID=2
SELECT fnc_available_ships_next_monday((SELECT NEXT_DAY(SYSDATE,'Segunda-Feira') "Next_Monday" FROM DUAL)) FROM DUAL;