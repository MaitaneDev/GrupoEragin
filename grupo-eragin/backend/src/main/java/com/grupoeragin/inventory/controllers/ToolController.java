package com.grupoeragin.inventory.controllers;

import com.grupoeragin.inventory.dtos.ToolCreateRequest;
import com.grupoeragin.inventory.dtos.ToolResponse;
import com.grupoeragin.inventory.services.ToolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
@RequiredArgsConstructor
public class ToolController {

    private final ToolService toolService;

    @PostMapping
    public ResponseEntity<ToolResponse> createTool(@Valid @RequestBody ToolCreateRequest tool) {
        ToolResponse response = toolService.create(tool);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolResponse> getToolById(@PathVariable Long id) {
        ToolResponse response = toolService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ToolResponse>> getAllTools() {
        List<ToolResponse> response = toolService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
