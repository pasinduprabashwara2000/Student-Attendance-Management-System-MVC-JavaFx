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
    private TableColumn<SubjectDto,String> course_id;

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

        loadAllSubjects();
    }

    private void loadAllSubjects() {
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(subjectController.getAllSubjects());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        detailsTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                searchSubject();
            }
        });

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
                    nameTxt.getText(),
                    courseTxt.getText()
            );
            String rsp = subjectController.addSubject(subjectDto);
            detailsTable.getItems().add(subjectDto);
            clear(event);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rsp);
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
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
            loadAllSubjects();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rsp);
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void deleteSubjects(ActionEvent event) {

        try {
            String rsp = subjectController.deleteSubject(idTxt.getText());
            loadAllSubjects();
            clear(event);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rsp);
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void searchSubject(){

        SubjectDto getSelectedItem = detailsTable.getSelectionModel().getSelectedItem();

        if(getSelectedItem == null){
            new Alert(Alert.AlertType.ERROR,"Please Select Row").showAndWait();
        }

        try {
            SubjectDto subjectDto = subjectController.searchSubject(getSelectedItem.getSubjectID());
            idTxt.setText(subjectDto.getSubjectID());
            nameTxt.setText(subjectDto.getName());
            courseTxt.setText(subjectDto.getCourseID());
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).showAndWait();
        }

    }
}
