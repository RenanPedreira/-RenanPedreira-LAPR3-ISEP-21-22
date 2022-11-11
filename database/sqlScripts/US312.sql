CREATE OR REPLACE FUNCTION fnc_leased_container_situation(p_clientID Lease.clientID%type, p_containerID Lease.containerID%type)
RETURN SYS_REFCURSOR
AS
    v_LeasedContainerCount INTEGER;
    v_ContainerCount INTEGER;
    v_container_location SYS_REFCURSOR;
    
BEGIN
    --Validates if the container exists
    SELECT COUNT(*)
    INTO v_ContainerCount
    FROM Container c
    WHERE c.containerID = p_containerID;
    
    IF v_ContainerCount = 0
        THEN raise_application_error(-20010, ' invalid container id.');
    END IF;
    

    --Validates if the client has access to that container
    SELECT COUNT(*)
    INTO v_LeasedContainerCount
    FROM Lease l
    WHERE l.clientID = p_clientID AND  l.containerID = p_containerID;
    
    IF v_LeasedContainerCount = 0
        THEN raise_application_error(-20011, 'container is not leased by client.');
    END IF;
    
    SELECT fnc_get_container_location(p_containerID) INTO v_container_location FROM DUAL ;
    RETURN v_container_location;
    
END;
/

--Container does not exist
SELECT fnc_leased_container_situation('Client7', 'Cont1') FROM DUAL;

--Container not leased
SELECT fnc_leased_container_situation('Client7', 'Cont1') FROM DUAL;

--Container exists and is leased
SELECT fnc_leased_container_situation('Client1', 'Cont1') FROM DUAL;
