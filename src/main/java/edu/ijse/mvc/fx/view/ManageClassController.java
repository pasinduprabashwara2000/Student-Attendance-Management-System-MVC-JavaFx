package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.ClassController;
import edu.ijse.mvc.fx.dto.ClassDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ManageClassController {

    private final ClassController classController = new ClassController();

    @FXML
    private TableColumn<ClassDto, String> classId;

    @FXML
    private TableColumn<ClassDto, String> courseId;

    @FXML
    private TextField courseTxt;

    @FXML
    private TableColumn<ClassDto, LocalDate> date;

    @FXML
    private Label dateLabel;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<ClassDto> detailsTabel;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private Label lecLabel;

    @FXML
    private TableColumn<ClassDto, String> lectureId;

    @FXML
    private TextField lectureTxt;

    @FXML
    private Label nameLabel;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Label subLabel;

    @FXML
    private TableColumn<ClassDto, String> subjectId;

    @FXML
    private TextField subjectTxt;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize() {

        classId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        subjectId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        courseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        lectureId.setCellValueFactory(new PropertyValueFactory<>("lectureId"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadTable();
    }

    @FXML
    private void loadTable() {
        try {
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(classController.getAllClass());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        detailsTabel.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                searchClasses();
            }
        });
    }

    @FXML
    void clear(ActionEvent event) {
        idTxt.clear();
        courseTxt.clear();
        subjectTxt.clear();
        lectureTxt.clear();
        dateTxt.setValue(null);
        detailsTabel.getSelectionModel().clearSelection();
    }

    @FXML
    void saveClasses(ActionEvent event) {
        try {
            ClassDto classesDto = new ClassDto(
                    idTxt.getText(),
                    courseTxt.getText(),
                    subjectTxt.getText(),
                    lectureTxt.getText(),
                    dateTxt.getValue()
            );

            String rsp = classController.addClass(classesDto);
            clear(event);
            loadTable();

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
    void updateClasses(ActionEvent event) {
        try {
            ClassDto classesDto = new ClassDto(
                    idTxt.getText(),
                    courseTxt.getText(),
                    subjectTxt.getText(),
                    lectureTxt.getText(),
                    dateTxt.getValue()
            );

            String rsp = classController.updateClass(classesDto);
            clear(event);
            loadTable();

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
    public void deleteClasses(ActionEvent event) {
        try {
            String rsp = classController.deleteClass(idTxt.getText());
            clear(event);
            loadTable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rsp);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void searchClasses(){
        ClassDto getSelectedClass = detailsTabel.getSelectionModel().getSelectedItem();
        if(getSelectedClass == null){
            new Alert(Alert.AlertType.ERROR,"Please Select Row");
        }

        try {
            ClassDto classDto = classController.searchClass(getSelectedClass.classId);
            idTxt.setText(classDto.getClassId());
            courseTxt.setText(classDto.getCourseId());
            subjectTxt.setText(classDto.getSubjectId());
            lectureTxt.setText(classDto.getLectureId());
            dateTxt.setValue(classDto.getDate());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }
}
