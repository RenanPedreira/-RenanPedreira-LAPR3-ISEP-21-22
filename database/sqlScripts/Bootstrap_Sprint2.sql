--INSERT VALUES

--Client
INSERT INTO Client VALUES('Client1');

--FleetManager
INSERT INTO FleetManager VALUES('FleetM1');

--TrafficManager
INSERT INTO TrafficManager VALUES('TrafficM1');

--WarehouseManager
INSERT INTO WarehouseManager VALUES('WarehouseM1');

--PortManager
INSERT INTO PortManager VALUES('PortM1');

--ShipCaptain
INSERT INTO ShipCaptain VALUES('ShipC1');
INSERT INTO ShipCaptain VALUES('ShipC2');
INSERT INTO ShipCaptain VALUES('ShipC3');
INSERT INTO ShipCaptain VALUES('ShipC4');
INSERT INTO ShipCaptain VALUES('ShipC5');

--ShipChiefElectricalEngineer
INSERT INTO ShipChiefElectricalEngineer VALUES('ChiefE1');
INSERT INTO ShipChiefElectricalEngineer VALUES('ChiefE2');
INSERT INTO ShipChiefElectricalEngineer VALUES('ChiefE3');
INSERT INTO ShipChiefElectricalEngineer VALUES('ChiefE4');
INSERT INTO ShipChiefElectricalEngineer VALUES('ChiefE5');

--TruckDriver
INSERT INTO TruckDriver VALUES('TruckD1');

--Port
INSERT INTO Port VALUES('29002', 'Liverpool', 'Europe', 'United Kingdom', 53.466, -3.033, 'PortM1');
INSERT INTO Port VALUES('14635', 'Los Angeles', 'America', 'United States', 33.7166, -118.266, 'PortM1');
INSERT INTO Port VALUES('25007', 'New Jersey', 'America', 'United States', 40.666, -74.166, 'PortM1');
INSERT INTO Port VALUES('20301', 'Rio Grande', 'America', 'Brazil', -32.066, -52.066, 'PortM1');
INSERT INTO Port VALUES('20351', 'Salvador', 'America', 'Brazil', -12.966, -38.516, 'PortM1');
INSERT INTO Port VALUES('27248', 'Valparaiso', 'America', 'Chile', -33.016, -71.633, 'PortM1');
INSERT INTO Port VALUES('22226', 'Dunkirk', 'Europe', 'France', 51.05, 2.366, 'PortM1');
INSERT INTO Port VALUES('25350', 'Brest', 'Europe', 'France', 48.4, -4.5, 'PortM1');
INSERT INTO Port VALUES('27792', 'Leixões', 'Europe', 'Portugal', 37.733, -25.666, 'PortM1');
INSERT INTO Port VALUES('28082', 'Funchal', 'Europe', 'Portugal', 32.65, -16.910, 'PortM1');

--PortStaff
INSERT INTO PortStaff VALUES('PortS1', '29002');
INSERT INTO PortStaff VALUES('PortS2', '14635');
INSERT INTO PortStaff VALUES('PortS3', '25007');

--Warehouse
INSERT INTO Warehouse VALUES('Warehouse1', 'War1', 'WarehouseM1', '29002');
INSERT INTO Warehouse VALUES('Warehouse2', 'War2', 'WarehouseM1', '14635');
INSERT INTO Warehouse VALUES('Warehouse3', 'War3', 'WarehouseM1', '25007');

--WarehouseStaff
INSERT INTO WarehouseStaff VALUES('WarehouseS1', 'Warehouse1');
INSERT INTO WarehouseStaff VALUES('WarehouseS2', 'Warehouse2');
INSERT INTO WarehouseStaff VALUES('WarehouseS3', 'Warehouse3');

--Location
INSERT INTO Location VALUES('Location1', 'Europe', 'United Kingdom', 49.283,-123.116);
INSERT INTO Location VALUES('Location2', 'Europe', 'Portugal', 41.333, 2.166);
INSERT INTO Location VALUES('Location3', 'Europe', 'Portugal', -36.733, -73.15);

--TRANSPORTS
--VesselType
INSERT INTO VesselType VALUES('Vtype1');
INSERT INTO VesselType VALUES('Vtype2');
INSERT INTO VesselType VALUES('Vtype3');

--Generator
INSERT INTO Generator VALUES('Gen1', 1200);
INSERT INTO Generator VALUES('Gen2', 900);
INSERT INTO Generator VALUES('Gen3', 1000);

