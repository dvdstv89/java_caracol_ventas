module com.steved.cinad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.steved.cinad to javafx.fxml;
    exports com.steved.cinad;
}