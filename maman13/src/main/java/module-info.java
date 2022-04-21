module exercise1 {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens exercise1 to javafx.fxml;

    exports exercise1;
}
