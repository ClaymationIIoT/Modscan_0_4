module com.example.modscan_update_test {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires eu.hansolo.tilesfx;
    requires EasyModbusJava;

    opens com.clay.modscan to javafx.fxml;
    exports com.clay.modscan;
}