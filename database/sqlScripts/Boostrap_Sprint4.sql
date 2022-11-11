--Continent
INSERT INTO Continent VALUES('America');
INSERT INTO Continent VALUES('Africa');
INSERT INTO Continent VALUES('Asia');
INSERT INTO Continent VALUES('Europe');
INSERT INTO Continent VALUES('Oceania');

--Auth
INSERT INTO Auth VALUES('client1@app.pt', '12345');
INSERT INTO Auth VALUES('client2@app.pt', '22345');
INSERT INTO Auth VALUES('client3@app.pt', '32345');
INSERT INTO Auth VALUES('client4@app.pt', '42345');
INSERT INTO Auth VALUES('client5@app.pt', '52345');

INSERT INTO Auth VALUES('fman1@app.pt', '12345'); 

INSERT INTO Auth VALUES('tfman1@app.pt', '12345'); 

INSERT INTO Auth VALUES('wman1@app.pt', '12345'); 

INSERT INTO Auth VALUES('pman1@app.pt', '12345');

INSERT INTO Auth VALUES('cap1@app.pt', '12345'); 
INSERT INTO Auth VALUES('cap2@app.pt', '12345'); 
INSERT INTO Auth VALUES('cap3@app.pt', '12345'); 
INSERT INTO Auth VALUES('cap4@app.pt', '12345'); 
INSERT INTO Auth VALUES('cap5@app.pt', '12345'); 

INSERT INTO Auth VALUES('chief1@app.pt', '12345'); 
INSERT INTO Auth VALUES('chief2@app.pt', '12345'); 
INSERT INTO Auth VALUES('chief3@app.pt', '12345'); 
INSERT INTO Auth VALUES('chief4@app.pt', '12345'); 
INSERT INTO Auth VALUES('chief5@app.pt', '12345'); 

INSERT INTO Auth VALUES('tkdr1@app.pt', '12345');
INSERT INTO Auth VALUES('tkdr2@app.pt', '12345'); 

INSERT INTO Auth VALUES('pstaff1@app.pt', '12345'); 
INSERT INTO Auth VALUES('pstaff2@app.pt', '12345'); 
INSERT INTO Auth VALUES('pstaff3@app.pt', '12345');

INSERT INTO Auth VALUES('wstaff1@app.pt', '12345'); 
INSERT INTO Auth VALUES('wstaff2@app.pt', '12345'); 
INSERT INTO Auth VALUES('wstaff3@app.pt', '12345'); 

--Employee
--Client
INSERT INTO Client VALUES('Client1', 'client1@app.pt');
INSERT INTO Client VALUES('Client2', 'client2@app.pt');
INSERT INTO Client VALUES('Client3', 'client3@app.pt');
INSERT INTO Client VALUES('Client4', 'client4@app.pt');
INSERT INTO Client VALUES('Client5', 'client5@app.pt');

--Employee
INSERT INTO Employee VALUES('FleetM1', 'fman1@app.pt', 'Tzarita'); 

INSERT INTO Employee VALUES('TrafficM1', 'tfman1@app.pt', 'Pierro');

INSERT INTO Employee VALUES('WarehouseM1', 'wman1@app.pt', 'Colombina');

INSERT INTO Employee VALUES('PortM1', 'pman1@app.pt', 'Tartaglia');

INSERT INTO Employee VALUES('ShipC1', 'cap1@app.pt', 'Signora');
INSERT INTO Employee VALUES('ShipC2', 'cap2@app.pt', 'Scaramouche');
INSERT INTO Employee VALUES('ShipC3', 'cap3@app.pt', 'Capitano');
INSERT INTO Employee VALUES('ShipC4', 'cap4@app.pt', 'Pantalone');
INSERT INTO Employee VALUES('ShipC5', 'cap5@app.pt', 'Pulcinella');

INSERT INTO Employee VALUES('ChiefE1', 'chief1@app.pt', 'Diluc');
INSERT INTO Employee VALUES('ChiefE2', 'chief2@app.pt', 'Qiqi');
INSERT INTO Employee VALUES('ChiefE3', 'chief3@app.pt', 'Albedo');
INSERT INTO Employee VALUES('ChiefE4', 'chief4@app.pt', 'Xiao');
INSERT INTO Employee VALUES('ChiefE5', 'chief5@app.pt', 'Ei');

INSERT INTO Employee VALUES('TruckD1', 'tkdr1@app.pt', 'Dottore');
INSERT INTO Employee VALUES('TruckD2', 'tkdr2@app.pt', 'Diavolo');

