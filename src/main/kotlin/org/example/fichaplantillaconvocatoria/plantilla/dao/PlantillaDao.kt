package org.example.fichaplantillaconvocatoria.plantilla.dao

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.lighthousegames.logging.logging

/**
 * Interfaz que implementa las consultas basicas.
 */
@RegisterKotlinMapper(PlantillaEntity::class)
interface PlantillaDao {

    //Consulta que selecciona a todos los miembros de la plantilla
    @SqlQuery("SELECT * FROM plantilla")
    fun findAll(): List<PlantillaEntity>

    //Consulta que selecciona miembros de la plantilla por su id
    @SqlQuery("SELECT * FROM plantilla WHERE id = :id")
    fun findById(@Bind("id") id: Long?): PlantillaEntity?

    //Consulta que añade miembros a la tabla plantilla
    @SqlUpdate("INSERT INTO plantilla (nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, posicion, dorsal, altura, peso, goles, partidosJugados, especialidad, rutaImagen, minutosJugados) VALUES (:nombre, :apellidos, :fechaNacimiento, :fechaIncorporacion, :salario, :pais, :rol, :posicion, :dorsal, :altura, :peso, :goles, :partidosJugados, :especialidad, :rutaImagen, :minutosJugados)")
    @GetGeneratedKeys
    fun save(@BindBean plantillaEntity: PlantillaEntity): Long

    //Cosulta que elimina a un miembro de la plantilla por id
    @SqlUpdate("DELETE FROM plantilla WHERE id = :id")
    fun delete(@Bind("id") id: Long?) : Long?

    //Consulta que elimina toda la informacion de miembros de la plantilla por id
    @SqlUpdate("DELETE FROM plantilla")
    fun deleteAll()

}

fun providePlantillaDao(jdbi: Jdbi): PlantillaDao {
    return jdbi.onDemand(PlantillaDao::class.java)
}