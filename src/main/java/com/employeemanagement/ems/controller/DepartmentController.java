package com.employeemanagement.ems.controller;

import com.employeemanagement.ems.entity.Department;
import com.employeemanagement.ems.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;

    }

    @PostMapping("/api/departments")
    public Department createDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/api/departments")
    public List<Department> getAllDepartments(){
        return departmentService.getDepartment();
    }

}
