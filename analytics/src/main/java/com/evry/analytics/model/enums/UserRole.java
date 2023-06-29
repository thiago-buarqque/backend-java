package com.evry.analytics.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_DEFAULT("ROLE_DEFAULT");

    private UserRole(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    private final String name;
}
