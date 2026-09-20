package com.employeemanagement.ems.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SalaryRequestDto {

    @NotNull(message = "Basic Salary cannot leave blank")
    private BigDecimal basicSalary;

    @NotNull(message = "Deductions cannot leave blank")
    private BigDecimal deduction;

    @NotNull(message = "Allowances cannot leave blank")
    private BigDecimal allowances;

    public void setBasicSalary(BigDecimal basicSalary){
        this.basicSalary = basicSalary;
    }

    public void setDeduction(BigDecimal deduction){
        this.deduction = deduction;
    }

    public void setAllowances(BigDecimal allowances){
        this.allowances = allowances;
    }

    public BigDecimal getBasicSalary(){
        return basicSalary;
    }

    public BigDecimal getDeduction(){
        return deduction;
    }

    public BigDecimal getAllowances(){
        return allowances;
    }

}
