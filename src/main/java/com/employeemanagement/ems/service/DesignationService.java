package com.employeemanagement.ems.service;

import com.employeemanagement.ems.entity.Designation;
import com.employeemanagement.ems.repository.DesignationRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignationService {

    private final DesignationRespository designationRespository;

    public DesignationService(DesignationRespository designationRespository){
        this.designationRespository = designationRespository;
    }

    public Designation saveDesingations(Designation designation){
        return designationRespository.save(designation);
    }

    public List<Designation> getDesingations(){
        return designationRespository.findAll();
    }


}
