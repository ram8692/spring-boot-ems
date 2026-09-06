package com.employeemanagement.ems.repository;

import com.employeemanagement.ems.entity.Attendance;
import com.employeemanagement.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepository  extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
}
