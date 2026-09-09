module edu.westga.dsdm2project {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.westga.dsdm2project1 to javafx.fxml;
    exports edu.westga.dsdm2project1;
    exports edu.westga.dsdm2project1.View;
    opens edu.westga.dsdm2project1.View to javafx.fxml;
    exports edu.westga.dsdm2project1.Model;
    opens edu.westga.dsdm2project1.Model to javafx.fxml;
}