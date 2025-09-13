package edu.ijse.mvc.fx.controller;

import edu.ijse.mvc.fx.dto.AttendanceDto;
import edu.ijse.mvc.fx.model.AttendanceReportModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceReportController {

    AttendanceReportModel attendanceReportModel = new AttendanceReportModel();

    public ArrayList <AttendanceDto> filteredAttendance(LocalDate startDate, LocalDate endDate) throws Exception{
        return attendanceReportModel.searchAttendance(startDate.toString(),endDate.toString());
    }

}
