module com.frontend.keltis {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.frontend.keltis to javafx.fxml;
    exports com.frontend.keltis;
}