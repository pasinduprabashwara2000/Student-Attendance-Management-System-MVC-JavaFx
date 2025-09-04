package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.SubjectController;
import edu.ijse.mvc.fx.dto.SubjectDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageSubjectsController {

    private SubjectController subjectController = new SubjectController();

    @FXML
    private Label courseLabel;

    @FXML
    private TextField courseTxt;

    @FXML
    private TableColumn<SubjectDto, String> course_id;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<SubjectDto> detailsTable;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TableColumn<SubjectDto, String> subject_id;

    @FXML
    private TableColumn<SubjectDto, String> subject_name;

    @FXML
    private Label titleLabel;

    @FXML
    private Button updateBtn;

    @FXML
    public void initialize(){
        subject_id.setCellValueFactory(new PropertyValueFactory<>("subject_id"));
        subject_name.setCellValueFactory(new PropertyValueFactory<>("subject_name"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        loadTabel();
    }

    @FXML
    public void loadTabel(){
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(subjectController.getAllSubjects());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void clear(ActionEvent event) {
        idTxt.setText("");
        nameTxt.setText("");
        courseTxt.setText("");
    }

    @FXML
    void deleteSubjects(ActionEvent event) {
        try {
            String rsp = subjectController.deleteSubject(idTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTabel();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void saveSubjects(ActionEvent event) {
        try {
            SubjectDto subjectDto = new SubjectDto(
              idTxt.getText(),
              nameTxt.getText(),
              courseTxt.getText()
            );
            String rsp = subjectController.addSubject(subjectDto);
            clear(event);
            loadTabel();
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void updateSubjects(ActionEvent event) {
            try {
                SubjectDto subjectDto = new SubjectDto(
                  idTxt.getText(),
                  nameTxt.getText(),
                  courseTxt.getText()
                );
                String rsp = subjectController.updateSubject(subjectDto);
                new Alert(Alert.AlertType.INFORMATION,rsp).show();
                clear(event);
                loadTabel();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
    }

}
