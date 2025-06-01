package org.example.fichaplantillaconvocatoria

import javafx.application.Application
import javafx.stage.Stage
import org.example.fichaplantillaconvocatoria.config.Config
import org.example.fichaplantillaconvocatoria.di.appModule
import org.example.fichaplantillaconvocatoria.routes.RoutesManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.lighthousegames.logging.logging

private val logger = logging()

/*
 * Clase que se encarga de inicar la aplicacion
 */
class HelloApplication : Application(), KoinComponent {

    init {
        startKoin {
            printLogger()
            modules(appModule)
        }
    }

    override fun start(stage: Stage) {
        logger.debug { "Iniciando New-Team APP" }

        // Forzamos la carga de Config aquí para validar que la configuración se carga bien
        val config: Config by inject()
        logger.debug { "Config cargada" }

        RoutesManager.apply {
            app = this@HelloApplication
        }.run {
            initSplashScreen(stage)
        }
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}