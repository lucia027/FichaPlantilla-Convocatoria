package org.example.fichaplantillaconvocatoria.plantilla.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Clase que representa a un jugador del equipo.
 */
class Jugador(
    id: Long = 0,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double?,
    pais: String,
    rol: String = "Jugador",
    rutaImagen: String = "",
    var posicion: String?,
    var dorsal: Int?,
    var altura: Double?,
    var peso: Double?,
    var goles: Int,
    var partidosJugados: Int,
    var minutosJugados: Double?
): Plantilla(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, rutaImagen) {

    /**
     * Sobreescribe la cadena que se muestra al imprimir el modelo.
     */
    override fun toString(): String {
        return ("Jugador(id=$id, nombre=$nombre, apellidos=$apellidos, fechaNacimiento=$fechaNacimiento, fechaIncorporacion=$fechaIncorporacion, salario=$salario, pais=$pais, posiciom=$posicion, dorsal=$dorsal, altura=$altura, peso=$peso, goles=$goles, partidosJugados=$partidosJugados, minutosJugados=$minutosJugados)")
    }

    /**
     * Crea una copia del objeto jugador con los valores proporcionados.
     */
    override fun copy(
        id: Long,
        nombre: String,
        apellidos: String,
        fechaNacimiento: String,
        fechaIncorporacion: String,
        salario: Double,
        pais: String,
        rol: String,
        rutaImagen: String
    ): Plantilla {
        return Jugador(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, rutaImagen, this.posicion, this.dorsal, this.altura, this.peso, this.goles, this.partidosJugados, this.minutosJugados)
    }

    /**
     * Enum que representa las posibles posiciones de un jugador en el campo.
     */
    @Serializable
    enum class Posicion {
        @SerialName("posicion")
        DEFENSA, CENTROCAMPISTA, DELANTERO, PORTERO, NINGUNO
    }
}