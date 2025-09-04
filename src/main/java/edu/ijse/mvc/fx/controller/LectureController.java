package edu.ijse.mvc.fx.controller;

import edu.ijse.mvc.fx.dto.LectureDto;
import edu.ijse.mvc.fx.model.LectureModel;

import java.util.ArrayList;

public class LectureController {

    LectureModel lectureModel = new LectureModel();

    public String addLecture(LectureDto lectureDto) throws Exception{
        return lectureModel.addLecture(lectureDto);
    }

    public String updateLecture(LectureDto lectureDto) throws Exception{
        return lectureModel.updateLecture(lectureDto);
    }

    public String deleteLecture(String lecture_id) throws Exception{
        return lectureModel.deleteLecture(lecture_id);
    }

    public LectureDto searchLecture(String lecture_id) throws Exception{
        return lectureModel.searchLecture(lecture_id);
    }

    public ArrayList <LectureDto> getAllLectures() throws Exception{
        return lectureModel.getAllLectures();
    }

}
