CREATE TABLE IF NOT EXISTS plantilla (
     id IDENTITY NOT NULL PRIMARY KEY,
     nombre VARCHAR(100) NOT NULL,
     apellidos VARCHAR(100) NOT NULL,
     fechaNacimiento DATE NOT NULL,
     fechaIncorporacion DATE NOT NULL,
     salario NUMERIC NOT NULL,
     pais VARCHAR(50) NOT NULL,
     rol VARCHAR(50) NOT NULL,
     especialidad VARCHAR(100),
     posicion VARCHAR(50),
     dorsal INTEGER,
     altura DOUBLE,
     peso DOUBLE,
     goles INTEGER,
     partidosJugados INTEGER,
     rutaImagen VARCHAR(255) DEFAULT 'images/default_profile.png',
     minutosJugados DOUBLE
);