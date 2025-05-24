package org.example.fichaplantillaconvocatoria.validator

import org.example.fichaplantillaconvocatoria.plantilla.dao.JugadorEntity
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toModel
import org.example.fichaplantillaconvocatoria.plantilla.models.Jugador
import org.example.fichaplantillaconvocatoria.plantilla.validator.JugadorValidator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class JugadorValidatorTest {

    private var validator = JugadorValidator()

    @BeforeEach
    fun setUp() {
        validator = JugadorValidator()
    }

    @Nested
    @DisplayName("Casos inválidos de Jugador")
    inner class CasosInvalidos {

        @Test
        @DisplayName("Nombre no puede estar en blanco")
        fun nombreBlanco() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Nombre no puede superar los 15 caracteres")
        fun nombreLargo() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "NombreExcesivamenteLargo",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Apellidos no pueden estar en blanco")
        fun apellidosBlanco() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Apellidos no pueden superar los 30 caracteres")
        fun apellidosLargos() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "ApellidoExcesivamenteLarguisimoDeJugador",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Fecha de nacimiento no puede estar en blanco")
        fun fechaNacimientoBlanca() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Fecha de incorporación no puede estar en blanco")
        fun fechaIncorporacionBlanca() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Salario no puede ser NaN")
        fun salarioNaN() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = Double.NaN,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("País no puede estar en blanco")
        fun paisBlanco() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0,
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Rol no puede estar en blanco")
        fun rolBlanco() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0,
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Dorsal no puede ser null")
        fun dorsalNull() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = null,
                altura = 1.80,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Altura no puede ser NaN")
        fun alturaNaN() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = Double.NaN,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0,
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Altura debe estar entre 0.0 y 2.5 metros")
        fun alturaFueraDeRango() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 3.0,
                peso = 75.0,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0,
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Peso no puede ser NaN")
        fun pesoNaN() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = Double.NaN,
                goles = 5,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0,
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Goles no pueden ser negativos")
        fun golesNegativos() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0,
                goles = -1,
                partidosJugados = 10,
                rutaImagen = "",
                minutosJugados = 50.0,
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

        @Test
        @DisplayName("Partidos jugados no pueden ser negativos")
        fun partidosJugadosNegativos() {
            val jugador = JugadorEntity(
                id = 1L,
                nombre = "Carlos",
                apellidos = "Ramírez",
                fechaNacimiento = "1990-01-01",
                fechaIncorporacion = "2010-01-01",
                salario = 1000.0, pais = "España",
                rol = "Jugador",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 9,
                altura = 1.80,
                peso = 75.0, goles = 5,
                partidosJugados = -3,
                rutaImagen = "",
                minutosJugados = 50.0,
            )
            val modelo = jugador.toModel()
            val result = validator.validate(modelo)
            assertFalse(result.isErr)
        }

    }
}