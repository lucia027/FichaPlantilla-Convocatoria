package org.example.fichaplantillaconvocatoria.plantilla.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador

/**
 * Clase que representa la transferencia de datos (DTO) de un entrenador.
 * Esta clase se utiliza para enviar y recibir datos de un entrenador entre diferentes capas de la aplicación o a través de la red.
 */
@Serializable
class EntrenadorDto(
    val id: Long,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val fechaIncorporacion: String,
    val salario: Double,
    val pais: String,
    val rol: String,
    @SerialName("especialidad")
    val especialidad: Entrenador.Especializacion,
    var rutaImagen: String
) : java.io.Serializable