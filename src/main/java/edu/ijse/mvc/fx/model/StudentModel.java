package edu.ijse.mvc.fx.model;

import edu.ijse.mvc.fx.db.DBConnection;
import edu.ijse.mvc.fx.dto.StudentsDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentModel {

    public String addStudent(StudentsDto studentsDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO student VALUES (?,?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1,studentsDto.getRegNum());
        st.setString(2,studentsDto.getName());
        st.setString(3,studentsDto.getContactDetails());

        return st.executeUpdate() > 0 ? "Student Save Successfully" : "Student Saved Failed";

    }

    public String updateStudent(StudentsDto studentsDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE student SET name = ? , contact_details = ? WHERE reg_number = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,studentsDto.getName());
        st.setString(2,studentsDto.getContactDetails());
        st.setInt(3,studentsDto.getRegNum());

        return st.executeUpdate() > 0 ? "Student Updated Successfully" : "Student Updated Failed";

    }

    public String deleteStudent(Integer reg_num) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM student WHERE reg_number = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1,reg_num);

        return st.executeUpdate() > 0 ? "Student Deleted Successfully" : "Student Deleted Failed";

    }

    public StudentsDto searchStudent(Integer reg_num) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM student WHERE reg_number = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, reg_num);

        ResultSet rst = st.executeQuery();

        if (rst.next()) {
            return new StudentsDto(
                    rst.getInt("reg_number"),
                    rst.getString("name"),
                    rst.getString("contact_details")
            );
        }

        return null;

    }

    public ArrayList <StudentsDto> getAllStudent() throws Exception {

        ArrayList <StudentsDto> studentsDtos = new ArrayList<>();

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM student";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        while (rst.next()){
            studentsDtos.add(new StudentsDto(
               rst.getInt("reg_number"),
               rst.getString("name"),
               rst.getString("contact_details")
            ));
        }

        return studentsDtos;

    }
}
