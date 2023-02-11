module com.frontend.keltis {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.frontend.keltis to javafx.fxml;
    exports com.frontend.keltis;
}