package com.interswitch.erp.service;

import com.interswitch.erp.dto.*;
import com.interswitch.erp.entity.Department;
import com.interswitch.erp.entity.Employee;
import com.interswitch.erp.exception.DuplicateResourceException;
import com.interswitch.erp.exception.ResourceNotFoundException;
import com.interswitch.erp.mapper.EmployeeMapper;
import com.interswitch.erp.repository.DepartmentRep;
import com.interswitch.erp.repository.EmployeeRep;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRep employeeRep;
    private final DepartmentRep departmentRep;
    private final EmployeeMapper mapper;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeCreateRequestDTO request) {

        if (employeeRep.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Employee email already exists");
        }

        Department department = departmentRep.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        Employee employee = mapper.toEntity(request);

        employee.setDepartment(department);

        Employee saved = employeeRep.save(employee);

        return mapper.toResponseDTO(saved);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeUpdateRequestDTO request) {

        Employee employee = employeeRep.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        mapper.toEntity(request, employee);

        if (request.getDepartmentId() != null) {

            Department department = departmentRep.findById(request.getDepartmentId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Department not found"));

            employee.setDepartment(department);
        }

        Employee updated = employeeRep.save(employee);

        return mapper.toResponseDTO(updated);
    }

    @Override
    public EmployeeResponseDTO getEmployee(Long id) {

        Employee employee = employeeRep.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        return mapper.toResponseDTO(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRep.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employeeRep.delete(employee);
    }

    @Override
    public PageResponse<EmployeeResponseDTO> getEmployees(
            int page,
            int size,
            String sortBy,
            String direction,
            Long departmentId,
            String search
    ) {

        page = Math.max(page - 1, 0);

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage;

        if (departmentId != null) {
            employeePage = employeeRep.findByDepartment_Id(departmentId, pageable);
        }
        else if (search != null && !search.isBlank()) {
            employeePage = employeeRep
                    .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                            search,
                            search,
                            pageable
                    );
        }
        else {
            employeePage = employeeRep.findAll(pageable);
        }

        List<EmployeeResponseDTO> data =
                employeePage.getContent()
                        .stream()
                        .map(mapper::toResponseDTO)
                        .toList();

        return new PageResponse<>(
                data,
                employeePage.getNumber() + 1,
                employeePage.getSize(),
                employeePage.getTotalElements(),
                employeePage.getTotalPages(),
                employeePage.isLast()
        );
    }
}