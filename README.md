# Gestor de Herramientas (Entrega Final Desarrollo Web)

## Descripción

Proyecto desarrollado por Juan Manuel Aguiar y Andrés Duarte con el objetivo de gestionar una interfaz con usuarios autenticados que interactúan con un sistema gestor de inventario de herramientas. El sistema cuenta con tres microservicios realizados en Spring Boot, los cuales se comunican con una base de datos MySQL en un servidor MariaDB para el almacenamiento de datos. Además, se ha desarrollado una interfaz de usuarios que se comunica con estos microservicios y está desplegada en un servidor Node.js con el framework Angular.

## Documentación

### BackEnd
#### Microservicio de Autenticación
[Documentación del Microservicio de Autenticación](Documentation/BackEnd/Auth_Service/index.html)
#### Microservicio de Herramientas
[Documentación del Microservicio de Herramientas](Documentation/BackEnd/Tool_Service/index.html)
#### Microservicio de Usuarios
[Documentación del Microservicio de Usuarios](Documentation/BackEnd/User_Service/index.html)
### FrontEnd
[Documentación del FrontEnd](Documentation/FrontEnd/index.html)

## Navegación

En esta página web, encontrarás una barra de navegación que te permitirá acceder a las siguientes opciones:

- **Home**
  - Ver más
- **Herramientas**
  - Crear herramienta
  - Editar
  - Eliminar
- **Usuarios**
  - Crear usuario
  - Editar
  - Eliminar
- **Log-In**
- **Buscador**
  - Buscar

Cada una de las opciones en la barra de navegación tiene una función específica, las cuales se detallan a continuación:

### Opción HOME

Esta es la pestaña principal de la página donde encontrarás todas las herramientas disponibles. Cada herramienta tiene un nombre, una marca, una descripción y una imagen. Además, cada herramienta tiene un botón "Ver más..." que te permitirá obtener información detallada sobre dicha herramienta.

En la parte inferior de la sección de herramientas, encontrarás una paginación que te permitirá desplazarte entre las diferentes herramientas disponibles.

### Opción Herramientas

En esta pestaña, encontrarás una tabla con todas las herramientas. Además, verás botones como "Crear herramienta", "Editar" y "Eliminar". Al hacer clic en "Crear herramienta" o "Editar", se abrirá un formulario donde podrás ingresar la marca y descripción de la herramienta. Dependiendo del botón seleccionado, aparecerá un botón "Crear" o "Actualizar" en el formulario. Si seleccionas "Eliminar", la herramienta seleccionada se eliminará instantáneamente.

### Opción Usuarios

En esta pestaña, encontrarás una tabla con todos los usuarios registrados. También verás botones como "Crear usuario", "Editar" y "Eliminar". Al hacer clic en "Crear usuario" o "Editar", se abrirá un formulario donde podrás ingresar el nombre, apellido y cargo del usuario. Dependiendo del botón seleccionado, aparecerá un botón "Crear" o "Actualizar" en el formulario. Si seleccionas "Eliminar", el usuario seleccionado se eliminará instantáneamente.

### Opción Log-In

En esta pestaña, encontrarás un formulario con dos campos: "Usuario" y "Contraseña". Podrás utilizar este formulario para registrarte en el sitio web. Se realizará una validación de usuario y contraseña para autenticar al usuario. Solo se permitirá el acceso si los datos ingresados son válidos.

### Opción Buscador

Este buscador solo funciona en la página principal (HOME). Permite al usuario buscar herramientas utilizando filtros. Puedes ingresar el nombre de la herramienta o el nombre de la marca, y luego hacer clic en el botón "Buscar" para realizar la búsqueda. Si no se encuentra ninguna herramienta que coincida con los criterios de búsqueda, se mostrará un mensaje indicando que no se encontraron resultados.
