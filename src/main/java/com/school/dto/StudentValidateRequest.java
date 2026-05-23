package com.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentValidateRequest(
        @NotBlank String studentCode,
        @NotNull LocalDate dateOfBirth
) {
}