--Ship
INSERT INTO Ship VALUES(111111111, 'IMO1111111','CLS1', 'Ship1', 2,  500, 200, 200, 200, 400, 100 , 100, 'Vtype1', 'FleetM1', 'Gen1'); 
INSERT INTO Ship VALUES(222222222, 'IMO2222222','CLS2', 'Ship2', 2,  500, 200, 200, 200, 400, 100 , 100, 'Vtype1', 'FleetM1', 'Gen1'); 
INSERT INTO Ship VALUES(333333333, 'IMO3333333','CLS3', 'Ship3', 2,  500, 200, 200, 200, 400, 100 , 100, 'Vtype1', 'FleetM1', 'Gen1'); 
INSERT INTO Ship VALUES(444444444, 'IMO4444444','CLS4', 'Ship4', 2,  500, 200, 200, 200, 400, 100 , 100, 'Vtype1', 'FleetM1', 'Gen1'); 
INSERT INTO Ship VALUES(555555555, 'IMO5555555','CLS5', 'Ship5', 2,  500, 200, 200, 200, 400, 100 , 100, 'Vtype1', 'FleetM1', 'Gen1'); 


--Truck
INSERT INTO Truck VALUES('Truck1', 'FleetM1');

--Trip
--Trips for ships
INSERT INTO Trip VALUES('Trip1', '2021-11-11 09:00:00', '2021-11-13 15:00:00', '2021-11-13 15:00:00');
INSERT INTO Trip VALUES('Trip2', '2021-11-12 11:00:00', '2021-11-20 11:11:11', '2021-11-20 11:00:00');
INSERT INTO Trip VALUES('Trip3', '2021-11-20 11:00:00', '2022-11-25 10:00:00', '2022-11-20 11:00:00');
INSERT INTO Trip VALUES('Trip4', '2021-12-01 11:00:00', '2021-12-05 10:00:00', '2021-12-16 10:00:00');
INSERT INTO Trip VALUES('Trip5', '2021-12-03 11:00:00', '2021-12-07 15:00:00', '2021-12-07 15:00:00');
INSERT INTO Trip VALUES('Trip6', '2021-12-20 11:00:00', '2021-12-25 10:00:00', '2021-12-25 10:00:00');
INSERT INTO Trip VALUES('Trip7', '2021-12-25 11:00:00', '2021-12-26 10:00:00', '2021-12-26 10:00:00');
INSERT INTO Trip VALUES('Trip8', '2021-12-30 11:00:00', '2021-12-31 10:00:00', '2021-12-31 10:00:00');
INSERT INTO Trip VALUES('Trip9', '2022-01-15 11:00:00', '2022-01-18 10:00:00', '2022-01-18 10:00:00');
INSERT INTO Trip VALUES('Trip10', '2022-01-20 11:00:00', '2022-01-22 10:00:00', '2022-01-22 10:00:00');
INSERT INTO Trip VALUES('Trip11', '2022-01-25 11:00:00', '2022-01-30 10:00:00', '2022-01-30 10:00:00');
INSERT INTO Trip VALUES('Trip12', '2022-02-10 11:00:00', '2022-02-17 10:00:00', '2022-02-17 10:00:00');
--Trips for truck
INSERT INTO Trip VALUES('Trip13', '2021-11-12 11:00:00', '2021-11-15 10:00:00', '2021-11-15 10:00:00');

--Crew
INSERT INTO Crew VALUES('Crew1', 'ShipC1', 'ChiefE1');
INSERT INTO Crew VALUES('Crew2', 'ShipC2', 'ChiefE2');
INSERT INTO Crew VALUES('Crew3', 'ShipC3', 'ChiefE3');
INSERT INTO Crew VALUES('Crew4', 'ShipC4', 'ChiefE4');
INSERT INTO Crew VALUES('Crew5', 'ShipC5', 'ChiefE5');

