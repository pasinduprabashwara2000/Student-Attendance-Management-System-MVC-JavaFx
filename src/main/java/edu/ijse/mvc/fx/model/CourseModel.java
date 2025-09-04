package edu.ijse.mvc.fx.model;

import edu.ijse.mvc.fx.db.DBConnection;
import edu.ijse.mvc.fx.dto.CourseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CourseModel {

    public String addCourse(CourseDto courseDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO course VALUES (?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"course_id");
        st.setString(2,"name");

        return st.executeUpdate() > 0 ? "Course Saved Successfully" : "Course Saved Failed";

    }

    public String updateCourse(CourseDto courseDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE course SET name = ? , WHERE course_id = ? ";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"course_id");
        st.setString(2,"name");

        return st.executeUpdate() > 0 ? "Course Updated Successfully" : "Course Updated Failed";

    }
}
