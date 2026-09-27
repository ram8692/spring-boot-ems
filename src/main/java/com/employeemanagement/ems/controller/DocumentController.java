package com.employeemanagement.ems.controller;

import com.employeemanagement.ems.dto.DocumentResponseDto;
import com.employeemanagement.ems.repository.EmployeeDocumentRepository;
import com.employeemanagement.ems.service.DocumentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/api/employees/{employeeId}/documents")
    public DocumentResponseDto uploadDocument(
            @PathVariable Long employeeId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentType") String documentType) throws IOException {

        return documentService.uploadDocument(employeeId, file, documentType);
    }

}
