package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.CourseController;
import edu.ijse.mvc.fx.dto.CourseDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageCourseController {

    private CourseController courseController = new CourseController();

    @FXML
    private TableColumn<CourseDto, String> colId;

    @FXML
    private TableColumn<CourseDto, String> colName;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<CourseDto> detailsTabel;

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

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        loadTabel();
    }

    @FXML
    public void loadTabel(){
        try{
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(courseController.getAllCourse());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }

        detailsTabel.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                searchCourse();
            }
        });

    }

    @FXML
    void clear(ActionEvent event) {
        idTxt.setText("");
        nameTxt.setText("");
    }

    @FXML
    void deleteCourse(ActionEvent event) {
        try {
            String rsp = courseController.deleteCourse(idTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTabel();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void saveCourse(ActionEvent event) {
        try {
            CourseDto courseDto = new CourseDto(
              idTxt.getText(),
              nameTxt.getText()
            );
            String rsp  = courseController.addCourse(courseDto);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTabel();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void updateCourse(ActionEvent event) {
        try {
            CourseDto courseDto = new CourseDto(
              idTxt.getText(),
              nameTxt.getText()
            );
            String rsp = courseController.updateCourse(courseDto);
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTabel();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void searchCourse() {

        CourseDto getSelectCourse = detailsTabel.getSelectionModel().getSelectedItem();

        if(getSelectCourse == null){
            new Alert(Alert.AlertType.WARNING,"Please Select Row").showAndWait();
        }

        try {
            CourseDto courseDto = courseController.searchCourse(getSelectCourse.getCourseID());
            idTxt.setText(courseDto.getCourseID());
            nameTxt.setText(courseDto.getName());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }
}
