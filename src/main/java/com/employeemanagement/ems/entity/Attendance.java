package com.employeemanagement.ems.entity;

import com.employeemanagement.ems.enums.AttendanceStatus;
import com.employeemanagement.ems.enums.LeaveStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "employee_attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime clockInTime;
    private LocalTime clockOutTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    @Enumerated(EnumType.STRING) // Tells MySQL to save the word (e.g., "PENDING") instead of a number (0)
    private AttendanceStatus status;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setClockInTime(LocalTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public void setClockOutTime(LocalTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getClockInTime() {
        return clockInTime;
    }

    public LocalTime getClockOutTime() {
        return clockOutTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public AttendanceStatus getStatus() {
        return status;
    }


}
