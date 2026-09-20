package com.employeemanagement.ems.service;

import com.employeemanagement.ems.dto.SalaryRequestDto;
import com.employeemanagement.ems.dto.SalaryResponseDto;
import com.employeemanagement.ems.entity.Employee;
import com.employeemanagement.ems.entity.Salary;
import com.employeemanagement.ems.exception.ResourceNotFoundException;
import com.employeemanagement.ems.repository.EmployeeRepository;
import com.employeemanagement.ems.repository.SalaryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SalaryService {

private final SalaryRepository salaryRepository;
private final EmployeeRepository employeeRepository;

public SalaryService(SalaryRepository salaryRepository,EmployeeRepository employeeRepository){
    this.salaryRepository =salaryRepository;
    this.employeeRepository=employeeRepository;
}

    public SalaryResponseDto assignOrUpdateSalary(Long employeeId, SalaryRequestDto requestDto) {

        // 1. Employee dhundo (using employeeRepository.findById). Agar nahi mila toh ResourceNotFoundException.
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));

        // 2. Database mein check karo kya is employee ka salary pehle se hai (salaryRepository.findByEmployeeId).
        //    (Hint: Use .orElse(new Salary()) to either get the existing one OR create a blank one!)
        Salary s1 = salaryRepository.findByEmployeeId(employeeId).orElse(new Salary());


        // 3. Salary object mein employee, basicSalary, allowances, aur deduction set karo.
        s1.setEmployee(employee);
        s1.setBasicSalary(requestDto.getBasicSalary());
        s1.setAllowances(requestDto.getAllowances());
        s1.setDeduction(requestDto.getDeduction());

        Salary s2 = salaryRepository.save(s1);
        // 4. Salary ko save karo.

        // 5. Saved salary ko mapToDto() mein pass karke return karo.
        return mapToDto(s2);
    }

    private SalaryResponseDto mapToDto(Salary salary) {
        SalaryResponseDto dto = new SalaryResponseDto();
        // ... set id, basic, allowances, deduction ...
        dto.setId( salary.getId() );
        dto.setBasicSalary(salary.getBasicSalary());
        dto.setAllowances(salary.getAllowances());
        dto.setDeduction(salary.getDeduction());

        dto.setEmployeeId(salary.getEmployee().getId());

        // ... set employeeId ...

        // THE MATH: How to add and subtract BigDecimals in Java
        BigDecimal net = salary.getBasicSalary()
                .add(salary.getAllowances())
                .subtract(salary.getDeduction());

        dto.setNetSalary(net);
        return dto;
    }

}
