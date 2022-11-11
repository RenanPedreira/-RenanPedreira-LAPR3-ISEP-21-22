--DROP TABLES

--Actors
DROP TABLE Client                       CASCADE CONSTRAINTS PURGE;
DROP TABLE FleetManager                 CASCADE CONSTRAINTS PURGE;
DROP TABLE TrafficManager               CASCADE CONSTRAINTS PURGE;
DROP TABLE WarehouseManager             CASCADE CONSTRAINTS PURGE;
DROP TABLE PortManager                  CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipCaptain                  CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipChiefElectricalEngineer  CASCADE CONSTRAINTS PURGE;
DROP TABLE TruckDriver                  CASCADE CONSTRAINTS PURGE;
DROP TABLE PortStaff                    CASCADE CONSTRAINTS PURGE;
DROP TABLE WarehouseStaff               CASCADE CONSTRAINTS PURGE;
DROP TABLE Employee                     CASCADE CONSTRAINTS PURGE;
DROP TABLE Auth                         CASCADE CONSTRAINTS PURGE;

--Locations
DROP TABLE Continent                    CASCADE CONSTRAINTS PURGE;
DROP TABLE Country                      CASCADE CONSTRAINTS PURGE;
DROP TABLE Border                       CASCADE CONSTRAINTS PURGE;
DROP TABLE Port                         CASCADE CONSTRAINTS PURGE;
DROP TABLE Seadist                      CASCADE CONSTRAINTS PURGE;
DROP TABLE Warehouse                    CASCADE CONSTRAINTS PURGE;
DROP TABLE Location                     CASCADE CONSTRAINTS PURGE;

--Transportation
DROP TABLE Ship                         CASCADE CONSTRAINTS PURGE;
DROP TABLE Generator                    CASCADE CONSTRAINTS PURGE;
DROP TABLE VesselType                   CASCADE CONSTRAINTS PURGE;
DROP TABLE Truck                        CASCADE CONSTRAINTS PURGE;

--Container
DROP TABLE CargoManifest                CASCADE CONSTRAINTS PURGE;
DROP TABLE CargoManifestType            CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipCargoManifest            CASCADE CONSTRAINTS PURGE;
DROP TABLE TruckCargoManifest           CASCADE CONSTRAINTS PURGE;
DROP TABLE Container                    CASCADE CONSTRAINTS PURGE;
DROP TABLE ContainerType                CASCADE CONSTRAINTS PURGE;
DROP TABLE Lease                        CASCADE CONSTRAINTS PURGE;
DROP TABLE Stay                         CASCADE CONSTRAINTS PURGE;

--Trip
DROP TABLE Trip                         CASCADE CONSTRAINTS PURGE;
DROP TABLE Crew                         CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipTrip                     CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipTripStop                 CASCADE CONSTRAINTS PURGE;
DROP TABLE TruckTrip                    CASCADE CONSTRAINTS PURGE;
DROP TABLE TruckTripStop                CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipPositioning              CASCADE CONSTRAINTS PURGE;
DROP TABLE TransceiverClass             CASCADE CONSTRAINTS PURGE;

--Auxiliary Tables
DROP TABLE ContainerOperation           CASCADE CONSTRAINTS PURGE;
DROP TABLE TripStopReport               CASCADE CONSTRAINTS PURGE;
DROP TABLE DailyOccupancyRate           CASCADE CONSTRAINTS PURGE;
DROP TABLE ContainersLeavingWarehouse30days  CASCADE CONSTRAINTS PURGE;
DROP TABLE ResourceMap                  CASCADE CONSTRAINTS PURGE;
DROP TABLE GravityCenter                CASCADE CONSTRAINTS PURGE;


--CREATE TABLES-----------------------------------------------------------------

--Actors
CREATE TABLE Auth(
    authEmail            VARCHAR(50)     CONSTRAINT pkAuthAuthEmail PRIMARY KEY,
    authPassword         VARCHAR(20)     CONSTRAINT nnAuthAuthPassword NOT NULL
);

CREATE TABLE Employee(
    employeeID           VARCHAR(20)     CONSTRAINT pkEmployeeEmployeeID PRIMARY KEY,
    employeeEmail        VARCHAR(50),
    employeeName         VARCHAR(50)
);

CREATE TABLE Client(
    clientID            VARCHAR(20)     CONSTRAINT pkClientClientID PRIMARY KEY,
    clientEmail         VARCHAR(20)
);

CREATE TABLE FleetManager(
    fleetManagerID      VARCHAR(20)     CONSTRAINT pkFleetManagerFleetManagerID PRIMARY KEY
);

CREATE TABLE TrafficManager(
    trafficManagerID    VARCHAR(20)     CONSTRAINT pkTrafficManagerTrafficManagerID PRIMARY KEY
);

