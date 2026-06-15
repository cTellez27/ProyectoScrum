-- 1. Crear la base de datos
CREATE DATABASE IF NOT EXISTS torneo_futbol;
USE torneo_futbol;

-- 2. Crear tablas
CREATE TABLE Torneos (
    idTorneos INT PRIMARY KEY,
    nombre_Torneo VARCHAR(80),
    Categoria VARCHAR(45),
    Fecha_In DATE,
    Fecha_Fin VARCHAR(45),
    Equipos_idEquipos INT,
    Partidos_idPartidos INT
);

CREATE TABLE Equipos (
    idEquipos INT PRIMARY KEY,
    Nombre_Equipo VARCHAR(45),
    Director_Tecnico VARCHAR(80),
    Jugadores_idJugadores INT,
    Torneos_idTorneos INT,
    Torneos_Equipos_idEquipos INT,
    Torneos_Partidos_idPartidos INT,
    FOREIGN KEY (Torneos_idTorneos) REFERENCES Torneos(idTorneos)
);

CREATE TABLE Partidos (
    idPartidos INT PRIMARY KEY,
    Goles_Local INT,
    Goles_Visitante INT,
    Fechaa_Partido DATE,
    Estado ENUM('Programado', 'Jugado', 'Cancelado'),
    Resultado ENUM('Local', 'Visitante', 'Empate'),
    Equipos_idEquipos INT,
    Equipos_Jugadores_idJugadores INT,
    Usuarios_idUsuarios INT,
    Torneos_idTorneos INT,
    Torneos_Equipos_idEquipos INT,
    Torneos_Partidos_idPartidos INT,
    FOREIGN KEY (Equipos_idEquipos) REFERENCES Equipos(idEquipos),
    FOREIGN KEY (Torneos_idTorneos) REFERENCES Torneos(idTorneos)
);

CREATE TABLE Jugadores (
    idJugadores INT PRIMARY KEY,
    Nombre_Jugador VARCHAR(100),
    Posición VARCHAR(45),
    Edad INT,
    Equipos_idEquipos INT,
    Equipos_Jugadores_idJugadores INT,
    Equipos_Torneos_idTorneos INT,
    Equipos_Torneos_Equipos_idEquipos INT,
    Equipos_Torneos_Partidos_idPartidos INT,
    FOREIGN KEY (Equipos_idEquipos) REFERENCES Equipos(idEquipos)
);

CREATE TABLE Usuarios (
    idUsuarios INT PRIMARY KEY,
    Nombre_Usuario VARCHAR(45),
    Correo_Usuario VARCHAR(45),
    Contraseña VARCHAR(45),
    Rol ENUM('Admin', 'Arbitro', 'Usuario'),
    Torneos_idTorneos INT,
    Torneos_Equipos_idEquipos INT,
    Torneos_Partidos_idPartidos INT,
    FOREIGN KEY (Torneos_idTorneos) REFERENCES Torneos(idTorneos)
);