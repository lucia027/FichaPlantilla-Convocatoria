package org.example.fichaplantillaconvocatoria.plantilla.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Clase que representa a un entrenador del equipo.
 */
class Entrenador(
    id: Long = 0,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double?,
    pais: String,
    rol: String = "Entrenador",
    rutaImagen: String,
    var especialidad: String
) : Plantilla(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, rutaImagen){ //tipo){

    /**
     * Sobreescribe la cadena que se muestra al imprimir el modelo.
     */
    override fun toString(): String{
        return("Entrenador(id=$id, nombre=$nombre, apellidos=$apellidos, fechaNacimiento=$fechaNacimiento, fechaIncorporacion=$fechaIncorporacion, salario=$salario, pais=$pais, especializacion=$especialidad)")
    }

    /**
     * Crea una copia del objeto entrenador con los valores proporcionados.
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
        rutaImagen: String,
    ): Plantilla {
        return Entrenador(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, rutaImagen, this.especialidad)
    }

    /**
     * Enum que representa las especializaciones posibles de un entrenador.
     */
    @Serializable
    enum class Especializacion {
        @SerialName("especialidad")
        ENTRENADOR_ASISTENTE, ENTRENADOR_PORTEROS, ENTRENADOR_PRINCIPAL
    }
}