CREATE TABLE WarehouseManager(
    warehouseManagerID  VARCHAR(20)     CONSTRAINT pkWarehouseManagerWarehouseManagerID PRIMARY KEY
);

CREATE TABLE PortManager(
    portManagerID       VARCHAR(20)     CONSTRAINT pkPortManagerPortManagerID PRIMARY KEY
);

CREATE TABLE ShipCaptain(
    shipCaptainID       VARCHAR(20)     CONSTRAINT pkShipCaptainShipCaptainID PRIMARY KEY
);

CREATE TABLE ShipChiefElectricalEngineer(
    shipChiefID         VARCHAR(20)     CONSTRAINT pkShipChiefElectricalEngineerShipChiefID PRIMARY KEY
);

CREATE TABLE TruckDriver(
    truckDriverID       VARCHAR(20)     CONSTRAINT pkTruckDriverTruckDriverID PRIMARY KEY
);

CREATE TABLE PortStaff(
    portStaffID         VARCHAR(20)     CONSTRAINT pkPortStaffPortStaffPortStaffID PRIMARY KEY,
    portID              VARCHAR(20)   
);

CREATE TABLE WarehouseStaff(
    warehouseStaffID    VARCHAR(20)     CONSTRAINT pkWarehouseStaffWarehouseStaffWarehouseStaffID PRIMARY KEY,
    warehouseID         VARCHAR(20)
);



--Location
CREATE TABLE Continent (
    continentID         VARCHAR(10)     CONSTRAINT pkContinentContinentID PRIMARY KEY
);

CREATE TABLE Country (
    countryID           VARCHAR(30)     CONSTRAINT pkCountryCountryID PRIMARY KEY,
    countryAlpha2Code   VARCHAR(2)      CONSTRAINT nnCountryCountryAlpha2Code NOT NULL,
    countryAlpha3Code   VARCHAR(3)      CONSTRAINT nnCountryCountryAlpha3Code NOT NULL,
    countryCapital      VARCHAR(30)     CONSTRAINT nnCountryCountryCapital NOT NULL,
    countryPopulation   NUMERIC(8,5)    CONSTRAINT nnCountryCountryPopulation NOT NULL, 
    countryLatitude     NUMERIC(12,10)   CONSTRAINT ckCountryCountryLatitude CHECK (countryLatitude BETWEEN -90 AND 90),
    countryLongitude    NUMERIC(13,10)   CONSTRAINT ckCountryCountryLongitude CHECK (countryLongitude BETWEEN -180 AND 180),
    continentID         VARCHAR(10)
);

CREATE TABLE Border (
    countryID1                  VARCHAR(30),
    countryID2                  VARCHAR(30),
    CONSTRAINT pkBorder         PRIMARY KEY(countryID1, countryID2)
);

CREATE TABLE Port (
    portID                      VARCHAR(20)      CONSTRAINT pkPortPortID PRIMARY KEY,
    portName                    VARCHAR(30)      CONSTRAINT nnPortPortName NOT NULL,
    portLatitude                NUMERIC(7,5)     CONSTRAINT ckPortPortLatitude CHECK (portLatitude BETWEEN -90 AND 90),
    portLongitude               NUMERIC(8,5)     CONSTRAINT ckPortPortLongitude CHECK (portLongitude BETWEEN -180 AND 180),    
    portDimensionX              INT              CONSTRAINT nnPortPortDimensionX NOT NULL,
    portDimensionY              INT              CONSTRAINT nnPortPortDimensionY NOT NULL,
    portDimensionZ              INT              CONSTRAINT nnPortPortDimensionZ NOT NULL,
    portManagerID               VARCHAR(20),
    countryID                   VARCHAR(30)
);

CREATE TABLE Seadist (
    fromPortID                  VARCHAR(20),
    toPortID                    VARCHAR(20),
    seadistDistance             NUMERIC (9,5),
    CONSTRAINT pkSeadistID PRIMARY KEY(fromPortID,toPortID)  
);

CREATE TABLE Warehouse (
    warehouseID                 VARCHAR(20)      CONSTRAINT pkWarehouseWarehouseID PRIMARY KEY,
    warehouseName               VARCHAR(20)      CONSTRAINT nnWarehouseWarehouseName NOT NULL,
    warehouseDimensionX         INT              CONSTRAINT nnWarehouseWarehouseDimensionX NOT NULL,
    warehouseDimensionY         INT              CONSTRAINT nnWarehouseWarehouseDimensionY NOT NULL,
    warehouseDimensionZ         INT              CONSTRAINT nnWarehouseWarehouseDimensionZ NOT NULL,    
    warehouseManagerID          VARCHAR(20),
    portID                      VARCHAR(20)
);


