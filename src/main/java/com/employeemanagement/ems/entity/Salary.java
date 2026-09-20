package com.employeemanagement.ems.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "employee_salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal basicSalary;
    private BigDecimal deduction;
    private BigDecimal allowances;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public BigDecimal getBasicSalary(){
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary){
        this.basicSalary = basicSalary;
    }

    public BigDecimal getDeduction(){
        return deduction;
    }

    public void setDeduction(BigDecimal deduction){
        this.deduction = deduction;
    }

    public BigDecimal getAllowances(){
        return allowances;
    }

    public void setAllowances(BigDecimal allowances){
        this.allowances = allowances;
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }
}
