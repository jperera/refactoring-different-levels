# Refactoring different levels kata
Kata para practicar refactoring en diferentes niveles: código, contrato API REST, cambios en arquitectura,...

## Punto de partida

Disponemos de un API REST para gestionar fotos. Cada foto tiene esta estructura:

`
{
    "id": 1
    "position": 1,
    "title": "Una foto",
    "url": "http://fotos.org/unafoto.jpg"
}

`

Este API ofrece las operaciones típicas CRUD:
 * Método POST para la creación        (Create)
 * Método GET para la  obtenición      (Read)
 * Método UPDATE para la actualización (Update)
 * Método DELETE para el borrado       (Delete)


