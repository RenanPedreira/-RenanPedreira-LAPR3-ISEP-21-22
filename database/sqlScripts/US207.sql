CREATE OR REPLACE FUNCTION fnc_count_cargo_manifest2(p_shipMMSI Ship.shipMMSI%type, p_ano INTEGER := EXTRACT(YEAR FROM SYSDATE))
RETURN SYS_REFCURSOR
AS
    v_cargomanifest SYS_REFCURSOR;  -- variavel de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
    OPEN v_cargomanifest FOR                                                        
     
     SELECT COUNT(cm.cargoManifestid) Manifests_Count, (SELECT COUNT(cm.cargoManifestid) / (SELECT COUNT(scm.cargoManifestid)
                                                        FROM shipcargomanifest scm
                                                        WHERE scm.shipmmsi = p_shipMMSI
                                                        AND scm.cargomanifestid IN (SELECT (cm.cargomanifestid)
                                                                                    FROM CargoManifest cm
                                                                                    WHERE EXTRACT (YEAR FROM cm.arrivalDate) = p_ano)) AVG

                                                                                    FROM cargomanifest cm
                                                                                    WHERE cm.cargomanifestid IN (SELECT (sc.cargomanifestid)
                                                                                                                 FROM ShipCargoManifest sc
                                                                                                                 WHERE sc.shipmmsi=p_shipMMSI)
                                                                                                                 AND EXTRACT (YEAR FROM cm.arrivalDate) = p_ano
                                                                                                                 GROUP BY 1
                                                        )Container_Avg

    FROM cargomanifest cm
    WHERE cm.cargomanifestid IN (SELECT (sc.cargomanifestid)
                             FROM ShipCargoManifest sc
                             WHERE sc.shipmmsi=p_shipMMSI)
    AND EXTRACT (YEAR FROM cm.arrivalDate) = p_ano
    GROUP BY 1;    
    
    RETURN v_cargomanifest;                     
END;

--Test

--Expected: MANIFEST_COUNT=6, CONTAINER_AVG=1
SELECT fnc_count_cargo_manifest2(111111111, 2020) FROM DUAL;