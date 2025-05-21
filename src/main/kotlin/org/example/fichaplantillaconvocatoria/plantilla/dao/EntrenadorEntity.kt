package org.example.fichaplantillaconvocatoria.plantilla.dao

import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador

/**
 * Clase que representa a un entrenador del equipo como entidad.
 */
class EntrenadorEntity(
    id: Long,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double?,
    pais: String,
    rol: String,
    var especialidad: Entrenador.Especializacion,
    rutaImagen: String
) : PlantillaEntity(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, rutaImagen)