--Transportation
CREATE TABLE Ship (
    shipMMSI                        NUMBER(9,0)     CONSTRAINT pkShipShipMMSI               PRIMARY KEY,
    shipIMO                         VARCHAR(10)     CONSTRAINT nnShipShipIMO                UNIQUE NOT NULL,
    shipCallSign                    VARCHAR(20)     CONSTRAINT nnShipShipCallSign           UNIQUE NOT NULL,
    shipName                        VARCHAR(50)     CONSTRAINT nnShipShipName               NOT NULL,
    shipEnergyGenerators            INT             CONSTRAINT nnShipShipEnergyGenerators   NOT NULL,
    shipLength                      NUMERIC(5,2)    CONSTRAINT nnShipShipLength             NOT NULL,
    shipWidth                       NUMERIC(5,2)    CONSTRAINT nnShipShipWidth              NOT NULL,
    shipCapacity                    NUMERIC(5,2)    CONSTRAINT nnShipShipCapacity           NOT NULL,
    shipDraft                       NUMERIC(5,2)    CONSTRAINT nnShipShipDraft              NOT NULL,
    shipDimensionX                  INT             CONSTRAINT nnShipShipDimensionX         NOT NULL,
    shipDimensionY                  INT             CONSTRAINT nnShipShipDimensionY         NOT NULL,
    shipDimensionZ                  INT             CONSTRAINT nnShipShipDimensionZ         NOT NULL,
    vesselTypeCode                  VARCHAR(20),
    fleetManagerID                  VARCHAR(20),
    generatorID                     VARCHAR(20)
);

CREATE TABLE Generator (
    generatorID                     VARCHAR(20)     CONSTRAINT pkGeneratorGeneratorID       PRIMARY KEY,
    generatorPower                  INT             CONSTRAINT nnGeneratorPower             NOT NULL
);

CREATE TABLE VesselType (
    vesselTypeCode                  VARCHAR(20)     CONSTRAINT pkVesselTypeVesselTypeCode   PRIMARY KEY,
    vesselTypeNumber                INTEGER         CONSTRAINT nnVesselTypeVesselTypeNumber NOT NULL,
    vesselTypeName                  VARCHAR(20)     CONSTRAINT nnVesselTypeVesselTypeName   NOT NULL,
    vesselTypeLength                NUMBER          CONSTRAINT nnVesselTypeVesselTypeLength NOT NULL,
    vesselTypeWidth                 NUMBER          CONSTRAINT nnVesselTypeVesselTypeWidth  NOT NULL
);

CREATE TABLE Truck (
    truckID                         VARCHAR(20)     CONSTRAINT pkTruckTruckCode             PRIMARY KEY,
    fleetManagerID                  VARCHAR(20)
);



--Container
CREATE TABLE CargoManifest (
    cargoManifestID         VARCHAR(20)         CONSTRAINT pkCargoManifestCargoManifestID PRIMARY KEY,
    arrivalDate             TIMESTAMP           CONSTRAINT nnCargoManifestArrivalDate NOT NULL,
    cargoManifestTypeID     VARCHAR(20)
);

CREATE TABLE CargoManifestType (
    cargoManifestTypeID     VARCHAR(20)         CONSTRAINT pkCargoManifestTypeCargoManifestTypeID PRIMARY KEY
);

CREATE TABLE ShipCargoManifest (
    cargoManifestID             VARCHAR(20),
    containerID                 VARCHAR(20),
    containerPositionX          INTEGER         CONSTRAINT nnShipCargoManifestContainerPositionX NOT NULL,
    containerPositionY          INTEGER         CONSTRAINT nnShipCargoManifestContainerPositionY NOT NULL,
    containerPositionZ          INTEGER         CONSTRAINT nnShipCargoManifestContainerPositionZ NOT NULL,
    shipMMSI                    NUMBER(9,0),
    CONSTRAINT pkShipCargoManifest PRIMARY KEY(cargoManifestID, containerID) 
);

CREATE TABLE TruckCargoManifest (
    cargoManifestID             VARCHAR(20),
    containerID                 VARCHAR(20),
    containerPositionX          INTEGER        CONSTRAINT nnTruckCargoManifestContainerPositionX NOT NULL,
    containerPositionY          INTEGER        CONSTRAINT nnTruckCargoManifestContainerPositionY NOT NULL,
    containerPositionZ          INTEGER        CONSTRAINT nnTruckCargoManifestContainerPositionZ NOT NULL,
    truckID                     VARCHAR(20),
    CONSTRAINT pkTruckCargoManifest PRIMARY KEY(cargoManifestID, containerID)
);

