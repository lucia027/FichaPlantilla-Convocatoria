package org.example.fichaplantillaconvocatoria.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import org.example.fichaplantillaconvocatoria.plantilla.models.Plantilla
import org.example.fichaplantillaconvocatoria.routes.RoutesManager
import org.lighthousegames.logging.logging
import javafx.scene.input.KeyCode
import org.example.fichaplantillaconvocatoria.plantilla.models.Entrenador
import org.example.fichaplantillaconvocatoria.plantilla.models.Jugador
import org.example.fichaplantillaconvocatoria.plantilla.viewmodel.PlantillaViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private val logger = logging()
/*
 * Clase que se encarga de conectar las funciones con la interfaz principal y desarrolarlas
 */
class HelloController : KoinComponent {

    private val viewModel: PlantillaViewModel by inject()


    // Elementos de la interfaz para vincular con fx:id en el FXML
    @FXML
    private lateinit var cambioUser: ToggleButton

    @FXML
    private lateinit var menuHelp: MenuItem

    @FXML
    private lateinit var menuExportarJson: MenuItem

    @FXML
    private lateinit var menuImportarJson: MenuItem

    @FXML
    private lateinit var menuExportarZip: MenuItem

    @FXML
    private lateinit var menuImportarZip: MenuItem

    @FXML
    private lateinit var menuPegar: MenuItem

    @FXML
    private lateinit var menuCopiar: MenuItem

    @FXML
    private lateinit var menuSalir: MenuItem

    @FXML
    private lateinit var menuAbrir: MenuItem

    @FXML
    private lateinit var menuItem: MenuItem

    @FXML
    private lateinit var nuevoButton: Button

    @FXML
    private lateinit var editarButton: Button

    @FXML
    private lateinit var eliminarButton: Button

    @FXML
    private lateinit var exportarButton: Button

    @FXML
    private lateinit var importarButton: Button

    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private lateinit var plantillaTable: TableView<Plantilla>

    @FXML
    private lateinit var idColumn: TableColumn<Plantilla, Long>

    @FXML
    private lateinit var nombreColumn: TableColumn<Plantilla, String>

    @FXML
    private lateinit var apellidosColumn: TableColumn<Plantilla, String>

    @FXML
    private lateinit var rolColumn: TableColumn<Plantilla, String>

    @FXML
    private lateinit var nombreField: TextField

    @FXML
    private lateinit var apellidosField: TextField

    @FXML
    private lateinit var fechaNacimientoField: DatePicker

    @FXML
    private lateinit var fechaIncorporacionField: DatePicker

    @FXML
    private lateinit var salarioField: TextField

    @FXML
    private lateinit var paisField: TextField

    @FXML
    private lateinit var rolComboBox: ComboBox<String>

    @FXML
    private lateinit var posicionComboBox: ComboBox<String>

    @FXML
    private lateinit var dorsalField: TextField

    @FXML
    private lateinit var alturaField: TextField

    @FXML
    private lateinit var pesoField: TextField

    @FXML
    private lateinit var golesField: TextField

    @FXML
    private lateinit var partidosField: TextField

    @FXML
    private lateinit var especialidadComboBox: ComboBox<String>

    @FXML
    private lateinit var statusLabel: Label

    @FXML
    private lateinit var photoImageView: ImageView

    @FXML
    private lateinit var nombreCompletoLabel: Label

    @FXML
    private lateinit var rolPosicionLabel: Label

    @FXML
    private lateinit var paisLabel: Label

    @FXML
    private lateinit var incorporacionLabel: Label

    @FXML
    private lateinit var edadLabel: Label

    @FXML
    private lateinit var añadirButton: Button

    @FXML
    private lateinit var buttonCancelar: Button

    @FXML
    private lateinit var buttonGuardar: Button

    @FXML
    private lateinit var entrenadoresEspañolesField: TextField

    @FXML
    private lateinit var entrenadoresAsistentesField: TextField

    @FXML
    private lateinit var fechaActualField: TextField

    @FXML
    private lateinit var fechaAntiguaField: TextField

    @FXML
    private lateinit var salarioPromedioField: TextField

    @FXML
    private lateinit var NumJugadoresField: TextField

    @FXML
    private lateinit var PartidosTotalField: TextField

    @FXML
    private lateinit var AlturaMinimaField: TextField

    @FXML
    private lateinit var salarioMaximoField: TextField

    @FXML
    private lateinit var golesPromedioField: TextField

