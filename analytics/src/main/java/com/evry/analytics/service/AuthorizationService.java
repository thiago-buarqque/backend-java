package com.evry.analytics.service;

import com.evry.analytics.entity.User;
import com.evry.analytics.security.dao.response.JwtAuthenticationResponse;

public interface AuthorizationService {
    JwtAuthenticationResponse signup(User user);

    JwtAuthenticationResponse signIn(String email, String password);
}
