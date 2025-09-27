package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.AttendanceController;
import edu.ijse.mvc.fx.dto.AttendanceDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Date;
import java.util.Objects;

public class ManageAttendanceController {

    private final AttendanceController attendanceController = new AttendanceController();

    @FXML
    private Label attendanceLabel;

    @FXML
    private TextField attendanceTxt;

    @FXML
    private TableColumn<AttendanceDto, String> colCourseName;

    @FXML
    private TableColumn<AttendanceDto, Date> colDate;

    @FXML
    private TableColumn<AttendanceDto, Integer> colId;

    @FXML
    private TableColumn<AttendanceDto, String> colLectureId;

    @FXML
    private TableColumn<AttendanceDto, String> colStatus;

    @FXML
    private TableColumn<AttendanceDto, String> colStudentName;

    @FXML
    private TableColumn<AttendanceDto, String> colSubjectName;

    @FXML
    private TextField courseTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView <AttendanceDto> detailsTable;

    @FXML
    private TextField lectureTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox <String> statusPicker;

    @FXML
    private TextField studentTxt;

    @FXML
    private TextField subjectTxt;

    @FXML
    private Label titleLabel;

    @FXML
    private Button updateBtn;

    @FXML
    private Button delBtn;

    @FXML
    private TextField delTxt;

    @FXML
    void Delete(ActionEvent event) {
            try {
                String rsp = attendanceController.deleteAttendance(Integer.parseInt(delTxt.getText()));
                new Alert(Alert.AlertType.INFORMATION,rsp).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
    }

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colLectureId.setCellValueFactory(new PropertyValueFactory<>("lectureId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadTable();
    }

    @FXML
    public void loadTable(){
        try{
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(attendanceController.getAllAttendance());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        detailsTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                searchAttendance();
            }
        });
    }

    @FXML
    void Clear(ActionEvent event) {
        attendanceTxt.setText("");
        lectureTxt.setText("");
        studentTxt.setText("");
        courseTxt.setText("");
        subjectTxt.setText("");
        datePicker.setValue(null);
        statusPicker.setValue(null);
    }

    @FXML
    void navigateDelete(ActionEvent event) {
            try {
               String rsp = attendanceController.deleteAttendance(Integer.parseInt(attendanceTxt.getText()));
                new Alert(Alert.AlertType.INFORMATION,rsp);
                Clear(event);
                loadTable();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            AttendanceDto attendanceDto = new AttendanceDto(
                    Integer.parseInt(attendanceTxt.getText()),
                    datePicker.getValue(),
                    lectureTxt.getText(),
                    studentTxt.getText(),
                    courseTxt.getText(),
                    subjectTxt.getText(),
                    statusPicker.getValue()
            );
            String rsp = attendanceController.addAttendance(attendanceDto);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            Clear(event);
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
            try{
                AttendanceDto attendanceDto = new AttendanceDto(
                        Integer.parseInt(attendanceTxt.getText()),
                        datePicker.getValue(),
                        lectureTxt.getText(),
                        studentTxt.getText(),
                        courseTxt.getText(),
                        subjectTxt.getText(),
                        statusPicker.getValue()
                );
                String rsp = attendanceController.updateAttendance(attendanceDto);
                new Alert(Alert.AlertType.INFORMATION,rsp).show();
                Clear(event);
                loadTable();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
    }

    @FXML
    public void searchAttendance(){
        AttendanceDto getSelectedAttendance = detailsTable.getSelectionModel().getSelectedItem();
        if(getSelectedAttendance == null){
            new Alert(Alert.AlertType.ERROR,"Please Select Row");
        }

        try {
            AttendanceDto attendanceDto = attendanceController.searchAttendance(getSelectedAttendance.getAttendanceId());
                    attendanceTxt.setText(String.valueOf(attendanceDto.getAttendanceId()));
                    datePicker.setValue(attendanceDto.getDate());
                    lectureTxt.setText(attendanceDto.getLectureId());
                    studentTxt.setText(attendanceDto.getStudentName());
                    courseTxt.setText(attendanceDto.getCourseName());
                    subjectTxt.setText(attendanceDto.getSubjectName());
                    statusPicker.setValue(attendanceDto.getStatus());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

}
