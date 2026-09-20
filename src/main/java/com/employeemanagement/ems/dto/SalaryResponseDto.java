package com.employeemanagement.ems.dto;

import java.math.BigDecimal;

public class SalaryResponseDto {

    private Long id;
    private Long employeeId;
    private BigDecimal basicSalary;
    private BigDecimal allowances;
    private BigDecimal deduction;
    private BigDecimal netSalary;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getEmployeeId(){
        return employeeId;
    }

    public void setEmployeeId(Long employeeId){
        this.employeeId = employeeId;
    }


    public void setBasicSalary(BigDecimal basicSalary){
        this.basicSalary = basicSalary;
    }

    public void setDeduction(BigDecimal deduction){
        this.deduction = deduction;
    }

    public void setAllowances(BigDecimal allowances){
        this.allowances = allowances;
    }

    public void setNetSalary(BigDecimal netSalary){
        this.netSalary = netSalary;
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

    public BigDecimal getNetSalary(){
        return netSalary;
    }

}
