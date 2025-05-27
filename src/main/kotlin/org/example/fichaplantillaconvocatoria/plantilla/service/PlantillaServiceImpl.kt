package org.example.fichaplantillaconvocatoria.plantilla.service

import com.github.benmanes.caffeine.cache.Cache
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.fichaplantillaconvocatoria.config.Config
import org.example.fichaplantillaconvocatoria.plantilla.dto.PlantillaDto
import org.example.fichaplantillaconvocatoria.plantilla.error.PlantillaError
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toDto
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toEntrenador
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toJugador
import org.example.fichaplantillaconvocatoria.plantilla.mapper.toModel
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador
import org.example.fichaplantillaconvocatoria.plantilla.models.Jugador
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import org.example.fichaplantillaconvocatoria.plantilla.repositories.PlantillaRepository
import org.lighthousegames.logging.logging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.time.Instant
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.name

class PlantillaServiceImpl (
    private val repository: PlantillaRepository,
    private val config : Config,
    private val cache : Cache<Long, Plantilla>
) : PlantillaService {

    private val logger = logging()

    init {
        logger.info { "Iniciando servicio" }
    }

    val tempDir = "miembrosPlantilla"

    //Función que devuelve una lista de los miembros de la plantilla
    override fun findAll(): Result<List<Plantilla>, PlantillaError> {
        return Ok(repository.findAll())
    }

    //Función que busca a un miembro de la plantilla por id
    override fun findById(id: Long): Result<Plantilla, PlantillaError> {
        return cache.getIfPresent(id)?.let {
            Ok(it)
        } ?: repository.findById(id)?.also {
            cache.put(id, it)
        } ?.let {
            Ok(it)
        } ?: Err(PlantillaError.PlantillaIdNotFound("Plantilla $id no econtrada"))
    }

    //Funcion que guarda una entidad
    override fun save(item: Plantilla): Result<Plantilla, PlantillaError> {
        logger.debug { "Salvando miembro de la plantilla" }
        val savedItem = repository.save(item)
        cache.put(savedItem.id, savedItem)
        return Ok(savedItem)
    }

    //Función que borra el identificador de un miembro de la plantilla
    override fun deleteById(id: Long): Result<Unit, PlantillaError> {
        logger.debug { "Borrando miembro de la plantilla por id : $id" }
        repository.deleteById(id)
        cache.invalidate(id)
        return Ok(Unit)
    }


    //Función que lee un archivo con información de la plantilla y lo convierte en una lista de objetos
    override fun readFile(file: File, format: FileFormat): List<Plantilla> {
        println()
        logger.debug { "Leyendo JSON" }

        if (!file.exists() || !file.isFile || !file.canRead()) {
            throw IllegalArgumentException("El fichero no se puede leer, no es un fichero o no se ha encontrado")
        } else {
            val json = Json { ignoreUnknownKeys = true; prettyPrint = true }
            val imprimirJson = file.readText()
            val listaPlantillaDto = json.decodeFromString<List<PlantillaDto>>(imprimirJson)

            val listaPersonalModel = listaPlantillaDto.map {
                when (it.rol) {
                    "Entrenador" -> it.toEntrenador()
                    else -> it.toJugador()
                }
            }
            return listaPersonalModel
        }
    }

    //Función que escribe una lista de objetos de plantilla en un archivo en el formato especificado.
    override fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>) {
        if (!file.parentFile.exists() || !file.parentFile.isDirectory) {
            throw IllegalArgumentException("El fichero json no se puede sobreescribir o no existe en su directorio padre")
        } else {
            val json = Json { ignoreUnknownKeys = true; prettyPrint = true }
            val listaPersonalDto = personal.map {
                when (it) {
                    is Jugador -> { it.toDto() }
                    is Entrenador -> { it.toDto() }
                    else -> null
                }
            }

            val jsonString = json.encodeToString(listaPersonalDto)
            file.writeText(jsonString)
        }
    }

    //Función que elimina toda la informacion sobre un miembro de la plantilla
    override fun deleteAll(): Result<Unit, PlantillaError> {
        repository.deleteAll().also {
            cache.invalidateAll()
            return Ok(it)
        }
    }

    //Función que guarda todos los items en una lista
    override fun saveAll(plantilla: List<Plantilla>): Result<List<Plantilla>, PlantillaError> {
        repository.saveAll(plantilla).also {
            cache.invalidateAll()
            return Ok(it)
        }
    }

    override fun storageDataJson(file: File, data: List<Plantilla>): Result<Long, PlantillaError> {
        logger.debug { "Guardando datos en fichero $file" }
        return try {
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            val jsonString = json.encodeToString<List<PlantillaDto>>(data.toDto())
            file.writeText(jsonString)
            Ok(data.size.toLong())
        } catch (e: Exception) {
            Err(PlantillaError.PlantillaStorageError("Error al escribir el JSON: ${e.message}"))
        }
    }

    private fun getImagenName(newFileImage: File): String {
        val name = newFileImage.name
        val extension = name.substring(name.lastIndexOf(".") + 1)
        return "${Instant.now().toEpochMilli()}.$extension"
    }

    override fun deleteAllImages(): Result<Long, PlantillaError> {
        return try {
            Ok(
                Files.walk(Paths.get(config.imagesDirectory)).filter {
                    Files.isRegularFile(it)
                }.map { Files.deleteIfExists(it) }
                    .count())
        } catch (e: Exception) {
            Err(PlantillaError.PlantillaStorageError("No se ha podido eliminar la imagen: ${e.message}"))
        }
    }

    override fun loadDataJson(file: File): Result<List<Plantilla>, PlantillaError> {
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
        return try {
            val jsonString = file.readText()
            val data = json.decodeFromString<List<PlantillaDto>>(jsonString)
            Ok(data.toModel())
        } catch (e: Exception) {
            Err(PlantillaError.PlantillaStorageError("Error a la hora de cargar los datos del Json"))
        }
    }

    override fun loadImage(imgnName: String): Result<File, PlantillaError> {
        val file = File(config.imagesDirectory + imgnName)
        return if (file.exists()) {
            Ok(file)
        } else {
            Err(PlantillaError.PlantillaStorageError("La imagen no existe: ${file.name}"))
        }
    }

    override fun saveImage(fileName: File): Result<File, PlantillaError> {
        return try {
            val newImage = File(config.imagesDirectory + getImagenName(fileName))
            Files.copy(fileName.toPath(), newImage.toPath(), StandardCopyOption.REPLACE_EXISTING)
            Ok(newImage)
        } catch (e: Exception) {
            Err(PlantillaError.PlantillaStorageError("La imagen no se puede guardar: ${fileName.name}"))
        }
    }

    override fun deleteImage(fileName: File): Result<Unit, PlantillaError> {
        Files.deleteIfExists(fileName.toPath())
        return Ok(Unit)
    }

    override fun exportToZip(zipFile: File, data: List<Plantilla>): Result<File, PlantillaError> {
        logger.debug { "Exportando a ZIP $zipFile" }
        val tempDir = Files.createTempDirectory(tempDir)
        return try {

            data.forEach {
                val file = File(config.imagesDirectory + it.rutaImagen)
                if (file.exists()) {
                    Files.copy(
                        file.toPath(),
                        Paths.get(tempDir.toString(), file.name),
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }
            }
            storageDataJson(File("$tempDir/data.json"), data)
            Files.walk(tempDir).forEach { logger.debug { it } }
            val archivos = Files.walk(tempDir)
                .filter { Files.isRegularFile(it) }
                .toList()
            ZipOutputStream(Files.newOutputStream(zipFile.toPath())).use { zip ->
                archivos.forEach { archivo ->
                    val entradaZip = ZipEntry(tempDir.relativize(archivo).toString())
                    zip.putNextEntry(entradaZip)
                    Files.copy(archivo, zip)
                    zip.closeEntry()
                }
            }
            tempDir.toFile().deleteRecursively()
            Ok(zipFile)
        } catch (e: Exception) {
            logger.error { "Error al exportar a ZIP: ${e.message}" }
            Err(PlantillaError.PlantillaStorageError("Error al exportar a ZIP: ${e.message}"))
        }
    }

    override fun loadFromZip(unzipFile: File): Result<List<Plantilla>, PlantillaError> {
        logger.debug { "Importando desde ZIP $unzipFile" }
        val tempDir = Files.createTempDirectory(tempDir)
        return try {
            ZipFile(unzipFile).use { zip ->
                zip.entries().asSequence().forEach { entrada ->
                    zip.getInputStream(entrada).use { input ->
                        Files.copy(
                            input,
                            Paths.get(tempDir.toString(), entrada.name),
                            StandardCopyOption.REPLACE_EXISTING
                        )
                    }
                }
            }
            Files.walk(tempDir).forEach {
                // copiamos todas las imagenes, es decir, todo lo que no es .json al directorio de imagenes
                if (!it.toString().endsWith(".json") && Files.isRegularFile(it)) {
                    Files.copy(
                        it,
                        Paths.get(config.imagesDirectory, it.name),
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }
            }
            val data = loadDataJson(File("$tempDir/data.json"))
            tempDir.toFile().deleteRecursively()
            return data
        } catch (e: Exception) {
            logger.error { "Error al importar desde ZIP: ${e.message}" }
            Err(PlantillaError.PlantillaStorageError("Error al importar desde ZIP: ${e.message}"))
        }
    }

    override fun updateImage(imgName: String, newFileImg: File): Result<File, PlantillaError> {
        return try {
            val newImage = File(config.imagesDirectory + imgName)
            Files.copy(newFileImg.toPath(), newImage.toPath(), StandardCopyOption.REPLACE_EXISTING)
            Ok(newImage)
        } catch (e: Exception) {
            Err(PlantillaError.PlantillaStorageError("La imagen no se puede actualizar: ${newFileImg.name}"))
        }
    }
}