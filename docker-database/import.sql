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

LOAD DATA INFILE '/var/lib/mysql-files/estaciones.csv'
INTO TABLE estaciones
CHARACTER SET latin1
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"' -- ESTO ES VITAL para que ignore las comas de las direcciones
LINES TERMINATED BY '\n'   -- Prueba con \n si estás en Docker/Linux, si falla vuelve a \r\n
IGNORE 1 LINES
(id, codigo, descripcion, @lat, @lon, direccion, cp, poblacion, provincia, pais, cercanias, feve, comun)
SET latitud = NULLIF(REPLACE(@lat, ',', '.'), ''),
    longitud = NULLIF(REPLACE(@lon, ',', '.'), '');