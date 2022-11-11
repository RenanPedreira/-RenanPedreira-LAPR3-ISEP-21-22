CREATE OR REPLACE FUNCTION fnc_leased_container_route(p_clientID Lease.clientID%type, p_containerID Lease.containerID%type)
RETURN SYS_REFCURSOR
AS
    v_LeasedContainerCount INTEGER;
    
    v_GeneralRoute SYS_REFCURSOR;
    
    v_LeasingStartingDate DATE;
    v_LeasingEndingDate DATE;
    
    v_TripStopID    tripstopreport.tripid%type;
    v_TripStopDate  tripstopreport.tripstopreportarrivaldate%type;
    
BEGIN
    --Validates if the client has access to that container
    SELECT COUNT(*)
    INTO v_LeasedContainerCount
    FROM Lease l
    WHERE l.clientID = p_clientID AND  l.containerID = p_containerID;
    
    IF v_LeasedContainerCount = 0
        THEN raise_application_error(-20000, 'No permission');
    END IF;
    
     --Gets the time period in which the container was leased(Refers to the most recent leasing of that container by that client)
    
    --Start
    SELECT l.departureDate INTO v_LeasingStartingDate
    FROM Lease l
    WHERE l.clientId=p_clientID AND l.containerID=p_containerID  AND l.departureDate = (SELECT MAX(l.departureDate)
                                                                                        FROM Lease l
                                                                                        WHERE l.clientId=p_clientID AND l.containerID=p_containerID);
    --End
    SELECT l.estimatedArrivalDate INTO v_LeasingEndingDate
    FROM Lease l
    WHERE l.clientId=p_clientID AND l.containerID=p_containerID AND l.departureDate = (SELECT MAX(l.departureDate)
                                                                                        FROM Lease l
                                                                                        WHERE l.clientId=p_clientID AND l.containerID=p_containerID);
                                                                                        
                                                                                                                                                                         
    OPEN v_GeneralRoute FOR
    SELECT *
    FROM TripStopReport tsr
    WHERE tsr.tripID IN (SELECT sts.tripID
                         FROM ShipTripStop sts
                         WHERE sts.loadingCargoManifestID = ANY ( SELECT scm.cargoManifestID
                                                             FROM ShipCargoManifest scm
                                                             INNER JOIN CargoManifest cm ON scm.cargoManifestID = cm.cargoManifestID
                                                             INNER JOIN Lease l ON l.containerID = scm.containerID
                                                             WHERE scm.containerID = p_containerID
                                                             AND cm.arrivalDate >= l.departureDate 
                                                             AND cm.arrivalDate <= l.estimatedarrivaldate)
                        
                        OR sts.unloadingCargoManifestID = ANY ( SELECT scm.cargoManifestID
                                                             FROM ShipCargoManifest scm
                                                             INNER JOIN CargoManifest cm ON scm.cargoManifestID = cm.cargoManifestID
                                                             INNER JOIN Lease l ON l.containerID = scm.containerID
                                                             WHERE scm.containerID = p_containerID
                                                             AND cm.arrivalDate >= l.departureDate 
                                                             AND cm.arrivalDate <= l.estimatedarrivaldate)
                        
                        )
                        
    AND tsr.tripStopReportLocation IN (SELECT sts.currentPort
                         FROM ShipTripStop sts
                         WHERE sts.loadingCargoManifestID = ANY ( SELECT scm.cargoManifestID
                                                             FROM ShipCargoManifest scm
                                                             INNER JOIN CargoManifest cm ON scm.cargoManifestID = cm.cargoManifestID
                                                             INNER JOIN Lease l ON l.containerID = scm.containerID
                                                             WHERE scm.containerID = p_containerID
                                                             AND cm.arrivalDate >= l.departureDate 
                                                             AND cm.arrivalDate <= l.estimatedarrivaldate)
                        OR sts.unloadingCargoManifestID = ANY ( SELECT scm.cargoManifestID
                                                             FROM ShipCargoManifest scm
                                                             INNER JOIN CargoManifest cm ON scm.cargoManifestID = cm.cargoManifestID
                                                             INNER JOIN Lease l ON l.containerID = scm.containerID
                                                             WHERE scm.containerID = p_containerID
                                                             AND cm.arrivalDate >= l.departureDate 
                                                             AND cm.arrivalDate <= l.estimatedarrivaldate)
                                        
                        )
                        
                
        
        
                        
                        
    OR tsr.tripID in (SELECT tripID
                        FROM TruckTripStop tts
                        WHERE tts.loadingCargoManifestID = ANY ( SELECT tcm.cargoManifestID
                                                            FROM TruckCargoManifest tcm
                                                            INNER JOIN CargoManifest cm ON tcm.cargoManifestID = cm.cargoManifestID
                                                            INNER JOIN Lease l ON l.containerID = tcm.containerID
                                                            WHERE tcm.containerID = p_containerID
                                                            AND cm.arrivalDate >= l.departureDate 
                                                            AND cm.arrivalDate <= l.estimatedarrivaldate)
                                                            
                        OR tts.unloadingCargoManifestID = ANY ( SELECT tcm.cargoManifestID
                                                            FROM TruckCargoManifest tcm
                                                            INNER JOIN CargoManifest cm ON tcm.cargoManifestID = cm.cargoManifestID
                                                            INNER JOIN Lease l ON l.containerID = tcm.containerID
                                                            WHERE tcm.containerID = p_containerID
                                                            AND cm.arrivalDate >= l.departureDate 
                                                            AND cm.arrivalDate <= l.estimatedarrivaldate)
                    )
                    
    AND tsr.tripStopReportLocation in (SELECT currentWarehouse
                        FROM TruckTripStop tts
                        WHERE tts.loadingCargoManifestID = ANY ( SELECT tcm.cargoManifestID
                                                            FROM TruckCargoManifest tcm
                                                            INNER JOIN CargoManifest cm ON tcm.cargoManifestID = cm.cargoManifestID
                                                            INNER JOIN Lease l ON l.containerID = tcm.containerID
                                                            WHERE tcm.containerID = p_containerID
                                                            AND cm.arrivalDate >= l.departureDate 
                                                            AND cm.arrivalDate <= l.estimatedarrivaldate)
                                                            
                        OR tts.unloadingCargoManifestID = ANY ( SELECT tcm.cargoManifestID
                                                            FROM TruckCargoManifest tcm
                                                            INNER JOIN CargoManifest cm ON tcm.cargoManifestID = cm.cargoManifestID
                                                            INNER JOIN Lease l ON l.containerID = tcm.containerID
                                                            WHERE tcm.containerID = p_containerID
                                                            AND cm.arrivalDate >= l.departureDate 
                                                            AND cm.arrivalDate <= l.estimatedarrivaldate)
                    )
                    
                    
                    
    
    ORDER BY tsr.reportID;
    RETURN v_GeneralRoute;
                                                                                                                                                                                
END;

SELECT fnc_leased_container_route('Client4', 'Cont4') FROM DUAL;

SET SERVEROUTPUT ON;
DECLARE
    v_repID         TripStopReport.reportID%type;
    v_tripID        TripStopReport.tripID%type;
    v_location      TripStopReport.tripStopReportLocation%type;
    v_transport     TripStopReport.tripStopReportTransport%type;
    v_arr           TripStopReport.tripStopReportArrivalDate%type;
    v_dep           TripStopReport.tripStopReportDepartDate%type;
    v_result        SYS_REFCURSOR;

BEGIN
    v_result := fnc_leased_container_route('Client4', 'Cont4');
    LOOP
        FETCH v_result INTO v_repID, v_tripID, v_location, v_transport, v_arr, v_dep;
        EXIT WHEN v_result%NOTFOUND;
        dbms_output.put_line('Location: '  || v_location);
        dbms_output.put_line('Transport: '  || v_transport);
        dbms_output.put_line('Arrival Date: '  || v_arr);
        dbms_output.put_line('Departure Date: '  || v_dep);
        dbms_output.put_line('------------------');
    END LOOP;
    CLOSE v_result;
END;
