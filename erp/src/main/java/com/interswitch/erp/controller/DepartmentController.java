package com.interswitch.erp.controller;

import com.interswitch.erp.dto.DepartmentResponseDTO;
import com.interswitch.erp.dto.PageResponse;
import com.interswitch.erp.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDTO createDepartment(
            @RequestBody DepartmentResponseDTO dto
    ) {
        return departmentService.createDepartment(dto);
    }

    @GetMapping("/{id}")
    public DepartmentResponseDTO getDepartmentById(
            @PathVariable Long id
    ) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping
    public List<DepartmentResponseDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentResponseDTO dto
    ) {
        return departmentService.updateDepartment(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(
            @PathVariable Long id
    ) {
        departmentService.deleteDepartment(id);
    }

    // Paginated Department Search
    @GetMapping("/search")
    public PageResponse<DepartmentResponseDTO> searchDepartments(

            @RequestParam(required = false) String name,

            @RequestParam(defaultValue = "1") int page,

            @RequestParam(defaultValue = "10") int size
    ) {

        // convert to 0-based for Spring Pageable
        page = Math.max(page - 1, 0);

        Pageable pageable = PageRequest.of(page, size);

        return departmentService.getDepartments(name, pageable);
    }
}
