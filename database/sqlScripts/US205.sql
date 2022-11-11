CREATE OR REPLACE FUNCTION fnc_next_port_offload(p_tripID ShipTripStop.tripID%type, p_tripStopArrivalPort shiptripstop.currentport%type) 
RETURN SYS_REFCURSOR
AS
    v_containerList SYS_REFCURSOR;  -- variável de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
    OPEN v_containerList FOR                                                        
    SELECT sts_next.currentPort NextPort, m.containerID ContainerID, c.containerTypeID ContainerType, c.containerPayload Payload, m.containerPositionX X, m.containerPositionY Y, m.containerPositionZ Z
    FROM ShipCargoManifest m, Container c, ShipTripStop sts_next
    WHERE m.cargoManifestID IN ( SELECT unloadingCargoManifestID
                             FROM ShipTripStop sts_next
                             WHERE sts_next.tripID = p_tripID AND sts_next.tripStopEstimatedArrivalDate = ( SELECT MIN(sts_next.tripStopEstimatedArrivalDate)
                                                                                                            FROM ShipTripStop sts_next
                                                                                                            WHERE sts_next.tripStopEstimatedArrivalDate > ( SELECT tripStopEstimatedArrivalDate
                                                                                                                                                             FROM ShipTripStop sts_curr
                                                                                                                                                             WHERE sts_curr.tripID = p_tripID AND sts_curr.currentPort = p_tripStopArrivalPort)))
    AND c.containerID = m.containerID
    AND sts_next.unloadingCargoManifestID = m.cargoManifestID;     
                             
    RETURN v_containerList;                                                                                                                                                                           
END;
       

--------
--TEST

--Expected: NEXTPORT=Port3, CONTAINERID=Cont6, CONTAINERTYPE=ContT2, PAYLOAD=1255, X=3, Y=1, Z=7
SELECT fnc_next_port_offload('Trip1', '29002') FROM DUAL;
