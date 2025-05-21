package org.example.fichaplantillaconvocatoria.plantilla.error
/**
 * Clase que almacena los diferentes tipos de errores que se podrían dar en el programa
 */
sealed class PlantillaError(msg:String) {
    class PlantillaValidatorError(msg:String) : PlantillaError(msg) //Error que se mostrará en el validador
    class PlantillaIdNotFound(msg:String) : PlantillaError(msg) //Error que se mostrará cuando no se encuentre el id
    class PlantillaInvalidId(msg:String) : PlantillaError(msg) //Error que se mostrará cuando el identificador no se válido
    class PlantillaStorageError(msg:String) : PlantillaError(msg) //Error que se mostrará cuando el storage del programa no encuentre el archivo
}