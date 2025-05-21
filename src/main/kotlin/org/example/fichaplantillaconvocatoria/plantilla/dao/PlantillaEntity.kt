package org.example.fichaplantillaconvocatoria.plantilla.dao

/**
 * Clase que representa a una persona del equipo como entidad.
 * Esta clase se utiliza como base para definir las entidades de jugadores y entrenadores.
 */
open class PlantillaEntity(
    val id: Long,
    var nombre: String,
    var apellidos: String,
    var fechaNacimiento: String,
    var fechaIncorporacion: String,
    var salario: Double?,
    var pais: String,
    val rol: String,
    var rutaImagen: String
)