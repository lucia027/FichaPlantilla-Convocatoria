DELETE FROM Pplantilla;

ALTER TABLE plantilla
    ALTER COLUMN id RESTART WITH 1;

INSERT INTO plantilla (nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, posicion, dorsal, altura, peso, goles, partidosJugados, especialidad, rutaImagen, minutosJugados) VALUES
         ('Juan', 'Pérez', '1990-05-10', '2022-01-15', 35000, 'España', 'Delantero', 'Extremo', 7, 1.80, 75, 15, 30, 'Regateador', 'images/juan_perez.png', 2100),
         ('María', 'Gómez', '1995-09-22', '2023-07-01', 32000, 'Argentina', 'Defensa', 'Central', 4, 1.75, 65, 2, 25, 'Marcadora', 'images/maria_gomez.png', 1800),
         ('Luis', 'Fernández', '1992-11-30', '2021-08-20', 40000, 'Uruguay', 'Portero', NULL, 1, 1.88, 82, 0, 40, 'Atajador', NULL, 3600),
         ('Ana', 'Ríos', '1998-03-12', '2023-02-10', 30000, 'México', 'Centrocampista', 'Ofensivo', 8, 1.68, 60, 5, 28, 'Pasadora', 'images/ana_rios.png', 1900),
         ('Pedro', 'Sosa', '1988-07-19', '2020-09-05', 42000, 'Chile', 'Defensa', 'Lateral', 2, 1.82, 78, 1, 45, 'Rápido', NULL, 4100),
         ('Lucía', 'Martínez', '1996-11-05', '2022-12-12', 31000, 'Colombia', 'Delantero', 'Centro', 9, 1.73, 62, 18, 33, 'Rematadora', 'images/lucia_martinez.png', 2030),
         ('Mateo', 'Ramírez', '1994-02-21', '2023-03-03', 39000, 'Ecuador', 'Centrocampista', 'Defensivo', 6, 1.77, 68, 3, 29, 'Recuperador', NULL, 1780),
         ('Sofía', 'López', '1997-08-18', '2024-01-20', 32500, 'Perú', 'Delantero', 'Extremo', 11, 1.65, 59, 12, 21, 'Velocista', 'images/sofia_lopez.png', 1000),
         ('Carlos', 'Navarro', '1991-12-01', '2021-11-13', 41000, 'Paraguay', 'Defensa', 'Central', 5, 1.83, 80, 0, 38, 'Fuerte', NULL, 3400),
         ('Valentina', 'Ruiz', '2000-04-27', '2024-05-01', 29500, 'Venezuela', 'Portero', NULL, 12, 1.74, 67, 0, 12, 'Reflejos', 'images/valentina_ruiz.png', 800);
