package org.example.fichaplantillaconvocatoria.plantilla.dao

import org.example.fichaplantillaconvocatoria.plantilla.models.Jugador.Posicion

/**
 * Clase que representa a un jugador del equipo como entidad.
 */
class JugadorEntity(
    id: Long,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double?,
    pais: String,
    rol: String,
    var posicion: Posicion?,
    var dorsal: Int?,
    var altura: Double?,
    var peso: Double?,
    var goles: Int,
    var partidosJugados: Int,
    var minutosJugados: Double,
    rutaImagen: String
) : PlantillaEntity(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, rutaImagen)