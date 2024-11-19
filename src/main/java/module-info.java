module org.prodigy_sd_01 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.prodigy_sd_01 to javafx.fxml;
    exports org.prodigy_sd_01;
}