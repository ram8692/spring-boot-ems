package com.employeemanagement.ems.repository;


import com.employeemanagement.ems.entity.Employee;
import com.employeemanagement.ems.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByEmployeeId(Long employeeId);
}
