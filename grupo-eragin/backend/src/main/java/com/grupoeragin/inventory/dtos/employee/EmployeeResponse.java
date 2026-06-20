package com.grupoeragin.inventory.dtos.employee;

import com.grupoeragin.inventory.dtos.tools.ToolSummary;

import java.time.LocalDateTime;
import java.util.List;

public record EmployeeResponse(
        Long id,
        String fullName,
        String dni,
        String email,
        String phone,
        Boolean active,
        LocalDateTime createdAt
) {
}