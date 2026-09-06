package com.employeemanagement.ems.controller;

import com.employeemanagement.ems.dto.AttendanceRequestDto;
import com.employeemanagement.ems.dto.AttendanceResponseDto;
import com.employeemanagement.ems.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    private final AttendanceService  attendanceService;

    public AttendanceController(AttendanceService  attendanceService){
        this.attendanceService = attendanceService;
    }

    @PostMapping("/api/attendance/clock-in")
    public AttendanceResponseDto clockIn(@Valid @RequestBody AttendanceRequestDto requestDto){
         return attendanceService.clockIn(requestDto);
    }

    @PutMapping("/api/attendance/clock-out")
    public AttendanceResponseDto clockOut(@Valid @RequestBody AttendanceRequestDto requestDto){
        return attendanceService.clockOut(requestDto);
    }

}
