CREATE OR REPLACE FUNCTION fnc_next_port_load(p_tripID ShipTripStop.tripID%type, p_tripStopArrivalPort shiptripstop.currentport%type) 
RETURN SYS_REFCURSOR
AS
    v_containerList SYS_REFCURSOR;  -- variável de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
    OPEN v_containerList FOR                                                        
    SELECT sts_next.currentPort NextPort, m.containerID ContainerID, c.containerTypeID ContainerType, c.containerPayload Payload, m.containerPositionX X, m.containerPositionY Y, m.containerPositionZ Z
    FROM ShipCargoManifest m, Container c, ShipTripStop sts_next
    WHERE m.cargoManifestID IN ( SELECT loadingCargoManifestID
                             FROM ShipTripStop sts_next
                             WHERE sts_next.tripID = p_tripID AND sts_next.tripStopEstimatedArrivalDate = ( SELECT MIN(sts_next.tripStopEstimatedArrivalDate)
                                                                                                            FROM ShipTripStop sts_next
                                                                                                            WHERE sts_next.tripStopEstimatedArrivalDate > ( SELECT tripStopEstimatedArrivalDate
                                                                                                                                                             FROM ShipTripStop sts_curr
                                                                                                                                                             WHERE sts_curr.tripID = p_tripID AND sts_curr.currentPort = p_tripStopArrivalPort)))
    AND c.containerID = m.containerID
    AND sts_next.loadingCargoManifestID = m.cargoManifestID;     
                             
    RETURN v_containerList;                                                                                                                                                                           
END;
       

--------
--TEST

--Expected: NEXTPORT=Port3, CONTAINERID=Cont5, CONTAINERTYPE=ContT2, PAYLOAD=1254, X=1, Y=4, Z=1
SELECT fnc_next_port_load('Trip1', '29002') FROM DUAL;