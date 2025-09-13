package edu.ijse.mvc.fx.controller;

import edu.ijse.mvc.fx.dto.StudentsDto;
import edu.ijse.mvc.fx.model.StudentModel;

import java.util.ArrayList;

public class StudentController {

    private StudentModel studentModel = new StudentModel();

    public String addStudent(StudentsDto studentsDto) throws Exception{
        return studentModel.addStudent(studentsDto);
    }

    public String updateStudent(StudentsDto studentsDto) throws Exception{
        return studentModel.updateStudent(studentsDto);
    }

    public String deleteStudent(Integer reg_num) throws Exception{
        return studentModel.deleteStudent(reg_num);
    }

    public StudentsDto selectStudent(Integer reg_num) throws Exception{
        return studentModel.searchStudent(reg_num);
    }

    public ArrayList <StudentsDto> getAllStudent() throws Exception{
        return studentModel.getAllStudent();
    }

}
