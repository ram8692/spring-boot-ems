package com.employeemanagement.ems.controller;


import com.employeemanagement.ems.dto.LeaveRequestDto;
import com.employeemanagement.ems.dto.LeaveResponseDto;
import com.employeemanagement.ems.dto.LeaveStatusUpdateRequestDto;
import com.employeemanagement.ems.entity.Employee;
import com.employeemanagement.ems.entity.Leave;
import com.employeemanagement.ems.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService){
        this.leaveService = leaveService;
    }

    @PostMapping("/api/leaves")
    public LeaveResponseDto createLeave(@Valid @RequestBody LeaveRequestDto requestDto) {
        return leaveService.applyForLeave(requestDto);
    }

    @PutMapping("/api/leaves/{id}/status")
    public LeaveResponseDto updateLeave(@PathVariable Long id, @RequestBody LeaveStatusUpdateRequestDto leave){
        return leaveService.updateLeaveStatus(id,leave);
    }

    @DeleteMapping("/api/leaves/{id}")
    public Map<String,String> deleteLeave(@PathVariable Long id){
         leaveService.deleteLeave(id);
         return Map.of("status","deleted scuccessfully");
    }
}
