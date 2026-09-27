package com.employeemanagement.ems.dto;

import com.employeemanagement.ems.entity.Employee;

public class DocumentResponseDto {

    private Long id;

    private String documentName;

    private String documentType;

    private String filePath;

    private Long employeeId;

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

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
