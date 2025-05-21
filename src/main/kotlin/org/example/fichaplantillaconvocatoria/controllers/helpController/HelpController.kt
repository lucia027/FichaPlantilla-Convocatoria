package org.example.fichaplantillaconvocatoria.controllers.helpController

import com.vaadin.open.Open
import javafx.fxml.FXML
import org.lighthousegames.logging.logging

private val logger = logging()
class HelpController {

    @FXML
    private lateinit var link1 : javafx.scene.control.Hyperlink

    @FXML
    private lateinit var link2 : javafx.scene.control.Hyperlink

    @FXML
    private lateinit var link3 : javafx.scene.control.Hyperlink

    @FXML
    fun initialize() {
        logger.debug { "Inicializando HelpController" }
        link1.setOnAction {
            logger.debug { "Redirigiendo a GitHub" }
            val url1 = "https://github.com/PabloDLF06"
            Open.open(url1)
        }

        link2.setOnAction {
            logger.debug { "Redirigiendo a GitHub" }
            val url2 = "https://github.com/karrasmil80"
            Open.open(url2)
        }

        link3.setOnAction {
            logger.debug { "Redirigiendo a GitHub" }
            val url3 = "https://github.com/lucia027"
            Open.open(url3)
        }
    }
}