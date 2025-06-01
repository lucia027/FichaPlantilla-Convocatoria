package org.example.fichaplantillaconvocatoria.controllers.splashController

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.fxml.FXML
import javafx.scene.control.ProgressBar
import javafx.util.Duration
import org.example.fichaplantillaconvocatoria.routes.RoutesManager

/*
 * Clase que se encarga de cargar la splash screen
 */
class SplashController {

    @FXML
    lateinit var progressBar: ProgressBar

    fun initialize() {
        progressBar.progress = 0.0
        var progress = 0.0

        val timeline = Timeline(
            KeyFrame(Duration.millis(50.0), {
                progress += 0.01
                progressBar.progress = progress

                if (progress >= 1.0) {
                    // Aquí llamas a la función para abrir tu ventana principal
                    RoutesManager.initMainStage(RoutesManager.escenaPrincipal)
                }
            })
        )
        timeline.cycleCount = 100 // 100 * 0.01 = 1.0 (100%)
        timeline.play()
    }
}