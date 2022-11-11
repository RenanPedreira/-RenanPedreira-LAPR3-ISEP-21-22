CREATE OR REPLACE TRIGGER trg_ship_trip_stop_report_create_entry
AFTER INSERT on ShipTripStop
FOR EACH ROW
DECLARE
    vTripStopReportID           tripstopreport.reportid%type;

BEGIN
    SELECT COUNT(*) INTO vTripStopReportID
    FROM TripStopReport;

    vTripStopReportID := vTripStopReportID+1;

    INSERT INTO TripStopReport VALUES(vTripStopReportID, :new.tripID, :new.currentPort, 'Ship', :new.tripStopActualArrivalDate, :new.tripStopDepartDate);

END;
/



CREATE OR REPLACE TRIGGER trg_truck_trip_stop_report_create_entry
AFTER INSERT on TruckTripStop
FOR EACH ROW
DECLARE
    vTripStopReportID           tripstopreport.reportid%type;

BEGIN
    SELECT COUNT(*) INTO vTripStopReportID
    FROM TripStopReport;

    vTripStopReportID := vTripStopReportID+1;

    INSERT INTO TripStopReport VALUES(vTripStopReportID, :new.tripID, :new.currentWarehouse, 'Truck', :new.tripStopActualArrivalDate, :new.tripStopDepartDate);

END;
/