package com.employeemanagement.ems.controller;

import com.employeemanagement.ems.dto.EmployeeRequestDto;
import com.employeemanagement.ems.dto.EmployeeResponseDto;
import com.employeemanagement.ems.entity.Employee;
import com.employeemanagement.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;

    }

    @PostMapping("/api/employees")
    public EmployeeResponseDto createEmployee(@Valid @RequestBody EmployeeRequestDto requestDto) {
        return employeeService.createEmployee(requestDto);
    }
    //@PostMapping("/api/employees")
    /*public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }*/

    @GetMapping("/api/employees")
    public Page<EmployeeResponseDto> getAllEmployees(@RequestParam(required = false) String keyword,@RequestParam(defaultValue = "0") int PageNo,@RequestParam(defaultValue = "10") int PageSize,@RequestParam(defaultValue = "id") String sortBy){
        return employeeService.getAllEmployeesPaginated(keyword,PageNo,PageSize,sortBy);
    }

    @GetMapping("/api/employees/{id}")
    public EmployeeResponseDto getEmployeeByID(@PathVariable Long id){
        return employeeService.getEmployeeByID(id);
    }

    @PutMapping("/api/employees/{id}")
    public Employee updateEmployeeByID(@PathVariable Long id,@RequestBody Employee employee){
        return employeeService.updateEmployeeDetails(id,employee);
    }

    @DeleteMapping("/api/employees/{id}")
    public String deleteEmployeeByID(@PathVariable Long id){
        return employeeService.deleteEmployeeByID(id);
    }

    @GetMapping("/api/employees/search")
    public Employee getEmployeeByEmail(@RequestParam String email){
        return employeeService.getEmployeeByEmail(email);
    }

    @GetMapping("/api/employees/page")
    public Page<Employee> getAllEmployeesPaginated(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "3") int pageSize,@RequestParam(defaultValue = "id") String sortBy){
        return employeeService.getEmployeePaginated(pageNo,pageSize,sortBy);
    }

}
