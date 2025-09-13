package edu.ijse.mvc.fx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceDto {

    private int attendanceId;
    private LocalDate date;
    private String lectureId;
    private String studentName;
    private String courseName;
    private String subjectName;
    private String status;

}
