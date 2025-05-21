module org.example.fichaplantillaconvocatoria {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens org.example.fichaplantillaconvocatoria to javafx.fxml;
    exports org.example.fichaplantillaconvocatoria;
}