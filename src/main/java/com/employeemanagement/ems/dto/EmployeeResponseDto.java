package com.employeemanagement.ems.dto;

public class EmployeeResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String departmentName;
    private String designationName;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastname){
        this.lastName = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }

    public String getDesignationName(){
        return designationName;
    }

    public void setDesignationName(String designationName){
        this.designationName = designationName;
    }
}
