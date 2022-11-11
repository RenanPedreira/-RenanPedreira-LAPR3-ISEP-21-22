CREATE OR REPLACE FUNCTION fnc_get_audit_trail(p_containerID Container.containerID%type, p_cargoManifestID CargoManifest.cargoManifestID%type)
RETURN SYS_REFCURSOR
AS    
    v_auditTrail SYS_REFCURSOR;
    
BEGIN
    OPEN v_auditTrail FOR
        SELECT *
        FROM ContainerOperation co
        WHERE co.containerID = p_containerID AND co.cargoManifestID = p_cargoManifestID
        ORDER BY co.operationdatetime;

    RETURN v_auditTrail;

END;
/

SELECT fnc_get_audit_trail('Cont20', 'CargoManifest1') FROM DUAL;