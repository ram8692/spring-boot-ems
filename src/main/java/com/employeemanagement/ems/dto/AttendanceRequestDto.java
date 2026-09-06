package com.employeemanagement.ems.dto;

import com.employeemanagement.ems.entity.Employee;
import jakarta.validation.constraints.NotNull;

public class AttendanceRequestDto {

    @NotNull(message = "Employee id is required to clockin")
    private Long employeeId;

    public Long getEmployeeId() {
            return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

}
