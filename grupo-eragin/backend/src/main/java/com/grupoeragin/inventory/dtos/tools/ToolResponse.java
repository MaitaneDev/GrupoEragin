package com.grupoeragin.inventory.dtos.tools;

import com.grupoeragin.inventory.dtos.employee.EmployeeSummary;
import com.grupoeragin.inventory.entities.enums.ToolStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ToolResponse(
        Long id,
        String serialNumber,
        String name,
        String brand,
        String model,
        ToolStatus status,
        String location,
        LocalDate purchaseDate,
        LocalDateTime createdAt,
        EmployeeSummary employee
) {
}