CREATE TABLE Container (
    containerID               VARCHAR(20)       CONSTRAINT pkContainerContainerID PRIMARY KEY,
    containerPayload          INTEGER           CONSTRAINT nnContainerCountainerPayload NOT NULL,
    containerTare             INTEGER           CONSTRAINT nnContainerContainerTare NOT NULL,
    containerGross            INTEGER           CONSTRAINT nnContainerContainerGross NOT NULL,
    containerISOCode          VARCHAR(4)        CONSTRAINT nnContainerContainerISOcode NOT NULL,
    containerLength           NUMBER            CONSTRAINT nnContainerContainerLength NOT NULL,
    containerWidth            NUMBER            CONSTRAINT nnContainerContainerWidth NOT NULL,
    containerTypeID           VARCHAR(20)
);

CREATE TABLE ContainerType (
    containerTypeID           VARCHAR(20)       CONSTRAINT pkContainerTypeContainerTypeID PRIMARY KEY,
    maxTemperature            INTEGER           CONSTRAINT nnContainerTypeMaxTemperature NOT NULL
);

CREATE TABLE Lease(
    leaseID                   VARCHAR(20)       CONSTRAINT pkLeaseLeaseID PRIMARY KEY,
    goodID                    VARCHAR(20)       CONSTRAINT nnLeaseGoodID NOT NULL,
    departureDate             TIMESTAMP         CONSTRAINT nnLeaseDepartureDateID NOT NULL,
    estimatedArrivalDate      TIMESTAMP         CONSTRAINT nnLeaseEstimatedArrivalDateID NOT NULL,
    clientID                  VARCHAR(20),
    containerID               VARCHAR(20)
    
);

CREATE TABLE Stay (
    containerID                     VARCHAR(20),
    warehouseID                     VARCHAR(20),
    containerArrivalDate            TIMESTAMP,
    containerDepartureDate          TIMESTAMP       CONSTRAINT nnContainerDepartureDate NOT NULL,
    CONSTRAINT pkStay     PRIMARY KEY(containerID, warehouseID, containerArrivalDate)
);


--Trip
CREATE TABLE Trip (
    tripID                      VARCHAR(20)      CONSTRAINT pkTripTripID PRIMARY KEY,
    tripStartDate               TIMESTAMP        CONSTRAINT nnTripTripStartDate NOT NULL,
    tripEstimatedEndingDate     TIMESTAMP        CONSTRAINT nnTripTripEstimatedEndingDate NOT NULL,
    tripActualEndingDate        TIMESTAMP        CONSTRAINT nnTripTripActualEndingDate NOT NULL
);


CREATE TABLE Crew (
    crewID                      VARCHAR(20)     CONSTRAINT pkCrewCrewID PRIMARY KEY,
    shipCaptainID               VARCHAR(20),
    shipChiefID                 VARCHAR(20)
);


CREATE TABLE ShipTrip (
    tripID                     VARCHAR(20)       CONSTRAINT pkShipTripTripID PRIMARY KEY,                  
    tripStartingPort           VARCHAR(20),
    tripEndingPort             VARCHAR(20), 
    shipMMSI                   NUMBER(9,0),
    crewID                     VARCHAR(20)
);

CREATE TABLE TransceiverClass (
    transceiverClassID      VARCHAR(20)         CONSTRAINT pkTransceiverClassTransceiverClassID PRIMARY KEY
);

CREATE TABLE ShipPositioning (
    baseDateTime                TIMESTAMP,  
    tripID                      VARCHAR(20),
    shipLatitude                NUMERIC(7,5)   CONSTRAINT ckShipPositioningShipLatitude CHECK (shipLatitude BETWEEN -90 AND 90),
    shipLongitude               NUMERIC(8,5)   CONSTRAINT ckShipPositioningShipLongitude CHECK (shipLongitude BETWEEN -180 AND 180),
    shipSOG                     NUMERIC(8,5)   CONSTRAINT nnShipPositioningShipSOG NOT NULL,
    shipCOG                     NUMERIC(8,5)   CONSTRAINT nnShipPositioningShipCOG CHECK (shipCOG BETWEEN 0 AND 359),
    shipHeading                 NUMERIC(8,5)   CONSTRAINT ckShipPositioningShipHeading CHECK (shipHeading BETWEEN 0 AND 359 or shipHeading = 511),
    shipPosition                INTEGER        CONSTRAINT nnShipPositioningShipPosition NOT NULL,
    shipTransceiverClassID      VARCHAR(20),
    trafficManagerID            VARCHAR(20),
    CONSTRAINT pkShipPositioning        PRIMARY KEY (baseDateTime, tripID)
);


CREATE TABLE TruckTrip (
    tripID                     VARCHAR(20)       CONSTRAINT pkTruckTripTripID PRIMARY KEY,                  
    tripStartingWarehouseID    VARCHAR(20),
    tripEndingWarehouseID      VARCHAR(20), 
    truckID                    VARCHAR(20), 
    truckDriverID              VARCHAR(20)
);


