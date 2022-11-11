CREATE OR REPLACE FUNCTION fnc_resource_map_next_week(p_portManagerID Employee.employeeid%type, p_next_monday TIMESTAMP)
RETURN SYS_REFCURSOR

AS
    v_portCount INTEGER;
    v_ResourceMapResult SYS_REFCURSOR;
    
    v_number INTEGER;
    

BEGIN
    --Checks if port manager work in any port
    SELECT COUNT(*) INTO v_portCount
    FROM Port p
    WHERE p.portmanagerid = p_portManagerID;
    
    IF v_portCount = 0
        THEN raise_application_error(-20013, 'Port manager manages no port');
    END IF;

    SELECT fnc_resource_map_manifests(p_portManagerID, p_next_monday) INTO v_number FROM DUAL;
 
    --Gets the resource map
    OPEN v_ResourceMapResult FOR
    SELECT UNIQUE container, cargoDate, port, warehouse, manifest, manifestType, transport, containerCount, containerPositionX, containerPositionY, containerPositionZ
    FROM ResourceMap
    GROUP BY cargoDate, port, warehouse, manifest, manifestType, transport, containerCount, containerPositionX, containerPositionY, containerPositionZ, container
    ORDER BY cargoDate, port;
    
    prc_delete_resource_map(0);
    
    
    RETURN v_ResourceMapResult;
END;
/

SELECT fnc_resource_map_next_week('PortM1', '2021-12-01') FROM DUAL;
