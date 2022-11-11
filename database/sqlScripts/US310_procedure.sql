CREATE OR REPLACE PROCEDURE prc_record_daily_rate(p_day INTEGER, p_warehouse_rate NUMBER, p_port_rate NUMBER)
AS
    pragma autonomous_transaction;
BEGIN
    INSERT INTO DailyOccupancyRate VALUES(p_day, p_warehouse_rate, p_port_rate);
    COMMIT;
END;
/



CREATE OR REPLACE PROCEDURE prc_delete_daily_rate(p_del INTEGER)
AS
    pragma autonomous_transaction;
BEGIN
    DELETE FROM DailyOccupancyRate;
    COMMIT;
END;
/