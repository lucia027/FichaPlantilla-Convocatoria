package org.example.fichaplantillaconvocatoria.plantilla.mapper

import org.example.fichaplantillaconvocatoria.plantilla.dao.EntrenadorEntity
import org.example.fichaplantillaconvocatoria.plantilla.dto.EntrenadorDto
import org.example.fichaplantillaconvocatoria.plantilla.dto.PlantillaDto
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador
import org.example.fichaplantillaconvocatoria.plantilla.viewmodel.PlantillaViewModel.EntrenadorState

/**
 * Función que convierte un modelo a dto
 */
fun Entrenador.toDto(): PlantillaDto {
    return PlantillaDto(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fecha_nacimiento = fechaNacimiento.toString(),
        fecha_incorporacion = fechaIncorporacion.toString(),
        salario = salario!!,
        pais = pais,
        especialidad = especialidad.toString(),
        rol = "Entrenador",
        posicion = "",
        dorsal = null,
        altura = null,
        peso = null,
        goles = null,
        partidosJugados = null,
        rutaImagen = this.rutaImagen,
        minutosJugados = null
    )
}

/**
 * Función que convierte un dto a modelo
 */
fun EntrenadorDto.toModel(): Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        especialidad = especialidad.toString(),
        rol = this.rol,
        rutaImagen = this.rutaImagen
    )
}

/**
 * Función que convierte un modelo a entidad
 */
fun Entrenador.toEntity(): EntrenadorEntity {
    return EntrenadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        especialidad = Entrenador.Especializacion.valueOf(this.especialidad),
        rol = this.rol,
        rutaImagen = this.rutaImagen
    )
}

/**
 * Función que convierte una entidad a modelo
 */
fun EntrenadorEntity.toModel(): Entrenador {
    return Entrenador(
        id = this.id!!,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        especialidad = this.especialidad.toString(),
        rol = this.rol,
        rutaImagen = this.rutaImagen
    )
}

/**
 * Función que convierte un dto a modelo
 */
fun EntrenadorDto.toEntity(): EntrenadorEntity {
    return EntrenadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        especialidad = this.especialidad,
        rol = this.rol,
        rutaImagen = this.rutaImagen
    )
}

/**
 * Función que convierte un state a modelo
 */
fun EntrenadorState.toModel() : Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        especialidad = this.especialidad,
        rol = this.rol,
        rutaImagen = this.rutaImagen.toString()
    )
}