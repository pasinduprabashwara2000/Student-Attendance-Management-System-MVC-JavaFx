module edu.ijse.mvc.fx.studentmanagementmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;


    opens edu.ijse.mvc.fx to javafx.fxml;
    exports edu.ijse.mvc.fx;
}