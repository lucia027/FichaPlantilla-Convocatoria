package org.example.fichaplantillaconvocatoria.validator

import org.example.fichaplantillaconvocatoria.plantilla.dao.PlantillaEntity
import org.example.fichaplantillaconvocatoria.plantilla.error.PlantillaError
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toModel
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import org.example.fichaplantillaconvocatoria.plantilla.validator.PlantillaValidator
import org.example.fichaplantillaconvocatoria.plantilla.validator.Validator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/*
 * Clase que implementa los test del validador de la plantilla
 */
class PlantillaValidatorTest {

    private lateinit var validator: Validator<Plantilla, PlantillaError>

    @BeforeEach
    fun setUp() {
        validator = PlantillaValidator()
    }

    @Nested
    @DisplayName("Casos correctos")
    inner class CasosCorrectos {

        @Test
        @DisplayName("Deberia devolver un miembro de la plantilla, totalmente valido")
        fun plantillaOk() {
            val plantilla = PlantillaEntity(
                id = 1,
                nombre = "Pepe",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = Entrenador.Especializacion.ENTRENADOR_PORTEROS.toString(),
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()

            val plantillaTrue = validator.validate(modelo)

            assertTrue(plantillaTrue.isOk)
        }
    }

    @Nested
    @DisplayName("Casos incorrectos")
    inner class CasosIncorrectos {
        @Test
        @DisplayName("Deberia de devolver un miembro de la plantilla invalido")
        fun plantillaNotOK() {
            val plantilla = PlantillaEntity(
                id = 0L,
                nombre = "Pepe",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = Entrenador.Especializacion.ENTRENADOR_PORTEROS.toString(),
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()

            val plantillaInvalida = validator.validate(modelo)

            assertFalse(plantillaInvalida.isErr)
        }

        @Test
        @DisplayName("Debería fallar si el nombre está en blanco")
        fun nombreVacio() {
            val plantilla = PlantillaEntity(
                id = 1L,
                nombre = "",
                apellidos = "Gomez",
                fechaNacimiento = "1980-01-01",
                fechaIncorporacion = "2000-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Debería fallar si el nombre tiene más de 15 caracteres")
        fun nombreLargo() {
            val plantilla = PlantillaEntity(
                id = 1L,
                nombre = "NombreExcesivamenteLargo",
                apellidos = "Gomez",
                fechaNacimiento = "1980-01-01",
                fechaIncorporacion = "2000-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Debería fallar si los apellidos están en blanco")
        fun apellidosVacios() {
            val plantilla = PlantillaEntity(
                id = 1L,
                nombre = "Pedro",
                apellidos = "",
                fechaNacimiento = "1980-01-01",
                fechaIncorporacion = "2000-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Debería fallar si los apellidos tienen más de 30 caracteres")
        fun apellidosLargos() {
            val plantilla = PlantillaEntity(
                id = 1L,
                nombre = "Pedro",
                apellidos = "ApellidoExcesivamenteExtremadamenteLargo",
                fechaNacimiento = "1980-01-01",
                fechaIncorporacion = "2000-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Debería fallar si la fecha de nacimiento está en blanco")
        fun fechaNacimientoVacia() {
            val plantilla = PlantillaEntity(
                id = 1L,
                nombre = "Pedro",
                apellidos = "Gomez",
                fechaNacimiento = "",
                fechaIncorporacion = "2000-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Debería fallar si la fecha de incorporación está en blanco")
        fun fechaIncorporacionVacia() {
            val plantilla = PlantillaEntity(
                id = 1L,
                nombre = "Pedro",
                apellidos = "Gomez",
                fechaNacimiento = "1980-01-01",
                fechaIncorporacion = "",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Debería fallar si el salario es NaN")
        fun salarioInvalido() {
            val plantilla = PlantillaEntity(
                id = 1L,
                nombre = "Pedro",
                apellidos = "Gomez",
                fechaNacimiento = "1980-01-01",
                fechaIncorporacion = "2000-01-01",
                salario = Double.NaN,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Debería fallar si el país está en blanco")
        fun paisVacio() {
            val plantilla = PlantillaEntity(
                id = 1L,
                nombre = "Pedro",
                apellidos = "Gomez",
                fechaNacimiento = "1980-01-01",
                fechaIncorporacion = "2000-01-01",
                salario = 1000.0,
                pais = "",
                rol = "Entrenador",
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Debería fallar si el rol está en blanco")
        fun rolVacio() {
            val plantilla = PlantillaEntity(
                id = 1L,
                nombre = "Pedro",
                apellidos = "Gomez",
                fechaNacimiento = "1980-01-01",
                fechaIncorporacion = "2000-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "",
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }
    }
}