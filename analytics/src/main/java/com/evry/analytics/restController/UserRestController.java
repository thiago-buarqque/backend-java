package com.evry.analytics.restController;

import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.entity.User;
import com.evry.analytics.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserRestController extends BaseRestController {
    @PostMapping("/register")
    public ResponseEntity<UserDTO>
        registerUser(@Valid @RequestBody UserDTO userDTO) {

        return ResponseEntity.ok(new UserDTO(_userService.addUser(
                _objectMapper.convertValue(userDTO, User.class)))
        );
    }

    @PostMapping("/{userId}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try{
            _userService.deleteUser(userId);

            return new ResponseEntity<>("User removed successfully.",
                    HttpStatus.OK);
        } catch (
                EmptyResultDataAccessException emptyResultDataAccessException
        ) {
            return new ResponseEntity<>("User not found.",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    UserService _userService;

    @Autowired
    ObjectMapper _objectMapper;
}
