package com.school.security;

public enum Role {
    ADMIN,
    STUDENT;

    public String authority() {
        return "ROLE_" + name();
    }
}
