package com.employeemanagement.ems.repository;

import com.employeemanagement.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    Page<Employee> findByFirstNameContainingIgnoreCase(String keyword, Pageable pageable);

}