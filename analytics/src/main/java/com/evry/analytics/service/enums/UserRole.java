package com.evry.analytics.service.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_DEFAULT;

    public String getAuthority() {
        return name();
    }
}
