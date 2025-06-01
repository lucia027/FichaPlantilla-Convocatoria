DELETE FROM plantilla;
ALTER TABLE plantilla ALTER COLUMN id RESTART WITH 1;

INSERT INTO plantilla (id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, especialidad, posicion, dorsal, altura, peso, goles, partidosJugados, rutaImagen, minutosJugados) VALUES
     (1, 'Mark', 'Evans', '1995-01-01', '1995-01-01', 43694.2, 'Japón', 'Jugador', NULL, 'PORTERO', 20, 1.80, 75.97, 78, 61, 'images/mark_evans.png', 8387),
     (2, 'Axel', 'Blaze', '1995-01-01', '1995-01-01', 55100.76, 'Japón', 'Jugador', NULL, 'DELANTERO', 11, 1.70, 61.06, 4, 96, 'images/axel_blaze.png', 12683),
     (3, 'Jude', 'Sharp', '1995-01-01', '1995-01-01', 51083.94, 'Japón', 'Jugador', NULL, 'CENTROCAMPISTA', 9, 1.66, 57.28, 22, 72, 'images/jude_sharp.png', 10010),
     (4, 'Shawn', 'Frost', '1995-01-01', '1995-01-01', 38431.72, 'Japón', 'Jugador', NULL, 'DELANTERO', 20, 1.83, 73.83, 72, 93, 'images/shawn_frost.png', 7737),
     (5, 'Nathan', 'Swift', '1995-01-01', '1995-01-01', 39080.75, 'Japón', 'Jugador', NULL, 'DEFENSA', 3, 1.81, 59.79, 79, 107, 'images/nathan_swift.png', 14453),
     (6, 'Jack', 'Wallside', '1995-01-01', '1995-01-01', 55876.91, 'Japón', 'Jugador', NULL, 'DEFENSA', 7, 1.76, 69.34, 2, 59, 'images/jack_wallside.png', 8099),
     (7, 'Kevin', 'Dragonfly', '1995-01-01', '1995-01-01', 48990.67, 'Japón', 'Jugador', NULL, 'DELANTERO', 3, 1.65, 69.88, 12, 135, 'images/kevin_dragonfly.png', 15746),
     (8, 'Erik', 'Eagle', '1995-01-01', '1995-01-01', 30878.25, 'Estados Unidos', 'Jugador', NULL, 'DELANTERO', 11, 1.64, 63.64, 40, 159, 'images/erik_eagle.png', 7018),
     (9, 'Xavier', 'Foster', '1995-01-01', '1995-01-01', 35175.01, 'Japón', 'Jugador', NULL, 'CENTROCAMPISTA', 5, 1.74, 81.01, 31, 156, 'images/xavier_foster.png', 10318),
     (10, 'Jordan', 'Greenway', '1995-01-01', '1995-01-01', 32386.93, 'Japón', 'Jugador', NULL, 'CENTROCAMPISTA', 7, 1.85, 68.42, 58, 167, 'images/jordan_greenway.png', 8924),
     (11, 'Scott', 'Banyan', '1995-01-01', '1995-01-01', 36795.22, 'Japón', 'Jugador', NULL, 'CENTROCAMPISTA', 1, 1.89, 72.0, 15, 189, 'images/scott_banyan.png', 9243),
     (12, 'Darren', 'LaChance', '1995-01-01', '1995-01-01', 41753.19, 'Japón', 'Jugador', NULL, 'PORTERO', 2, 1.78, 56.0, 6, 116, 'images/darren_lachance.png', 7896),
     (13, 'Byron', 'Love', '1995-01-01', '1995-01-01', 43403.55, 'Corea del Sur', 'Jugador', NULL, 'CENTROCAMPISTA', 4, 1.79, 61.36, 53, 75, 'images/byron_love.png', 14546),
     (14, 'Dave', 'Quagmire', '1995-01-01', '1995-01-01', 35010.61, 'Japón', 'Jugador', NULL, 'DEFENSA', 4, 1.78, 78.96, 9, 186, 'images/dave_quagmire.png', 12246),
     (15, 'Caleb', 'Stonewall', '1995-01-01', '1995-01-01', 55638.33, 'Japón', 'Jugador', NULL, 'DEFENSA', 3, 1.70, 68.34, 37, 168, 'images/caleb_stonewall.png', 10268),
     (16, 'Torch', NULL, '1995-01-01', '1995-01-01', 52612.61, 'Corea del Sur', 'Jugador', NULL, 'DELANTERO', 7, 1.73, 57.66, 49, 94, 'images/torch.png', 17238),
     (17, 'Gazelle', NULL, '1995-01-01', '1995-01-01', 44985.27, 'Corea del Sur', 'Jugador', NULL, 'DELANTERO', 11, 1.82, 58.02, 77, 107, 'images/gazelle.png', 7385),
     (18, 'Austin', 'Hobbes', '1995-01-01', '1995-01-01', 45601.24, 'Japón', 'Jugador', NULL, 'DELANTERO', 2, 1.72, 55.22, 70, 134, 'images/austin_hobbes.png', 15766),
     (19, 'Seymour', 'Hillman', '1955-03-15', '1995-01-01', 65000.0, 'Inglaterra', 'Entrenador', 'ENTRENADOR_PRINCIPAL', NULL, NULL, NULL, NULL, NULL, 'images/seymour_hillman.png', NULL),
     (20, 'Percival', 'Travis', '1958-08-22', '1995-01-01', 62000.0, 'Estados Unidos', 'Entrenador', 'ENTRENADOR_ASISTENTE', NULL, NULL, NULL, NULL, NULL, 'images/percival_travis.png', NULL),
     (21, 'Aquilina', 'Schiller', '1962-11-10', '1995-01-01', 60000.0, 'Alemania', 'Entrenador', 'ENTRENADOR_PORTEROS', NULL, NULL, NULL, NULL, NULL, 'images/aquilina_schiller.png', NULL);