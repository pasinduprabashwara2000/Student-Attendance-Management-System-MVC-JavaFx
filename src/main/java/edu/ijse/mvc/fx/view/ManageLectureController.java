package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.controller.LectureController;
import edu.ijse.mvc.fx.dto.LectureDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageLectureController {

    private LectureController lectureController = new LectureController();

    @FXML
    private TableColumn<LectureDto, String> contactColmn;

    @FXML
    private Label contactLabel;

    @FXML
    private TextField contactTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<LectureDto> detailsTabel;

    @FXML
    private TableColumn<LectureDto, String> idColmn;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private TableColumn<LectureDto, String> nameColmn;

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
        idColmn.setCellValueFactory(new PropertyValueFactory<>("lectureID"));
        nameColmn.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactColmn.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        loadTabel();

        detailsTabel.setOnMouseClicked(event -> {
            searchLecture();
        });
    }

    @FXML
    public void loadTabel(){

        try {
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(lectureController.getAllLectures());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void clear(ActionEvent event) {
        idTxt.setText("");
        nameTxt.setText("");
        contactTxt.setText("");
    }

    @FXML
    void deleteLecture(ActionEvent event) {
        try {
            String rsp = lectureController.deleteLecture(idTxt.getText());
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
            clear(event);
            loadTabel();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void saveLecture(ActionEvent event) {
        try {
            LectureDto lectureDto = new LectureDto(
              idTxt.getText(),
              nameTxt.getText(),
              contactTxt.getText()
            );
            String rsp = lectureController.addLecture(lectureDto);
            clear(event);
            loadTabel();
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void updateLecture(ActionEvent event) {

        try {
            LectureDto lectureDto = new LectureDto(
                    idTxt.getText(),
                    nameTxt.getText(),
                    contactTxt.getText()
            );
            String rsp = lectureController.updateLecture(lectureDto);
            clear(event);
            loadTabel();
            new Alert(Alert.AlertType.INFORMATION,rsp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void searchLecture(){

        LectureDto getSelectedLecture = detailsTabel.getSelectionModel().getSelectedItem();
            if(getSelectedLecture == null){
                new Alert(Alert.AlertType.WARNING,"Please SELECT RAW");
            }

        try {
            LectureDto lectureDto = lectureController.searchLecture(getSelectedLecture.getLectureID());
            idTxt.setText(lectureDto.getLectureID());
            nameTxt.setText(lectureDto.getName());
            contactTxt.setText(lectureDto.getContact_number());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

}
