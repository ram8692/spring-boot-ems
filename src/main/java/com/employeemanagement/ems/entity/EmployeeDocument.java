package com.employeemanagement.ems.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_documents")
public class EmployeeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;
    private String documentType;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;



    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDocumentName(String documentName) {
      this.documentName = documentName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentType(String documentType) {
     this.documentType = documentType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setFilePath(String filePath) {
      this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }




}
