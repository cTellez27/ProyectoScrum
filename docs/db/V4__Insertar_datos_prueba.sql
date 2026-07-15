-- 1. Insertar 3 Equipos
INSERT INTO Equipos (Nombre_Equipo, Director_Tecnico) VALUES 
('Leones FC', 'Carlos Ancelotti'),
('Tigres del Sur', 'Pep Guardiola'),
('Aguilas Doradas', 'Marcelo Gallardo');

-- 2. Insertar 15 Jugadores (5 por equipo)
-- Asumiendo IDs 1, 2 y 3 para los equipos recién creados

-- Jugadores de Leones FC (idEquipos = 1)
INSERT INTO Jugadores (Nombre_Jugador, Posicion, Edad, Equipos_idEquipos) VALUES
('Juan Pérez', 'Portero', 25, 1),
('Luis Díaz', 'Defensa', 22, 1),
('Mateo Uribe', 'Mediocampista', 28, 1),
('Radamel Falcao', 'Delantero', 35, 1),
('James Rodríguez', 'Mediocampista', 30, 1);

-- Jugadores de Tigres del Sur (idEquipos = 2)
INSERT INTO Jugadores (Nombre_Jugador, Posicion, Edad, Equipos_idEquipos) VALUES
('Pedro García', 'Portero', 24, 2),
('Andrés Iniesta', 'Mediocampista', 38, 2),
('David Ospina', 'Portero', 33, 2),
('Yerry Mina', 'Defensa', 27, 2),
('Luis Sinisterra', 'Delantero', 23, 2);

-- Jugadores de Aguilas Doradas (idEquipos = 3)
INSERT INTO Jugadores (Nombre_Jugador, Posicion, Edad, Equipos_idEquipos) VALUES
('Carlos Bacca', 'Delantero', 36, 3),
('Wilmar Barrios', 'Mediocampista', 28, 3),
('Dávinson Sánchez', 'Defensa', 26, 3),
('Frank Fabra', 'Defensa', 31, 3),
('Rafael Borré', 'Delantero', 27, 3);