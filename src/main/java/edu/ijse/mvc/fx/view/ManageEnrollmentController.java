package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.EnrollController;
import edu.ijse.mvc.fx.dto.EnrollDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageEnrollmentController {

    private final EnrollController enrollController = new EnrollController();

    @FXML
    private TableColumn<EnrollDto, String> colCourseId;

    @FXML
    private TableColumn<EnrollDto, Integer> colRegNum;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<EnrollDto> detailsTable;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private Label regLabel;

    @FXML
    private TextField regTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Label titleLabel;

    @FXML
    private Button updateBtn;

    @FXML
    public void initialize(){
        colRegNum.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        loadTable();
    }

    @FXML
    public void loadTable(){
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(enrollController.getAllEnroll());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void clear(ActionEvent event) {
        regTxt.setText("");
        idTxt.setText("");
    }

    @FXML
    void deleteEnrollment(ActionEvent event) {
        try {
            String rsp = enrollController.deleteEnroll(Integer.parseInt(regTxt.getText()));
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void saveEnrollment(ActionEvent event) {
        try {
            EnrollDto enrollDto = new EnrollDto(
                    Integer.parseInt(regTxt.getText()),
                    idTxt.getText()

            );
            String rsp = enrollController.addEnroll(enrollDto);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }
    }

    @FXML
    void updateEnrollment(ActionEvent event) {
        try {
            EnrollDto enrollDto = new EnrollDto(
                    Integer.parseInt(regTxt.getText()),
                    idTxt.getText()
            );
            String rsp = enrollController.updateEnroll(enrollDto);
            clear(event);
            loadTable();
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
