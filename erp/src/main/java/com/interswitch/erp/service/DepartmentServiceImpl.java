package com.interswitch.erp.service;

import com.interswitch.erp.dto.DepartmentResponseDTO;
import com.interswitch.erp.dto.PageResponse;
import com.interswitch.erp.entity.Department;
import com.interswitch.erp.mapper.DepartmentMapper;
import com.interswitch.erp.repository.DepartmentRep;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRep departmentRep;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentResponseDTO dto) {

        if (departmentRep.existsByNameIgnoreCase(dto.getName())) {
            throw new RuntimeException("Department already exists");
        }

        Department department = DepartmentMapper.toEntity(dto);

        Department saved = departmentRep.save(department);

        return DepartmentMapper.toDTO(saved);
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {

        Department department = departmentRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return DepartmentMapper.toDTO(department);
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {

        return departmentRep.findAll()
                .stream()
                .map(DepartmentMapper::toDTO)
                .toList();
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentResponseDTO dto) {

        Department department = departmentRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (!department.getName().equalsIgnoreCase(dto.getName())
                && departmentRep.existsByNameIgnoreCase(dto.getName())) {
            throw new RuntimeException("Department name already exists");
        }

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());

        Department updated = departmentRep.save(department);

        return DepartmentMapper.toDTO(updated);
    }

    @Override
    public void deleteDepartment(Long id) {

        Department department = departmentRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRep.delete(department);
    }

    @Override
    public PageResponse<DepartmentResponseDTO> getDepartments(String name, Pageable pageable) {

        List<DepartmentResponseDTO> filtered = departmentRep.findAll()
                .stream()
                .filter(d -> name == null || d.getName().toLowerCase().contains(name.toLowerCase()))
                .map(DepartmentMapper::toDTO)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());

        List<DepartmentResponseDTO> content = filtered.subList(start, end);

        int totalPages = (int) Math.ceil((double) filtered.size() / pageable.getPageSize());

        return new PageResponse<>(
                content,
                pageable.getPageNumber() + 1, // convert to 1-based page
                pageable.getPageSize(),
                filtered.size(),
                totalPages,
                pageable.getPageNumber() + 1 >= totalPages
        );
    }
}
