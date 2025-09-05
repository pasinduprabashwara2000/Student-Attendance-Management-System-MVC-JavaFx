module edu.ijse.mvc.fx.studentmanagementmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;

    opens edu.ijse.mvc.fx to javafx.fxml;
    opens edu.ijse.mvc.fx.view to javafx.fxml;
    opens edu.ijse.mvc.fx.dto to javafx.base;

    exports edu.ijse.mvc.fx;
}
