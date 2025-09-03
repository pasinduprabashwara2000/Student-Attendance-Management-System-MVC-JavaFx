package edu.ijse.mvc.fx.model;

import edu.ijse.mvc.fx.db.DBConnection;
import edu.ijse.mvc.fx.dto.SubjectDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectModel {

    public String addSubject(SubjectDto subjectDto) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO subject VALUES (?,?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, "subject_id");
        st.setString(2, "name");
        st.setString(3, "course_id");

        return st.executeUpdate() > 0 ? "Subject Saved Successfully" : "Subject Saved Failed";

    }

    public String updateSubject(SubjectDto subjectDto) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE subject SET name = ? , course_id = ? WHERE subject_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, "name");
        st.setString(2, "course_id");
        st.setString(3, "subject_id");

        return st.executeUpdate() > 0 ? "Subject Updated Successfully" : "Subject Updated Failed";

    }

    public String deleteSubject(String subject_id) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM subject WHERE subject_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, "subject_id");

        return st.executeUpdate() > 0 ? "Subject Deleted Successfully" : "Subject Deleted Failed";

    }

    public SubjectDto searchSubject(String id) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM subject WHERE subject_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        if (rst.next()) {
            return new SubjectDto(
                    rst.getString("subject_id"),
                    rst.getString("name"),
                    rst.getString("course_id")
            );
        }

        return null;

    }

    public ArrayList<SubjectDto> getAllSubjects() throws Exception {

        ArrayList<SubjectDto> subjectDtos = new ArrayList<>();

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM subject";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        while (rst.next()) {
            subjectDtos.add(new SubjectDto(
                    rst.getString("subject_id"),
                    rst.getString("name"),
                    rst.getString("course_id")
            ));
        }

        return subjectDtos;

    }

}
