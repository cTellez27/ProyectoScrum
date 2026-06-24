<div align="center">
  
#  Sistema Gestión de Torneos de Fútbol
## **Fullstack | Proyecto Educativo | API REST + Interfaz Web**
El Sistema de **Gestión de Torneos de Fútbol** es una aplicación diseñada para facilitar la **organización y administración de torneos deportivos**.

---

![Estado](https://img.shields.io/badge/STATUS-En%20Desarrollo-00ffcc?style=for-the-badge&logo=github)
![Licencia](https://img.shields.io/badge/Licencia-MIT-8a2be2?style=for-the-badge)
[![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen.svg?style=for-the-badge)](http://makeapullrequest.com)

</div>

---

<h2 align="center">Descripción del proyecto </h2>
El sistema debe permitir registrar equipos, jugadores, programar partidos, registrar resultados y generar reportes de los partidos pendientes y de los partido jugados. Este proyecto busca mejorar la gestión de torneos, evitando procesos manuales y permitiendo un mejor control de la información.


## 🎯 Visión del Proyecto
Desarrollar una plataforma digital eficiente e intuitiva para la gestión integral de torneos de fútbol, diseñada para organizar, administrar y automatizar procesos clave como la inscripción de equipos, la programación de encuentros y el control de resultados. 

A largo plazo, el sistema se proyecta como una solución altamente escalable, adaptable a diferentes formatos de competición y completamente accesible desde múltiples dispositivos.

---

## 🚀 Objetivos del Proyecto

### 📌 Objetivo General
Desarrollar un sistema robusto que permita gestionar torneos de fútbol de manera organizada, automatizada y eficiente.

### 📌 Objetivos Específicos
* **Optimizar el registro:** Administrar de forma ágil la inscripción de equipos y sus respectivos jugadores.
* **Automatizar el fixture:** Programar y calendarizar los partidos del torneo de manera inteligente.
* **Controlar resultados:** Registrar los marcadores de cada encuentro en tiempo real.
* **Centralizar estadísticas:** Generar automáticamente la tabla de posiciones y permitir la consulta pública de reportes y rendimiento.

---

## 👥 Roles y Actores del Sistema

### 👤 Usuarios de la Aplicación
* **Administrador del Torneo**
  * Creación y configuración de nuevos torneos.
  * Gestión y registro de equipos participantes.
  * Programación y calendarización de partidos.
  * Control y captura de resultados de los encuentros.
* **Usuario / Espectador**
  * Consulta del calendario y estado de los partidos.
  * Visualización en tiempo real de las tablas de posiciones.
  * Acceso al histórico de resultados y estadísticas.

### 💻 Equipo de Desarrollo (Scrum)
* **Scrum Master:** Daniella Rodriguez
* **Frontend Developer:** Charly Jhoan Murillo
* **Backend Developer:** Christian Tellez
* **Database Administrator:** Sebastian

---

## ⚙️ Funcionalidades Principales
* ✨ Registro y gestión de equipos.
* ✨ Inscripción y fichaje de jugadores.
* ✨ Programación automatizada de partidos (Fixture).
* ✨ Registro dinámico de resultados.
* ✨ Cálculo automático de la tabla de posiciones.
* ✨ Módulo de consultas y reportes estadísticos.

---

## 🛠️ Stack Tecnológico

### **Backend & Arquitectura**
* **Java & Spring Boot:** Framework principal para el desarrollo de la API y la lógica de negocio.
* **Apache Maven:** Gestor de dependencias y automatización de la construcción del proyecto.
* **JDK:** Entorno de ejecución y desarrollo de la aplicación.

### **Almacenamiento & Reportes**
* **MariaDB:** Sistema de gestión de bases de datos relacionales para asegurar la integridad de los datos.
* **JasperReports:** Motor especializado para la generación y exportación de reportes detallados.

### **Frontend**
* **HTML5 & CSS3:** Estructura y diseño de la interfaz de usuario, garantizando una experiencia limpia y visual.

### **Herramientas & DevOps**
* **Git:** Control de versiones distribuido.
* **GitHub:** Alojamiento del código fuente y colaboración.
* **Trello:** Gestión del flujo de trabajo bajo el marco de trabajo Scrum.

---
## 📂 Estructura del Código

El proyecto sigue la estructura estándar de una aplicación **Spring Boot** estructurada por capas para mantener el código ordenado, escalable y fácil de mantener por el equipo:

```text
torneo-futbol/
├── src/
│   ├── main/
│   │   ├── java/com/torneo/
│   │   │   ├── controllers/      # Controladores HTTP (Manejan las peticiones del Frontend)
│   │   │   ├── models/           # Entidades de la Base de Datos (Equipos, Jugadores, Partidos)
│   │   │   ├── repositories/     # Interfaces de comunicación con MariaDB (Consultas SQL)
│   │   │   ├── services/         # Lógica de negocio y reglas del torneo
│   │   │   └── TorneoApplication.java # Clase principal para arrancar el sistema
│   │   │
│   │   └── resources/
│   │       ├── static/           # Archivos del Frontend (CSS, Imágenes, JavaScript)
│   │       │   ├── css/
│   │       │   └── js/
│   │       ├── templates/        # Vistas de la aplicación (Archivos HTML)
│   │       ├── reports/          # Plantillas de JasperReports (.jrxml)
│   │       └── application.properties # Configuración de la Base de Datos MariaDB
│   └── test/                     # Pruebas unitarias del sistema
│
├── pom.xml                       # Configuración de dependencias de Apache Maven
└── README.md                     # Documentación del proyecto
```


## 📊 Estado del Proyecto & Tablero de Trabajo

* **Estado Actual:** 🟡 **Próximo a desarrollar** *(Planificación completada, listo para iniciar el Sprint 1).*

> 📋 **Tablero Scrum (Trello):** Puedes seguir el avance del desarrollo y las historias de usuario a través de nuestro tablero oficial.
> 
> [🔗 Acceder al Scrum Board en Trello](https://trello.com/invite/b/69becd1de9749cce0dbfbed3/ATTIdabf7856ef002c61fd0ed0069b30ce78E540341A/scrumboard-torneos)

## Estructura y Flujo de Trabajo (Trello)
Nuestro tablero de Trello está organizado siguiendo las mejores prácticas de Scrum para gestionar las Historias de Usuario (HU). El ciclo de vida de una tarea se divide en las siguientes listas:

 ```text
📋 [1. Backlog (HUs)] ➔ 📝 [2. Por Hacer] ➔ ⏳ [3. En Proceso] ➔ 🧪 [4. Pruebas / QA] ➔ ✅ [5. Hecho]
```

## 🚀 Cómo descargar y ejecutar el proyecto localmente

Sigue estos pasos para clonar el proyecto y configurarlo en tu entorno local.

### 📋 Prerrequisitos

Antes de empezar, asegúrate de tener instalado lo siguiente:
* **Java JDK 17** (o la versión que estés usando en tu proyecto).
* **Maven** 
* **MariaDB Server** activo.
* Un IDE de tu preferencia (IntelliJ IDEA, Eclipse, VS Code).
* **Git** instalado en tu sistema.

---

### 🛠️ Paso a Paso

#### 1. Clonar el repositorio
Abre tu terminal o consola de comandos y ejecuta el siguiente comando para clonar el proyecto:
```bash
https://github.com/cTellez27/ProyectoScrum.git
```

#### 2. Configurar la Base de Datos (MariaDB)
Abre tu gestor de base de datos (DBeaver, HeidiSQL, MySQL Workbench, etc.) o la consola de MariaDB.

**Crea una nueva base de datos para el sistema de torneos ejecutando:**

 ```text
SQL
CREATE DATABASE torneo_db;
```

### 3. Configurar las credenciales en Spring Boot
Navega en el proyecto hasta la ruta src/main/resources/application.properties (o application.yml) y asegúrate de modificar las credenciales de conexión con las tuyas:

Properties
 ```text
spring.datasource.url=jdbc:mariadb://localhost:3306/torneo_db
spring.datasource.username=TU_USUARIO_DE_MARIADB
spring.datasource.password=TU_CONTRASENA_DE_MARIADB

# Configuración de Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

#### 4. Ejecutar la aplicación
Abre tu IDE (ej. IntelliJ IDEA).

1. Importa el proyecto como un Proyecto Maven existente.
2. Espera a que se descarguen las dependencias automáticamente (Spring Web, Spring Data JPA, Thymeleaf, etc.).
3. Busca la clase principal del proyecto (la que tiene la anotación @SpringBootApplication) y haz clic en Run.

### 5. Acceder a la aplicación
Una vez que en la consola del IDE o terminal veas el mensaje Started ... Application in ... seconds, abre tu navegador web e ingresa a la siguiente dirección:

Plaintext
 ```text
http://localhost:8080
```
!Ya puedes Navegar sobre el sistema!