--ShipTrip
--SHIP1
INSERT INTO ShipTrip VALUES('Trip1', '29002', '25007', 111111111, 'Crew1');
INSERT INTO ShipTrip VALUES('Trip3', '25007', '20351', 111111111, 'Crew1');
INSERT INTO ShipTrip VALUES('Trip4', '20351', '22226', 111111111, 'Crew1');
--SHIP2
INSERT INTO ShipTrip VALUES('Trip2', '14635', '20301', 222222222, 'Crew2');
INSERT INTO ShipTrip VALUES('Trip7', '20301', '27248', 222222222, 'Crew2');
INSERT INTO ShipTrip VALUES('Trip10', '27248', '27792', 222222222, 'Crew2');
--SHIP3
INSERT INTO ShipTrip VALUES('Trip5', '28082', '22226', 333333333, 'Crew3');
INSERT INTO ShipTrip VALUES('Trip8', '22226', '20351', 333333333, 'Crew3');
--SHIP4
INSERT INTO ShipTrip VALUES('Trip6', '20301', '25007', 444444444, 'Crew4');
INSERT INTO ShipTrip VALUES('Trip9', '25007', '25350', 444444444, 'Crew4');
--SHIP5
INSERT INTO ShipTrip VALUES('Trip11', '27792', '20351', 555555555, 'Crew5');
INSERT INTO ShipTrip VALUES('Trip12', '20351', '22226', 555555555, 'Crew5');

--TruckTrip
INSERT INTO TruckTrip VALUES('Trip13', 'Location1', 'Location3', 'Truck1', 'TruckD1');

--ContainerType
INSERT INTO ContainerType VALUES('ContT1', 7);
INSERT INTO ContainerType VALUES('ContT2', -5);

--Container
INSERT INTO Container VALUES('Cont1', 1250, 750, 500, '1234', 'Warehouse1', 'ContT1');
INSERT INTO Container VALUES('Cont2', 1250, 750, 500, '1234', 'Warehouse1', 'ContT1');
INSERT INTO Container VALUES('Cont3', 1252, 752, 502, '1234', 'Warehouse2', 'ContT1');
INSERT INTO Container VALUES('Cont4', 1253, 753, 503, '1234', 'Warehouse2', 'ContT2');
INSERT INTO Container VALUES('Cont5', 1254, 754, 504, '1234', 'Warehouse3', 'ContT2');
INSERT INTO Container VALUES('Cont6', 1255, 755, 505, '1234', 'Warehouse3', 'ContT2');

--CargoManifestType
INSERT INTO CargoManifestType VALUES('Load');
INSERT INTO CargoManifestType VALUES('Unload');

