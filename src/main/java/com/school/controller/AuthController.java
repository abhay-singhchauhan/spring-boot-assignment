package com.school.controller;

import com.school.dto.AdminLoginRequest;
import com.school.dto.AuthResponse;
import com.school.dto.StudentValidateRequest;
import com.school.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/admin/login")
    public ResponseEntity<AuthResponse> adminLogin(@Valid @RequestBody AdminLoginRequest request) {
        return ResponseEntity.ok(authService.adminLogin(request));
    }

    @PostMapping("/student/validate")
    public ResponseEntity<AuthResponse> validateStudent(@Valid @RequestBody StudentValidateRequest request) {
        return ResponseEntity.ok(authService.validateStudent(request));
    }
}
