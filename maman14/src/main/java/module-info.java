module exercise2 {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens exercise2 to javafx.fxml;

    exports exercise2;
}
