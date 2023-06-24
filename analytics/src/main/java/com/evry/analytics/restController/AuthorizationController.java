package com.evry.analytics.restController;

import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.entity.User;
import com.evry.analytics.service.impl.AuthorizationServiceImpl;
import com.evry.analytics.service.security.dao.request.SignInRequest;
import com.evry.analytics.service.security.dao.response.JwtAuthenticationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/v1/auth")
@RestController
public class AuthorizationController extends BaseRestController {
    AuthorizationServiceImpl authorizationService;
    ObjectMapper objectMapper;

    public AuthorizationController(AuthorizationServiceImpl authorizationService, ObjectMapper objectMapper) {
        this.authorizationService = authorizationService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@Valid @RequestBody UserDTO userDTO) {
        return authorizationService.signup(objectMapper.convertValue(userDTO, User.class));
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest signInRequest) {
        return authorizationService.signIn(signInRequest.getEmail(), signInRequest.getPassword());
    }

}
