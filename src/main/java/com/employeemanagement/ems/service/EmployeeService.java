package com.employeemanagement.ems.service;

import com.employeemanagement.ems.dto.EmployeeRequestDto;
import com.employeemanagement.ems.dto.EmployeeResponseDto;
import com.employeemanagement.ems.entity.Department;
import com.employeemanagement.ems.entity.Designation;
import com.employeemanagement.ems.entity.Employee;
import com.employeemanagement.ems.exception.ResourceNotFoundException;
import com.employeemanagement.ems.repository.DepartmentRepository;
import com.employeemanagement.ems.repository.DesignationRespository;
import com.employeemanagement.ems.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository; // 1. Add this Bouncer
    private final DesignationRespository designationRespository; // 2. Add this Bouncer (using your exact spelling!)

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           DesignationRespository designationRespository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.designationRespository = designationRespository;
    }

    /*public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }*/

    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto){
        Employee employee = new Employee();
        employee.setFirstName(requestDto.getFirstName());
        employee.setLastName(requestDto.getLastName());
        employee.setEmail(requestDto.getEmail());

        if(requestDto.getDepartmentId() != null){
            Department dept = departmentRepository.findById(requestDto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            employee.setDepartment(dept);
        }

        if(requestDto.getDepartmentId() != null){
            Designation desig = designationRespository.findById(requestDto.getDesignationId()).orElseThrow(()->new RuntimeException("Designation not found"));
            employee.setDesignation(desig);
        }

        // THE MISSING MAGIC LINE: Hand the VIP to the Database Clerk to save permanently!
        Employee savedEmployee = employeeRepository.save(employee);

        return  mapToDto(savedEmployee);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public EmployeeResponseDto getEmployeeByID(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not founf="+id));
        return mapToDto(employee);
    }

    public Employee updateEmployeeDetails(Long id,Employee employeeDetails){

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
        existingEmployee.setFirstName((employeeDetails.getFirstName()));
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        return employeeRepository.save(existingEmployee);
    }

    public String deleteEmployeeByID(Long id){
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    public Employee getEmployeeByEmail(String email){
        return employeeRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Employee not found"));
    }

    public Page<Employee> getEmployeePaginated(int pageNo,int pageSize,String sortby){
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortby));
        return employeeRepository.findAll(pageable);
    }

    private EmployeeResponseDto mapToDto(Employee employee){
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());

        if(employee.getDepartment() != null){
            dto.setDepartmentName(employee.getDepartment().getName());
        }

        if(employee.getDesignation() != null){
            dto.setDesignationName(employee.getDesignation().getName());
        }

        return dto;
    }

    public Page<EmployeeResponseDto> getAllEmployeesPaginated(String keyword,int pageNo,int pageSize,String sortby){

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortby));
        if(keyword != null && !keyword.isEmpty()){
            Page<Employee> page = employeeRepository.findByFirstNameContainingIgnoreCase(keyword,pageable);
        }else{
            Page<Employee> page = employeeRepository.findAll(pageable);
        }
        Page<Employee> page = employeeRepository.findAll(pageable);
        return page.map(this::mapToDto);
    }
    // We will add our business methods here
}