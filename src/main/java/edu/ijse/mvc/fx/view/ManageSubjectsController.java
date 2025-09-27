package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.SubjectController;
import edu.ijse.mvc.fx.dto.SubjectDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageSubjectsController {

    private final SubjectController subjectController = new SubjectController();

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
    private Button updateBtn;

    @FXML
    public void initialize() {
        subject_id.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
        subject_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("courseID"));

        detailsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                searchSubject();
            }
        });

        loadAllSubjects();
    }

    private void loadAllSubjects() {
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(subjectController.getAllSubject());
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    @FXML
    void clear(ActionEvent event) {
        idTxt.clear();
        nameTxt.clear();
        courseTxt.clear();
    }

    @FXML
    void saveSubjects(ActionEvent event) {
        try {
            SubjectDto subjectDto = new SubjectDto(
                    idTxt.getText(),
                    courseTxt.getText(),
                    nameTxt.getText()
            );
            String rsp = subjectController.addSubject(subjectDto);
            loadAllSubjects();
            clear(event);
            new Alert(Alert.AlertType.INFORMATION, rsp).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    @FXML
    void updateSubjects(ActionEvent event) {
        try {
            SubjectDto subjectDto = new SubjectDto(
                    idTxt.getText(),
                    courseTxt.getText(),
                    nameTxt.getText()
            );
            String rsp = subjectController.updateSubject(subjectDto);
            loadAllSubjects();
            new Alert(Alert.AlertType.INFORMATION, rsp).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    @FXML
    void deleteSubjects(ActionEvent event) {
        try {
            String rsp = subjectController.deleteSubject(idTxt.getText());
            loadAllSubjects();
            clear(event);
            new Alert(Alert.AlertType.INFORMATION, rsp).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void searchSubject() {
        SubjectDto selectedItem = detailsTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.ERROR, "Please Select Row").showAndWait();
            return;
        }
        try {
            SubjectDto subjectDto = subjectController.searchSubject(selectedItem.getSubjectID());
            if (subjectDto == null) {
                new Alert(Alert.AlertType.WARNING, "Subject not found!").showAndWait();
                return;
            }
            idTxt.setText(subjectDto.getSubjectID());
            nameTxt.setText(subjectDto.getName());
            courseTxt.setText(subjectDto.getCourseID());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }
}