CREATE TABLE ShipTripStop (
    tripID                          VARCHAR(20),
    currentPort                     VARCHAR(20),
    tripStopEstimatedArrivalDate    TIMESTAMP        CONSTRAINT nnShipTripStopEstimatedArrivalDate NOT NULL,
    tripStopActualArrivalDate       TIMESTAMP        CONSTRAINT nnShipTripStopActualArrivalDate NOT NULL,
    tripStopDepartDate              TIMESTAMP        CONSTRAINT nnShipTripStopDepartDate NOT NULL,
    loadingCargoManifestID          VARCHAR(20),
    unloadingCargoManifestID        VARCHAR(20),
    CONSTRAINT pkShipTripStop       PRIMARY KEY(tripID, currentPort)
);

CREATE TABLE TruckTripStop (
    tripID                          VARCHAR(20),
    currentWarehouse                VARCHAR(20),
    tripStopEstimatedArrivalDate    TIMESTAMP        CONSTRAINT nnTruckTripStopEstimatedArrivalDate NOT NULL,
    tripStopActualArrivalDate       TIMESTAMP        CONSTRAINT nnTruckTripStopActualArrivalDate NOT NULL,
    tripStopDepartDate              TIMESTAMP        CONSTRAINT nnTruckTripStopDepartDate NOT NULL,
    loadingCargoManifestID          VARCHAR(20),
    unloadingCargoManifestID        VARCHAR(20),
    CONSTRAINT pkTruckTripStop      PRIMARY KEY(tripID, currentWarehouse)
);

--US304
CREATE TABLE ContainerOperation (
    operationID                     INTEGER             CONSTRAINT pkContainerOperationOperationID PRIMARY KEY,
    userID                          VARCHAR(20)         CONSTRAINT nnContainerOperationUserID NOT NULL,
    operationDateTime               TIMESTAMP           CONSTRAINT nnContainerOperationOperationDateTime NOT NULL,
    operationType                   VARCHAR(20)         CONSTRAINT nnContainerOperationOperationType NOT NULL,
    containerID                     VARCHAR(20),
    cargoManifestID                 VARCHAR(20)  
);

--US305
CREATE TABLE TripStopReport (
    reportID                        INTEGER             CONSTRAINT pkTripStopReportID PRIMARY KEY,
    tripID                          VARCHAR(20)         CONSTRAINT nnTripStopReportTripID NOT NULL,
    tripStopReportLocation          VARCHAR(20)         CONSTRAINT nnTripStopReportTripStopReportLocation NOT NULL,
    tripStopReportTransport         VARCHAR(20)         CONSTRAINT nnTripStopReportTripStopReportTransport NOT NULL,
    tripStopReportArrivalDate       TIMESTAMP           CONSTRAINT nnTripStopReportTripStopReportArrivalDate NOT NULL,
    tripStopReportDepartDate        TIMESTAMP           CONSTRAINT nnTripStopReportTripStopReportDepartDate NOT NULL
);

--US306
CREATE TABLE ContainersLeavingWarehouse30days (
    recordID                        INTEGER           CONSTRAINT pkContainersLeavingWarehouse30daysRecordID PRIMARY KEY,
    warehouseID                     VARCHAR(20)       CONSTRAINT nnContainersLeavingWarehouse30daysWarehouseID NOT NULL,
    occupationRate                  NUMERIC(8,5)      CONSTRAINT nnContainersLeavingWarehouse30daysOccupationRate NOT NULL,
    containersLeaving               INTEGER           CONSTRAINT nnContainersLeavingWarehouse30daysContainersLeaving NOT NULL
);

--US310
CREATE TABLE DailyOccupancyRate (
    day                             INTEGER            CONSTRAINT pkDailyOccupancyRateDay PRIMARY KEY,
    warehouseRate                   NUMERIC(8,5)       CONSTRAINT nnDailyOccupancyRateWarehouseRate NOT NULL,
    portRate                        NUMERIC(8,5)       CONSTRAINT nnDailyOccupancyRatePortRate NOT NULL
);



--US407
CREATE TABLE ResourceMap (
    id                              INTEGER             CONSTRAINT pkResourceMapID  PRIMARY KEY,
    cargoDate                       TIMESTAMP           CONSTRAINT nnResourceMapCargoDate NOT NULL,  
    port                            VARCHAR(20)         CONSTRAINT nnResourceMapPort NOT NULL,        
    warehouse                       VARCHAR(20),
    manifest                        VARCHAR(20)         CONSTRAINT nnResourceMapManifest NOT NULL,
    manifesttype                    VARCHAR(30)         CONSTRAINT nnResourceMapManifestType NOT NULL,
    transport                       VARCHAR(30)         CONSTRAINT nnResourceMapTransport NOT NULL,
    containerCount                  INTEGER             CONSTRAINT nnResourceMapWarehouseStaffCount NOT NULL,
    containerPositionX              INTEGER,
    containerPositionY              INTEGER,
    containerPositionZ              INTEGER,
    container                       VARCHAR(20)         CONSTRAINT nnResourceMapContainer NOT NULL  
);

