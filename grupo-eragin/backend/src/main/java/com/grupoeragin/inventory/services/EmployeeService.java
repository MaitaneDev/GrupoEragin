package com.grupoeragin.inventory.services;

import com.grupoeragin.inventory.dtos.employee.EmployeeCreateRequest;
import com.grupoeragin.inventory.dtos.employee.EmployeeResponse;
import com.grupoeragin.inventory.dtos.tools.ToolSummary;
import com.grupoeragin.inventory.entities.Employee;
import com.grupoeragin.inventory.entities.Tool;
import com.grupoeragin.inventory.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse findById(Long id) {
        return employeeRepository.findById(id).map(this::convertToResponse).orElseThrow(() -> new IllegalArgumentException("Employee not found."));
    }

    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll().stream().map(this::convertToResponse).toList();
    }

    public EmployeeResponse findByDni(String dni){
        return employeeRepository.findByDni(dni).map(this::convertToResponse).orElseThrow(() -> new IllegalArgumentException("Employee not found."));
    }

    public EmployeeResponse create(EmployeeCreateRequest employee) {
        if (employeeRepository.findByDni(employee.dni()).isPresent()) {
            throw new IllegalArgumentException("Employee already exists.");
        }

        Employee employeeEntity = new Employee();
        employeeEntity.setDni(employee.dni());
        employeeEntity.setFullName(employee.fullName());
        employeeEntity.setEmail(employee.email());
        employeeEntity.setPhone(employee.phone());
        employeeEntity.setCreatedAt(LocalDateTime.now());

        Employee employeeSaved = employeeRepository.save(employeeEntity);
        return convertToResponse(employeeSaved);
    }

    public EmployeeResponse update(Long id, EmployeeCreateRequest employee) {
        Employee existEmployee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found."));

        if (!existEmployee.getDni().equals(employee.dni()) && employeeRepository.findByDni(employee.dni()).isPresent()) {
            throw new IllegalArgumentException("Employee already exists.");
        }

        existEmployee.setFullName(employee.fullName());
        existEmployee.setDni(employee.dni());
        existEmployee.setEmail(employee.email());
        existEmployee.setPhone(employee.phone());
        existEmployee.setCreatedAt(LocalDateTime.now());

        Employee updatedEmployee = employeeRepository.save(existEmployee);
        return convertToResponse(updatedEmployee);
    }

    public void delete(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Employee not found.");
        }
    }

    private EmployeeResponse convertToResponse(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getFullName(), employee.getDni(), employee.getEmail(), employee.getPhone(), employee.isActive(), employee.getCreatedAt());
    }
}
