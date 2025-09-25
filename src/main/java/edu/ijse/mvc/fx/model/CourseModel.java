package edu.ijse.mvc.fx.model;

import edu.ijse.mvc.fx.db.DBConnection;
import edu.ijse.mvc.fx.dto.ClassDto;
import edu.ijse.mvc.fx.dto.CourseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseModel {

    public String addCourse(CourseDto courseDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO course VALUES (?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,courseDto.getCourseID());
        st.setString(2,courseDto.getName());

        return st.executeUpdate() > 0 ? "Course Saved Successfully" : "Course Saved Failed";

    }

    public String updateCourse(CourseDto courseDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE course SET name = ? WHERE course_id = ? ";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,courseDto.getName());
        st.setString(2,courseDto.getCourseID());

        return st.executeUpdate() > 0 ? "Course Updated Successfully" : "Course Updated Failed";

    }

    public String deleteCourse(String course_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM course WHERE course_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,course_id);

        return st.executeUpdate() > 0 ? "Course Deleted Successfully" : "Course Deleted Failed";

    }

    public CourseDto searchCourse(String course_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM course WHERE course_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,course_id);

        ResultSet rst = st.executeQuery();

        if(rst.next()){
            return new CourseDto(
              rst.getString("course_id"),
              rst.getString("name")
            );
        }

        return null;

    }

    public ArrayList <CourseDto> getAllCourses() throws Exception{

        ArrayList <CourseDto> courseDtos  = new ArrayList<>();

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM course";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();
        while (rst.next()){
            courseDtos.add(new CourseDto(
                    rst.getString("course_id"),
                    rst.getString("name")
            ));
        }

        return courseDtos;

    }
}
