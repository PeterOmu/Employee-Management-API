package com.interswitch.erp.controller;

import com.interswitch.erp.dto.*;
import com.interswitch.erp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponseDTO createEmployee(
            @Valid @RequestBody EmployeeCreateRequestDTO request
    ) {
        return employeeService.createEmployee(request);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeUpdateRequestDTO request
    ) {
        return employeeService.updateEmployee(id, request);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployee(
            @PathVariable Long id
    ) {
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(
            @PathVariable Long id
    ) {
        employeeService.deleteEmployee(id);
    }

     // Get Employees (Pagination + Filtering + Sorting)
    @GetMapping
    public PageResponse<EmployeeResponseDTO> getEmployees(

            @RequestParam(defaultValue = "1") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction,

            @RequestParam(required = false) Long departmentId,

            @RequestParam(required = false) String search
    ) {

        return employeeService.getEmployees(
                page,
                size,
                sortBy,
                direction,
                departmentId,
                search
        );
    }
}