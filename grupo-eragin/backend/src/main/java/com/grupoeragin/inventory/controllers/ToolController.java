package com.grupoeragin.inventory.controllers;

import com.grupoeragin.inventory.dtos.ToolCreateRequest;
import com.grupoeragin.inventory.dtos.ToolResponse;
import com.grupoeragin.inventory.services.ToolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
