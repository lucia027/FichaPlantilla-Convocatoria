package org.example.fichaplantillaconvocatoria.plantilla.service

import org.example.fichaplantillaconvocatoria.plantilla.error.PlantillaError
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import com.github.michaelbull.result.Result
import java.io.File

/*
 * Intefaz que define todas las funciones del service y ademas en esta caso tambien las normalmente ubicadas en storage
 */
interface PlantillaService {
    fun findAll(): Result<List<Plantilla>, PlantillaError>
    fun findById(id: Long): Result<Plantilla, PlantillaError>
    fun save(item : Plantilla): Result<Plantilla, PlantillaError>
    fun deleteById(id: Long): Result<Unit, PlantillaError>
    fun readFile(file: File, format: FileFormat): List<Plantilla>
    fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>)
    fun deleteAll(): Result<Unit, PlantillaError>
    fun saveAll(plantilla: List<Plantilla>): Result<List<Plantilla>, PlantillaError>
    fun storageDataJson(file: File, data: List<Plantilla>): Result<Long, PlantillaError>
    fun deleteAllImages(): Result<Long, PlantillaError>
    fun loadDataJson(file: File): Result<List<Plantilla>, PlantillaError>
    fun loadImage(imgnName: String): Result<File, PlantillaError>
    fun saveImage(fileName: File):Result<File, PlantillaError>
    fun deleteImage(fileName: File):Result<Unit, PlantillaError>
    fun exportToZip(zipFile: File, data: List<Plantilla>): Result<File, PlantillaError>
    fun loadFromZip(unzipFile: File): Result<List<Plantilla>, PlantillaError>
    fun updateImage(imgName: String, newFileImg: File): Result<File, PlantillaError>
}