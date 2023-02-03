# ProyectoFuegoQuasar
Proyecto de MELI Fuego Quasar

Api que retorna la fuente y contenido del mensaje de auxilio. Para esto, 
se cuenta con tres satélites que permitirán triangular la posición.
El mensaje puede no llegar completo a cada satélite debido al campo de asteroides frente a la nave.

### Patron usado
#### CQRS 
Tenemos un único sistema que se encarga de realizar operaciones de negocio y nos permite consultar la información en la que se encuentra nuestro sistema. Manejando servicios de tipo monolitico al tratarse de un proyecto pequeño.
Este patron divide en proyecto en dos subsistemas uno responsable de las peticiones y otro de las consultas 

![modelo CQRS](https://miro.medium.com/v2/resize:fit:720/format:webp/1*QF4XnD2Zhmv_K-85SOtPIA.png)

#### Singleton 
Patron usado por defecto por Spring y que permite la inyeccionde dependencias al crear una unica instancia.

#### Vista de Paquetes
En la vista de paquetes se definen los paquetes que tiene el proyecto de software. Cada Paquete es una capa y agrupa un conjunto de clases con responsabilidades, los paquetes son:

![vista de paquetes](https://github.com/eariaspardo/ProyectoFuegoQuasar/blob/main/src/main/resources/assets/Modelo%20de%20paquetes.PNG?raw=true)

- controller: Todos las clases que reciben peticiones HTTP a los end point definidos
- services: Todas las clases con la lógica de negocio de localizacion de la nave y construccion del mensaje
- entidades: Todas las clases que representan el negocio.
- constants: La clase que maneja variables contantes para la aplicacion.
- validation: Dependencias con librerías externas.
- exceptions: Todas las excepciones para el manejo de errores controlados.

### Heramientas
- Java 1.8
- Spring 2.6.4
- Postman
- Maven 3.8
- AWS EC2 (creacion de una instancia para subir el .jar)

Api rest que cuenta con tres servicios expuestos

### Nivel 1 - Nivel 2

#### Endpoint 1
```shell
/api/v1/topsecret/ [POST]
```
Este endpoint recibe informacion de los tres satelite, en caso de que la informacion no este OK respondera un mensaje de error.
Si toda la informacion esta completa devolvera la posición y el mensaje.

Acepta un json como el siguiente
```json
{
    "satellites": [
        {
            "name": "kenobi",
            "distance": 100.0,
            "message": [
                "este","","","mensaje",""
            ]
        },
        {
            "name": "skywalker",
            "distance": 115.5,
            "message": [
                "","es","","","secreto"
            ]
        },
        {
            "name": "sato",
            "distance": 142.7,
            "message": [
                "este","","un","",""
            ]
        }
    ]
}
```
Y como respuesta obtenemos un json, por ejemplo
```json
{
    "position": {
        "x": 100.0,
        "y": 75.5
    },
    "message": "este es un mensaje secreto"
}
```
### Nivel 3

#### Endpoint 1
Este endpoint, recibe informacion del satelite, lo valida y guarda de forma local en una variable Global
```shell
/api/v1/topsecret_split/{satellite_name} [POST]
```
Se debe enviar como pararametro de URL el nombre del satelite, la informacion debe estar completa en caso contrario arrojara un mensaje de error.
Acepta como body request

```json
{
    "distance": 100.0,
    "message": ["este", "", "", "mensaje"]
}
```
#### Endpoint 2

```shell
 /api/v1/topsecret_split/{satellite_name} [GET]
```
Devuelve la pocision de la nave y el mensaje, siemprey cuando exista la informacion del satelite.
Para cada satelite debe existir cargada la distancia y el mensaje, de lo contrario devolvera un error, con el mesaje de error correspondiente.

Los End Points se encuentran alojados en un EC2 de AWS, por se parte de la capa gratuita no se encuentra habilitado hasta su entrega para evitar el cierre del EC2 por limite de tiempo

[EndPoint AWS api/v1/topsecret/(POST) ip publica(174.4.6.12)](18.118.144.60:8080/api/v1/topsecret/)

