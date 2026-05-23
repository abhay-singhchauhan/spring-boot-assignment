package com.school.security;

public record AuthenticatedUser(String subject, Role role, Long studentId) {
}
