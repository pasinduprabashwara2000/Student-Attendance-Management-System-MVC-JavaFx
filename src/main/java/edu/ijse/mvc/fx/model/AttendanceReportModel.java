package edu.ijse.mvc.fx.model;

import edu.ijse.mvc.fx.db.DBConnection;
import edu.ijse.mvc.fx.dto.AttendanceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AttendanceReportModel {

    public ArrayList <AttendanceDto> searchAttendance(String startDate, String endDate) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM attendance WHERE date BETWEEN ? AND ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,startDate);
        st.setString(2,endDate);

        ResultSet rst = st.executeQuery();

        ArrayList <AttendanceDto> filteredAttendance = new ArrayList<>();
        while (rst.next()){
            filteredAttendance.add(new AttendanceDto(
                    rst.getInt("attendance_id"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("lecture_id"),
                    rst.getString("student_name"),
                    rst.getString("course_name"),
                    rst.getString("subject_name"),
                    rst.getString("status")
            ));
        }

        return filteredAttendance;

    }

}
