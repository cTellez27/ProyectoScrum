# **Produc Backlog**
## Sistema de Gestión de Talleres
*  Product Owner: Christian Camilo Tellez
*  Scrum Master: Daniella Rodriguez
Fecha de creación: Marzo 2026

## Objetivo del Producto
Desarrollar un sistema web para la gestión de torneos de fútbol, permitiendo administrar equipos, jugadores, partidos, resultados y reportes, facilitando la organización del torneo.

## MÓDULO: AUTENTICACIÓN Y USUARIOS
Todo lo relacionado con acceso al sistema y control de usuarios.
| ID    | Backlog            | Descripción                                                                                                | Prioridad |
| ----- | ------------------ | ---------------------------------------------------------------------------------------------------------- | --------- |
| US-01 | Iniciar sesión     | Como administrador, quiero iniciar sesión para acceder al sistema de torneos.                              | **Alta**  |
| US-02 | Cerrar sesión      | Como usuario, quiero cerrar sesión para proteger la información del sistema.                               | **Alta**  |
| US-03 | Recuperar acceso   | Como administrador, quiero recuperar mi contraseña para volver a entrar al sistema si la olvido.           | **Media** |
| US-04 | Gestionar usuarios | Como administrador, quiero registrar y administrar usuarios del sistema para controlar quién tiene acceso. | **Media** |
| US-05 | Asignar roles      | Como administrador, quiero asignar roles (admin, organizador) para definir permisos dentro del sistema.    | **Media** |

## 2. MÓDULO: GESTIÓN DE EQUIPOS
CRUD de equipos participantes del torneo.

   | ID    | Backlog           | Descripción                                                                                        | Prioridad |
| ----- | ----------------- | -------------------------------------------------------------------------------------------------- | --------- |
| US-06 | Registrar equipo  | Como administrador, quiero registrar equipos para incluirlos en el torneo.                         | **Alta**  |
| US-07 | Editar equipo     | Como administrador, quiero modificar la información de un equipo para mantener datos actualizados. | **Alta**  |
| US-08 | Eliminar equipo   | Como administrador, quiero eliminar equipos para mantener organizado el sistema.                   | **Alta**  |
| US-09 | Consultar equipos | Como usuario, quiero visualizar los equipos registrados para conocer los participantes.            | **Alta**  |
| US-10 | Buscar equipo     | Como administrador, quiero buscar un equipo por nombre para encontrarlo rápidamente.               | **Media** |

## 3. MÓDULO: GESTIÓN DE JUGADORES
Administración de los jugadores pertenecientes a cada equipo.

   | ID    | Backlog                  | Descripción                                                                                           | Prioridad |
| ----- | ------------------------ | ----------------------------------------------------------------------------------------------------- | --------- |
| US-11 | Registrar jugador        | Como administrador, quiero registrar jugadores para asociarlos a un equipo.                           | **Alta**  |
| US-12 | Editar jugador           | Como administrador, quiero actualizar la información de un jugador para mantener sus datos correctos. | **Alta**  |
| US-13 | Eliminar jugador         | Como administrador, quiero eliminar jugadores cuando ya no participen en el torneo.                   | **Alta**  |
| US-14 | Consultar jugadores      | Como usuario, quiero visualizar la lista de jugadores para conocer los integrantes de cada equipo.    | **Alta**  |
| US-15 | Asociar jugador a equipo | Como administrador, quiero vincular jugadores a equipos para organizar correctamente la plantilla.    | **Alta**  |
| US-16 | Buscar jugador           | Como administrador, quiero buscar jugadores por nombre o documento para encontrarlos fácilmente.      | **Media** |

## 4. MÓDULO: GESTIÓN DE TORNEOS
Administración del torneo como entidad principal.

| ID    | Backlog                   | Descripción                                                                                 | Prioridad |
| ----- | ------------------------- | ------------------------------------------------------------------------------------------- | --------- |
| US-17 | Crear torneo              | Como administrador, quiero crear un torneo para organizar la competencia.                   | **Alta**  |
| US-18 | Editar torneo             | Como administrador, quiero modificar datos del torneo para actualizar su información.       | **Alta**  |
| US-19 | Eliminar torneo           | Como administrador, quiero eliminar torneos cancelados o no utilizados.                     | **Media** |
| US-20 | Consultar torneos         | Como usuario, quiero visualizar los torneos disponibles para conocer la competencia activa. | **Alta**  |
| US-21 | Asignar equipos al torneo | Como administrador, quiero agregar equipos al torneo para definir los participantes.        | **Alta**  |

## 5. MÓDULO: PROGRAMACIÓN DE PARTIDOS
Organización de fechas, encuentros y horarios.

| ID    | Backlog                | Descripción                                                                                                 | Prioridad |
| ----- | ---------------------- | ----------------------------------------------------------------------------------------------------------- | --------- |
| US-22 | Crear partido          | Como administrador, quiero programar partidos para organizar el calendario del torneo.                      | **Alta**  |
| US-23 | Editar partido         | Como administrador, quiero modificar fecha, hora o equipos de un partido para ajustar la programación.      | **Alta**  |
| US-24 | Eliminar partido       | Como administrador, quiero cancelar o eliminar partidos programados cuando sea necesario.                   | **Media** |
| US-25 | Consultar calendario   | Como usuario, quiero visualizar el calendario de partidos para conocer cuándo se jugarán.                   | **Alta**  |
| US-26 | Asignar fecha y hora   | Como administrador, quiero definir la fecha y hora de cada partido para planificar correctamente el torneo. | **Alta**  |
| US-27 | Asignar cancha o lugar | Como administrador, quiero asignar una cancha a cada partido para organizar los encuentros.                 | **Media** |

## 6. MÓDULO: RESULTADOS Y ESTADÍSTICAS
Registro de resultados deportivos y rendimiento.

| ID    | Backlog              | Descripción                                                                                             | Prioridad |
| ----- | -------------------- | ------------------------------------------------------------------------------------------------------- | --------- |
| US-28 | Registrar resultado  | Como administrador, quiero registrar el marcador de un partido para llevar control de los resultados.   | **Alta**  |
| US-29 | Editar resultado     | Como administrador, quiero corregir un resultado si fue registrado incorrectamente.                     | **Alta**  |
| US-30 | Registrar goles      | Como administrador, quiero registrar los goles anotados por equipo o jugador para generar estadísticas. | **Media** |
| US-31 | Registrar tarjetas   | Como administrador, quiero registrar tarjetas amarillas o rojas para llevar control disciplinario.      | **Media** |
| US-32 | Consultar resultados | Como usuario, quiero visualizar los resultados de los partidos para seguir el torneo.                   | **Alta**  |

## 7. MÓDULO: REPORTES
Salidas e informes del sistema.

| ID    | Backlog                       | Descripción                                                                                                  | Prioridad |
| ----- | ----------------------------- | ------------------------------------------------------------------------------------------------------------ | --------- |
| US-36 | Generar reporte de equipos    | Como administrador, quiero generar un reporte de equipos registrados para tener control de participación.    | **Media** |
| US-37 | Generar reporte de jugadores  | Como administrador, quiero generar un reporte de jugadores por equipo para fines de organización.            | **Media** |
| US-38 | Generar reporte de partidos   | Como administrador, quiero generar un reporte de partidos programados y jugados para seguimiento del torneo. | **Media** |
| US-39 | Generar reporte de resultados | Como administrador, quiero exportar los resultados del torneo para compartirlos o archivarlos.               | **Media** |
| US-40 | Exportar reportes PDF         | Como administrador, quiero descargar reportes en PDF para presentarlos fácilmente.                           | **Baja**  |