--US417
CREATE TABLE GravityCenter (
    cargoManifestID                 VARCHAR(20)         CONSTRAINT pkGravityCenterCargoManifestID PRIMARY KEY,
    centerOfMassX                   NUMBER              CONSTRAINT nnGravityCenterCenterOfMassX NOT NULL,
    centerOfMassY                   NUMBER              CONSTRAINT nnGravityCenterCenterOfMassY NOT NULL,
    centerOfMassZ                   NUMBER              CONSTRAINT nnGravityCenterCenterOfMassZ NOT NULL
);

--ALTER TABLES------------------------------------------------------------------

--Actors
ALTER TABLE Employee                    ADD CONSTRAINT  fkEmployeeEmployeeEmail                 FOREIGN KEY (employeeEmail)             REFERENCES Auth(authEmail);
ALTER TABLE Client                      ADD CONSTRAINT  fkClientClientEmail                     FOREIGN KEY (clientEmail)               REFERENCES Auth(authEmail);
ALTER TABLE FleetManager                ADD CONSTRAINT  fkFleetManagerFleetManagerID            FOREIGN KEY (fleetManagerID)            REFERENCES Employee(employeeID);
ALTER TABLE TrafficManager              ADD CONSTRAINT  fkTrafficManagerTrafficManagerID        FOREIGN KEY (trafficManagerID)          REFERENCES Employee(employeeID);
ALTER TABLE WarehouseManager            ADD CONSTRAINT  fkWarehouseManagerWarehouseManagerID    FOREIGN KEY (warehouseManagerID)        REFERENCES Employee(employeeID);
ALTER TABLE PortManager                 ADD CONSTRAINT  fkPortManagerPortManagerID              FOREIGN KEY (portManagerID)             REFERENCES Employee(employeeID);
ALTER TABLE ShipCaptain                 ADD CONSTRAINT  fkShipCaptainShipCaptainID              FOREIGN KEY (shipCaptainID)             REFERENCES Employee(employeeID);
ALTER TABLE ShipChiefElectricalEngineer ADD CONSTRAINT  fkShipChiefShipChiefID                  FOREIGN KEY (shipChiefID)               REFERENCES Employee(employeeID);
ALTER TABLE TruckDriver                 ADD CONSTRAINT  fkTruckDriverTruckDriverID              FOREIGN KEY (truckDriverID)             REFERENCES Employee(employeeID);
ALTER TABLE PortStaff                   ADD CONSTRAINT  fkPortStaffPortStaffID                  FOREIGN KEY (portStaffID)               REFERENCES Employee(employeeID);
ALTER TABLE WarehouseStaff              ADD CONSTRAINT  fkWarehouseStaffWarehouseStaffID        FOREIGN KEY (warehouseStaffID)          REFERENCES Employee(employeeID);
ALTER TABLE PortStaff                   ADD CONSTRAINT fkPortStaffPortID                        FOREIGN KEY (portID)                    REFERENCES Port(portID);
ALTER TABLE WarehouseStaff              ADD CONSTRAINT fkWarehouseStaffWarehouseID              FOREIGN KEY (warehouseID)               REFERENCES Warehouse(warehouseID);



--Location
ALTER TABLE Country                     ADD CONSTRAINT fkCountryContinentID                     FOREIGN KEY (continentID)               REFERENCES Continent(continentID);

ALTER TABLE Port                        ADD CONSTRAINT fkPortCountryID                          FOREIGN KEY (countryID)                 REFERENCES Country(countryID);
ALTER TABLE Port                        ADD CONSTRAINT fkPortPortManagerID                      FOREIGN KEY (portManagerID)             REFERENCES PortManager(portManagerID);

ALTER TABLE Seadist                     ADD CONSTRAINT fkSeadistFromPortID                      FOREIGN KEY (fromPortID)                REFERENCES Port(portID);
ALTER TABLE Seadist                     ADD CONSTRAINT fkSeadistToPortID                        FOREIGN KEY (toPortID)                  REFERENCES Port(portID);

ALTER TABLE Warehouse                   ADD CONSTRAINT fkWarehouseWarehousemanagerID            FOREIGN KEY (warehouseManagerID)        REFERENCES WarehouseManager(warehouseManagerID);
ALTER TABLE Warehouse                   ADD CONSTRAINT fkWarehousePortID                        FOREIGN KEY (portID)                    REFERENCES Port(portID);

