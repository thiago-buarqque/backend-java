package com.evry.analytics.controller;

import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.model.entity.User;
import com.evry.analytics.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/user")
@AllArgsConstructor
@RestController
public class UserController extends BaseController {

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        Optional<User> userOptional = userService.getUserByEmail(email);

        if(!userOptional.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = objectMapper.convertValue(userOptional.get(), UserDTO.class);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    private final ObjectMapper objectMapper;
    private final UserServiceImpl userService;
}
