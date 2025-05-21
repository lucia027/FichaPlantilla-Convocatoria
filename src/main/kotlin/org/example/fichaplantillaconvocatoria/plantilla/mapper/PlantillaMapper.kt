package org.example.fichaplantillaconvocatoria.plantilla.mapper

import org.example.fichaplantillaconvocatoria.plantilla.dto.PlantillaDto
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador
import org.example.fichaplantillaconvocatoria.plantilla.models.Jugador
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import org.example.fichaplantillaconvocatoria.plantilla.dao.PlantillaEntity
import org.example.fichaplantillaconvocatoria.plantilla.dao.JugadorEntity
import org.example.fichaplantillaconvocatoria.plantilla.dao.EntrenadorEntity

/**
 * Función que convierte PlantillaDto a un modelo (Jugador)
 */
fun PlantillaDto.toJugador(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fecha_nacimiento,
        fechaIncorporacion = this.fecha_incorporacion,
        salario = this.salario,
        pais = this.pais,
        rol = this.rol,
        posicion = Jugador.Posicion.valueOf(posicion!!).toString(),
        dorsal = this.dorsal ?: 0,
        altura = this.altura ?: 0.0,
        peso = this.peso ?: 0.0,
        goles = this.goles ?: 0,
        partidosJugados = this.partidosJugados ?: 0,
        minutosJugados = this.minutosJugados,
        rutaImagen = this.rutaImagen ?: ""
    )
}

/**
 * Función que convierte PlantillaDto a un modelo (Entrenador)
 */
fun PlantillaDto.toEntrenador(): Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fecha_nacimiento,
        fechaIncorporacion = this.fecha_incorporacion,
        salario = this.salario,
        pais = this.pais,
        rol = this.rol,
        especialidad = Entrenador.Especializacion.valueOf(especialidad!!).toString(),
        rutaImagen = this.rutaImagen ?: ""
    )
}

/**
 * Función de extensión que convierte un PlantillaDto a Plantilla
 */
fun PlantillaDto.toModel(): Plantilla {
    return if (this.rol == "Jugador") {
        Jugador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fechaNacimiento = fecha_nacimiento,
            fechaIncorporacion = fecha_incorporacion,
            salario = salario,
            pais = pais,
            rol = rol,
            posicion = Jugador.Posicion.valueOf(posicion!!).toString(),
            dorsal = dorsal!!,
            altura = altura!!,
            peso = peso!!,
            goles = goles!!,
            partidosJugados = partidosJugados!!,
            minutosJugados = minutosJugados,
            rutaImagen = rutaImagen ?: ""
        )
    } else {
        Entrenador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fechaNacimiento = fecha_nacimiento,
            fechaIncorporacion = fecha_incorporacion,
            salario = salario,
            pais = pais,
            rol = rol,
            especialidad = Entrenador.Especializacion.valueOf(especialidad!!).toString(),
            rutaImagen = rutaImagen ?: ""
        )
    }
}

/**
 * Función que convierte un modelo a una entidad
 */
fun Plantilla.toEntity(): PlantillaEntity {
    return if (rol == "jugador") {
        val jugador = this as Jugador
        JugadorEntity(
            id = jugador.id,
            nombre = jugador.nombre,
            apellidos = jugador.apellidos,
            fechaNacimiento = jugador.fechaNacimiento,
            fechaIncorporacion = jugador.fechaIncorporacion,
            salario = jugador.salario,
            pais = jugador.pais,
            rol = jugador.rol,
            posicion = Jugador.Posicion.valueOf(jugador.posicion!!),
            dorsal = jugador.dorsal!!,
            altura = jugador.altura!!,
            peso = jugador.peso!!,
            goles = jugador.goles!!,
            partidosJugados = jugador.partidosJugados!!,
            minutosJugados = jugador.minutosJugados!!,
            rutaImagen = jugador.rutaImagen!!
        )
    } else {
        val entrenador = this as Entrenador
        EntrenadorEntity(
            id = entrenador.id,
            nombre = entrenador.nombre,
            apellidos = entrenador.apellidos,
            fechaNacimiento = entrenador.fechaNacimiento,
            fechaIncorporacion = entrenador.fechaIncorporacion,
            salario = entrenador.salario,
            pais = entrenador.pais,
            rol = entrenador.rol,
            especialidad = Entrenador.Especializacion.valueOf(entrenador.especialidad),
            rutaImagen = entrenador.rutaImagen
        )
    }
}

/**
 * Función que convierte PlantillaEntity a un modelo (Jugador)
 */
fun PlantillaEntity.toJugador(): Jugador {
    val jugador = this as JugadorEntity
    return Jugador(
        id = jugador.id,
        nombre = jugador.nombre,
        apellidos = jugador.apellidos,
        fechaNacimiento = jugador.fechaNacimiento,
        fechaIncorporacion = jugador.fechaIncorporacion,
        salario = jugador.salario,
        pais = jugador.pais,
        rol = jugador.rol,
        posicion = Jugador.Posicion.valueOf(jugador.posicion!!.toString()).toString(),
        dorsal = jugador.dorsal!!,
        altura = jugador.altura!!,
        peso = jugador.peso!!,
        goles = jugador.goles!!,
        partidosJugados = jugador.partidosJugados!!,
        minutosJugados = jugador.minutosJugados,
        rutaImagen = jugador.rutaImagen
    )
}

