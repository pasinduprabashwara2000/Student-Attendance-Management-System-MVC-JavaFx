package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.UserController;
import edu.ijse.mvc.fx.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ManageLoginController {

    private final UserController userController = new UserController();

    @FXML
    private Button clearBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private Label nameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Label roleLabel;

    @FXML
    private ChoiceBox<String> rolePicker;

    @FXML
    private Label titleLabel;

    @FXML
    private Hyperlink tradeMarkLabel;

    @FXML
    private TextField userTxt;

    @FXML
    void navigateClear(ActionEvent event) {
        userTxt.setText("");
        passwordTxt.setText("");
        rolePicker.setValue(null);
    }

    @FXML
    void navigateLogin(ActionEvent event) {
        try {
            UserDto userDto = new UserDto(
                    userTxt.getText(),
                    passwordTxt.getText(),
                    rolePicker.getValue()
            );

            boolean rsp = userController.login(userDto);

            if(rsp){
                if(userDto.getRole().equalsIgnoreCase("Admin")){
                    openWindow("/edu/ijse/mvc/fx/MainMenu.fxml","Main Menu");
                    ((Stage) loginBtn.getScene().getWindow()).close();
                } else if (userDto.getRole().equalsIgnoreCase("Lecture")) {
                    openWindow("/edu/ijse/mvc/fx/ManageAttendance.fxml","Manage Attendance");
                    ((Stage) loginBtn.getScene().getWindow()).close();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Invalid Username or Password");
                }
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void openWindow (String fxmlpath, String title) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(ManageLoginController.class.getResource(fxmlpath));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
    }
}
