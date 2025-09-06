package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.AttendanceController;
import edu.ijse.mvc.fx.dto.AttendanceDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.Objects;

public class ManageAttendanceController {

    private final AttendanceController attendanceController = new AttendanceController();

    @FXML
    private TableColumn<AttendanceDto, String> colCourseName;

    @FXML
    private TableColumn<AttendanceDto, Date> colDate;

    @FXML
    private TableColumn<AttendanceDto, Integer> colId;

    @FXML
    private TableColumn<AttendanceDto, String> colLectureId;

    @FXML
    private TableColumn<AttendanceDto, Objects> colStatus;

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
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        colLectureId.setCellValueFactory(new PropertyValueFactory<>("lectureId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
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
    }

    @FXML
    void Clear(ActionEvent event) {
        studentTxt.setText("");
        lectureTxt.setText("");
        courseTxt.setText("");
        subjectTxt.setText("");
        datePicker.setValue(null);
        statusPicker.setValue(null);
    }

    @FXML
    void navigateDelete(ActionEvent event) {

    }

    @FXML
    void navigateSave(ActionEvent event) {
        try {
            AttendanceDto attendanceDto = new AttendanceDto(
                    1,
                    datePicker.getValue(),
                    studentTxt.getText(),
                    lectureTxt.getText(),
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
                        1,
                        datePicker.getValue(),
                        studentTxt.getText(),
                        lectureTxt.getText(),
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

}
