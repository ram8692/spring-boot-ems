package com.employeemanagement.ems.service;

import com.employeemanagement.ems.dto.LeaveRequestDto;
import com.employeemanagement.ems.dto.LeaveResponseDto;
import com.employeemanagement.ems.dto.LeaveStatusUpdateRequestDto;
import com.employeemanagement.ems.entity.Employee;
import com.employeemanagement.ems.entity.Leave;
import com.employeemanagement.ems.enums.LeaveStatus;
import com.employeemanagement.ems.exception.ResourceNotFoundException;
import com.employeemanagement.ems.repository.EmployeeRepository;
import com.employeemanagement.ems.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository; // 1. Add this Bouncer

    public LeaveService(LeaveRepository leaveRepository,EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
    }

    public LeaveResponseDto applyForLeave(LeaveRequestDto requestDto){
        Leave leave = new Leave();

        // Check if the end date is before the start date
        if (requestDto.getEndDate().isBefore(requestDto.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        if(requestDto.getEmployeeId() != null){
            Employee emp = employeeRepository.findById(requestDto.getEmployeeId()).orElseThrow(()->new ResourceNotFoundException("employee not found"));
            leave.setEmployee(emp);
        }

        leave.setStartDate(requestDto.getStartDate());
        leave.setEndDate(requestDto.getEndDate());
        leave.setReason(requestDto.getReason());
        leave.setStatus(LeaveStatus.PENDING);

        // THE MISSING MAGIC LINE: Hand the VIP to the Database Clerk to save permanently!
        Leave savedLeave = leaveRepository.save(leave);

        return  mapToDto(savedLeave);
    }

    private LeaveResponseDto mapToDto(Leave leave){
        LeaveResponseDto dto = new LeaveResponseDto();
        dto.setId(leave.getId());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());

        // Add the missing status mapping!
        dto.setStatus(leave.getStatus());

        if(leave.getEmployee() != null){
            // Add the missing employeeId mapping!
            dto.setEmployeeId(leave.getEmployee().getId());

            // Combine first and last name
            dto.setEmployeeName(leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName());
        }

        return dto;
    }

    public LeaveResponseDto updateLeaveStatus(Long leaveId, LeaveStatusUpdateRequestDto updateDto){


        // 1. Fetch the EXISTING leave from the database (No "new Leave()")
        Leave existingLeave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found with ID: " + leaveId));

// 2. Modify only the field we want to update (the status)
        existingLeave.setStatus(updateDto.getStatus());

        Leave savedLeave = leaveRepository.save(existingLeave);

        return  mapToDto(savedLeave);
    }

    public List<LeaveResponseDto> getEmployeeLeaves(Long employeeId){
        // 1. Get the list of raw Leave entities from the database
        List<Leave> leaves = leaveRepository.findByEmployeeId(employeeId);

        // 2. If the list is empty, we just return an empty list! (No need to throw an error)

        // 3. The Conveyor Belt (Stream): Convert every Leave into a LeaveResponseDto
        return leaves.stream()
                .map(this::mapToDto) // Passes each leave to your helper method
                .collect(Collectors.toList()); // Packages them back into a List

    }

    public void deleteLeave(Long leaveId){
        Leave existingLeave = leaveRepository.findById(leaveId).orElseThrow(() -> new ResourceNotFoundException("Leave not found with ID: " + leaveId));;

        // 2. Delete the actual object we just found
        leaveRepository.delete(existingLeave);

        // Note: System.out.println is okay for testing, but in the future, we will use a Logger!
        System.out.println("Leave deleted successfully from database");
    }
}
