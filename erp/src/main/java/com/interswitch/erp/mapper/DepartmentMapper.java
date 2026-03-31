package com.interswitch.erp.mapper;

import com.interswitch.erp.dto.DepartmentResponseDTO;
import com.interswitch.erp.entity.Department;

public class DepartmentMapper {

    public static DepartmentResponseDTO toDTO(Department department) {
        return DepartmentResponseDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }

    public static Department toEntity(DepartmentResponseDTO dto) {
        return Department.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}