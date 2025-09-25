package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.ClassController;
import edu.ijse.mvc.fx.dto.ClassDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageClassController {

    private final ClassController classController = new ClassController();

    @FXML
    private TableColumn<ClassDto, String> classId;

    @FXML
    private TableColumn<ClassDto, String> courseId;

    @FXML
    private TextField courseTxt;

    @FXML
    private TableColumn<ClassDto, String> date;

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
    private Label titleLabel;

    @FXML
    private Button updateBtn;

    @FXML
    public void initialize(){
        classId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        subjectId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        courseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        lectureId.setCellValueFactory(new PropertyValueFactory<>("lectureId"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadTable();
    }

    @FXML
    public void loadTable(){
        try{
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(classController.getAllClass());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

        detailsTabel.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                searchClasses();
            }
        });
    }

    @FXML
    void clear(ActionEvent event) {
        idTxt.setText("");
        subjectTxt.setText("");
        courseTxt.setText("");
        lectureTxt.setText("");
        dateTxt.setValue(null);
    }

    @FXML
    void deleteClasses(ActionEvent event) {
        try{
            String rsp = classController.deleteClass(idTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void saveClasses(ActionEvent event) {
        try {
            ClassDto classDto = new ClassDto(
              idTxt.getText(),
              subjectTxt.getText(),
              courseTxt.getText(),
              lectureTxt.getText(),
              dateTxt.getValue()
            );
            String rsp = classController.addClass(classDto);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void updateClasses(ActionEvent event) {
        try {
            ClassDto classDto = new ClassDto(
                    idTxt.getText(),
                    subjectTxt.getText(),
                    courseTxt.getText(),
                    lectureTxt.getText(),
                    dateTxt.getValue()
            );
            String rsp = classController.updateClass(classDto);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    public void searchClasses(){

        ClassDto getSelectedClass = detailsTabel.getSelectionModel().getSelectedItem();
        if(getSelectedClass == null){
            new Alert(Alert.AlertType.ERROR,"Please Select Row").showAndWait();
        }

        try {
            ClassDto classDto = classController.searchClass(getSelectedClass.getClassId());
            idTxt.setText(classDto.getClassId());
            subjectTxt.setText(classDto.getSubjectId());
            courseTxt.setText(classDto.getCourseId());
            lectureTxt.setText(classDto.getLectureId());
            dateTxt.setValue(classDto.getDate());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

}