INSERT INTO Employee VALUES('PortS1', 'pstaff1@app.pt', 'Zhongli');
INSERT INTO Employee VALUES('PortS2', 'pstaff2@app.pt', 'Ayaka');
INSERT INTO Employee VALUES('PortS3', 'pstaff3@app.pt', 'Eula');

INSERT INTO Employee VALUES('WarehouseS1', 'wstaff1@app.pt', 'Keqing');
INSERT INTO Employee VALUES('WarehouseS2', 'wstaff2@app.pt', 'Jean');
INSERT INTO Employee VALUES('WarehouseS3', 'wstaff3@app.pt', 'Venti');


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
INSERT INTO TruckDriver VALUES('TruckD2');

--IMPORT COUNTRIES, IMPORT PORTS, BORDERS, SEADISTS

--PortStaff
INSERT INTO PortStaff VALUES('PortS1', '29002');
INSERT INTO PortStaff VALUES('PortS2', '14635');
INSERT INTO PortStaff VALUES('PortS3', '25007');

--Warehouse
INSERT INTO Warehouse VALUES('Warehouse1', 'War1', 5, 5, 4, 'WarehouseM1', '29002');
INSERT INTO Warehouse VALUES('Warehouse2', 'War2', 5, 5, 4, 'WarehouseM1', '14635');
INSERT INTO Warehouse VALUES('Warehouse3', 'War3', 5, 5, 4, 'WarehouseM1', '25007');
INSERT INTO Warehouse VALUES('Warehouse4', 'War4', 5, 5, 4, 'WarehouseM1', '20301');
INSERT INTO Warehouse VALUES('Warehouse5', 'War5', 5, 5, 4, 'WarehouseM1', '20351');
INSERT INTO Warehouse VALUES('Warehouse6', 'War6', 5, 5, 4, 'WarehouseM1', '27248');
INSERT INTO Warehouse VALUES('Warehouse7', 'War7', 5, 5, 4, 'WarehouseM1', '22226');
INSERT INTO Warehouse VALUES('Warehouse8', 'War8', 5, 5, 4, 'WarehouseM1', '25350');
INSERT INTO Warehouse VALUES('Warehouse9', 'War9', 5, 5, 4, 'WarehouseM1', '27792');
INSERT INTO Warehouse VALUES('Warehouse10', 'War10', 5, 5, 4, 'WarehouseM1', '28082');

--WarehouseStaff
INSERT INTO WarehouseStaff VALUES('WarehouseS1', 'Warehouse1');
INSERT INTO WarehouseStaff VALUES('WarehouseS2', 'Warehouse2');
INSERT INTO WarehouseStaff VALUES('WarehouseS3', 'Warehouse3');

--TRANSPORTS
--VesselType
INSERT INTO VesselType VALUES('Vtype1', 70, 'Cargo', 166.0, 25.0);
INSERT INTO VesselType VALUES('Vtype2', 80, 'Tanker', 288.0, 44.0);
INSERT INTO VesselType VALUES('Vtype3', 60, 'Passenger', 292.0, 38.0);
INSERT INTO VesselType VALUES('Vtype4', 30, 'Fishing', 37.0, 9.0);

--Generator
INSERT INTO Generator VALUES('Gen1', 1200);
INSERT INTO Generator VALUES('Gen2', 900);
INSERT INTO Generator VALUES('Gen3', 1000);

--Ship
INSERT INTO Ship VALUES(111111111, 'IMO1111111','CLS1', 'Ship1', 2,  500, 200, 200, 200, 5, 5, 4, 'Vtype1', 'FleetM1', 'Gen1'); 
INSERT INTO Ship VALUES(222222222, 'IMO2222222','CLS2', 'Ship2', 2,  500, 200, 200, 200, 5, 5, 4, 'Vtype1', 'FleetM1', 'Gen1'); 
INSERT INTO Ship VALUES(333333333, 'IMO3333333','CLS3', 'Ship3', 2,  500, 200, 200, 200, 5, 5, 4, 'Vtype1', 'FleetM1', 'Gen1'); 
INSERT INTO Ship VALUES(444444444, 'IMO4444444','CLS4', 'Ship4', 2,  500, 200, 200, 200, 5, 5, 4, 'Vtype1', 'FleetM1', 'Gen1'); 
INSERT INTO Ship VALUES(555555555, 'IMO5555555','CLS5', 'Ship5', 2,  500, 200, 200, 200, 5, 5, 4, 'Vtype1', 'FleetM1', 'Gen1'); 

