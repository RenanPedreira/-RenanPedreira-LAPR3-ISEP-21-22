CREATE OR REPLACE PROCEDURE prc_record_containers_leaving_next_30_days(p_warehouseID Warehouse.warehouseID%type, p_warehouse_rate NUMBER, p_number INTEGER)
AS
    pragma autonomous_transaction;
    v_n INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_n
    FROM ContainersLeavingWarehouse30days;
    
    INSERT INTO ContainersLeavingWarehouse30days VALUES(v_n + 1, p_warehouseID, p_warehouse_rate, p_number);
    COMMIT;
END;
/



CREATE OR REPLACE PROCEDURE prc_delete_containers_leaving_next_30_days(p_del INTEGER)
AS
    pragma autonomous_transaction;
BEGIN
    DELETE FROM ContainersLeavingWarehouse30days;
    COMMIT;
END;
/