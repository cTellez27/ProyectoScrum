CREATE TABLE IF NOT EXISTS Equipos (
    idEquipos INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Equipo VARCHAR(45) NOT NULL UNIQUE,
    Director_Tecnico VARCHAR(80) NOT NULL,
    Jugadores_idJugadores INT,
    Torneos_idTorneos INT,
    Torneos_Equipos_idEquipos INT,
    Torneos_Partidos_idPartidos INT,
    
    -- Restricciones de llaves foráneas (ajustar según las tablas referenciadas)
    FOREIGN KEY (Torneos_idTorneos) REFERENCES Torneos(idTorneos)
);