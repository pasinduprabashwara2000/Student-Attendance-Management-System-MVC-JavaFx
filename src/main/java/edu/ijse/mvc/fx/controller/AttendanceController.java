package edu.ijse.mvc.fx.controller;

import edu.ijse.mvc.fx.dto.AttendanceDto;
import edu.ijse.mvc.fx.model.AttendanceModel;

import java.util.ArrayList;

public class AttendanceController {

    AttendanceModel attendanceModel = new AttendanceModel();

    public String addAttendance(AttendanceDto attendanceDto) throws Exception{
        return attendanceModel.addAttendance(attendanceDto);
    }

    public String updateAttendance(AttendanceDto attendanceDto) throws Exception{
        return attendanceModel.updateAttendance(attendanceDto);
    }

    public String deleteAttendance(int attendance_id) throws Exception{
        return attendanceModel.deleteAttendance(attendance_id);
    }

    public AttendanceDto searchAttendance(int attendance_id) throws Exception{
        return attendanceModel.searchAttendance(attendance_id);
    }

    public ArrayList<AttendanceDto> getAllAttendance() throws Exception{
        return attendanceModel.getAllAttendance();
    }

}
