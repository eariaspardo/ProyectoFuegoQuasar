# ProyectoFuegoQuasar
Proyecto de MELI Fuego Quasar


Api que retorna la fuente y contenido del mensaje de auxilio. Para esto, 
se cuenta con tres satélites que permitirán triangular la posición.
El mensaje puede no llegar completo a cada satélite debido al campo de asteroides frente a la nave.

Api rest que  cuenta con tres servicios expuestos

### Heramientas
Java 1.8
Spring 2.6.4
Postman
Maven 3.8

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
        "x": .100.0,
        "y": 75.5
    },
    "message": "este es un mensaje secreto"
}
### Nivel 3

#### Endpoint 1
Este endpoint, recibe informacion del satelite, lo valida y guarda de forma local en una variable Global.
```shell
 api/v1/topsecret_split/{satellite_name} [POST]
```
Se debe enviar como pararametro de URL el nombre del satelite, la informacion debe estar completa en caso contrario arrojara un mensaje de error.
Acepta como body request:

```json
{
    "distance": .100.0,
    "message": ["este", "", "", "mensaje"]
}
```
#### Endpoint 2

```shell

 /api/v1/topsecret_split/{satellite_name} [GET]
 
```
Devuelve la pocision de la nave y el mensaje, siemprey cuando exista la informacion del satelite.
Para cada satelite debe existir cargada la distancia y el mensaje, de lo contrario devolvera un error, con el mesaje de error correspondiente.

### Host de los End Points, estos se encuentran alojados en un EC2 de AWS, por se parte de la capa gratuita no se encuentra habilitado hasta su entrega

[EndPoint AWS api/v1/topsecret/(POST) ip publica(174.4.6.12)](https://thawing-journey-10473.herokuapp.com/swagger-ui.html)



