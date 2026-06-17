package com.grupoeragin.inventory.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ToolCreateRequest(
        @NotBlank String serialNumber,
        @NotBlank String name,
        @NotBlank String brand,
        @NotBlank String model,
        String location,
        LocalDate purchaseDate
) {}