--Truck
INSERT INTO Truck VALUES('Truck1', 'FleetM1');
INSERT INTO Truck VALUES('Truck2', 'FleetM1');
INSERT INTO Truck VALUES('Truck3', 'FleetM1');


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

--Crew
INSERT INTO Crew    VALUES('Crew1', 'ShipC1', 'ChiefE1');
INSERT INTO Crew    VALUES('Crew2', 'ShipC2', 'ChiefE2');

--ContainerType
INSERT INTO ContainerType VALUES('ContT1', 7);
INSERT INTO ContainerType VALUES('ContT2', -5);

--Container
INSERT INTO Container VALUES('Cont1', 1250, 750, 500, '1234', 6.0, 2.3, 'ContT1');
INSERT INTO Container VALUES('Cont2', 1250, 750, 500, '1234', 6.0, 2.3, 'ContT1');
INSERT INTO Container VALUES('Cont3', 1252, 752, 502, '1234', 6.0, 2.3, 'ContT1');
INSERT INTO Container VALUES('Cont4', 1253, 753, 503, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont5', 1254, 754, 504, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont6', 1255, 755, 505, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont7', 1250, 750, 500, '1234', 6.0, 2.3, 'ContT1');
INSERT INTO Container VALUES('Cont8', 1250, 750, 500, '1234', 6.0, 2.3, 'ContT1');
INSERT INTO Container VALUES('Cont9', 1252, 752, 502, '1234', 6.0, 2.3, 'ContT1');
INSERT INTO Container VALUES('Cont10', 1253, 753, 503, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont11', 1254, 754, 504, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont12', 1255, 755, 505, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont13', 1255, 755, 505, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont14', 1250, 750, 500, '1234', 6.0, 2.3, 'ContT1');
INSERT INTO Container VALUES('Cont15', 1250, 750, 500, '1234', 6.0, 2.3, 'ContT1');
INSERT INTO Container VALUES('Cont16', 1252, 752, 502, '1234', 6.0, 2.3, 'ContT1');
INSERT INTO Container VALUES('Cont17', 1253, 753, 503, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont18', 1254, 754, 504, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont19', 1255, 755, 505, '1234', 6.0, 2.3, 'ContT2');
INSERT INTO Container VALUES('Cont20', 1250, 750, 500, '1234', 6.0, 2.3, 'ContT1');

INSERT INTO Lease VALUES('Lease1', '1111', '2021-11-30 06:00:00', '2021-12-10 15:00:00', 'Client1', 'Cont1');
INSERT INTO Lease VALUES('Lease2', '2222', '2021-11-30 06:00:00', '2021-12-10 15:00:00', 'Client2', 'Cont2');
INSERT INTO Lease VALUES('Lease3', '3333', '2021-11-30 06:00:00', '2021-12-10 15:00:00', 'Client3', 'Cont3');
INSERT INTO Lease VALUES('Lease4', '4444', '2021-11-30 06:00:00', '2021-12-10 15:00:00', 'Client4', 'Cont4'); --
INSERT INTO Lease VALUES('Lease5', '5555', '2021-11-30 06:00:00', '2021-12-10 15:00:00', 'Client5', 'Cont5');

INSERT INTO Lease VALUES('Lease6', '6666', '2021-11-30 06:00:00', '2021-12-10 15:00:00', 'Client1', 'Cont6');
INSERT INTO Lease VALUES('Lease7', '7777', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client2', 'Cont7');
INSERT INTO Lease VALUES('Lease8', '8888', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client3', 'Cont8');
INSERT INTO Lease VALUES('Lease9', '9999', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client4', 'Cont9');
INSERT INTO Lease VALUES('Lease10', '1010', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client5', 'Cont10');

INSERT INTO Lease VALUES('Lease11', '1100', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client1', 'Cont11');
INSERT INTO Lease VALUES('Lease12', '2200', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client2', 'Cont12');
INSERT INTO Lease VALUES('Lease13', '3300', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client3', 'Cont13');
INSERT INTO Lease VALUES('Lease14', '4400', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client4', 'Cont14');
INSERT INTO Lease VALUES('Lease15', '5500', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client5', 'Cont15');

INSERT INTO Lease VALUES('Lease16', '6600', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client1', 'Cont16');
INSERT INTO Lease VALUES('Lease17', '7700', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client2', 'Cont17');
INSERT INTO Lease VALUES('Lease18', '8800', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client3', 'Cont18');
INSERT INTO Lease VALUES('Lease19', '9900', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client4', 'Cont19');
INSERT INTO Lease VALUES('Lease20', '0101', '2021-11-30 06:00:00', '2021-12-10 15:00:00',  'Client5', 'Cont20');

INSERT INTO Lease VALUES('Lease21', '4441', '2021-11-30 06:00:00', '2021-12-10 20:00:00', 'Client1', 'Cont4'); --
INSERT INTO Lease VALUES('Lease22', '4442', '2021-11-30 06:00:00', '2021-12-10 20:00:00', 'Client2', 'Cont4'); --
INSERT INTO Lease VALUES('Lease23', '4443', '2021-11-30 06:00:00', '2021-12-10 20:00:00', 'Client3', 'Cont4'); --
INSERT INTO Lease VALUES('Lease24', '4445', '2021-11-30 06:00:00', '2021-12-10 20:00:00', 'Client5', 'Cont4'); --

--TRIP1
--Trip
--Ship
INSERT INTO Trip VALUES('Trip1', '2021-12-01 09:00:00', '2021-12-09 15:00:00', '2021-12-09 15:00:00');
--Truck
INSERT INTO Trip VALUES('Trip11', '2021-11-30 06:00:00', '2021-11-30 21:00:00', '2021-11-30 21:00:00');
INSERT INTO Trip VALUES('Trip12', '2021-12-10 09:00:00', '2021-12-10 20:00:00', '2021-12-10 20:00:00');

--ShipTrip
INSERT INTO ShipTrip VALUES('Trip1', '29002', '20351', 111111111, 'Crew1');  

--CargoManifestType
INSERT INTO CargoManifestType VALUES('Load');
INSERT INTO CargoManifestType VALUES('Unload');

--CargoManifest
--P1
INSERT INTO CargoManifest VALUES('CargoManifest1', '2021-12-01 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest2', '2021-12-01 09:00:00', 'Unload');
--P2
INSERT INTO CargoManifest VALUES('CargoManifest3', '2021-12-03 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest4', '2021-12-03 09:00:00', 'Unload');
--P3
INSERT INTO CargoManifest VALUES('CargoManifest5', '2021-12-05 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest6', '2021-12-05 09:00:00', 'Unload');
--P4
INSERT INTO CargoManifest VALUES('CargoManifest7', '2021-12-07 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest8', '2021-12-07 09:00:00', 'Unload');
--P5
INSERT INTO CargoManifest VALUES('CargoManifest9', '2021-12-09 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest10', '2021-12-09 09:00:00', 'Unload');

--ShipCargoManifest
--P1 LOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont1', 1, 1, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont2', 1, 2, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont3', 1, 3, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont4', 1, 4, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont5', 1, 5, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont6', 1, 6, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont7', 1, 7, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont8', 1, 8, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont9', 1, 9, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest1', 'Cont10', 1, 10, 1, 111111111);

--P1 UNLOAD 
--CM_ID2 VAZIO

--P2 LOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest3', 'Cont11', 2, 1, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest3', 'Cont12', 2, 2, 1, 111111111);

--P2 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest4', 'Cont1', 1, 1, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest4', 'Cont3', 1, 3, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest4', 'Cont5', 1, 5, 1, 111111111);

--P3 LOAD
--CM_ID5 VAZIO

--P3 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest6', 'Cont2', 1, 2, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest6', 'Cont8', 1, 8, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest6', 'Cont9', 1, 9, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest6', 'Cont10', 1, 10, 1, 111111111);

--P4 LOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest7', 'Cont13', 3, 1, 1, 111111111);

--P4 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest8', 'Cont4', 1, 4, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest8', 'Cont6', 1, 6, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest8', 'Cont7', 1, 7, 1, 111111111);

--P5 LOAD 
--CM_ID9 VAZIO

--P5 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest10', 'Cont11', 2, 1, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest10', 'Cont12', 2, 2, 1, 111111111);
INSERT INTO ShipCargoManifest VALUES('CargoManifest10', 'Cont13', 3, 1, 1, 111111111);

--ShipTripStop
INSERT INTO ShipTripStop VALUES('Trip1', '29002', '2021-12-01 09:00:00', '2021-12-01 09:00:00','2021-12-01 10:00:00', 'CargoManifest1', 'CargoManifest2');
INSERT INTO ShipTripStop VALUES('Trip1', '14635', '2021-12-03 09:00:00', '2021-12-03 09:00:00','2021-12-03 10:00:00', 'CargoManifest3', 'CargoManifest4');
INSERT INTO ShipTripStop VALUES('Trip1', '25007', '2021-12-05 09:00:00', '2021-12-05 09:00:00','2021-12-05 10:00:00', 'CargoManifest5', 'CargoManifest6');
INSERT INTO ShipTripStop VALUES('Trip1', '20301', '2021-12-07 09:00:00', '2021-12-07 09:00:00','2021-12-07 10:00:00', 'CargoManifest7', 'CargoManifest8');
INSERT INTO ShipTripStop VALUES('Trip1', '20351', '2021-12-09 09:00:00', '2021-12-09 09:00:00','2021-12-09 10:00:00', 'CargoManifest9', 'CargoManifest10');

--TruckTrip
INSERT INTO TruckTrip VALUES('Trip11', 'Warehouse7', 'Warehouse1', 'Truck1', 'TruckD1');
INSERT INTO TruckTrip VALUES('Trip12', 'Warehouse4', 'Warehouse8', 'Truck2', 'TruckD2');

--CargoManifest
--TRIP_ID11
INSERT INTO CargoManifest VALUES('CargoManifest11', '2021-11-30 07:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest12', '2021-11-30 06:00:00', 'Unload');
INSERT INTO CargoManifest VALUES('CargoManifest13', '2021-11-30 22:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest14', '2021-11-30 21:00:00', 'Unload');

--TRIP_ID12
INSERT INTO CargoManifest VALUES('CargoManifest15', '2021-12-10 10:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest16', '2021-12-10 10:00:00', 'Unload');
INSERT INTO CargoManifest VALUES('CargoManifest17', '2021-12-10 20:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest18', '2021-12-10 20:00:00', 'Unload');

--TruckCargoManifest
--WAREHOUSE 7 LOAD
INSERT INTO TruckCargoManifest VALUES('CargoManifest11', 'Cont4', 1, 1, 1, 'Truck1');
--WAREHOUSE 7 UNLOAD
--CM_ID12 vazio

--WAREHOUSE 1 LOAD
--CM13 vazio

--WAREHOUSE 1 UNLOAD
INSERT INTO TruckCargoManifest VALUES('CargoManifest14', 'Cont4', 1, 1, 1, 'Truck1');

--WAREHOUSE 4 LOAD
INSERT INTO TruckCargoManifest VALUES('CargoManifest15', 'Cont4', 1, 1, 1, 'Truck2');

--WAREHOUSE 4 UNLOAD
--CM_ID16 vazio

--WAREHOUSE 8 LOAD
--CM_ID17 vazio

--WAREHOUSE 8 UNLOAD
INSERT INTO TruckCargoManifest VALUES('CargoManifest18', 'Cont4', 1, 1, 1, 'Truck2');

--TruckTripStop
INSERT INTO TruckTripStop VALUES('Trip11', 'Warehouse7', '2021-11-30 06:00:00', '2021-11-30 06:00:00', '2021-11-30 07:00:00', 'CargoManifest11', 'CargoManifest12');
INSERT INTO TruckTripStop VALUES('Trip11', 'Warehouse1', '2021-11-30 21:00:00', '2021-11-30 21:00:00', '2021-11-30 22:00:00', 'CargoManifest13', 'CargoManifest14');

INSERT INTO TruckTripStop VALUES('Trip12', 'Warehouse4', '2021-12-10 09:00:00', '2021-12-10 09:00:00','2021-12-10 10:00:00', 'CargoManifest15', 'CargoManifest16');
INSERT INTO TruckTripStop VALUES('Trip12', 'Warehouse8', '2021-12-10 20:00:00', '2021-12-10 20:00:00','2021-12-10 21:00:00', 'CargoManifest17', 'CargoManifest18');

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--****************************************************************************************************************************************************************************--
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--TRIP2 
--Trip
INSERT INTO Trip VALUES('Trip2', '2021-12-02 09:00:00', '2021-12-11 15:00:00', '2021-12-11 15:00:00');

--ShipTrip
INSERT INTO ShipTrip VALUES('Trip2', '22226', '27248', 222222222, 'Crew2');  

--CargoManifest
--P7
INSERT INTO CargoManifest VALUES('CargoManifest21', '2021-12-02 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest22', '2021-12-02 09:00:00', 'Unload');
--P2
INSERT INTO CargoManifest VALUES('CargoManifest23', '2021-12-04 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest24', '2021-12-04 09:00:00', 'Unload');
--P3
INSERT INTO CargoManifest VALUES('CargoManifest25', '2021-12-07 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest26', '2021-12-07 09:00:00', 'Unload');
--P6
INSERT INTO CargoManifest VALUES('CargoManifest27', '2021-12-11 09:00:00', 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest28', '2021-12-11 09:00:00', 'Unload');

--ShipCargoManifest
--P7 LOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest21', 'Cont14', 1, 1, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest21', 'Cont15', 1, 2, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest21', 'Cont16', 1, 3, 1, 222222222);

--P7 UNLOAD
--CM_ID12 VAZIO

--P2 LOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest23', 'Cont1', 2, 1, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest23', 'Cont3', 2, 2, 1, 222222222);

--P2 UNLOAD
--CM_ID14 VAZIO
--P3 LOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest25', 'Cont2', 3, 1, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest25', 'Cont9', 3, 2, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest25', 'Cont10', 3, 3, 1, 222222222);

--P3 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest26', 'Cont1', 2, 1, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest26', 'Cont3', 2, 2, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest26', 'Cont14', 3, 2, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest26', 'Cont15', 3, 3, 1, 222222222);

--P6 LOAD
--CM_ID17 VAZIO

--P6 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest28', 'Cont2', 3, 1, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest28', 'Cont9', 3, 2, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest28', 'Cont10', 3, 3, 1, 222222222);
INSERT INTO ShipCargoManifest VALUES('CargoManifest28', 'Cont16', 1, 3, 1, 222222222);

--ShipTripStop
INSERT INTO ShipTripStop VALUES('Trip2', '22226', '2021-12-02 09:00:00', '2021-12-02 09:00:00', '2021-12-02 10:00:00', 'CargoManifest21', 'CargoManifest22');
INSERT INTO ShipTripStop VALUES('Trip2', '14635', '2021-12-04 09:00:00', '2021-12-04 09:00:00', '2021-12-04 10:00:00', 'CargoManifest23', 'CargoManifest24');
INSERT INTO ShipTripStop VALUES('Trip2', '25007', '2021-12-07 09:00:00', '2021-12-07 09:00:00', '2021-12-07 10:00:00', 'CargoManifest25', 'CargoManifest26');
INSERT INTO ShipTripStop VALUES('Trip2', '27248', '2021-12-11 09:00:00', '2021-12-11 09:00:00', '2021-12-11 10:00:00', 'CargoManifest27', 'CargoManifest28');


--US310
----------------------------------------------------------------

--TRIP 11, 1, 12 - CONTAINERS STAY IN WAREHOUSE
--TRIP11 - W7 -> 29002
INSERT INTO Stay VALUES('Cont4', 'Warehouse7', '2021-11-29 06:00:00', '2021-11-30 06:00:00');

--TRIP1 - 29002(P1) -> 14635(P2) -> 25007(P3) -> 20301(P4) -> 20351(P5)
--29002 - WH1
INSERT INTO Stay VALUES('Cont1', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 10:00:00');
INSERT INTO Stay VALUES('Cont2', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 10:00:00');
INSERT INTO Stay VALUES('Cont3', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 10:00:00');
INSERT INTO Stay VALUES('Cont4', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 09:00:00');
INSERT INTO Stay VALUES('Cont5', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 09:00:00');
INSERT INTO Stay VALUES('Cont6', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 09:00:00');
INSERT INTO Stay VALUES('Cont7', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 09:00:00');
INSERT INTO Stay VALUES('Cont8', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 09:00:00');
INSERT INTO Stay VALUES('Cont9', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 09:00:00');
INSERT INTO Stay VALUES('Cont10', 'Warehouse1', '2021-11-22 09:00:00', '2021-12-01 09:00:00');

--14635 - WH2
INSERT INTO Stay VALUES('Cont1', 'Warehouse2', '2021-12-03 09:00:00', '2021-12-05 10:00:00');
INSERT INTO Stay VALUES('Cont3', 'Warehouse2', '2021-12-03 09:00:00', '2021-12-05 10:00:00');
INSERT INTO Stay VALUES('Cont5', 'Warehouse2', '2021-12-03 09:00:00', '2021-12-05 09:00:00');

INSERT INTO Stay VALUES('Cont11', 'Warehouse2', '2021-12-01 09:00:00', '2021-12-03 09:00:00');
INSERT INTO Stay VALUES('Cont12', 'Warehouse2', '2021-12-01 09:00:00', '2021-12-03 09:00:00');

--25007 - WH3
INSERT INTO Stay VALUES('Cont2', 'Warehouse3', '2021-12-05 09:00:00', '2021-12-10 10:00:00');
INSERT INTO Stay VALUES('Cont8', 'Warehouse3', '2021-12-05 09:00:00', '2021-12-10 09:00:00');
INSERT INTO Stay VALUES('Cont9', 'Warehouse3', '2021-12-05 09:00:00', '2021-12-10 09:00:00');
INSERT INTO Stay VALUES('Cont10', 'Warehouse3', '2021-12-05 09:00:00', '2021-12-10 09:00:00');

--20301 - WH4
INSERT INTO Stay VALUES('Cont13', 'Warehouse4', '2021-12-05 09:00:00', '2021-12-07 09:00:00');

INSERT INTO Stay VALUES('Cont4', 'Warehouse4', '2021-12-07 09:00:00', '2021-12-10 09:00:00');
INSERT INTO Stay VALUES('Cont6', 'Warehouse4', '2021-12-07 09:00:00', '2021-12-11 09:00:00');
INSERT INTO Stay VALUES('Cont7', 'Warehouse4', '2021-12-07 09:00:00', '2021-12-11 09:00:00');

--20351 - WH5
INSERT INTO Stay VALUES('Cont11', 'Warehouse5', '2021-12-09 09:00:00', '2021-12-20 09:00:00');
INSERT INTO Stay VALUES('Cont12', 'Warehouse5', '2021-12-09 09:00:00', '2021-12-20 09:00:00');
INSERT INTO Stay VALUES('Cont13', 'Warehouse5', '2021-12-09 09:00:00', '2021-12-20 09:00:00');

--TRIP12 - 20351 -> WH8
INSERT INTO Stay VALUES('Cont4', 'Warehouse8', '2021-12-10 21:00:00', '2021-12-15 10:00:00');

--------------------------------------------------------------------------------
--TRIP3
--Trip
--Ship
INSERT INTO Trip VALUES('Trip3', SYSDATE+8, SYSDATE+15, SYSDATE+15);
--Truck
INSERT INTO Trip VALUES('Trip31', SYSDATE+7, SYSDATE+7, SYSDATE+7);
INSERT INTO Trip VALUES('Trip32', SYSDATE+16, SYSDATE+16, SYSDATE+16);

--ShipTrip
INSERT INTO ShipTrip VALUES('Trip3', '29002', '20351', 555555555, 'Crew1');  

--CargoManifest
--P1
INSERT INTO CargoManifest VALUES('CargoManifest30', SYSDATE+8, 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest31', SYSDATE+8, 'Unload');
--P2
INSERT INTO CargoManifest VALUES('CargoManifest32', SYSDATE+9, 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest33', SYSDATE+9, 'Unload');
--P3
INSERT INTO CargoManifest VALUES('CargoManifest34', SYSDATE+10, 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest35', SYSDATE+10, 'Unload');
--P4
INSERT INTO CargoManifest VALUES('CargoManifest36', SYSDATE+11, 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest37', SYSDATE+11, 'Unload');
--P5
INSERT INTO CargoManifest VALUES('CargoManifest38', SYSDATE+12, 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest39', SYSDATE+12, 'Unload');

--ShipCargoManifest
--P1 LOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont1', 1, 1, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont2', 1, 2, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont3', 1, 3, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont4', 1, 4, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont5', 1, 5, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont6', 1, 6, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont7', 1, 7, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont8', 1, 8, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont9', 1, 9, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest30', 'Cont10', 1, 10, 1, 555555555);

--P1 UNLOAD 
--CM_ID2 VAZIO

--P2 LOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest32', 'Cont11', 2, 1, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest32', 'Cont12', 2, 2, 1, 555555555);

--P2 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest33', 'Cont1', 1, 1, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest33', 'Cont3', 1, 3, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest33', 'Cont5', 1, 5, 1, 555555555);

--P3 LOAD
--CM_ID5 VAZIO

--P3 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest35', 'Cont2', 1, 2, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest35', 'Cont8', 1, 8, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest35', 'Cont9', 1, 9, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest35', 'Cont10', 1, 10, 1, 555555555);

--P4 LOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest36', 'Cont13', 3, 1, 1, 555555555);

--P4 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest37', 'Cont4', 1, 4, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest37', 'Cont6', 1, 6, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest37', 'Cont7', 1, 7, 1, 555555555);

--P5 LOAD 
--CM_ID9 VAZIO

--P5 UNLOAD
INSERT INTO ShipCargoManifest VALUES('CargoManifest39', 'Cont11', 2, 1, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest39', 'Cont12', 2, 2, 1, 555555555);
INSERT INTO ShipCargoManifest VALUES('CargoManifest39', 'Cont13', 3, 1, 1, 555555555);

--ShipTripStop
INSERT INTO ShipTripStop VALUES('Trip3', '29002', SYSDATE+8, SYSDATE+8, SYSDATE+8, 'CargoManifest30', 'CargoManifest31');
INSERT INTO ShipTripStop VALUES('Trip3', '14635', SYSDATE+9, SYSDATE+9, SYSDATE+9, 'CargoManifest32', 'CargoManifest33');
INSERT INTO ShipTripStop VALUES('Trip3', '25007', SYSDATE+10, SYSDATE+10, SYSDATE+10, 'CargoManifest34', 'CargoManifest35');
INSERT INTO ShipTripStop VALUES('Trip3', '20301', SYSDATE+11, SYSDATE+11, SYSDATE+11, 'CargoManifest36', 'CargoManifest37');
INSERT INTO ShipTripStop VALUES('Trip3', '20351', SYSDATE+12, SYSDATE+12, SYSDATE+12, 'CargoManifest38', 'CargoManifest39');

--TruckTrip
INSERT INTO TruckTrip VALUES('Trip31', 'Warehouse7', 'Warehouse1', 'Truck1', 'TruckD1');
INSERT INTO TruckTrip VALUES('Trip32', 'Warehouse4', 'Warehouse8', 'Truck2', 'TruckD2');

--CargoManifest
--TRIP_ID11
INSERT INTO CargoManifest VALUES('CargoManifest61', SYSDATE+7, 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest62', SYSDATE+7, 'Unload');
INSERT INTO CargoManifest VALUES('CargoManifest63', SYSDATE+7, 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest64', SYSDATE+7, 'Unload');

--TRIP_ID12
INSERT INTO CargoManifest VALUES('CargoManifest65', SYSDATE+16, 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest66', SYSDATE+16, 'Unload');
INSERT INTO CargoManifest VALUES('CargoManifest67', SYSDATE+16, 'Load');
INSERT INTO CargoManifest VALUES('CargoManifest68', SYSDATE+16, 'Unload');

--TruckCargoManifest
--WAREHOUSE 7 LOAD
INSERT INTO TruckCargoManifest VALUES('CargoManifest61', 'Cont4', 1, 1, 1, 'Truck1');
--WAREHOUSE 7 UNLOAD
--CM_ID12 vazio

--WAREHOUSE 1 LOAD
--CM13 vazio

--WAREHOUSE 1 UNLOAD
INSERT INTO TruckCargoManifest VALUES('CargoManifest64', 'Cont4', 1, 1, 1, 'Truck1');

--WAREHOUSE 4 LOAD
INSERT INTO TruckCargoManifest VALUES('CargoManifest65', 'Cont4', 1, 1, 1, 'Truck2');

--WAREHOUSE 4 UNLOAD
--CM_ID16 vazio

--WAREHOUSE 8 LOAD
--CM_ID17 vazio

--WAREHOUSE 8 UNLOAD
INSERT INTO TruckCargoManifest VALUES('CargoManifest68', 'Cont4', 1, 1, 1, 'Truck2');

--TruckTripStop
INSERT INTO TruckTripStop VALUES('Trip31', 'Warehouse7', SYSDATE+7, SYSDATE+7, SYSDATE+7, 'CargoManifest61', 'CargoManifest62');
INSERT INTO TruckTripStop VALUES('Trip31', 'Warehouse1', SYSDATE+7, SYSDATE+7, SYSDATE+7, 'CargoManifest63', 'CargoManifest64');

INSERT INTO TruckTripStop VALUES('Trip32', 'Warehouse4', SYSDATE+16, SYSDATE+16, SYSDATE+16, 'CargoManifest65', 'CargoManifest66');
INSERT INTO TruckTripStop VALUES('Trip32', 'Warehouse8', SYSDATE+16, SYSDATE+16, SYSDATE+16, 'CargoManifest67', 'CargoManifest68');




/*
DELETE FROM GravityCenter
DELETE FROM ContainerOperation
DELETE FROM TripStopReport;
DELETE FROM ShipPositioning;
DELETE FROM TransceiverClass;
DELETE FROM Lease;
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
DELETE FROM WarehouseStaff;
DELETE FROM Warehouse;
DELETE FROM PortStaff;
DELETE FROM Seadist;
DELETE FROM Port;
DELETE FROM TruckDriver;
DELETE FROM ShipChiefElectricalEngineer;
DELETE FROM ShipCaptain;
DELETE FROM PortManager;
DELETE FROM WarehouseManager;
DELETE FROM TrafficManager;
DELETE FROM Client;
DELETE FROM FleetManager;
DELETE FROM Employee;
DELETE FROM Auth;
DELETE FROM Border;
DELETE FROM Country;
DELETE FROM Continent;
*/

