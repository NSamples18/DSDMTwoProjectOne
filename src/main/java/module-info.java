/**
 * JavaFX App.
 */
module edu.westga.dsdm2project1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens edu.westga.dsdm2project1 to javafx.fxml;
    exports edu.westga.dsdm2project1;
    exports edu.westga.dsdm2project1.view;
    opens edu.westga.dsdm2project1.view to javafx.fxml;
    exports edu.westga.dsdm2project1.model;
    opens edu.westga.dsdm2project1.model;
}