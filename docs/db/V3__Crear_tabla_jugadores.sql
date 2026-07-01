CREATE TABLE IF NOT EXISTS Jugadores (
    idJugadores INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Jugador VARCHAR(100) NOT NULL,
    Posicion VARCHAR(45) NOT NULL,
    Edad INT CHECK (Edad >= 0), -- Validación para evitar edades negativas
    Equipos_idEquipos INT,
    
    -- Restricción de llave foránea para conectar con la tabla Equipos
    FOREIGN KEY (Equipos_idEquipos) REFERENCES Equipos(idEquipos) 
        ON DELETE SET NULL -- Si se borra un equipo, el jugador no se borra, queda sin equipo.
);