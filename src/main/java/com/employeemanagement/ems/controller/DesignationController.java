package com.employeemanagement.ems.controller;

import com.employeemanagement.ems.entity.Designation;
import com.employeemanagement.ems.service.DesignationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DesignationController {

    private final DesignationService  designationService;

    public DesignationController(DesignationService  designationService){
        this.designationService = designationService;
    }

    @PostMapping("/api/designations")
    public Designation saveDesignation(@RequestBody Designation designation){
        return designationService.saveDesingations(designation);
    }

    @GetMapping("/api/designations")
    public List<Designation> getAllDesignations(){
        return designationService.getDesingations();
    }


}
