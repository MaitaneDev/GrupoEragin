package com.grupoeragin.inventory.services;

import com.grupoeragin.inventory.dtos.ToolCreateRequest;
import com.grupoeragin.inventory.dtos.ToolResponse;
import com.grupoeragin.inventory.entities.Tool;
import com.grupoeragin.inventory.entities.enums.ToolStatus;
import com.grupoeragin.inventory.repositories.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToolService {

    private final ToolRepository toolRepository;

    public ToolResponse create(ToolCreateRequest request) {
        if (toolRepository.existsBySerialNumber(request.serialNumber())) {
            throw new IllegalArgumentException("Serial number already exists");
        }
        Tool newTool = new Tool();
        newTool.setSerialNumber(request.serialNumber());
        newTool.setName(request.name());
        newTool.setBrand(request.brand());
        newTool.setModel(request.model());
        newTool.setStatus(ToolStatus.AVAILABLE);
        newTool.setLocation(request.location());
        newTool.setPurchaseDate(request.purchaseDate());
        newTool.setCreatedAt(LocalDateTime.now());

        Tool savedTool = toolRepository.save(newTool);
        return convertToResponse(savedTool);
    }

    public ToolResponse getById(Long id) {
        return toolRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new IllegalArgumentException("Tool not found"));
    }

    public List<ToolResponse> getAll() {
        return toolRepository.findAll()
                .stream().map(this::convertToResponse)
                .toList();
    }

    private ToolResponse convertToResponse(Tool tool) {
        return new ToolResponse(tool.getId(), tool.getSerialNumber(), tool.getName(), tool.getBrand(),
                tool.getModel(), tool.getStatus(), tool.getLocation(), tool.getPurchaseDate(), tool.getCreatedAt());
    }
}
