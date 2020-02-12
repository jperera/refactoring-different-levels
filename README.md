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

Este API ofrece las operaciones típicas CRUD:
 * Método POST para la creación        (Create)
 * Métodos GET para la  obtención      (Read)
 * Método UPDATE para la actualización (Update)
 * Método DELETE para el borrado       (Delete)


En todos los cambios debemos mantener los tests del paso anterior funcionando.

## Primer cambio

Queremos mostrar en el "get all" la información de cuál es la imagen principal (utilizada para mostrar en la portada de un producto, por ejmeplo) con
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
La imagen principal es la imagen que está en la posición 1