ALTER TABLE Border                      ADD CONSTRAINT fkBorderCountryID1                       FOREIGN KEY (countryID1)                REFERENCES Country(countryID);   
ALTER TABLE Border                      ADD CONSTRAINT fkBorderCountryID2                       FOREIGN KEY (countryID2)                REFERENCES Country(countryID);



--Transportation
ALTER TABLE Ship                        ADD CONSTRAINT  fkShipVesselCode                        FOREIGN KEY (vesselTypeCode)            REFERENCES VesselType(vesselTypeCode);
ALTER TABLE Ship                        ADD CONSTRAINT  fkShipFleetManager                      FOREIGN KEY (fleetManagerID)            REFERENCES FleetManager(fleetManagerID);
ALTER TABLE Ship                        ADD CONSTRAINT  fkShipGeneratorID                       FOREIGN KEY (generatorID)               REFERENCES Generator(generatorID);

ALTER TABLE Truck                       ADD CONSTRAINT  fkTruckFleetManager                     FOREIGN KEY (fleetManagerID)            REFERENCES FleetManager(fleetManagerID);



--Container
ALTER TABLE CargoManifest               ADD CONSTRAINT fkCargoManifestCargoManifestType         FOREIGN KEY (cargoManifestTypeID)       REFERENCES CargoManifestType(cargoManifestTypeID); 

ALTER TABLE ShipCargoManifest           ADD CONSTRAINT fkShipCargoManifestContainerID           FOREIGN KEY (containerID)               REFERENCES Container(containerID);
ALTER TABLE ShipCargoManifest           ADD CONSTRAINT fkShipCargoManifestShipID                FOREIGN KEY (shipMMSI)                  REFERENCES Ship(shipMMSI);
ALTER TABLE ShipCargoManifest           ADD CONSTRAINT fkShipCargoManifestCargoManifestID       FOREIGN KEY (cargoManifestID)           REFERENCES CargoManifest(CargoManifestID);

ALTER TABLE TruckCargoManifest          ADD CONSTRAINT fkTruckCargoManifestTruckID              FOREIGN KEY (truckID)                   REFERENCES Truck(truckID);
ALTER TABLE TruckCargoManifest          ADD CONSTRAINT fkTruckCargoManifestContainerID          FOREIGN KEY (containerID)               REFERENCES Container(containerID);
ALTER TABLE TruckCargoManifest          ADD CONSTRAINT fkTruckCargoManifestCargoManifestID      FOREIGN KEY (cargoManifestID)           REFERENCES CargoManifest(CargoManifestID);

ALTER TABLE Container                   ADD CONSTRAINT fkContainerContainerTypeID               FOREIGN KEY (containerTypeID)           REFERENCES ContainerType(containerTypeID);

ALTER TABLE Lease                       ADD CONSTRAINT fkLeaseContainerID                       FOREIGN KEY (containerID)               REFERENCES Container(containerID);
ALTER TABLE Lease                       ADD CONSTRAINT fkLeaseClientID                          FOREIGN KEY (clientID)                  REFERENCES Client(clientID);

ALTER TABLE Stay                        ADD CONSTRAINT fkStayContainerID                        FOREIGN KEY (containerID)               REFERENCES Container(containerID);
ALTER TABLE Stay                        ADD CONSTRAINT fkStayWarehouseID                        FOREIGN KEY (warehouseID)               REFERENCES Warehouse(warehouseID);

--Trip
ALTER TABLE Crew                        ADD CONSTRAINT  fkCrewShipCaptainID                     FOREIGN KEY (shipCaptainID)             REFERENCES ShipCaptain(shipCaptainID);
ALTER TABLE Crew                        ADD CONSTRAINT  fkCrewShipChiefID                       FOREIGN KEY (shipChiefID)               REFERENCES ShipChiefElectricalEngineer(shipChiefID);

ALTER TABLE ShipTrip                    ADD CONSTRAINT fkShipTripTripID                         FOREIGN KEY (tripID)                    REFERENCES Trip(tripID);
ALTER TABLE ShipTrip                    ADD CONSTRAINT fkShipTripTripStartingPort               FOREIGN KEY (tripStartingPort)          REFERENCES Port(portID);
ALTER TABLE ShipTrip                    ADD CONSTRAINT fkShipTripTripEndingPort                 FOREIGN KEY (tripEndingPort)            REFERENCES Port(portID);
ALTER TABLE ShipTrip                    ADD CONSTRAINT fkShipTripShipID                         FOREIGN KEY (shipMMSI)                  REFERENCES Ship(shipMMSI);
ALTER TABLE ShipTrip                    ADD CONSTRAINT fkShipTripCrewID                         FOREIGN KEY (crewID)                    REFERENCES Crew(crewID);

