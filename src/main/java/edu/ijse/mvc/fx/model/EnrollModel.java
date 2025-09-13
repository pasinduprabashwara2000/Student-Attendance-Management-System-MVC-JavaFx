package edu.ijse.mvc.fx.model;

import edu.ijse.mvc.fx.db.DBConnection;
import edu.ijse.mvc.fx.dto.EnrollDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EnrollModel {

    public String addEnroll(EnrollDto enrollDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO enroll VALUES (?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1,enrollDto.getRegNumber());
        st.setString(2,enrollDto.getCourseID());

        return st.executeUpdate() > 0 ? "Enroll Added Successfully" : "Enroll Added Failed";

    }

    public String updateEnroll(EnrollDto enrollDto) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE enroll SET course_id = ? WHERE reg_number = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,enrollDto.getCourseID());
        st.setInt(2,enrollDto.getRegNumber());

        return st.executeUpdate() > 0 ? "Enroll Updated Successfully" : "Enroll Updated Failed";

    }

    public String deleteEnroll(int reg_number) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM enroll WHERE reg_number = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1,reg_number);

        return st.executeUpdate() > 0 ? "Enroll Deleted Successfully" : "Enroll Deleted Failed";

    }

    public EnrollDto searchEnroll(int reg_number) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM enroll WHERE reg_number = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1,reg_number);

        ResultSet rst = st.executeQuery();

        if(rst.next()){
            rst.getInt("reg_number");
            rst.getString("course_id");
        }

        return null;

    }

    public ArrayList <EnrollDto> getAllEnroll() throws Exception{

        ArrayList <EnrollDto> enrollDtos = new ArrayList<>();

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM enroll";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rst = st.executeQuery();

        while (rst.next()){
            enrollDtos.add(new EnrollDto(
               rst.getInt("reg_number"),
               rst.getString("course_id")
            ));
        }

        return enrollDtos;

    }

}
