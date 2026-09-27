package com.employeemanagement.ems.service;

import com.employeemanagement.ems.dto.DocumentResponseDto;
import com.employeemanagement.ems.dto.SalaryResponseDto;
import com.employeemanagement.ems.entity.Employee;
import com.employeemanagement.ems.entity.EmployeeDocument;
import com.employeemanagement.ems.entity.Salary;
import com.employeemanagement.ems.exception.ResourceNotFoundException;
import com.employeemanagement.ems.repository.EmployeeDocumentRepository;
import com.employeemanagement.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class DocumentService {

    // This is the folder where we will save the physical files!
    private final String UPLOAD_DIR = "uploads/documents/";

    private final EmployeeDocumentRepository employeeDocumentRepository;
    private final EmployeeRepository employeeRepository;

    public DocumentService(EmployeeDocumentRepository employeeDocumentRepository,EmployeeRepository employeeRepository){
      this.employeeDocumentRepository = employeeDocumentRepository;
      this.employeeRepository = employeeRepository;
    }

    public DocumentResponseDto uploadDocument(Long employeeId, MultipartFile file, String documentType) throws IOException {

        // 1. Employee dhundo (findById). Agar nahi mila toh throw ResourceNotFoundException.
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));

        // 2. Folder create karo agar wo exist nahi karta hai. (Don't translate this, just copy it):
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 3. Ek unique file name banao taaki purani file overwrite na ho jaaye.
        //    Hint: String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // 4. Pura file path banao jahan file save hogi. (Don't translate, just copy):
        Path filePath = uploadPath.resolve(fileName);

        // 5. File ko physically hard drive par save karo. (Don't translate, just copy):
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 6. Ab ek naya EmployeeDocument entity object banao (new EmployeeDocument()).
        EmployeeDocument employeeDocument = new EmployeeDocument();

        // 7. Usme employee, documentName (use fileName), documentType, aur filePath (use filePath.toString()) set karo.
        employeeDocument.setDocumentName(fileName);
        employeeDocument.setDocumentType(documentType);
        employeeDocument.setFilePath(filePath.toString());
        employeeDocument.setEmployee(employee);


        // 8. Document ko database mein save karo (documentRepository.save) aur return karo.
        EmployeeDocument ed1 = employeeDocumentRepository.save(employeeDocument);
        return mapToDto(ed1);
    }

    private DocumentResponseDto mapToDto(EmployeeDocument employeeDocument) {
        DocumentResponseDto dto = new DocumentResponseDto();

        dto.setId( employeeDocument.getId() );
        dto.setDocumentName(employeeDocument.getDocumentName());
        dto.setDocumentType(employeeDocument.getDocumentType());
        dto.setFilePath(employeeDocument.getFilePath());

        dto.setEmployeeId(employeeDocument.getEmployee().getId());
        return dto;
    }
}
