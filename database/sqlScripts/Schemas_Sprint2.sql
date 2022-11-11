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

--Locations
DROP TABLE Port                         CASCADE CONSTRAINTS PURGE;
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
DROP TABLE Good                         CASCADE CONSTRAINTS PURGE;

--Trip
DROP TABLE Trip                         CASCADE CONSTRAINTS PURGE;
DROP TABLE Crew                         CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipTrip                     CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipTripStop                 CASCADE CONSTRAINTS PURGE;
DROP TABLE TruckTrip                    CASCADE CONSTRAINTS PURGE;
DROP TABLE TruckTripStop                CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipPositioning              CASCADE CONSTRAINTS PURGE;



--CREATE TABLES-----------------------------------------------------------------

--Actors
CREATE TABLE Client(
    clientID            VARCHAR(20)     CONSTRAINT pkClientClientID PRIMARY KEY
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
    vesselTypeCode                  VARCHAR(20)     CONSTRAINT pkVesselTypeVesselTypeCode   PRIMARY KEY
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
    warehouseID               VARCHAR(20),
    containerTypeID           VARCHAR(20)
);

CREATE TABLE ContainerType (
    containerTypeID           VARCHAR(20)       CONSTRAINT pkContainerTypeContainerTypeID PRIMARY KEY,
    maxTemperature            INTEGER           CONSTRAINT nnContainerTypeMaxTemperature NOT NULL
);

CREATE TABLE Good(
    goodID                    VARCHAR(20)       CONSTRAINT pkGoodGoodID PRIMARY KEY,
    containerID               VARCHAR(20),
    clientID                  VARCHAR(20)
);



--Location
CREATE TABLE Port (
    portID                      VARCHAR(20)      CONSTRAINT pkPortPortID PRIMARY KEY,
    portName                    VARCHAR(30)      CONSTRAINT nnPortPortName NOT NULL,
    portContinent               VARCHAR(20)      CONSTRAINT nnPortPortContinent NOT NULL,
    portCountry                 VARCHAR(20)      CONSTRAINT nnPortPortCountry NOT NULL,
    portLatitude                NUMERIC(7,5)     CONSTRAINT ckPortPortLatitude CHECK (portLatitude BETWEEN -90 AND 90),
    portLongitude               NUMERIC(8,5)     CONSTRAINT ckPortPortLongitude CHECK (portLongitude BETWEEN -180 AND 180),
    portManagerID               VARCHAR(20)
);

CREATE TABLE Warehouse (
    warehouseID                 VARCHAR(20)      CONSTRAINT pkWarehouseWarehouseID PRIMARY KEY,
    warehouseName               VARCHAR(20)      CONSTRAINT nnWarehouseWarehousename NOT NULL,
    warehouseManagerID          VARCHAR(20),          
    portID                      VARCHAR(20)
);