ALTER TABLE ShipPositioning             ADD CONSTRAINT fkShipPositioningTrafficManagerID        FOREIGN KEY (trafficManagerID)          REFERENCES TrafficManager(trafficManagerID);
ALTER TABLE ShipPositioning             ADD CONSTRAINT fkShipPositioningTripID                  FOREIGN KEY (tripID)                    REFERENCES Trip(tripID);
ALTER TABLE ShipPositioning             ADD CONSTRAINT fkShipPositioningtransceiverClassID      FOREIGN KEY (shipTransceiverClassID)    REFERENCES TransceiverClass(transceiverClassID);

ALTER TABLE TruckTrip                   ADD CONSTRAINT fkTruckTripTripID                        FOREIGN KEY (tripID)                    REFERENCES Trip(tripID);
ALTER TABLE TruckTrip                   ADD CONSTRAINT fkTruckTripTripStartingLocation          FOREIGN KEY (tripStartingWarehouseID)   REFERENCES Warehouse(warehouseID);
ALTER TABLE TruckTrip                   ADD CONSTRAINT fkTruckTripTripEndingLocation            FOREIGN KEY (tripEndingWarehouseID)     REFERENCES Warehouse(warehouseID);
ALTER TABLE TruckTrip                   ADD CONSTRAINT fkTruckTripTruckID                       FOREIGN KEY (truckID)                   REFERENCES Truck(truckID);
ALTER TABLE TruckTrip                   ADD CONSTRAINT fkTruckTripTruckDriverID                 FOREIGN KEY (truckDriverID)             REFERENCES TruckDriver(truckDriverID);

ALTER TABLE ShipTripStop                ADD CONSTRAINT fkShipTripStopTripID                     FOREIGN KEY (tripID)                    REFERENCES Trip(tripID);
ALTER TABLE ShipTripStop                ADD CONSTRAINT fkShipTripStopCurrentPort                FOREIGN KEY (currentPort)               REFERENCES Port(portID);
ALTER TABLE ShipTripStop                ADD CONSTRAINT fkShipTripStopUnloadingCargoManifestID   FOREIGN KEY (unloadingCargoManifestID)  REFERENCES CargoManifest(cargoManifestID);
ALTER TABLE ShipTripStop                ADD CONSTRAINT fkShipTripStopLoadingCargoManifestID     FOREIGN KEY (loadingCargoManifestID)    REFERENCES CargoManifest(cargoManifestID);

ALTER TABLE TruckTripStop               ADD CONSTRAINT fkTruckTripStopTripID                    FOREIGN KEY (tripID)                    REFERENCES Trip(tripID);
ALTER TABLE TruckTripStop               ADD CONSTRAINT fkTruckTripStopCurrentWarehouse          FOREIGN KEY (currentWarehouse)          REFERENCES Warehouse(warehouseID);
ALTER TABLE TruckTripStop               ADD CONSTRAINT fkTruckTripStopUnloadingCargoManifestID  FOREIGN KEY (unloadingCargoManifestID)  REFERENCES CargoManifest(cargoManifestID);
ALTER TABLE TruckTripStop               ADD CONSTRAINT fkTruckTripStopLoadingCargoManifestID    FOREIGN KEY (loadingCargoManifestID)    REFERENCES CargoManifest(cargoManifestID);

ALTER TABLE ContainerOperation          ADD CONSTRAINT fkContainerOperationContainerID          FOREIGN KEY (containerID)               REFERENCES Container(containerID);
ALTER TABLE ContainerOperation          ADD CONSTRAINT fkContainerOperationCargoManifestID      FOREIGN KEY (cargoManifestID)           REFERENCES CargoManifest(cargoManifestID);

ALTER TABLE ContainersLeavingWarehouse30days ADD CONSTRAINT fkContainersLeavingWarehouse30daysWarehouseID FOREIGN KEY (warehouseID) REFERENCES Warehouse(warehouseID);

ALTER TABLE ResourceMap                 ADD CONSTRAINT fkResourceMapPort                        FOREIGN KEY (port)                      REFERENCES Port(portID);
ALTER TABLE ResourceMap                 ADD CONSTRAINT fkResourceMapWarehouse                   FOREIGN KEY (warehouse)                 REFERENCES Warehouse(warehouseID);
ALTER TABLE ResourceMap                 ADD CONSTRAINT fkResourceMapManifest                    FOREIGN KEY (manifest)                  REFERENCES CargoManifest(cargoManifestID);
ALTER TABLE ResourceMap                 ADD CONSTRAINT fkResourceMapContainer                   FOREIGN KEY (container)                 REFERENCES Container(containerID);

ALTER TABLE GravityCenter               ADD CONSTRAINT fkGravityCenterManifest                  FOREIGN KEY (cargoManifestID)           REFERENCES CargoManifest(cargoManifestID);