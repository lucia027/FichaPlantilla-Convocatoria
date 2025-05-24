package org.example.fichaplantillaconvocatoria.repositories

import org.example.fichaplantillaconvocatoria.plantilla.dao.EntrenadorEntity
import org.example.fichaplantillaconvocatoria.plantilla.dao.PlantillaDao
import org.example.fichaplantillaconvocatoria.plantilla.dao.PlantillaEntity
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toModel
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador
import org.example.fichaplantillaconvocatoria.plantilla.repositories.PlantillaRepositoryImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class)
class TestPrueba {

    @Mock
    private lateinit var dao: PlantillaDao

    @InjectMocks
    private lateinit var repository: PlantillaRepositoryImpl

    @Nested
    @DisplayName("Casos correctos")

    inner class CasosCorrectos {

        @Test
        @DisplayName("Deberia de devolver una lista con todos los miembros")
        fun findAll() {
            val entrenadorEntity = EntrenadorEntity(
                id = 1,
                nombre = "Pedro",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_PORTEROS,
                rutaImagen = ""
            )

            whenever(dao.findAll()).thenReturn(listOf(entrenadorEntity))

            val entrandorLista = repository.findAll()

            assertAll(
                { assertNotNull(entrandorLista.size == 1, "Entrenador no nulo") },
                { assertEquals(1, entrandorLista.size, "Entrenador correcto") },
                { assertEquals(1, entrandorLista[0].id, "Casilla id correcta") },
                { assertEquals("Pedro", entrandorLista[0].nombre, "Casilla nombre correcta") },
                { assertEquals("Gutierrez", entrandorLista[0].apellidos, "Casilla apellidos correcta") },
                { assertEquals("1970-01-01", entrandorLista[0].fechaNacimiento, "Casilla fecha nacimiento correcta") },
                { assertEquals("1990-01-01", entrandorLista[0].fechaIncorporacion, "Casilla fecha incorporacion correcta") },
                { assertEquals(1000.0, entrandorLista[0].salario, "Casilla salario correcta") },
                { assertEquals("España", entrandorLista[0].pais, "Casilla pais correcta") },
                { assertEquals("Entrenador", entrandorLista[0].rol, "Casilla rol correcta") },
                { assertEquals(Entrenador.Especializacion.ENTRENADOR_PORTEROS, entrenadorEntity.especialidad, "Casilla especialidad correcta") },
                { assertEquals("", entrenadorEntity.rutaImagen, "Casilla ruta imagen correcta") },
            )
            verify(dao, atLeastOnce()).findAll()
        }

        @Test
        @DisplayName("Deberia devolver un miembro buscado por id")
        fun findById() {
            val entrenadorEntity = EntrenadorEntity(
                id = 2,
                nombre = "Pedro",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = "",
                especialidad = Entrenador.Especializacion.ENTRENADOR_PORTEROS,
            )

            whenever(dao.findById(2)).thenReturn(entrenadorEntity)

            val entrenadorId = repository.findById(2)

            assertAll(
                { assertNotNull(entrenadorId, "Entrenador no nulo") },
                { assertEquals(2, entrenadorId!!.id, "Casilla id correcta") },
                { assertEquals("Pedro", entrenadorId!!.nombre, "Casilla nombre correcta") },
                { assertEquals("Gutierrez", entrenadorId!!.apellidos, "Casilla apellidos correcta") },
                { assertEquals("1970-01-01", entrenadorId!!.fechaNacimiento, "Casilla fecha nacimiento correcta") },
                { assertEquals("1990-01-01", entrenadorId!!.fechaIncorporacion, "Casilla fecha incorporacion correcta") },
                { assertEquals(1000.0, entrenadorId!!.salario, "Casilla salario correcta") },
                { assertEquals("España", entrenadorId!!.pais, "Casilla pais correcta") },
                { assertEquals("Entrenador", entrenadorId!!.rol, "Casilla rol correcta") },
                { assertEquals(Entrenador.Especializacion.ENTRENADOR_PORTEROS, entrenadorEntity.especialidad, "Casilla especialidad correcta") },
                { assertEquals("", entrenadorEntity.rutaImagen, "Casilla ruta imagen correcta") },
            )

        }

        @Test
        @DisplayName("Deberia de salvar un miembro")
        fun save() {
            val plantillaEntity = PlantillaEntity(
                id = 2,
                nombre = "Pedro",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = "",

                )

            whenever(dao.save(argThat {
                id == 2L &&
                        nombre == "Pedro" &&
                        apellidos == "Gutierrez" &&
                        fechaNacimiento == "1970-01-01" &&
                        fechaIncorporacion == "1990-01-01" &&
                        salario == 1000.0 &&
                        pais == "España" &&
                        rol == "Entrenador"
            })).thenReturn(1)

            val plantillaSave = repository.save(plantillaEntity.toModel())

            assertAll(
                { assertNotNull(plantillaEntity, "Plantilla no nulo") },
                { assertEquals(2L, plantillaSave.id, "Casilla id correcta") },
                { assertEquals("Pedro", plantillaSave.nombre, "Casilla nombre correcta") },
                { assertEquals("Gutierrez", plantillaSave.apellidos, "Casilla apellidos correcta") },
                { assertEquals("1970-01-01", plantillaSave.fechaNacimiento, "Casilla fecha nacimiento correcta") },
                { assertEquals("1990-01-01", plantillaSave.fechaIncorporacion, "Casilla fecha incorporacion correcta") },
                { assertEquals(1000.0, plantillaSave.salario, "Casilla salario correcta") },
                { assertEquals("España", plantillaSave.pais, "Casilla pais correcta") },
                { assertEquals("Entrenador", plantillaSave.rol, "Casilla rol correcta") },
            )
        }

        @Test
        fun delete() {
            val plantillaEntity = PlantillaEntity(
                id = 1,
                nombre = "Pedro",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = "",
            )

            whenever(dao.findById(1)).thenReturn(plantillaEntity)
            whenever(dao.delete(1)).thenReturn(1)
            repository.deleteById(1L)
        }

        @Test
        @DisplayName("Deberia de eliminar los datos del miembro")
        fun deleteAll() {
            val plantillaEntity = PlantillaEntity(
                id = 1,
                nombre = "Pedro",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = "",
            )

            repository.deleteAll()

            verify(dao, times(1)).deleteAll()
        }

        @Test
        @DisplayName("Deberia salvar los datos de un mimebro")
        fun saveAll() {
            val plantillaEntity = PlantillaEntity(
                id = 1,
                nombre = "Pedro",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = "Entrenador",
                rutaImagen = "",
            )

            repository.saveAll(listOf(plantillaEntity).toModel())

        }
    }

    @Nested
    @DisplayName("CasosIncorrectos")
    inner class CasosIncorrectos {
        @Test
        @DisplayName("Deberia devolver una lista vacia")
        fun findAllIncorrecto() {

            whenever(dao.findAll()).thenReturn(emptyList())

            val emptyList = repository.findAll()

            assertEquals(0, emptyList.size, "La lista debe estar vacía")

            verify(dao, times(1)).findAll()
        }
    }

    @Test
    @DisplayName("Deberia de de ser un id nulo")
    fun findByIdIncorrecto() {

        whenever(dao.findById(1)).thenReturn(null)

        val idNulo = repository.findById(1)

        assertEquals(null, idNulo, "El id deberia de ser nulo")

        verify(dao, times(1)).findById(1)
    }

    @Test
    @DisplayName("Deberia de no encontrar un alumno por identificador")
    fun deleteByIdIncorrecto() {

        //llamo primero a findById por que la operacion del repositorio requiere encontrar primero el identificador
        //para poder borrar
        whenever(dao.findById(1)).thenReturn(null)

        repository.deleteById(1)

        verify(dao, times(1)).findById(1)
    }
}