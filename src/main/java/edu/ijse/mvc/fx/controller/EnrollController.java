package edu.ijse.mvc.fx.controller;

import edu.ijse.mvc.fx.dto.EnrollDto;
import edu.ijse.mvc.fx.model.EnrollModel;
import java.util.ArrayList;

public class EnrollController {

    EnrollModel enrollModel = new EnrollModel();

    public String addEnroll(EnrollDto enrollDto) throws Exception{
        return enrollModel.addEnroll(enrollDto);
    }

    public String updateEnroll(EnrollDto enrollDto) throws Exception{
        return enrollModel.updateEnroll(enrollDto);
    }

    public String deleteEnroll(int reg_number) throws Exception{
        return enrollModel.deleteEnroll(reg_number);
    }

    public EnrollDto searchEnroll(int reg_number) throws Exception{
        return enrollModel.searchEnroll(reg_number);
    }

    public ArrayList <EnrollDto> getAllEnroll() throws Exception{
        return enrollModel.getAllEnroll();
    }

}
