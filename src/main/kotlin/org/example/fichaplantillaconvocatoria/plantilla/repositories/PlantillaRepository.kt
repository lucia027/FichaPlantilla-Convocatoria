package org.example.fichaplantillaconvocatoria.plantilla.repositories

import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla

interface PlantillaRepository {
    fun findAll(): List<Plantilla>
    fun findById(id: Long): Plantilla?
    fun save(alumno: Plantilla): Plantilla
    fun deleteById(id: Long)
    fun deleteAll()
    fun saveAll(alumnos: List<Plantilla>): List<Plantilla>
}