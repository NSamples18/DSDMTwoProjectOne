module edu.westga.dsdm2project {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.westga.dsdm2project1 to javafx.fxml;
    exports edu.westga.dsdm2project1;
}