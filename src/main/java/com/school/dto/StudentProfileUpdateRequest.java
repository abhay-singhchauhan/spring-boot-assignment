package com.school.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import java.util.List;

public record StudentProfileUpdateRequest(
        @Email String email,
        String mobileNumber,
        String fatherName,
        String motherName,
        @Valid List<AddressRequest> addresses
) {
}
