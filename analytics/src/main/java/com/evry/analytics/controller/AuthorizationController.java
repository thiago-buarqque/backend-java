package com.evry.analytics.controller;

import com.evry.analytics.DTO.SignUpUserDTO;
import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.entity.User;
import com.evry.analytics.service.impl.AuthorizationServiceImpl;
import com.evry.analytics.security.dao.request.SignInRequest;
import com.evry.analytics.security.dao.response.JwtAuthenticationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/v1/auth")
@AllArgsConstructor
@RestController
public class AuthorizationController extends BaseController {
    AuthorizationServiceImpl authorizationService;
    ObjectMapper objectMapper;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@Valid @RequestBody SignUpUserDTO userDTO) {
        return authorizationService.signup(objectMapper.convertValue(userDTO, User.class));
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest signInRequest) {
        return authorizationService.signIn(signInRequest.getEmail(), signInRequest.getPassword());
    }

}
