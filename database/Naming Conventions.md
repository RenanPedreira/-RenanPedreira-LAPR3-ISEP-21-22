# Naming Conventions

| **_Object_**| **_Naming_** |
|:------------------------|:------------------------|
|Table|[name]|
|Variable|[table name + variable name]|
|PrimaryKey constraint|["pk" + table name + variable name]|
|ForeignKey constraint|["fk" + table name + variable name]|
|Check value constraint|["ck" + table name + variable name] + check (variable between x and y)|
|Not null|["c" + variable name]|

Exemple:

create table ShipChiefElectricalEngineer(
    shipChiefID         varchar(20)     constraint pkShipChiefID primary key
);


alter table WarehouseStaff add constraint fkWarehouseStaffWarehouseID  foreign key (warehouseID) references Warehouse(warehouseID);