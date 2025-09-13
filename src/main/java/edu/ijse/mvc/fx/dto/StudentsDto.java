package edu.ijse.mvc.fx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentsDto {

    private int regNum;
    private String name;
    private String contactDetails;

}
