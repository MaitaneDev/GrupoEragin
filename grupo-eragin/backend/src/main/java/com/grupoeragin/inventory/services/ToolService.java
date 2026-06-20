package com.grupoeragin.inventory.services;

import com.grupoeragin.inventory.dtos.employee.EmployeeCreateRequest;
import com.grupoeragin.inventory.dtos.employee.EmployeeSummary;
import com.grupoeragin.inventory.dtos.tools.ToolCreateRequest;
import com.grupoeragin.inventory.dtos.tools.ToolResponse;
import com.grupoeragin.inventory.entities.Tool;
import com.grupoeragin.inventory.entities.enums.ToolStatus;
import com.grupoeragin.inventory.repositories.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        return toolRepository.findById(id).map(this::convertToResponse).orElseThrow(() -> new IllegalArgumentException("Tool not found"));
    }

    public List<ToolResponse> getAll() {
        return toolRepository.findAll().stream().map(this::convertToResponse).toList();
    }

    public ToolResponse update(Long id, ToolCreateRequest request) {
        Tool existingTool = toolRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tool not found with ID: " + id));

        if (!existingTool.getSerialNumber().equals(request.serialNumber()) && toolRepository.existsBySerialNumber(request.serialNumber())) {
            throw new IllegalArgumentException("Serial number already exists");
        }

        existingTool.setSerialNumber(request.serialNumber());
        existingTool.setName(request.name());
        existingTool.setBrand(request.brand());
        existingTool.setModel(request.model());
        existingTool.setLocation(request.location());
        existingTool.setPurchaseDate(request.purchaseDate());

        Tool savedTool = toolRepository.save(existingTool);
        return convertToResponse(savedTool);
    }

    public void delete(Long id) {
        if (toolRepository.existsById(id)) {
            toolRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Tool not found");
        }
    }

    public void delete(String serialNumber) {
        if (toolRepository.existsBySerialNumber(serialNumber)) {
            toolRepository.deleteBySerialNumber(serialNumber);
        } else {
            throw new IllegalArgumentException("Tool not found");
        }
    }

    private ToolResponse convertToResponse(Tool tool) {
        EmployeeSummary employeeSummary = null;
        if (tool.getEmployee() != null) {
            employeeSummary = new EmployeeSummary(tool.getEmployee().getId(), tool.getEmployee().getFullName());
        }
        return new ToolResponse(tool.getId(), tool.getSerialNumber(), tool.getName(), tool.getBrand(),
                tool.getModel(), tool.getStatus(), tool.getLocation(), tool.getPurchaseDate(),
                tool.getCreatedAt(), employeeSummary);
    }
}