CREATE TABLE Location (
    locationID                  VARCHAR(20)      CONSTRAINT pkLocationLocationID PRIMARY KEY,
    locationContinent           VARCHAR(20)      CONSTRAINT nnLocationLocationContinent NOT NULL,
    locationCountry             VARCHAR(20)      CONSTRAINT nnLocationLocationCountry NOT NULL,
    locationLatitude            NUMERIC(7,5)     CONSTRAINT ckLocationLocationLatitude CHECK (locationLatitude BETWEEN -90 AND 90),
    locationLongitude           NUMERIC(8,5)     CONSTRAINT ckLocationLocationlongitude CHECK (locationLongitude BETWEEN -180 AND 180)
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


CREATE TABLE ShipPositioning (
    baseDateTime         TIMESTAMP,  
    tripID               VARCHAR(20),
    shipLatitude         NUMERIC(7,5)   CONSTRAINT ckShipPositioningShipLatitude CHECK (shipLatitude BETWEEN -90 AND 90),
    shipLongitude        NUMERIC(8,5)   CONSTRAINT ckShipPositioningShipLongitude CHECK (shipLongitude BETWEEN -180 AND 180),
    shipSOG              NUMERIC(8,5)   CONSTRAINT nnShipPositioningShipSOG NOT NULL,
    shipCOG              NUMERIC(8,5)   CONSTRAINT nnShipPositioningShipCOG CHECK (shipCOG BETWEEN 0 AND 359),
    shipHeading          NUMERIC(8,5)   CONSTRAINT ckShipPositioningShipHeading CHECK (shipHeading BETWEEN 0 AND 359 or shipHeading = 511),
    shipPosition         INTEGER        CONSTRAINT nnShipPositioningShipPosition NOT NULL,
    shipTransceiverClass VARCHAR(20)    CONSTRAINT nnShipPositioningShipTransceiverClass NOT NULL,
    trafficManagerID     VARCHAR(20),
    CONSTRAINT pkShipPositioning        PRIMARY KEY (baseDateTime, tripID)
);


CREATE TABLE TruckTrip (
    tripID                     VARCHAR(20)       CONSTRAINT pkTruckTripTripID PRIMARY KEY,                  
    tripStartingLocation       VARCHAR(20),
    tripEndingLocation         VARCHAR(20), 
    truckID                    VARCHAR(20), 
    truckDriverID              VARCHAR(20)
);


CREATE TABLE ShipTripStop (
    tripID                          VARCHAR(20),
    currentPort                     VARCHAR(20),
    tripStopEstimatedArrivalDate    TIMESTAMP        CONSTRAINT nnShipTripStopEstimatedArrivalDate NOT NULL,
    tripStopActualArrivalDate       TIMESTAMP        CONSTRAINT nnShipTripStopActualArrivalDate NOT NULL,
    unloadingCargoManifestID        VARCHAR(20),
    loadingCargoManifestID          VARCHAR(20),
    CONSTRAINT pkShipTripStop       PRIMARY KEY(tripID, currentPort)
);


CREATE TABLE TruckTripStop (
    tripID                          VARCHAR(20),
    locationID                      VARCHAR(20),
    tripStopEstimatedArrivalDate    TIMESTAMP        CONSTRAINT nnTruckTripStopEstimatedArrivalDate NOT NULL,
    tripStopActualArrivalDate       TIMESTAMP        CONSTRAINT nnTruckTripStopActualArrivalDate NOT NULL,
    unloadingCargoManifestID        VARCHAR(20),
    loadingCargoManifestID          VARCHAR(20),
    CONSTRAINT pkTruckTripStop      PRIMARY KEY(tripID, locationID)
);


--ALTER TABLES------------------------------------------------------------------
--Actors
ALTER TABLE PortStaff ADD CONSTRAINT fkPortStaffPortID FOREIGN KEY (portID) REFERENCES Port(portID);

ALTER TABLE WarehouseStaff ADD CONSTRAINT fkWarehouseStaffWarehouseID FOREIGN KEY (warehouseID) REFERENCES Warehouse(warehouseID);



--Location
ALTER TABLE Port ADD CONSTRAINT fkPortPortManagerID FOREIGN KEY (portManagerID) REFERENCES PortManager(portManagerID);

ALTER TABLE Warehouse ADD CONSTRAINT fkWarehouseWarehousemanagerID FOREIGN KEY (warehouseManagerID) REFERENCES WarehouseManager(warehouseManagerID);
ALTER TABLE Warehouse ADD CONSTRAINT fkWarehousePortID FOREIGN KEY (portID) REFERENCES Port(portID);



--Transportation
ALTER TABLE Ship  ADD CONSTRAINT  fkShipVesselCode   FOREIGN KEY (vesselTypeCode)   REFERENCES VesselType(vesselTypeCode);
ALTER TABLE Ship  ADD CONSTRAINT  fkShipFleetManager FOREIGN KEY (fleetManagerID) REFERENCES FleetManager(fleetManagerID);
ALTER TABLE Ship  ADD CONSTRAINT  fkShipGeneratorID  FOREIGN KEY (generatorID)    REFERENCES Generator(generatorID);

ALTER TABLE Truck ADD CONSTRAINT  fkTruckFleetManager FOREIGN KEY (fleetManagerID) REFERENCES FleetManager(fleetManagerID);



--Container
ALTER TABLE CargoManifest ADD CONSTRAINT fkCargoManifestCargoManifestType FOREIGN KEY (cargoManifestTypeID) REFERENCES CargoManifestType(cargoManifestTypeID); 

ALTER TABLE ShipCargoManifest ADD CONSTRAINT fkShipCargoManifestContainerID FOREIGN KEY (containerID) REFERENCES Container(containerID);
ALTER TABLE ShipCargoManifest ADD CONSTRAINT fkShipCargoManifestShipID FOREIGN KEY (shipMMSI) REFERENCES Ship(shipMMSI);
ALTER TABLE ShipCargoManifest ADD CONSTRAINT fkShipCargoManifestCargoManifestID FOREIGN KEY (cargoManifestID) REFERENCES CargoManifest(CargoManifestID);

ALTER TABLE TruckCargoManifest ADD CONSTRAINT fkTruckCargoManifestTruckID   FOREIGN KEY (truckID) REFERENCES Truck(truckID);
ALTER TABLE TruckCargoManifest ADD CONSTRAINT fkTruckCargoManifestContainerID FOREIGN KEY (containerID) REFERENCES Container(containerID);
ALTER TABLE TruckCargoManifest ADD CONSTRAINT fkTruckCargoManifestCargoManifestID FOREIGN KEY (cargoManifestID) REFERENCES CargoManifest(CargoManifestID);

ALTER TABLE Container ADD CONSTRAINT fkContainerWarehouseID FOREIGN KEY (warehouseID) REFERENCES Warehouse(warehouseID);
ALTER TABLE Container ADD CONSTRAINT fkContainerContainerTypeID FOREIGN KEY (containerTypeID) REFERENCES ContainerType(containerTypeID);

ALTER TABLE Good ADD CONSTRAINT fkGoodContainerID FOREIGN KEY (containerID) REFERENCES Container(containerID);
ALTER TABLE Good ADD CONSTRAINT fkGoodClientID FOREIGN KEY (clientID) REFERENCES Client(clientID);



--Trip
ALTER TABLE Crew ADD CONSTRAINT  fkCrewShipCaptainID    FOREIGN KEY (shipCaptainID)    REFERENCES ShipCaptain(shipCaptainID);
ALTER TABLE Crew ADD CONSTRAINT  fkCrewShipChiefID      FOREIGN KEY (shipChiefID)      REFERENCES ShipChiefElectricalEngineer(shipChiefID);

ALTER TABLE ShipTrip ADD CONSTRAINT fkShipTripTripID FOREIGN KEY (tripID) REFERENCES Trip(tripID);
ALTER TABLE ShipTrip ADD CONSTRAINT fkShipTripTripStartingPort FOREIGN KEY (tripStartingPort) REFERENCES Port(portID);
ALTER TABLE ShipTrip ADD CONSTRAINT fkShipTripTripEndingPort FOREIGN KEY (tripEndingPort) REFERENCES Port(portID);
ALTER TABLE ShipTrip ADD CONSTRAINT fkShipTripShipID FOREIGN KEY (shipMMSI) REFERENCES Ship(shipMMSI);
ALTER TABLE ShipTrip ADD CONSTRAINT fkShipTripCrewID FOREIGN KEY (crewID) REFERENCES Crew(crewID);

ALTER TABLE ShipPositioning ADD CONSTRAINT fkShipPositioningTrafficManagerID FOREIGN KEY (trafficManagerID) REFERENCES TrafficManager(trafficManagerID);
ALTER TABLE ShipPositioning ADD CONSTRAINT fkShipPositioningTripID FOREIGN KEY (tripID) REFERENCES Trip(tripID);

ALTER TABLE TruckTrip ADD CONSTRAINT fkTruckTripTripID FOREIGN KEY (tripID) REFERENCES Trip(tripID);
ALTER TABLE TruckTrip ADD CONSTRAINT fkTruckTripTripStartingLocation FOREIGN KEY (tripStartingLocation) REFERENCES Location(locationID);
ALTER TABLE TruckTrip ADD CONSTRAINT fkTruckTripTripEndingLocation FOREIGN KEY (tripEndingLocation) REFERENCES Location(locationID);
ALTER TABLE TruckTrip ADD CONSTRAINT fkTruckTripTruckID FOREIGN KEY (truckID) REFERENCES Truck(truckID);
ALTER TABLE TruckTrip ADD CONSTRAINT fkTruckTripTruckDriverID FOREIGN KEY (truckDriverID) REFERENCES TruckDriver(truckDriverID);

ALTER TABLE ShipTripStop ADD CONSTRAINT fkShipTripStopTripID                    FOREIGN KEY (tripID)                    REFERENCES Trip(tripID);
ALTER TABLE ShipTripStop ADD CONSTRAINT fkShipTripStopCurrentPort               FOREIGN KEY (currentPort)               REFERENCES Port(portID);
ALTER TABLE ShipTripStop ADD CONSTRAINT fkShipTripStopUnloadingCargoManifestID  FOREIGN KEY (unloadingCargoManifestID)  REFERENCES CargoManifest(cargoManifestID);
ALTER TABLE ShipTripStop ADD CONSTRAINT fkShipTripStopLoadingCargoManifestID    FOREIGN KEY (loadingCargoManifestID)    REFERENCES CargoManifest(cargoManifestID);

ALTER TABLE TruckTripStop ADD CONSTRAINT fkTruckTripStopTripID                    FOREIGN KEY (tripID)                    REFERENCES Trip(tripID);
ALTER TABLE TruckTripStop ADD CONSTRAINT fkTruckTripStopCurrentPort               FOREIGN KEY (locationID)                REFERENCES Location(locationID);
ALTER TABLE TruckTripStop ADD CONSTRAINT fkTruckTripStopUnloadingCargoManifestID  FOREIGN KEY (unloadingCargoManifestID)  REFERENCES CargoManifest(cargoManifestID);
ALTER TABLE TruckTripStop ADD CONSTRAINT fkTruckTripStopLoadingCargoManifestID    FOREIGN KEY (loadingCargoManifestID)    REFERENCES CargoManifest(cargoManifestID);

