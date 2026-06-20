package com.grupoeragin.inventory.controllers;

import com.grupoeragin.inventory.dtos.employee.EmployeeCreateRequest;
import com.grupoeragin.inventory.dtos.employee.EmployeeResponse;
import com.grupoeragin.inventory.dtos.tools.ToolCreateRequest;
import com.grupoeragin.inventory.dtos.tools.ToolResponse;
import com.grupoeragin.inventory.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeCreateRequest employee) {
        EmployeeResponse response = employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse response = employeeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<EmployeeResponse> getEmployeeByDni(@PathVariable String dni) {
        EmployeeResponse response = employeeService.findByDni(dni);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> response = employeeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeCreateRequest tool) {
        EmployeeResponse response = employeeService.update(id, tool);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
