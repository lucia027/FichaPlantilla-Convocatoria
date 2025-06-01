package org.example.fichaplantillaconvocatoria.database

import org.example.fichaplantillaconvocatoria.config.Config
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.lighthousegames.logging.logging

/*
 * Clase que gestiona la base de datos y sus tablas
 */
class JdbiManager (
    private val databaseUrl: String,
    private val databaseInitTables: Boolean
){

    val logger = logging()
    val jdbi by lazy { Jdbi.create(databaseUrl) }

    init {
        jdbi.installPlugin(KotlinPlugin())
        jdbi.installPlugin(SqlObjectPlugin())
        initDatabase(jdbi)

        if (databaseInitTables) {
            logger.debug { "Cargando Jdbi, creando tablas" }
            executeSqlScriptFromResources("/tables.sql")
        }

    }

    private fun executeSqlScriptFromResources(resourcePath: String) {
        val inputStream = ClassLoader.getSystemResourceAsStream(resourcePath)?.bufferedReader()!!
        val script = inputStream.readText()
        jdbi.useHandle<Exception> { handle ->
            handle.createScript(script).execute()
        }
    }
}

fun provideDatabaseManager(config: Config): Jdbi{
    return JdbiManager(
        config.databaseUrl,
        config.databaseInitTables,
    ).jdbi
}

fun initDatabase(jdbi: Jdbi) {
    val table = """
        CREATE TABLE IF NOT EXISTS plantilla (
            id IDENTITY NOT NULL PRIMARY KEY,
            nombre VARCHAR(100) NOT NULL,
            apellidos VARCHAR(100) NOT NULL,
            fechaNacimiento DATE NOT NULL,
            fechaIncorporacion DATE NOT NULL,
            salario NUMERIC NOT NULL,
            pais VARCHAR(50) NOT NULL,
            rol VARCHAR(50) NOT NULL,
            tipo VARCHAR(20) NOT NULL,
            posicion VARCHAR(50),
            dorsal INTEGER,
            altura DOUBLE,
            peso DOUBLE,
            goles INTEGER,
            partidosJugados INTEGER,
            especialidad VARCHAR(100),
            rutaImagen VARCHAR(255) DEFAULT 'images/default_profile.png',
            minutosJugados DOUBLE
        );
    """.trimIndent()

    jdbi.useHandle<Exception> { handle ->
        handle.execute(table)
    }
}