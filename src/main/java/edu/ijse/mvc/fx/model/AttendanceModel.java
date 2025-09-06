package edu.ijse.mvc.fx.model;

import edu.ijse.mvc.fx.db.DBConnection;
import edu.ijse.mvc.fx.dto.AttendanceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceModel {

    public String addAttendance(AttendanceDto attendanceDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO attendance VALUES (?,?,?,?,?,?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,"attendance_id");
            st.setString(2,"date");
            st.setString(3,"lecture_id");
            st.setString(4,"student_name");
            st.setString(5,"course_name");
            st.setString(6,"subject_name");
            st.setString(7,"status");

        return st.executeUpdate() > 0 ? "Attendance Inserted Successfully" : "Attendance Inserted Failed";

    }

    public String updateAttendance(AttendanceDto attendanceDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE attendance SET date = ?, lecture_id = ?, student_name = ? , course_name = ?, subject_name = ?, status = ? WHERE attendance_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,"date");
            st.setString(2,"lecture_id");
            st.setString(3,"student_name");
            st.setString(4,"course_name");
            st.setString(5,"subject_name");
            st.setString(6,"status");
            st.setString(7,"attendance_id");

        return st.executeUpdate() > 0 ? "Attendance Updated Successfully" : "Attendance Updated Failed";

    }

    public String deleteAttendance(int attendance_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM attendance WHERE attendance_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"attendance_id");

        return st.executeUpdate() > 0 ? "Attendance Delete Successfully" : "Attendance Delete Failed";

    }

    public AttendanceDto searchAttendance(int attendance_id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM attendance WHERE attendance_id = ?";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        if(rst.next()){
            return new AttendanceDto(
                    rst.getInt("attendance_id"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("lecture_id"),
                    rst.getString("student_name"),
                    rst.getString("course_name"),
                    rst.getString("subject_name"),
                    rst.getString("status")
            );
        }

        return null;

    }

    public ArrayList <AttendanceDto> getAllAttendance() throws Exception{

        ArrayList <AttendanceDto> attendanceDtos = new ArrayList<>();

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM attendance";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        while (rst.next()){
            attendanceDtos.add(new AttendanceDto(
                    rst.getInt("attendance_id"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("lecture_id"),
                    rst.getString("student_name"),
                    rst.getString("course_name"),
                    rst.getString("subject_name"),
                    rst.getString("status")
            ));
        }

        return attendanceDtos;

    }
}
