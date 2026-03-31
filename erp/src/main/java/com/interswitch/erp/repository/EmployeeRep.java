package com.interswitch.erp.repository;

import com.interswitch.erp.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRep extends JpaRepository<Employee, Long> {

    Page<Employee> findByDepartment_Id(Long departmentId, Pageable pageable);

    Page<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName,
            Pageable pageable
    );

    boolean existsByEmail(String email);
}