package com.interswitch.erp.service;

import com.interswitch.erp.dto.*;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeCreateRequestDTO request);

    EmployeeResponseDTO updateEmployee(Long id, EmployeeUpdateRequestDTO request);

    EmployeeResponseDTO getEmployee(Long id);

    void deleteEmployee(Long id);

    PageResponse<EmployeeResponseDTO> getEmployees(
            int page,
            int size,
            String sortBy,
            String direction,
            Long departmentId,
            String search
    );
}