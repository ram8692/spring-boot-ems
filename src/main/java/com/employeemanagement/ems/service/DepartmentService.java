package com.employeemanagement.ems.service;

import com.employeemanagement.ems.entity.Department;
import com.employeemanagement.ems.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }

    public List<Department> getDepartment(){
        return departmentRepository.findAll();
    }


}
