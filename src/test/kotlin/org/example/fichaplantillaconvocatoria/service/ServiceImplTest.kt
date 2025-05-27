package org.example.fichaplantillaconvocatoria.service

import com.github.benmanes.caffeine.cache.Cache
import org.example.fichaplantillaconvocatoria.plantilla.dao.EntrenadorEntity
import org.example.fichaplantillaconvocatoria.plantilla.dao.PlantillaEntity
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toModel
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import org.example.fichaplantillaconvocatoria.plantilla.repositories.PlantillaRepositoryImpl
import org.example.fichaplantillaconvocatoria.plantilla.service.PlantillaServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class)
class ServiceImplMockitoTest {

    @Mock
    private lateinit var repository: PlantillaRepositoryImpl

    @Mock
    private lateinit var cache : Cache<Long, Plantilla>

    @InjectMocks
    private lateinit var servicio: PlantillaServiceImpl

    @Nested
    @DisplayName("Casos correctos")
    inner class CasosCorrectos {

        @Test
        @DisplayName("Deberia de devolver una lista de la plantilla")
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

            val model = entrenadorEntity.toModel()

            whenever(repository.findAll()).thenReturn(listOf(model))

            val entrandorLista = servicio.findAll()

            assertTrue(entrandorLista.isOk, "El resultado deberia de ser una lista")

            verify(repository, times(1)).findAll()

        }

        @Test
        @DisplayName("Deberia devolver el identificador de un miembro")
        fun findById() {
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

            val model = entrenadorEntity.toModel()

            whenever(cache.getIfPresent(entrenadorEntity.id)).thenReturn(model)

            repository.findById(1L)

            val entrenadorId = servicio.findById(1L)

            assertTrue(entrenadorId.isOk)

            verify(cache, times(1)).getIfPresent(model.id)
            verify(repository, times(1)).findById(1L)

        }

        @Test
        @DisplayName("Debería eliminar el identificador de un miembro")
        fun deleteById() {
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

            //Pongo el do Nothing por que devuleven UNIT
            doNothing().whenever(repository).deleteById(1L)
            doNothing().whenever(cache).invalidate(1)

            val entrenadorId = servicio.deleteById(1L)

            assertTrue(entrenadorId.isOk)

            verify(repository, times(1)).deleteById(1L)
            verify(cache, times(1)).invalidate(1)
        }

        @Test
        @DisplayName("Deberia de borrar todo sobre un miembro")
        fun deleteAll() {
            val entrenadorEntity = EntrenadorEntity(
                id = 1,
                nombre = "Pedro",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "",
                rol = "Entrenador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_PORTEROS,
                rutaImagen = ""
            )

            val model = entrenadorEntity.toModel()

            doNothing().whenever(repository).deleteAll()
            doNothing().whenever(cache).invalidateAll()

            val entrenadorDelete = servicio.deleteAll()

            assertTrue(entrenadorDelete.isOk)

            verify(repository, times(1)).deleteAll()
            verify(cache, times(1)).invalidateAll()
        }

        @Test
        @DisplayName("Deberia de guardar todo sobre miembros")
        fun saveAll() {

            val plantillaList = listOf(PlantillaEntity(
                id = 1,
                nombre = "Pedro",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "",
                rol = "Entrenador",
                rutaImagen = "",
            ))

            val model = plantillaList.toModel()

            whenever(repository.saveAll(model)).thenReturn(model)

            val modelList = servicio.saveAll(model)

            assertTrue(modelList.isOk)

            verify(repository, times(1)).saveAll(model)
            verify(cache, times(1)).invalidateAll()
        }
    }

    //Casos Incorrectos por hacer



}