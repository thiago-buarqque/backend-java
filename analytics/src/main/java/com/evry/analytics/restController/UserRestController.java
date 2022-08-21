package com.evry.analytics.restController;

import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserRestController {
    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return new UserDTO(_userModel.addUser(userDTO));
    }

    @PostMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        _userModel.deleteUser(userId);

        return "Success";
    }

    @Autowired
    UserModel _userModel;
}
