module exercise1 {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens exercise1 to javafx.fxml;

    exports exercise1;
}
// module exercise2 {
// requires transitive javafx.graphics;
// requires javafx.controls;
// requires javafx.fxml;

// opens exercise2 to javafx.fxml;

// exports exercise2;
// }