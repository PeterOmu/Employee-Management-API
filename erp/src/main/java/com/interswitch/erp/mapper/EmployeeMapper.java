package com.interswitch.erp.mapper;

import com.interswitch.erp.dto.*;
import com.interswitch.erp.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeCreateRequestDTO dto) {
        return Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .position(dto.getPosition())
                .salary(dto.getSalary())
                .hireDate(dto.getHireDate())
                .build();
    }

    public Employee toEntity(EmployeeUpdateRequestDTO dto, Employee existing) {
        // Partial update logic (only non-null fields are updated)
        if (dto.getFirstName() != null) existing.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) existing.setLastName(dto.getLastName());
        if (dto.getEmail() != null) existing.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) existing.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getPosition() != null) existing.setPosition(dto.getPosition());
        if (dto.getSalary() != null) existing.setSalary(dto.getSalary());
        if (dto.getHireDate() != null) existing.setHireDate(dto.getHireDate());
        return existing;
    }

    public EmployeeResponseDTO toResponseDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .hireDate(employee.getHireDate())
                .department(DepartmentResponseDTO.builder()
                        .id(employee.getDepartment().getId())
                        .name(employee.getDepartment().getName())
                        .description(employee.getDepartment().getDescription())
                        .build())
                .build();
    }
}
