package com.grupoeragin.inventory.dtos.employee;

import com.grupoeragin.inventory.entities.Tool;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record EmployeeCreateRequest(
        @NotBlank String fullName,
        @NotBlank String dni,
        @NotBlank String email,
        String phone
) {
}