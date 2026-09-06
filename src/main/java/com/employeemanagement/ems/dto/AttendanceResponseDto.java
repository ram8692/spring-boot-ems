package com.employeemanagement.ems.dto;

import com.employeemanagement.ems.entity.Employee;
import com.employeemanagement.ems.enums.AttendanceStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceResponseDto {

    private Long id;
    private LocalDate date;
    private LocalTime clockInTime;
    private LocalTime clockOutTime;
    private Long employeeId;
    private String employeeName;
    private AttendanceStatus status;

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId()
    {
        return employeeId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId()
    {
        return id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setClockInTime(LocalTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public LocalTime getClockInTime() {
        return clockInTime;
    }

    public void setClockOutTime(LocalTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public LocalTime getClockOutTime() {
        return clockOutTime;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public AttendanceStatus getStatus() {
        return status;
    }


}
