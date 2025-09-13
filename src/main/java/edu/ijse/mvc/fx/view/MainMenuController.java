package edu.ijse.mvc.fx.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainMenuController {

    @FXML
    private Button attendenceBtn;

    @FXML
    private Button classesBtn;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button coursesBtn;

    @FXML
    private Button enrollBtn;

    @FXML
    private Button lecturesBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button reportsBtn;

    @FXML
    private Button studentsBtn;

    @FXML
    private Button subjectsBtn;

    @FXML
    private Label titleLabel;

    @FXML
    private Hyperlink tradeMarkLabel;

    @FXML
    public void initialize() {
        studentsBtn.setOnAction(e -> loadUI("ManageStudents"));
        lecturesBtn.setOnAction(e -> loadUI("ManageLectures"));
        subjectsBtn.setOnAction(e -> loadUI("ManageSubjects"));
        coursesBtn.setOnAction(e -> loadUI("ManageCourses"));
        classesBtn.setOnAction(e -> loadUI("ManageClasses"));
        enrollBtn.setOnAction(e -> loadUI("ManageEnrollment"));
        attendenceBtn.setOnAction(e -> loadUI("ManageAttendance"));
        reportsBtn.setOnAction(e -> loadUI("ManageAttendanceReport"));
    }

    private void loadUI(String fxmlName) {
        try {
            String fxmlPath = "/edu/ijse/mvc/fx/" + fxmlName + ".fxml";
            URL fxmlLocation = getClass().getResource(fxmlPath);
            Parent root = FXMLLoader.load(fxmlLocation);
            contentPane.getChildren().setAll(root);

        } catch (IOException e) {
            System.err.println("Error loading FXML: " + fxmlName);
            e.printStackTrace();
        }
    }

    public void navigateLogOut(ActionEvent event) throws IOException {

        ((Stage) logOutBtn.getScene().getWindow()).setScene(
                new Scene(FXMLLoader.load(getClass().getResource("/edu/ijse/mvc/fx/ManageLogin.fxml")))
        );
    }
}
