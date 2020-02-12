# Refactoring different levels kata
Kata para practicar refactoring en diferentes niveles: código, contrato API REST, cambios en arquitectura,...

## Punto de partida

Disponemos de un API REST para gestionar imágenes. Cada imagen tiene esta estructura:

```json

{
    "id": 1
    "position": 1,
    "title": "Una foto",
    "url": "http://fotos.org/unafoto.jpg"
}
```

Este API ofrece las operaciones típicas CRUD asociado a los métodos HTTP:
 * Método POST para la creación        (Create)
    * /v1/pictures
 * Métodos GET para la  obtención      (Read)
    * /v1/pictures para pedir todas la imágenes
    * /v1/pictures/{id} para pedir la información de una imagen
 * Método PUT para la actualización (Update)
    * /v1/pictures/{id}
 * Método DELETE para el borrado       (Delete)
    * /v1/pictures/{id}

En todos los cambios debemos mantener los tests del paso anterior funcionando.

## Primer cambio

Queremos mostrar en el "get all" la información de cuál es la imagen principal (utilizada para mostrar en un listado que apezca un producto, por ejemplo) cone
 la siguente estructura:

```json
{
  "pictures": [
    {
        "id": 1,
        "position": 1,
        "title": "Una foto",
        "url": "http://fotos.org/unafoto.jpg"
    },
    {
        "id": 2,
        "position": 2,
        "title": "Otra foto",
        "url": "http://fotos.org/otrafoto.jpg"
    }
   ],
   "mainPictureId": 1
}
```
La imagen principal es la imagen que está en la posición 1. Esto es una lógica interna al API, no es algo que los clientes deban saber.

## Segundo cambio

En lugar de que la imagen sea la de la posición 1, ofreceremos un endpoint para seleccionar qué imagen es la principal:
```
POST /¿version?/pictures/{id}/markAsMain
```

## Tercer cambio

No vamos a mostrar sólo una imagen del producto en un listado o portada, sino varias.

¿Cómo lo harías? ¿Sacarías una nueva versión de API para cambiar el contrato? 