    @FXML
    private lateinit var pesoMinimoField: TextField

    @FXML
    private lateinit var minutosPromedioField: TextField

    @FXML
    private lateinit var filterComboBox: ComboBox<String>

    @FXML
    private lateinit var searchText: TextField



    @FXML
    fun initialize() {
        initDefaultValues()

        initBindings()

        initEvents()

        //Las añado aqui por qué si no hay que hacer click dos veces para que se inicialice
        onComboBoxAction()
        onAddMemberAction()
        onEditMemberAction()
        println("Cargando datos: ${viewModel.state.value.plantilla}")
    }

    fun initDefaultValues() {
        logger.info { "Iniciando valores por defecto" }

        //Tabla
        plantillaTable.items = FXCollections.observableArrayList(viewModel.state.value.plantilla)

        //Columnas
        idColumn.cellValueFactory = PropertyValueFactory("id")
        nombreColumn.cellValueFactory = PropertyValueFactory("nombre")
        apellidosColumn.cellValueFactory = PropertyValueFactory("apellidos")
        rolColumn.cellValueFactory = PropertyValueFactory("rol")

        //Atajos del teclado
        menuCopiar.accelerator = KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN)
        menuPegar.accelerator = KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN)
        menuImportarJson.accelerator = KeyCodeCombination(KeyCode.J, KeyCombination.CONTROL_DOWN)
        menuExportarJson.accelerator = KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN)
        menuImportarZip.accelerator = KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN)
        menuExportarZip.accelerator = KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN)
        menuHelp.accelerator = KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN)

        //Variables desactivadas al inicio de la app para imposibilitar el editarlas
        paisField.isDisable = true
        fechaIncorporacionField.isDisable = true
        fechaNacimientoField.isDisable = true
        salarioField.isDisable = true
        apellidosField.isDisable = true
        nombreField.isDisable = true
        golesField.isDisable = true
        partidosField.isDisable = true
        dorsalField.isDisable = true
        alturaField.isDisable = true
        pesoField.isDisable = true
        rolComboBox.isDisable = true
        posicionComboBox.isDisable = true
        especialidadComboBox.isDisable = true
        pesoMinimoField.isDisable = true
        salarioMaximoField.isDisable = true
        salarioPromedioField.isDisable = true
        AlturaMinimaField.isDisable = true
        PartidosTotalField.isDisable = true
        NumJugadoresField.isDisable = true
        fechaAntiguaField.isDisable = true
        fechaActualField.isDisable = true
        entrenadoresAsistentesField.isDisable = true
        entrenadoresEspañolesField.isDisable = true

        if (viewModel.state.value.jugador.isEmpty()) {
            println("No hay jugadores")
        }

        //Opciones de la comboBox
        val boxItemsRol = listOf("Jugador", "Entrenador")
        rolComboBox.items.addAll(boxItemsRol)

        val boxItemsPosicion = listOf("Defensa", "Centrocampista", "Delantero", "Portero")
        posicionComboBox.items.addAll(boxItemsPosicion)

        val boxItemsFilter = listOf("Jugador", "Entrenador", "Todos")
        filterComboBox.items.addAll(boxItemsFilter)

        val boxItemsEspecialidad = listOf("ENTRENADOR_ASISTENTE", "ENTRENADOR_PORTEROS", "ENTRENADOR_PRINCIPAL")
        especialidadComboBox.items.addAll(boxItemsEspecialidad)
    }

    fun initBindings(){
//        //Valores de las consultas
//        // también se puede hacer con Bindings.select
//        textNotaMedia.textProperty().bind(Bindings.select(viewModel.state, "notaMedia"))
//
//        //Jugadores
//        pesoMinimoField.textProperty().bind(viewModel.state.map{ it.pesoMinimo } as ObservableValue<String>)
//        salarioMaximoField.textProperty().bind(viewModel.state.map { it.salarioMaximo } as ObservableValue<String>)
//        AlturaMinimaField.textProperty().bind(viewModel.state.map { it.alturaMinima } as ObservableValue<String>)
//        PartidosTotalField.textProperty().bind(viewModel.state.map { it.totalPartidos } as ObservableValue<String>)
//        NumJugadoresField.textProperty().bind(viewModel.state.map { it.jugadoresTotal } as ObservableValue<String> )
//
//        //Entrenadores
//        salarioPromedioField.textProperty().bind(viewModel.state.map { it.salarioPromedio } as ObservableValue<String>)
//        fechaAntiguaField.textProperty().bind(viewModel.state.map{ it.incorporacionAntigua })
//        fechaActualField.textProperty().bind(viewModel.state.map{ it.nacimientoActual })
//        entrenadoresAsistentesField.textProperty().bind(viewModel.state.map { it.entrenadoresAsistentes } as ObservableValue<String>)
//
//        //General
//        golesPromedioField.textProperty().bind(viewModel.state.map { it.golesPromedio } as ObservableValue<String>)
//        minutosPromedioField.textProperty().bind(viewModel.state.map { it.minutosPromedio } as ObservableValue<String>)


        //Valores de la plantila


        //Tabla
        viewModel.state.addListener{ _, _, newValue ->
            if (plantillaTable.items != newValue.plantilla) {
                plantillaTable.items = FXCollections.observableArrayList(newValue.plantilla)
            }
        }

    }

    fun initEvents() {
        logger.debug { "Iniciando eventos" }

        menuHelp.setOnAction { onHelpAction() }

        menuSalir.setOnAction { RoutesManager.onAppExit() }

        menuImportarZip.setOnAction {  }

        menuExportarZip.setOnAction {  }

        menuImportarJson.setOnAction {  }

        menuExportarJson.setOnAction {  }

        cambioUser.setOnAction { onLoginAction() }

        añadirButton.setOnAction { onAddMemberAction()}

        //Combo box de Tipo
        filterComboBox.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            newValue?.let { onComboSelected(newValue) }
        }

        //Tabla
        plantillaTable.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            newValue?.let { onTableSelected(newValue,) }
        }

        //Buscador
        searchText.setOnKeyReleased { newValue ->
            newValue?.let { onKeyReleasedAction() }
        }

    }

    fun onLoginAction(){
        TODO()
    }

    fun onHelpAction() {
        logger.debug { "onHelpAction" }
        RoutesManager.initHelpStage()
    }

    fun onTableSelected(newValue: Plantilla){
        viewModel.updatePlantillaSelecionado(newValue)
    }

    fun onKeyReleasedAction(){
        filterDataTable()
    }

    fun filterDataTable(){
        plantillaTable.items = FXCollections.observableList(viewModel.plantillaFilteredList(filterComboBox.value, searchText.text.trim()))
    }

    fun onComboSelected(newValue: String){
        filterDataTable()
    }

    fun onAddMemberAction() {
        logger.debug { "onAddMemberAction" }
        añadirButton.setOnAction {
            buttonGuardar.isDisable = false
            buttonCancelar.isDisable = false
            paisField.isDisable = false
            fechaIncorporacionField.isDisable = false
            fechaNacimientoField.isDisable = false
            salarioField.isDisable = false
            apellidosField.isDisable = false
            nombreField.isDisable = false
            rolComboBox.isDisable = false
        }
    }

    fun onEditMemberAction() {
        logger.debug { "onEditMemberAction" }
        editarButton.setOnAction {
            buttonGuardar.isDisable = false
            buttonCancelar.isDisable = false
            paisField.isDisable = false
            fechaIncorporacionField.isDisable = false
            fechaNacimientoField.isDisable = false
            salarioField.isDisable = false
            apellidosField.isDisable = false
            nombreField.isDisable = false
            rolComboBox.isDisable = false
        }
    }

    //Por implementar
    fun onDeleteMemberAction() {
        logger.debug { "onDeleteMemberAction" }
    }

    //Aquí irá lo que diferencia entre entrenador y jugador, es decir, cuando se seleccione en la comboBox de Rol la opcion jugador
    //Automaticamente se activarán los botones de dicho rol para poder salvarlos o editarlos
    fun onComboBoxAction() {
        rolComboBox.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            when (newValue) {
                "Jugador" -> {
                    //Field de jugador
                    posicionComboBox.isDisable = false
                    dorsalField.isDisable = false
                    alturaField.isDisable = false
                    pesoField.isDisable = false
                    golesField.isDisable = false
                    partidosField.isDisable = false

                    //Field de entrenador
                    especialidadComboBox.isDisable = true
                }
                "Entrenador" -> {
                    //Field de entrenador
                    especialidadComboBox.isDisable = false

                    //Field de jugador
                    posicionComboBox.isDisable = true
                    dorsalField.isDisable = true
                    alturaField.isDisable = true
                    pesoField.isDisable = true
                    golesField.isDisable = true
                    partidosField.isDisable = true
                }
            }
        }
    }
}