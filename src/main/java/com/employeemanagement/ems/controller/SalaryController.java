package com.employeemanagement.ems.controller;

import com.employeemanagement.ems.dto.SalaryRequestDto;
import com.employeemanagement.ems.dto.SalaryResponseDto;
import com.employeemanagement.ems.service.SalaryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

    private final SalaryService salaryService;
    public SalaryController(SalaryService salaryService){
      this.salaryService = salaryService;
    }

    @PutMapping("/api/employees/{employeeId}/salary")
    public SalaryResponseDto insertOrUpdate(@PathVariable Long employeeId,@Valid @RequestBody SalaryRequestDto requestDto){
        return salaryService.assignOrUpdateSalary(employeeId,requestDto);
    }
}
