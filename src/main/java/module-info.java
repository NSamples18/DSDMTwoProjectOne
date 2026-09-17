/**
 * JavaFX App.
 */
module edu.westga.dsdm2project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens edu.westga.dsdm2project1 to javafx.fxml;
    exports edu.westga.dsdm2project1;
    exports edu.westga.dsdm2project1.View;
    opens edu.westga.dsdm2project1.View to javafx.fxml;
    exports edu.westga.dsdm2project1.Model;
    opens edu.westga.dsdm2project1.Model;
}