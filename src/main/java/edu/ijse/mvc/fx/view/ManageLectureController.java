package edu.ijse.mvc.fx.view;

import edu.ijse.mvc.fx.dto.LectureDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageLectureController {

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
    void clear(ActionEvent event) {

    }

    @FXML
    void deleteLecture(ActionEvent event) {

    }

    @FXML
    void saveLecture(ActionEvent event) {

    }

    @FXML
    void updateLecture(ActionEvent event) {

    }

}
