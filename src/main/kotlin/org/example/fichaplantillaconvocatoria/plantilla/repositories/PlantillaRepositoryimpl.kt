package org.example.fichaplantillaconvocatoria.plantilla.repositories

import org.example.fichaplantillaconvocatoria.plantilla.dao.PlantillaDao
import org.example.fichaplantillaconvocatoria.plantilla.dao.JugadorEntity
import org.example.fichaplantillaconvocatoria.plantilla.dao.EntrenadorEntity
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toModel
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toEntity
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import org.lighthousegames.logging.logging

class PlantillaRepositoryImpl (
    val dao : PlantillaDao
) : PlantillaRepository {

    private val plantilla = mutableMapOf<Long, Plantilla>()
    private val logger = logging()

    init {
        logger.debug { "Iniciando repositorio" }
    }

    //Función que devuelve una lista de los miembros de la plantilla
    override fun findAll(): List<Plantilla> {
        logger.debug { "Obteniendo toda la plantilla" }
        return dao.findAll().mapNotNull {
            when (it) {
                is EntrenadorEntity -> it.toModel()
                is JugadorEntity -> it.toModel()
                else -> null
            }
        }
    }

    //Función que busca a un miembro de la plantilla por id
    override fun findById(id: Long): Plantilla? {
        logger.debug { "Buscando un miembro de la plantilla por id : $id" }
        return dao.findById(id)?.toModel()
    }

    //Para guardar un miembro
    override fun save(item: Plantilla): Plantilla {
        logger.debug { "Salvando miembro de la plantilla : $item" }
        val entityToSave = item.toEntity() // Asume que ignora id porque es autogenerado
        val generatedId = dao.save(entityToSave)
        return item.copy(id = generatedId)
    }

    //Función que borra el identificador de un miembro de la plantilla
    override fun deleteById(id: Long) {
        logger.debug { "Eliminando miembro de la plantilla : $id" }
        val plantilla: Plantilla? = dao.findById(id)?.toModel()
        if (plantilla != null) {
            val res = dao?.delete(id)
            if (res == 0L) {
                logger.error { "Fallo al remover el miembro de la plantilla" }
            }
        }
    }

    //Función que guarda todos los items en una lista
    override fun saveAll(t: List<Plantilla>): List<Plantilla> {
        return t.map { save(it) }
    }

    //Función que elimina toda la informacion sobre un miembro de la plantilla
    override fun deleteAll() {
        logger.debug { "Eliminando datos de un miembro de la plantilla" }
        return dao.deleteAll()
    }
}

