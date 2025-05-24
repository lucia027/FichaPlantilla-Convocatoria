package org.example.fichaplantillaconvocatoria.validator

import org.example.fichaplantillaconvocatoria.plantilla.dao.EntrenadorEntity
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toModel
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador
import org.example.fichaplantillaconvocatoria.plantilla.validator.EntrenadorValidator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EntrenadorValidatorTest {
    private var validator = EntrenadorValidator()

    @BeforeEach
    fun setUp() {
        validator = EntrenadorValidator()
    }

    @Nested
    @DisplayName("Casos inválidos de Entrenador")
    inner class CasosInvalidos {

        @Test
        @DisplayName("Nombre no puede estar en blanco")
        fun nombreBlanco() {
            val entrenador = EntrenadorEntity(
                id = 1L,
                nombre = "",
                apellidos = "García",
                fechaNacimiento = "1980-05-01",
                fechaIncorporacion = "2020-06-01",
                salario = 1200.0,
                pais = "España",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_ASISTENTE,
                rutaImagen = ""
            )
            val modelo = entrenador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Nombre no puede superar los 15 caracteres")
        fun nombreLargo() {
            val entrenador = EntrenadorEntity(
                id = 1L,
                nombre = "NombreDemasiadoLargo",
                apellidos = "García",
                fechaNacimiento = "1980-05-01",
                fechaIncorporacion = "2020-06-01",
                salario = 1200.0,
                pais = "España",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_ASISTENTE,
                rutaImagen = ""
            )
            val modelo = entrenador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Apellidos no pueden estar en blanco")
        fun apellidosBlancos() {
            val entrenador = EntrenadorEntity(
                id = 1L,
                nombre = "Luis",
                apellidos = "",
                fechaNacimiento = "1980-05-01",
                fechaIncorporacion = "2020-06-01",
                salario = 1200.0,
                pais = "España",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_ASISTENTE,
                rutaImagen = ""
            )
            val modelo = entrenador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Apellidos no pueden superar los 30 caracteres")
        fun apellidosLargos() {
            val entrenador = EntrenadorEntity(
                id = 1L,
                nombre = "Luis",
                apellidos = "ApellidoExcesivamenteLarguisimoParaEntrenador",
                fechaNacimiento = "1980-05-01",
                fechaIncorporacion = "2020-06-01",
                salario = 1200.0,
                pais = "España",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_ASISTENTE,
                rutaImagen = ""
            )
            val modelo = entrenador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Fecha de nacimiento no puede estar en blanco")
        fun fechaNacimientoBlanca() {
            val entrenador = EntrenadorEntity(
                id = 1L,
                nombre = "Luis",
                apellidos = "García",
                fechaNacimiento = "",
                fechaIncorporacion = "2020-06-01",
                salario = 1200.0,
                pais = "España",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_ASISTENTE,
                rutaImagen = ""
            )
            val modelo = entrenador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Fecha de incorporación no puede estar en blanco")
        fun fechaIncorporacionBlanca() {
            val entrenador = EntrenadorEntity(
                id = 1L,
                nombre = "Luis",
                apellidos = "García",
                fechaNacimiento = "1980-05-01",
                fechaIncorporacion = "",
                salario = 1200.0,
                pais = "España",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_ASISTENTE,
                rutaImagen = ""
            )
            val modelo = entrenador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Salario no puede ser NaN")
        fun salarioNaN() {
            val entrenador = EntrenadorEntity(
                id = 1L,
                nombre = "Luis",
                apellidos = "García",
                fechaNacimiento = "1980-05-01",
                fechaIncorporacion = "2020-06-01",
                salario = Double.NaN,
                pais = "España",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_ASISTENTE,
                rutaImagen = ""
            )
            val modelo = entrenador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("País no puede estar en blanco")
        fun paisBlanco() {
            val entrenador = EntrenadorEntity(
                id = 1L,
                nombre = "Luis",
                apellidos = "García",
                fechaNacimiento = "1980-05-01",
                fechaIncorporacion = "2020-06-01",
                salario = 1200.0,
                pais = "",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_ASISTENTE,
                rutaImagen = ""
            )
            val modelo = entrenador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Rol no puede estar en blanco")
        fun rolBlanco() {
            val entrenador = EntrenadorEntity(
                id = 1L,
                nombre = "Luis",
                apellidos = "García",
                fechaNacimiento = "1980-05-01",
                fechaIncorporacion = "2020-06-01",
                salario = 1200.0,
                pais = "España",
                rol = "",
                especialidad = Entrenador.Especializacion.ENTRENADOR_ASISTENTE,
                rutaImagen = ""
            )
            val modelo = entrenador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

    }
}