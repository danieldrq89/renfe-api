CREATE TABLE estaciones (
    id INT PRIMARY KEY,
    nombre VARCHAR(255),
    latitud DOUBLE,
    longitud DOUBLE
);

-- Este comando le dice a MySQL que chupe los datos del CSV
LOAD DATA INFILE '/var/lib/mysql-files/estaciones.csv'
INTO TABLE estaciones
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS; -- Para saltarse la cabecera del CSV