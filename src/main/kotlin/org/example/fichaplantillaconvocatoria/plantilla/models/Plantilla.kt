package org.example.fichaplantillaconvocatoria.plantilla.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Clase abstracta que representa a una persona del equipo.
 * Esta clase se utiliza como base para definir jugadores y entrenadores.
 */
@Serializable
abstract class Plantilla(
    val id: Long,
    var nombre: String,
    var apellidos: String,
    var fechaNacimiento: String,
    var fechaIncorporacion: String,
    var salario: Double?,
    var pais: String,
    val rol: String,
    var rutaImagen: String
) {

    companion object{
        val NEW_ID = 1L
    }

    abstract fun copy(
        id: Long = this.id,
        nombre: String = this.nombre,
        apellidos: String = this.apellidos,
        fechaNacimiento: String = this.fechaNacimiento,
        fechaIncorporacion: String = this.fechaNacimiento,
        salario: Double = this.salario!!,
        pais: String = this.pais,
        rol: String = this.rol,
        rutaImagen: String = this.rutaImagen
    ) : Plantilla

    /**
     * Enum que representa los posibles tipos de roles en el equipo.
     */
    @Serializable
    enum class Tipo {
        @SerialName("tipo")
        Jugador, Entrenador,
        @SerialName("")
        NINGUNO
    }
}