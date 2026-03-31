package com.interswitch.erp.service;

import com.interswitch.erp.dto.DepartmentResponseDTO;
import com.interswitch.erp.dto.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentResponseDTO dto);

    DepartmentResponseDTO getDepartmentById(Long id);

    List<DepartmentResponseDTO> getAllDepartments();

    DepartmentResponseDTO updateDepartment(Long id, DepartmentResponseDTO dto);

    void deleteDepartment(Long id);

    PageResponse<DepartmentResponseDTO> getDepartments(
            String name,
            Pageable pageable
    );
}