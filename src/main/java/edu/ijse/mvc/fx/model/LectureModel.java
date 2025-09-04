package edu.ijse.mvc.fx.model;

import edu.ijse.mvc.fx.db.DBConnection;
import edu.ijse.mvc.fx.dto.LectureDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LectureModel {

    public String addLecture(LectureDto lectureDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO lecture VALUES (?,?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"lecture_id");
        st.setString(2,"name");
        st.setString(3,"contact_number");

        return st.executeUpdate() > 0 ? "Lecture Added Successfully" : "Lecture Added Failed";

    }

    public String updateLecture(LectureDto lectureDto) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE lecture SET name = ? , contact_number = ? , WHERE lecture_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"name");
        st.setString(2,"contact_number");
        st.setString(3,"lecture_id");

        return st.executeUpdate() > 0 ? "Lecture Updated Successfully" : "Lecture Updated Failed";

    }

    public String deleteLecture(String lecture_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM lecture WHERE lecture_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"lecture_id");

        return st.executeUpdate() > 0 ? "Lecture Deleted Successfully" : "Lecture Deleted Failed";

    }

    public LectureDto searchLecture(String lecture_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM lecture WHERE lecture_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        if(rst.next()){
            return new LectureDto(
              rst.getString("lecture_id"),
              rst.getString("name"),
              rst.getString("contact_number")
            );
        }

        return null;

    }

    public ArrayList <LectureDto> getAllLectures() throws Exception{

        ArrayList <LectureDto> lectureDtos = new ArrayList<>();

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM lecture_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        while (rst.next()){
            lectureDtos.add(new LectureDto(
                rst.getString("lecture_id"),
                rst.getString("name"),
                rst.getString("contact_number")
            ));
        }

        return lectureDtos;

    }

}
