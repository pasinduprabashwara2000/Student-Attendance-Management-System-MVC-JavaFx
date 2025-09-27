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
        st.setString(1,classDto.getClassId());
        st.setString(2,classDto.getCourseId());
        st.setString(3,classDto.getSubjectId());
        st.setString(4,classDto.getLectureId());
        st.setDate(5,java.sql.Date.valueOf(classDto.getDate()));

        return st.executeUpdate() > 0 ? "Class Saved Successfully" : "Class Saved Failed";

    }

    public String updateClass(ClassDto classDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE class SET course_id = ?, subject_id = ?, lecture_id = ?, date = ? WHERE class_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,classDto.getCourseId());
        st.setString(2,classDto.getSubjectId());
        st.setString(3,classDto.getLectureId());
        st.setDate(4,java.sql.Date.valueOf(classDto.getDate()));
        st.setString(5,classDto.getClassId());

        return st.executeUpdate() > 0 ? "Class Updated Successfully" : "Class Updated Failed";

    }

    public String deleteClass(String class_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM class WHERE class_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,class_id);

        return st.executeUpdate() > 0 ? "Class Deleted Successfully" : "Class Deleted Failed";

    }

    public ClassDto searchClass(String class_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM class WHERE class_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,class_id);

        ResultSet rst = st.executeQuery();

        if (rst.next()){
            return new ClassDto(
                    rst.getString("class_id"),
                    rst.getString("course_id"),
                    rst.getString("subject_id"),
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
                    rst.getString("subject_id"),
                    rst.getString("lecture_id"),
                    rst.getDate("date").toLocalDate()
            ));
        }

        return classDtos;

    }
}
