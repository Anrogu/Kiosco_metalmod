# Metalmod MES - API de Ruteo y Trazabilidad (Viajero Digital)

Backend de misión crítica desarrollado para el control de piso de producción en Metalmod. Este sistema elimina la captura manual de datos, previene el ocultamiento de mermas (SCRAP) y asegura la trazabilidad estricta de las piezas (ACP) mediante la integración de Kioscos Edge y tecnología de códigos de barras.

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 17 / 21
* **Framework:** Spring Boot 3.x
* **Capa de Datos:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL
* **Control de Versiones BD:** Flyway
* **Construcción y Dependencias:** Maven
* **Productividad:** Lombok

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una arquitectura de capas limpia orientada a servicios (Service-Oriented Architecture), implementando un patrón **Facade** para orquestar transacciones complejas de manufactura sin acoplar los dominios.

* **`com.metalmod.mes.model`**: Entidades mapeadas directamente a PostgreSQL (Operador, Maquina, LoteViajero, SesionRuteo, RecetaOperacion).
* **`com.metalmod.mes.repository`**: Interfaces JPA con consultas seguras usando `Optional` para prevenir fallos en tiempo de ejecución.
* **`com.metalmod.mes.service`**: Lógica de negocio dividida por dominios. `TransaccionKioscoService` actúa como el orquestador principal con seguridad `@Transactional`.
* **`com.metalmod.mes.controller`**: Endpoints RESTful que consumen las Raspberry Pi ubicadas en piso.
* **`com.metalmod.mes.dto`**: Objetos de transferencia para sanitizar las peticiones entrantes.

## 🚀 Instalación y Configuración

### 1. Requisitos Previos
* PostgreSQL instalado y corriendo en el puerto `5432`.
* Base de datos creada con el nombre `metalmod_mes`.
* Maven instalado localmente.

### 2. Configuración de Base de Datos
Modifica las credenciales en el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/metalmod_mes
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
