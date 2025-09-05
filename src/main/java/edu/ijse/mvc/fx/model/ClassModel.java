package edu.ijse.mvc.fx.model;

import edu.ijse.mvc.fx.db.DBConnection;
import edu.ijse.mvc.fx.dto.ClassDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClassModel {

    public String addClass(ClassDto classDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO class VALUES (?,?,?,?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"class_id");
        st.setString(2,"course_id");
        st.setString(3,"subject_name");
        st.setString(4,"lecture_id");
        st.setString(5,"date");

        return st.executeUpdate() > 0 ? "Class Saved Successfully" : "Class Saved Failed";

    }

    public String updateClass(ClassDto classDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE class SET course_id = ?, subject_name = ?, lecture_id = ?, date = ?, WHERE class_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"course_id");
        st.setString(2,"subject_name");
        st.setString(3,"lecture_id");
        st.setString(4,"date");
        st.setString(5,"class_id");

        return st.executeUpdate() > 0 ? "Class Updated Successfully" : "Class Updated Failed";

    }

    public String deleteClass(String class_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM class WHERE class_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"class_id");

        return st.executeUpdate() > 0 ? "Class Deleted Successfully" : "Class Deleted Failed";

    }

    public ClassDto searchClass(String class_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM class WHERE class_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        if (rst.next()){
            return new ClassDto(
              rst.getString("class_id"),
              rst.getString("course_id"),
              rst.getString("subject_name"),
              rst.getString("lecture_id"),
              rst.getDate("date").toLocalDate()
            );
        }

        return null;

    }

    public ArrayList <ClassDto> getAllClasses() throws Exception{

        ArrayList <ClassDto> classDtos = new ArrayList<>();

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM class";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        while (rst.next()){
            classDtos.add(new ClassDto(
                    rst.getString("class_id"),
                    rst.getString("course_id"),
                    rst.getString("subject_name"),
                    rst.getString("lecture_id"),
                    rst.getDate("date").toLocalDate()
            ));
        }

        return classDtos;

    }
}
