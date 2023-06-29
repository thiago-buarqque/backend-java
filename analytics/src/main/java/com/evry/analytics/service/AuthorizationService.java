package com.evry.analytics.service;

import com.evry.analytics.model.entity.User;
import com.evry.analytics.model.response.JwtAuthenticationResponse;

public interface AuthorizationService {
    JwtAuthenticationResponse signup(User user);

    JwtAuthenticationResponse signIn(String email, String password);
}
