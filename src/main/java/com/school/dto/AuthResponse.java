package com.school.dto;

public record AuthResponse(
        String accessToken,
        String tokenType,
        String role
) {
    public static AuthResponse bearer(String accessToken, String role) {
        return new AuthResponse(accessToken, "Bearer", role);
    }
}
