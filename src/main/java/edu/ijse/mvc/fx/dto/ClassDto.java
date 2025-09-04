package edu.ijse.mvc.fx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassDto {

    public String classId;
    public String subjectId;
    public String courseId;
    public String lectureId;
    public LocalDate date;

}
