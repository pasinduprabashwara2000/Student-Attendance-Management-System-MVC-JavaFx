module edu.ijse.mvc.fx.studentmanagementmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;

    opens edu.ijse.mvc.fx to javafx.fxml;
    opens edu.ijse.mvc.fx.view to javafx.fxml;
    opens edu.ijse.mvc.fx.dto to javafx.base;

    exports edu.ijse.mvc.fx;
}
