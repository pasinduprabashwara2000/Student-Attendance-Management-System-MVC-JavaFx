package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.StudentController;
import edu.ijse.mvc.fx.dto.StudentsDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageStudentsController {

    @FXML
    private TableColumn<StudentsDto, String> colContact;

    @FXML
    private TableColumn<StudentsDto, Integer> colId;

    @FXML
    private TableColumn<StudentsDto, String> colName;

    @FXML
    private Label contactLabel;

    @FXML
    private TextField contactTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<StudentsDto> detailsTabel;

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
    private Label titleLabel;

    @FXML
    private Button updateBtn;

    private StudentController studentController = new StudentController();

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("regNum"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactDetails"));
        loadTabel();
    }

    @FXML
    public void loadTabel(){
        try {
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(studentController.getAllStudent());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        detailsTabel.setOnMouseClicked( event -> {
            if(event.getClickCount() == 1){
                selectStudent();
            }
        });
    }

    @FXML
    void clear(ActionEvent event) {
        idTxt.setText("");
        nameTxt.setText("");
        contactTxt.setText("");
    }

    @FXML
    void deleteStudents(ActionEvent event) {
        try {
            String rsp = studentController.deleteStudent(Integer.parseInt(idTxt.getText()));
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTabel();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void saveStudents(ActionEvent event) {
        try {
            StudentsDto studentsDto = new StudentsDto(
              Integer.parseInt(idTxt.getText()),
              nameTxt.getText(),
              contactTxt.getText()
            );
            String rsp = studentController.addStudent(studentsDto);
            clear(event);
            loadTabel();
            new Alert(Alert.AlertType.INFORMATION,rsp).show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void updateStudents(ActionEvent event) {
        try {
            StudentsDto studentsDto = new StudentsDto(
              Integer.parseInt(idTxt.getText()),
              nameTxt.getText(),
              contactTxt.getText()
            );
            String rsp = studentController.updateStudent(studentsDto);
            loadTabel();
            clear(event);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void selectStudent(){

        StudentsDto selectedItem = detailsTabel.getSelectionModel().getSelectedItem();

        if(selectedItem == null){
            new Alert(Alert.AlertType.WARNING,"Please Select Raw").show();
        }

        try {
            StudentsDto studentsDto = studentController.selectStudent(selectedItem.getRegNum());
                idTxt.setText(String.valueOf(studentsDto.getRegNum()));
                nameTxt.setText(studentsDto.getName());
                contactTxt.setText(studentsDto.getContactDetails());
        } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

}
