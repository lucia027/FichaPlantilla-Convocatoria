package org.example.fichaplantillaconvocatoria.config

import java.io.File
import java.io.InputStream
import java.util.Properties

private const val CONFIG_FILE_NAME = "config.properties"

class Config {
    val APP_PATH = System.getProperty("user.dir")

    val imagesDirectory by lazy {
        val path = readProperty("app.images") ?: "imagenes"
        "$APP_PATH${File.separator}$path/"
    }

    val databaseUrl : String by lazy {
        readProperty("database.url") ?: "jdbc:h2:mem:plantilla;DB_CLOSE_DELAY=-1"
    }

    val databaseInitTables : Boolean by lazy {
        readProperty("database.init.tables") ?.toBoolean() ?: false
    }

    val databaseInitData : Boolean by lazy {
        readProperty("database.init.data") ?.toBoolean() ?: false
    }

    val cacheCapacity : Long by lazy {
        readProperty("app.cache.capacity") ?.toLong() ?: 5L
    }

    val cacheExpiration : Long by lazy {
        readProperty("app.cache.expiration") ?.toLong() ?: 60L
    }

    fun readProperty(key: String): String? {
        return try {
            val properties = Properties()
            val inputStream : InputStream = ClassLoader.getSystemResourceAsStream(CONFIG_FILE_NAME)
                ?: throw Exception("No se puede leer la configuracion $CONFIG_FILE_NAME")
            properties.load(inputStream)
            properties.getProperty(key)
        } catch (e : Exception) {
            println(e)
            null
        }
    }
}