--CargoManifest
--Trip 1
INSERT INTO CargoManifest VALUES('CargoManifest1', TO_timestamp('11/11/2020 09:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest2', TO_timestamp('11/11/2020 09:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Unload');
INSERT INTO CargoManifest VALUES('CargoManifest3', TO_timestamp('12/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest4', TO_timestamp('12/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Unload');
INSERT INTO CargoManifest VALUES('CargoManifest5', TO_timestamp('13/11/2020 12:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest6', TO_timestamp('13/11/2020 12:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Unload');


--Trip 2
INSERT INTO CargoManifest VALUES('CargoManifest7', TO_timestamp('11/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest8', TO_timestamp('11/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Unload');
INSERT INTO CargoManifest VALUES('CargoManifest9', TO_timestamp('15/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest10',TO_timestamp('15/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Unload');
INSERT INTO CargoManifest VALUES('CargoManifest11',TO_timestamp('20/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest12',TO_timestamp('20/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Unload');


--ShipCargoManifest
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont1', 1, 5, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest2', 'Cont2', 4, 1, 2, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest3', 'Cont3', 1, 2, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest4', 'Cont4', 3, 1, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest5', 'Cont5', 1, 4, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest6', 'Cont6', 3, 1, 7, 111111111);

--TruckCargoManifest
INSERT INTO TruckCargoManifest VALUES('CargoManifest7', 'Cont1', 0, 0, 0, 'Truck1');
INSERT INTO TruckCargoManifest VALUES('CargoManifest8', 'Cont2', 0, 0, 0, 'Truck1');
INSERT INTO TruckCargoManifest VALUES('CargoManifest9', 'Cont3', 0, 0, 0, 'Truck1');
INSERT INTO TruckCargoManifest VALUES('CargoManifest10', 'Cont4', 0, 0, 0, 'Truck1');
INSERT INTO TruckCargoManifest VALUES('CargoManifest11', 'Cont5', 0, 0, 0, 'Truck1');
INSERT INTO TruckCargoManifest VALUES('CargoManifest12', 'Cont6', 0, 0, 0, 'Truck1');

--ShipTripStop
--TRIP1
INSERT INTO ShipTripStop VALUES('Trip1', '29002', TO_timestamp('11/11/2020 09:00:00', 'DD/MM/YYYY hh:mi:ss'), TO_timestamp('11/11/2020 09:00:00', 'DD/MM/YYYY hh:mi:ss'), 'CargoManifest2', 'CargoManifest1');
INSERT INTO ShipTripStop VALUES('Trip1', '14635', TO_timestamp('15/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), TO_timestamp('15/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'CargoManifest4', 'CargoManifest3');
INSERT INTO ShipTripStop VALUES('Trip1', '25007', TO_timestamp('13/11/2020 12:00:00', 'DD/MM/YYYY hh:mi:ss'), TO_timestamp('13/11/2020 12:00:00', 'DD/MM/YYYY hh:mi:ss'), 'CargoManifest6', 'CargoManifest5');

--TruckTripStop
--TRIP2
INSERT INTO TruckTripStop VALUES('Trip13', 'Location1', TO_timestamp('11/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), TO_timestamp('11/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'CargoManifest7', 'CargoManifest8');
INSERT INTO TruckTripStop VALUES('Trip13', 'Location2', TO_timestamp('15/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), TO_timestamp('15/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'CargoManifest9', 'CargoManifest10');
INSERT INTO TruckTripStop VALUES('Trip13', 'Location3', TO_timestamp('20/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), TO_timestamp('20/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss') , 'CargoManifest11', 'CargoManifest12');

--Good
INSERT INTO Good VALUES('Good1', 'Cont1', 'Client1');
INSERT INTO Good VALUES('Good2', 'Cont2', 'Client1');
INSERT INTO Good VALUES('Good3', 'Cont3', 'Client1');

--ShipPositioning
INSERT INTO ShipPositioning VALUES(TO_timestamp('11/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Trip1', 49.283, -123.116, 11, 11, 11, 11, 'A', 'TrafficM1');
INSERT INTO ShipPositioning VALUES(TO_timestamp('12/11/2020 05:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Trip1', 41.333, 2.166, 12, 12, 12, 12, 'A', 'TrafficM1');
INSERT INTO ShipPositioning VALUES(TO_timestamp('12/11/2020 12:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Trip1', -36.733, -73.15, 13, 13, 13, 13, 'A', 'TrafficM1');
INSERT INTO ShipPositioning VALUES(TO_timestamp('13/11/2020 11:00:00', 'DD/MM/YYYY hh:mi:ss'), 'Trip1', -50.733, -20.15, 14, 14, 14, 14, 'A', 'TrafficM1');


--US 204------------------------------------------------------------------------
--Trip
INSERT INTO Trip VALUES('Trip111', '2021-12-01 09:00:00', '2021-12-04 15:00:00', '2021-12-04 15:00:00');
--ShipTrip
INSERT INTO ShipTrip VALUES('Trip111', '29002', '20351', 333333333, 'Crew1');
--Cargo Manifests
INSERT INTO CargoManifest VALUES('CargoManifest90', '2021-12-03 15:00:00', 'Unload');
INSERT INTO CargoManifest VALUES('CargoManifest91', '2021-12-03 15:00:00', 'Load');
--Ship Cargo Manifest
INSERT INTO ShipCargoManifest VALUES('CargoManifest90', 'Cont1', 1, 5, 1, 333333333);
INSERT INTO ShipCargoManifest VALUES('CargoManifest91', 'Cont2', 1, 5, 1, 333333333);
--ShipTripStop
INSERT INTO ShipTripStop VALUES('Trip111', '25007', '2021-12-03 15:00:00', '2021-12-04 15:00:00', 'CargoManifest90', 'CargoManifest91');




/*
DELETE FROM ShipPositioning;
DELETE FROM Good;
DELETE FROM TruckTripStop;
DELETE FROM ShipTripStop;
DELETE FROM TruckCargoManifest;
DELETE FROM ShipCargoManifest;
DELETE FROM CargoManifest;
DELETE FROM CargoManifestType;
DELETE FROM Container;
DELETE FROM ContainerType;
DELETE FROM TruckTrip;
DELETE FROM ShipTrip;
DELETE FROM Crew;
DELETE FROM Trip;
DELETE FROM Ship;
DELETE FROM Generator;
DELETE FROM Truck;
DELETE FROM VesselType;
DELETE FROM Location;
DELETE FROM WarehouseStaff;
DELETE FROM Warehouse;
DELETE FROM PortStaff;
DELETE FROM Port;
DELETE FROM TruckDriver;
DELETE FROM ShipChiefElectricalEngineer;
DELETE FROM ShipCaptain;
DELETE FROM PortManager;
DELETE FROM WarehouseManager;
DELETE FROM TrafficManager;
DELETE FROM Client;
DELETE FROM FleetManager;
*/























