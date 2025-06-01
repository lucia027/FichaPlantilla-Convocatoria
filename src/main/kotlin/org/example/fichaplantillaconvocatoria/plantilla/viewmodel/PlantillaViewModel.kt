package org.example.fichaplantillaconvocatoria.plantilla.viewmodel

import com.github.michaelbull.result.*
import com.github.michaelbull.result.map
import com.github.michaelbull.result.onSuccess
import javafx.beans.property.SimpleObjectProperty
import org.example.fichaplantillaconvocatoria.plantilla.service.PlantillaService
import org.example.fichaplantillaconvocatoria.plantilla.error.PlantillaError
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import org.example.fichaplantillaconvocatoria.plantilla.models.Jugador
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador
import org.example.fichaplantillaconvocatoria.routes.RoutesManager
import org.lighthousegames.logging.logging
import javafx.scene.image.Image
import kotlin.String
import java.io.File
import kotlin.collections.map
import kotlin.toString

private val logger = logging()

class PlantillaViewModel(
    private val servicio: PlantillaService
) {
    val state: SimpleObjectProperty<ExpedienteState> = SimpleObjectProperty(ExpedienteState())

    init {
        println("PlantillaViewModel init called")
        loadAllPlantilla()
        loadTypes()
    }

    private fun loadTypes() {
        state.value = state.value.copy(typesPlantilla = TipoFiltro.entries.map { it.value })
    }

    private fun loadAllPlantilla() {
        servicio.findAll().onSuccess {
            state.value = state.value.copy(plantilla = it)
            updateActualState()
        }
    }

    private fun updateActualState() {

        // Consultas jugadores
        val pesoMinimoConsulta = state.value.jugador.mapNotNull { it.peso }.minOrNull() ?: 0.0
        val salarioMaximoConsulta = state.value.jugador.mapNotNull { it.salario }.maxOrNull() ?: 0.0
        val alturaMinimaConsulta = state.value.jugador.mapNotNull { it.altura }.minOrNull() ?: 0.0
        val totalPartidosConsulta = state.value.jugador.sumOf { it.partidosJugados }
        val jugadoresTotalConsulta = state.value.jugador.count()

        // Consultas entrenadores
        val salarioPromedioConsulta = state.value.entrenador.mapNotNull { it.salario }.average()
        val incorporacionAntiguaConsulta = state.value.entrenador.minByOrNull { it.fechaIncorporacion }?.fechaIncorporacion ?: ""
        val nacimientoActualConsulta = state.value.entrenador.maxByOrNull { it.fechaNacimiento }?.fechaNacimiento ?: ""
        val entrenadoresAsistentesConsulta = state.value.entrenador.count { it.especialidad == "ASISTENTE" }
        val entrenadoresEspañolesConsulta = state.value.entrenador.count { it.pais == "España" }

        // Consultas general
        val golesPromedioConsulta = state.value.jugador.map { it.goles }.average()
        val minutosPromedioConsulta = state.value.jugador.mapNotNull { it.minutosJugados }.average()


        state.value = state.value.copy(
            // State de jugadores
            pesoMinimo = pesoMinimoConsulta,
            salarioMaximo = salarioMaximoConsulta,
            alturaMinima = alturaMinimaConsulta,
            totalPartidos = totalPartidosConsulta,
            jugadoresTotal = jugadoresTotalConsulta,

            // State de entrenadores
            salarioPromedio = salarioPromedioConsulta,
            incorporacionAntigua = incorporacionAntiguaConsulta,
            nacimientoActual = nacimientoActualConsulta,
            entrenadoresAsistentes = entrenadoresAsistentesConsulta,
            entrenadoresEspanoles = entrenadoresEspañolesConsulta,

            // State general
            minutosPromedio = minutosPromedioConsulta,
            golesPromedio = golesPromedioConsulta,

            // Aqui se guarda el state de los miembros de la plantilla
            miembro = PlantillaState()
        )
    }

    fun plantillaFilteredList(tipo: String, nombre: String): List<Plantilla>{
        return state.value.plantilla
            .filter { plantilla ->
                when (tipo) {
                    TipoFiltro.JUGADOR.value -> plantilla.rol == "Jugador"
                    TipoFiltro.ENTRENADOR.value -> plantilla.rol == "Entrenador"
                    else -> true
                }
            }.filter { plantilla ->
                plantilla.nombre.contains(nombre, true)
            }
    }

    fun savePlantillaToJson(file: File): Result<Long, PlantillaError>{
        return servicio.storageDataJson(file, state.value.plantilla)
    }

    fun loadPlantillaJson(file: File, withImages: Boolean = false): Result<List<Plantilla>, PlantillaError> {
        return servicio.deleteAllImages().andThen {
            servicio.loadDataJson(file).onSuccess {
                servicio.deleteAll() // Borramos todos los datos de la BD
                servicio.saveAll(
                    if (withImages)
                        it
                    else
                        it.map { a -> a.copy(id = Plantilla.NEW_ID, rutaImagen = TipoImagen.SIN_IMAGEN.value) }
                )
                loadAllPlantilla() // Actualizamos la lista
            }
        }
    }


    fun updatePlantillaSelecionado(plantilla: Plantilla) {
        var imagen = Image(RoutesManager.getResourceAsStream("images/default_profile.png"))
        var fileImage = File(RoutesManager.getResource("images/default_profile.png").toURI())

        servicio.loadImage(plantilla.rutaImagen).onSuccess {
            imagen = Image(it.absoluteFile.toURI().toString())
            fileImage = it
        }

        when(plantilla.rol){
            "Jugador" -> state.value = state.value.copy(
                jugador = listOf(PlantillaState(
                    id = plantilla.id,
                    nombre = plantilla.nombre,
                    apellidos = plantilla.apellidos,
                    fechaNacimiento = plantilla.fechaNacimiento,
                    fechaIncorporacion = plantilla.fechaIncorporacion,
                    salario = plantilla.salario!!,
                    pais = plantilla.pais,
                    rol = plantilla.rol,
//                    posicion = plantilla.posicion.toString(),
//                    dorsal = plantilla.dorsal,
//                    altura = plantilla.altura,
//                    peso = plantilla.peso,
//                    goles = plantilla.goles,
//                    partidosJugados = plantilla.partidosJugados,
//                    minutosJugados = plantilla.minutosJugados,
                ))
            )

            "Entrenador" -> state.value = state.value.copy(
                entrenador = listOf(PlantillaState(
                    id = plantilla.id,
                    nombre = plantilla.nombre,
                    apellidos = plantilla.apellidos,
                    fechaNacimiento = plantilla.fechaNacimiento,
                    fechaIncorporacion = plantilla.fechaIncorporacion,
                    salario = plantilla.salario!!,
                    pais = plantilla.pais,
                    rol = plantilla.rol,
//                    especialidad = plantilla.especialidad
                ))
            )
        }
    }

//    fun crearJugador(): Result<Jugador, PlantillaError> {
//        val newJugadorTemp = state.value.copy(jugador = state.value.jugador)
//        val newJugador = newJugadorTemp.toModel().copy(id = Plantilla.NEW_ID)
//    }
//   fun crearEntrenador(): Result<Entrenador, PlantillaError> {}
//   fun editarPlantilla(): Result<Plantilla, PlantillaError> {}

//    fun eliminarMiembro(): Result<Unit, PlantillaError> {
//        val miembro = (state.value.plantilla.find { it.id.toLong() == it.id.toLong() } )
//        val myId = miembro?.id?.toLong()
//
//        miembro.fileImage.let { file ->
//            if (file?.name != TipoImagen.SIN_IMAGEN.value) {
//                servicio.deleteImage(file!!)
//            }
//        }
//
//        servicio.deleteById(myId)
//        state.value = state.value.copy(plantilla = state.value.plantilla.toMutableList().apply{ this.removeIf { it.id == myId } })
//
//        updateActualState()
//        return Ok(Unit)
//    }

    /*

    fun updateImagePlantilaOperacio(fileImage: File, jugador: JugadorState, entrenador: EntrenadorState){
        state.value = state.value.copy(
            plantilla = state.value.plantilla.map { plantilla ->
                when (plantilla.rol) {
                    "Jugador" -> jugador.copy(
                        rutaImagen = Image(fileImage.toURI()),
                        fileImage = fileImage,
                        oldFileImage = state.value.jugador.fileImage
                    ).toModel()
                    else -> entrenador.copy(
                        rutaImagen = Image(fileImage.toURI()),
                        fileImage = fileImage,
                        oldFileImage = state.value.entrenador.fileImage
                    ).toModel()
                }
            }
        )
    }
    */


    fun exportToZip(fileToZip: File): Result<Unit, PlantillaError> {
        servicio.findAll().andThen {
            servicio.exportToZip(fileToZip, it)
        }.onFailure {
            return Err(it)
        }
        return Ok(Unit)
    }

    fun loadPlantillaFromZip(fileToUnzip: File): Result<List<Plantilla>, PlantillaError> {
        return servicio.loadFromZip(fileToUnzip).onSuccess {lista ->
            servicio.deleteAll().andThen {
                servicio.saveAll(lista.map{ a -> a.copy(id = Plantilla.NEW_ID) })
            }.onFailure {
                loadAllPlantilla()
            }
        }
    }

    fun changePlantillaOperacion(newValue: TipoOperacion){
        if (newValue == TipoOperacion.EDITAR){
            state.value = state.value.copy(
                plantilla = state.value.plantilla.map { it.copy() },
                tipoOperacion = newValue
            )
        } else {
            state.value = state.value.copy(
                plantilla = emptyList(),
                tipoOperacion = newValue,
            )
        }
    }

    fun updateDataPlantillaOperacion(
        jugador: Jugador,
        entrenador: Entrenador
    ) {
        state.value = state.value.copy(
            plantilla = state.value.plantilla.map { plantilla ->
                when (plantilla.rol) {
                    "Jugador" -> jugador.copy(
                        id = jugador.id,
                        nombre = jugador.nombre,
                        apellidos = jugador.apellidos,
                        fechaNacimiento = jugador.fechaNacimiento,
                        fechaIncorporacion = jugador.fechaIncorporacion,
                        salario = jugador.salario!!,
                        pais = jugador.pais,
                        rol = jugador.rol
                    )
                    else -> entrenador.copy(
                        id = entrenador.id,
                        nombre = entrenador.nombre,
                        apellidos = entrenador.apellidos,
                        fechaNacimiento = entrenador.fechaNacimiento,
                        fechaIncorporacion = entrenador.fechaIncorporacion,
                        salario = entrenador.salario!!,
                        pais = entrenador.pais,
                        rol = entrenador.rol,
                    )
                }
            }
        )
    }

    enum class TipoOperacion(val value: String){
        NUEVO("Nuevo"), EDITAR("Editar")
    }

    enum class TipoImagen(val value: String) {
        SIN_IMAGEN("images/default_profile.png"), EMPTY("sin-imagen.png")
    }

    enum class TipoFiltro(val value: String) {
        JUGADOR("Jugador"), ENTRENADOR("Entrenador")
    }

    data class ExpedienteState(
        //Contenedores
        val typesPlantilla: List<String> = emptyList(),
        val plantilla: List<Plantilla> = emptyList(),
        val jugador: List<PlantillaState> = emptyList(),
        val entrenador: List<PlantillaState> = emptyList(),

        //Variables de las consultas de jugadores
        val pesoMinimo: Double = 0.0,
        val salarioMaximo: Double = 0.00,
        val alturaMinima: Double = 0.00,
        val totalPartidos: Int = 0,
        val jugadoresTotal: Int = 0,

        //Variables de las consultas de entrenador
        val salarioPromedio: Double = 0.00,
        val incorporacionAntigua: String = "",
        val nacimientoActual: String = "",
        val entrenadoresAsistentes: Int = 0,
        val entrenadoresEspanoles: Int = 0,

        //Variables de las consultas generales
        val golesPromedio: Double = 0.00,
        val minutosPromedio: Double = 0.00,

        //Miembro hace referencia al conjunto es decir el individual de plantilla
        val miembro: PlantillaState = PlantillaState(),

        val tipoOperacion: TipoOperacion = TipoOperacion.NUEVO
    )

    data class PlantillaState(
        val id: Long = 0,
        val nombre: String = "",
        val apellidos: String = "",
        val fechaNacimiento: String = "",
        val fechaIncorporacion: String = "",
        val salario: Double = 0.00,
        val pais: String = "",
        val rol: String = "",
        val posicion: String = "",
        val dorsal: Int? = 0,
        val altura: Double? = 0.0,
        val peso: Double? = 0.0,
        val goles: Int = 0,
        val partidosJugados: Int = 0,
        val minutosJugados: Double? = 0.0,
        val especialidad: String = "",

        val rutaImagen: Image = Image(RoutesManager.getResourceAsStream("images/default_profile.png")),
        val fileImage: File? = null,
        val oldFileImage: File? = null,
    )
}