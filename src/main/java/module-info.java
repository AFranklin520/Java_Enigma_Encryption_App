module com.cis2235.franklin.franklinp7 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;
    requires java.desktop;

    opens com.cis2235.franklin.franklinp7 to javafx.fxml;
    exports com.cis2235.franklin.franklinp7;
}