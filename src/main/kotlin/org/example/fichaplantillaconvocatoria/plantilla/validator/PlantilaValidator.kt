package org.example.fichaplantillaconvocatoria.plantilla.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.fichaplantillaconvocatoria.plantilla.error.PlantillaError
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla

/*
 * Clase que implementa la funcion validate
 */
class PlantillaValidator : Validator<Plantilla, PlantillaError> {
    override fun validate(t: Plantilla): Result<Plantilla, PlantillaError> {
        if (t.id == 0L) {
            Err(PlantillaError.PlantillaInvalidId("El id no puede ser 0"))
        }
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
        return Ok(t)
    }
}