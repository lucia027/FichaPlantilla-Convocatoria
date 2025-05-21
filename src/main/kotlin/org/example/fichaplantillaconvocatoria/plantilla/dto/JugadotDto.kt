package org.example.fichaplantillaconvocatoria.plantilla.dto

import kotlinx.serialization.Serializable
import org.example.fichaplantillaconvocatoria.plantilla.models.Jugador

/**
 * Clase que representa la transferencia de datos (DTO) de un jugador.
 * Esta clase se utiliza para enviar y recibir datos de un jugador entre diferentes capas de la aplicación o a través de la red.
 */

@Serializable
class JugadorDto(
    val id: Long,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val fechaIncorporacion: String,
    val salario: Double,
    val pais: String,
    val rol: String,
    val posicion: Jugador.Posicion,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val partidosJugados: Int,
    val minutosJugados: Double,
    var rutaImagen: String
) : java.io.Serializable