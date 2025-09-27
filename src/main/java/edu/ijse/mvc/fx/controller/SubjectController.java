package edu.ijse.mvc.fx.controller;

import edu.ijse.mvc.fx.dto.SubjectDto;
import edu.ijse.mvc.fx.model.SubjectModel;
import java.util.ArrayList;

public class SubjectController {

    SubjectModel subjectModel = new SubjectModel();

    public String addSubject(SubjectDto subjectDto) throws Exception{
        return subjectModel.addSubject(subjectDto);
    }

    public String updateSubject(SubjectDto subjectDto) throws Exception{
        return subjectModel.updateSubject(subjectDto);
    }

    public String deleteSubject(String subject_id) throws Exception{
        return subjectModel.deleteSubject(subject_id);
    }

    public SubjectDto searchSubject(String subject_id) throws Exception{
        return subjectModel.searchSubject(subject_id);
    }

    public ArrayList <SubjectDto> getAllSubject() throws Exception{
        return subjectModel.getAllSubjects();
    }

}
