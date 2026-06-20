package com.grupoeragin.inventory.dtos.tools;

public record ToolSummary(
        Long id,
        String serialNumber,
        String name,
        String location
) {
}
