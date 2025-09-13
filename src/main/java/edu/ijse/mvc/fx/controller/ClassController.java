package edu.ijse.mvc.fx.controller;

import edu.ijse.mvc.fx.dto.ClassDto;
import edu.ijse.mvc.fx.model.ClassModel;

import java.util.ArrayList;

public class ClassController {

    ClassModel classModel = new ClassModel();

    public String addClass(ClassDto classDto) throws Exception{
        return classModel.addClass(classDto);
    }

    public String updateClass(ClassDto classDto) throws Exception{
        return classModel.updateClass(classDto);
    }

    public String deleteClass(String class_id) throws Exception{
        return classModel.deleteClass(class_id);
    }

    public ClassDto searchClass(String class_id) throws Exception{
        return classModel.searchClass(class_id);
    }

    public ArrayList <ClassDto> getAllClass() throws Exception{
        return classModel.getAllClasses();
    }

}
