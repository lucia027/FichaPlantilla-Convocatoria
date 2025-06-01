package org.example.fichaplantillaconvocatoria.plantilla.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.fichaplantillaconvocatoria.plantilla.error.PlantillaError
import org.example.fichaplantillaconvocatoria.plantilla.models.Jugador

/*
 * Clase que desarrolla la funcion validate para jugador
 */
class JugadorValidator : Validator<Jugador, PlantillaError> {
    override fun validate(t: Jugador): Result<Jugador, PlantillaError> {
        if (t.nombre.isBlank()) {
            Err(PlantillaError.PlantillaValidatorError("El nombre no puede estar en blanco"))
        }
        if (t.nombre.length >= 15) {
            Err(PlantillaError.PlantillaValidatorError("El nombre no puede tener mas de 15 caracteres"))
        }
        if (t.apellidos.isBlank()) {
            Err(PlantillaError.PlantillaValidatorError("El apellido no puede estar en blanco"))
        }
        if (t.apellidos.length >= 30) {
            Err(PlantillaError.PlantillaValidatorError("El apellido no puede tener mas de 30 caracteres"))
        }
        if (t.fechaNacimiento.isBlank()) {
            Err(PlantillaError.PlantillaValidatorError("La fecha no puede estar en blanco"))
        }
        if (t.fechaIncorporacion.isBlank()) {
            Err(PlantillaError.PlantillaValidatorError("La fecha de incorporacion no puede estar en blanco"))
        }
        if (t.salario!!.isNaN()) {
            Err(PlantillaError.PlantillaValidatorError("El salario no puede ser nulo"))
        }
        if (t.pais.isBlank()) {
            Err(PlantillaError.PlantillaValidatorError("El pais no puede estar en blanco"))
        }
        if (t.rol.isBlank()) {
            Err(PlantillaError.PlantillaValidatorError("El rol no puede estar en blanco"))
        }
        if (t.posicion == null) {
            Err(PlantillaError.PlantillaValidatorError("El rol no puede estar en blanco"))
        }

        // Validación de dorsal
        if (t.dorsal == null) {
            Err(PlantillaError.PlantillaValidatorError("El dorsal no puede ser nulo"))
        }

        // Validación de altura
        if (t.altura!!.isNaN()) {
            Err(PlantillaError.PlantillaValidatorError("La altura np puede ser nula"))
        }

        // validación de altura entre 0.0 y 2.5 m
        if (t.altura!! !in 0.0..2.5) {
            Err(PlantillaError.PlantillaValidatorError("La altura no puede superar los 2.5m"))
        }

        // Validación de peso
        if (t.peso!!.isNaN()) {
            Err(PlantillaError.PlantillaValidatorError("El peso no puede ser nulo"))
        }

        // Validación de goles
        if (t.goles < 0) {
            Err(PlantillaError.PlantillaValidatorError("Los goles no pueden ser menores que 0"))
        }

        // Validación de partidos jugados
        if (t.partidosJugados < 0) {
            Err(PlantillaError.PlantillaValidatorError("Los partidos jugados no pueden ser menores que 0"))
        }
        return Ok(t)
    }
}