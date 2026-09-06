package com.employeemanagement.ems.entity;

import com.employeemanagement.ems.enums.LeaveStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_leaves") // "leave" is a reserved keyword in some databases, so we rename the table!
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    @Enumerated(EnumType.STRING) // Tells MySQL to save the word (e.g., "PENDING") instead of a number (0)
    private LeaveStatus status;

    // TODO: Add the ManyToOne relationship to Employee here!
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // TODO: Generate all Getters and Setters!
    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public Long getId(){
        return id;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public String getReason() {
        return reason;
    }
    public Employee getEmployee() {
        return employee;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}