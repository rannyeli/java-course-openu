module exercise2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens exercise2 to javafx.fxml;
    exports exercise2;
}
