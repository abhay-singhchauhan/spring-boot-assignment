package com.school.service;

import com.school.dto.AdminLoginRequest;
import com.school.dto.AuthResponse;
import com.school.dto.StudentValidateRequest;
import com.school.entity.Student;
import com.school.repository.StudentRepository;
import com.school.security.AdminProperties;
import com.school.security.JwtService;
import com.school.security.Role;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AdminProperties adminProperties;
    private final JwtService jwtService;
    private final StudentRepository studentRepository;

    public AuthService(
            AdminProperties adminProperties,
            JwtService jwtService,
            StudentRepository studentRepository) {
        this.adminProperties = adminProperties;
        this.jwtService = jwtService;
        this.studentRepository = studentRepository;
    }

    public AuthResponse adminLogin(AdminLoginRequest request) {
        if (!adminProperties.username().equals(request.username())
                || !adminProperties.password().equals(request.password())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        String token = jwtService.generateToken(request.username(), Role.ADMIN, null);
        return AuthResponse.bearer(token, Role.ADMIN.name());
    }

    public AuthResponse validateStudent(StudentValidateRequest request) {
        Student student = studentRepository
                .findByStudentCodeAndDateOfBirth(request.studentCode(), request.dateOfBirth())
                .orElseThrow(() -> new BadCredentialsException("Invalid student code or date of birth"));

        String token = jwtService.generateToken(student.getStudentCode(), Role.STUDENT, student.getId());
        return AuthResponse.bearer(token, Role.STUDENT.name());
    }
}