/**
 * Función que convierte PlantillaEntity a un modelo (Entrenador)
 */
fun PlantillaEntity.toEntrenador(): Entrenador {
    return Entrenador(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fechaNacimiento = fechaNacimiento,
        fechaIncorporacion = fechaIncorporacion,
        salario = salario,
        pais = pais,
        rol = rol,
        especialidad = Entrenador.Especializacion.ENTRENADOR_PORTEROS.toString(),
        rutaImagen = rutaImagen
    )
}

/**
 * Función que convierte PlantillaEntity a modelo diferenciando entre jugador y entrenador
 */
fun PlantillaEntity.toModel(): Plantilla {
    return if (rol == "jugador") {
        this.toJugador()
    } else {
        this.toEntrenador()
    }
}

/**
 * --- FUNCIONES DE LISTAS ---
 */
@JvmName("dtoToModelList")
fun List<PlantillaDto>.toModel(): List<Plantilla> {
    return map { it.toModel() }
}

@JvmName("modelToDtoList")
fun List<Plantilla>.toDto(): List<PlantillaDto> {
    return map { it.toDto() }
}

@JvmName("entityToModelList")
fun List<PlantillaEntity>.toModel(): List<Plantilla> {
    return map { it.toModel() }
}

@JvmName("PlantillaListToJugadorList")
fun List<Plantilla>.toJugadorList(): List<Jugador> {
    return this.filter { it.rol == "Jugador" }
        .map { it.toJugador() }
}

@JvmName("PlantillaListToEntrenadorList")
fun List<Plantilla>.toEntrenadorList(): List<Entrenador> {
    return this.filter { it.rol == "Entrenador" }
        .map { it.toEntrenador() }
}

/**
 * Extensión para modelo a DTO
 */
fun Plantilla.toDto(): PlantillaDto {
    return if (rol == "jugador") {
        val jugador = this as Jugador
        PlantillaDto(
            id = jugador.id,
            nombre = jugador.nombre,
            apellidos = jugador.apellidos,
            fecha_nacimiento = jugador.fechaNacimiento,
            fecha_incorporacion = jugador.fechaIncorporacion,
            salario = jugador.salario!!,
            pais = jugador.pais,
            rol = jugador.rol,
            posicion = jugador.posicion,
            dorsal = jugador.dorsal,
            altura = jugador.altura,
            peso = jugador.peso,
            goles = jugador.goles,
            partidosJugados = jugador.partidosJugados,
            minutosJugados = jugador.minutosJugados,
            rutaImagen = jugador.rutaImagen,
            especialidad = null
        )
    } else {
        val entrenador = this as Entrenador
        PlantillaDto(
            id = entrenador.id,
            nombre = entrenador.nombre,
            apellidos = entrenador.apellidos,
            fecha_nacimiento = entrenador.fechaNacimiento,
            fecha_incorporacion = entrenador.fechaIncorporacion,
            salario = entrenador.salario!!,
            pais = entrenador.pais,
            rol = entrenador.rol,
            especialidad = entrenador.especialidad,
            rutaImagen = entrenador.rutaImagen!!,
            posicion = null,
            dorsal = null,
            altura = null,
            peso = null,
            goles = null,
            partidosJugados = null,
            minutosJugados = null
        )
    }
}

/**
 * Funcion que tranforma un miembro de la plantilla a entrenador
 */
fun Plantilla.toEntrenador(): Entrenador {
    val entrenador = this as Entrenador
    return Entrenador(
        id = entrenador.id,
        nombre = entrenador.nombre,
        apellidos = entrenador.apellidos,
        fechaNacimiento = entrenador.fechaNacimiento,
        fechaIncorporacion = entrenador.fechaIncorporacion,
        salario = entrenador.salario,
        pais = entrenador.pais,
        rol = entrenador.rol,
        especialidad = entrenador.especialidad,
        rutaImagen = entrenador.rutaImagen,
    )
}

/**
 * Funcion que tranforma un miembro de la plantilla a jugador
 */
fun Plantilla.toJugador(): Jugador {
    val jugador = this as Jugador
    return Jugador(
        id = jugador.id,
        nombre = jugador.nombre,
        apellidos = jugador.apellidos,
        fechaNacimiento = jugador.fechaNacimiento,
        fechaIncorporacion = jugador.fechaIncorporacion,
        salario = jugador.salario,
        pais = jugador.pais,
        rol = jugador.rol,
        posicion = jugador.posicion,
        dorsal = jugador.dorsal,
        altura = jugador.altura,
        peso = jugador.peso,
        goles = jugador.goles,
        partidosJugados = jugador.partidosJugados,
        minutosJugados = jugador.minutosJugados,
        rutaImagen = jugador.rutaImagen,
    )
}