package org.example.fichaplantillaconvocatoria.routes

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.WindowEvent
import org.koin.java.KoinJavaComponent.getKoin
import org.lighthousegames.logging.logging
import java.io.InputStream
import java.net.URL
import java.util.Locale

private val logger = logging()

//Objeto principal que gestionará parte del programa
object RoutesManager {

    lateinit var escenaPrincipal : Stage
    private lateinit var _escenarioActiva : Stage
    val escenaActiva : Stage
        get() = _escenarioActiva
    lateinit var app : Application

    private var scenesMap : HashMap<String, Pane> = HashMap()

    /**
     * Clase anidada que diferencia las diferentes views
     * @param fxml --> para saber cuál es el formato del archivo
     */
    enum class View(val fxml : String) {
        PLANTILLA("views/plantilla-view.fxml"),
        HELP("views/acerca-de-view.fxml"),
        SPLASH("views/splash.fxml"),
        LOGIN("views/login-view.fxml"),
    }

    /**
     * Al inicializar el routes manager ponemos el programa por defecto en español
     */
    init {
        logger.debug { "Iniciando Routes Manager" }
        Locale.setDefault(Locale.forLanguageTag("es-ES"))
    }

    /**
     * Funcion para cargar recursos por flujo de entrada
     * @param resource --> ruta del archivo a cargar
     * @param resource --> ruta del archivo a cargar
     * @return --> devuelve la imagen o una excepción
     */
    fun getResourceAsStream(resource: String): InputStream {
        return app::class.java.getResourceAsStream(resource)
            ?: throw RuntimeException("No se ha encontrado el recurso como stream: $resource")
    }

    /**
     * Función para cargar recursos
     * @param resource --> ruta del archivo a cargar
     * @return --> devuelve la URL de la imagen o una excepción
     */
    fun getResource(resource: String): URL {
        return app::class.java.getResource(resource)
            ?: throw RuntimeException("No se ha encontrado el recurso: $resource")
    }

    /**
     * Funcion que mostrará la escena principal del programa en el Controller
     * @param mainStage --> escena principal de la aplicación
     */
    fun initMainStage(mainStage: Stage) {
        logger.debug { "Iniciando Main Stage" }
        val fxmlLoader = FXMLLoader(getResource(View.LOGIN.fxml))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 400.0, 400.0)
        mainStage.apply {
            title = "New-Team"
            scene = newScene
            centerOnScreen()
            icons.add(Image(getResourceAsStream("images/logo.png")))
            isResizable = false
        }.show()

        escenaPrincipal = mainStage
        _escenarioActiva = mainStage
    }

    /**
     * Función que a través de un evento mostrara la escena de Acerca-De
     */
    fun initHelpStage() {
        logger.debug { "Cargando vista HELP" }
        val fxmlLoader = FXMLLoader(getResource(View.HELP.fxml))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 600.0, 400.0)
        Stage().apply {
            title = "Acerca De New-Team"
            scene = newScene
            initOwner(escenaPrincipal)
            centerOnScreen()
            isResizable = false
            initModality(Modality.WINDOW_MODAL)
            icons.add(Image(getResourceAsStream("images/logo.png")))
        }.show()
    }

    fun initSplashScreen(stage : Stage) {
        val fxmlLoader = FXMLLoader(getResource(View.SPLASH.fxml))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 1160.0, 720.0)

        escenaPrincipal = stage
        _escenarioActiva = stage

        stage.apply {
            title = "Splash"
            isResizable = false
            scene = newScene
            centerOnScreen()
            icons.add(Image(getResourceAsStream("images/logo.png")))

        }.show()
    }

    fun initPlantillaStage() {
        val fxmlLoader = FXMLLoader(getResource(View.PLANTILLA.fxml))
        val root = fxmlLoader.load<Pane>()
        val newScene = Scene(root, 1160.0, 720.0)
        Stage().apply {
            title = "Plantilla"
            scene = newScene
            initOwner(escenaPrincipal)
            centerOnScreen()
            icons.add(Image(getResourceAsStream("images/logo.png")))
            isResizable = false
            initModality(Modality.WINDOW_MODAL)
        }.show()
    }

    /**
     * Funcion que gestiona la ruta para salir de la aplicacion
     * @param title --> título que se mostrará al presionar el botón exit
     * @param header --> subtitulo que se mostrará debajo del título
     * @param content --> aviso de cambios no guardados
     * @param event --> evento que se hará al presionar el botón de salir o cancelar
     */
    fun onAppExit(
        title : String = "¿Salir de la Aplicacion de New-Team?",
        header : String = "¿Seguro que desea salir de la aplicación?",
        content : String = "Se perderán los datos no guardados",
        event: WindowEvent? = null
    ){
        logger.debug { "Cerrando aplicacion" }
        Alert(Alert.AlertType.CONFIRMATION).apply {
            this.title = title
            this.headerText = header
            this.contentText = content
        }.showAndWait().ifPresent { opcion ->
            if (opcion == ButtonType.OK) {
                Platform.exit()
            } else {
                event?.consume()
            }
        }
    }
}