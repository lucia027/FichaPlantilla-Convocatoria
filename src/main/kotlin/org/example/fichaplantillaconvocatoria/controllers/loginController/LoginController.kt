package org.example.fichaplantillaconvocatoria.controllers.loginController

import javafx.fxml.FXML
import javafx.scene.control.*
import org.example.fichaplantillaconvocatoria.routes.RoutesManager
import org.lighthousegames.logging.logging
import org.mindrot.jbcrypt.BCrypt

private val logger = logging()

/*
 * Clase que se encarga de conectar las funciones con la interfaz de login y desarrolarlas
 */
class LoginController {

    @FXML
    private lateinit var mensajeLabel: Label
    @FXML
    private lateinit var loginButton: ToggleButton
    @FXML
    private lateinit var contrasenaField: PasswordField
    @FXML
    private lateinit var usuarioField: TextField

    private val usuarios = mutableMapOf<String, String>(
        "Lucia" to BCrypt.hashpw("admin", BCrypt.gensalt()),
        "User" to BCrypt.hashpw("user", BCrypt.gensalt())
    )

    fun initialize() {
        logger.debug { "Iniciando pantalla de login" }
        initEvents()
    }

    fun initEvents(){
        loginButton.setOnAction {
            val username = usuarioField.text
            val password = contrasenaField.text
            onLoginAction(username, password)
        }
    }

    fun onLoginAction(userName : String, password: String){
        val passwordHashed = usuarios[userName]

        if (passwordHashed != null && BCrypt.checkpw(password, passwordHashed)) {
            RoutesManager.initPlantillaStage()
        } else {
            contrasenaField.clear()
            usuarioField.clear()
            mensajeLabel.text = "Usuario o contraseña incorrectos"
            Alert(Alert.AlertType.ERROR).apply {
                title = "Error de autenticación"
                headerText = "Vuelva a introducir sus datos correctamente"
            }.showAndWait()
        }
    }
}