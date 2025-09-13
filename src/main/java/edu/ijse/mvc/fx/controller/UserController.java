package edu.ijse.mvc.fx.controller;

import edu.ijse.mvc.fx.dto.UserDto;
import edu.ijse.mvc.fx.model.UserModel;

public class UserController {

    UserModel userModel = new UserModel();

    public boolean login(UserDto userDto) throws Exception{
        return userModel.searchUser(userDto);
    }
}
