package edu.ijse.mvc.fx.controller;

import edu.ijse.mvc.fx.dto.CourseDto;
import edu.ijse.mvc.fx.model.CourseModel;

import java.util.ArrayList;

public class CourseController {

    CourseModel courseModel = new CourseModel();

    public String addCourse(CourseDto courseDto) throws Exception{
        return courseModel.addCourse(courseDto);
    }

    public String updateCourse(CourseDto courseDto) throws Exception{
        return courseModel.updateCourse(courseDto);
    }

    public String deleteCourse(String course_id) throws Exception{
        return courseModel.deleteCourse(course_id);
    }

    public CourseDto searchCourse(String course_id) throws Exception{
        return courseModel.searchCourse(course_id);
    }

    public ArrayList <CourseDto> getAllCourse() throws Exception{
        return courseModel.getAllCourses();
    }

}
