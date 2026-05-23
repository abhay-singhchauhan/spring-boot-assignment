package com.school.dto;

import com.school.enums.AddressType;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotNull AddressType type,
        String street,
        String city,
        String state,
        String pincode
) {
}
