package org.example.fichaplantillaconvocatoria.plantilla.repositories

import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla

/*
 * Interfaz que define las funciones del respositorio
 */
interface PlantillaRepository {
    fun findAll(): List<Plantilla>
    fun findById(id: Long): Plantilla?
    fun save(plantilla: Plantilla): Plantilla
    fun deleteById(id: Long?)
    fun deleteAll()
    fun saveAll(plantilla: List<Plantilla>): List<Plantilla>
}