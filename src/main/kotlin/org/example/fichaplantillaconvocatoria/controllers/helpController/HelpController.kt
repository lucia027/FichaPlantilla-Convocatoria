package org.example.fichaplantillaconvocatoria.controllers.helpController

import com.vaadin.open.Open
import javafx.fxml.FXML
import org.lighthousegames.logging.logging

private val logger = logging()
/*
 * Clase que se encarga de conectar las funciones con la interfaz de acerca de y desarrolarlas
 */
class HelpController {

    @FXML
    private lateinit var link : javafx.scene.control.Hyperlink

    @FXML
    fun initialize() {
        logger.debug { "Inicializando HelpController" }
        link.setOnAction {
            logger.debug { "Redirigiendo a GitHub" }
            val url2 = "https://github.com/lucia027"
            Open.open(url2)
        }
    }
}