SET NAMES utf8mb4;
USE renfe;

DROP TABLE IF EXISTS estaciones;

CREATE TABLE estaciones (
    id INT PRIMARY KEY,
    codigo VARCHAR(50),
    descripcion VARCHAR(255),
    latitud DOUBLE,
    longitud DOUBLE,
    direccion VARCHAR(500),
    cp VARCHAR(20),
    poblacion VARCHAR(200),
    provincia VARCHAR(200),
    pais VARCHAR(100),
    cercanias VARCHAR(20),
    feve VARCHAR(20),
    comun TEXT
);

-- COMENTADO: No hay archivo estaciones.csv en Jenkins
/*
LOAD DATA INFILE '/var/lib/mysql-files/estaciones.csv'
INTO TABLE estaciones
CHARACTER SET latin1
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(id, codigo, descripcion, @lat, @lon, direccion, cp, poblacion, provincia, pais, cercanias, feve, comun)
SET latitud = NULLIF(REPLACE(@lat, ',', '.'), ''),
    longitud = NULLIF(REPLACE(@lon, ',', '.'), '');
*/

CREATE TABLE rutas (
    route_id VARCHAR(50) PRIMARY KEY,
    route_short_name VARCHAR(100),
    route_long_name VARCHAR(255),
    route_type INT, -- 2 para trenes de cercanías/largo recorrido
    route_color VARCHAR(10),
    route_text_color VARCHAR(10)
);

-- COMENTADO: No hay archivo routes.txt en Jenkins
/*
LOAD DATA INFILE '/var/lib/mysql-files/routes.txt'
INTO TABLE rutas
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;
*/