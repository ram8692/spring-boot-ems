package com.employeemanagement.ems.service;

import com.employeemanagement.ems.dto.AttendanceRequestDto;
import com.employeemanagement.ems.dto.AttendanceResponseDto;
import com.employeemanagement.ems.dto.LeaveResponseDto;
import com.employeemanagement.ems.entity.Attendance;
import com.employeemanagement.ems.entity.Employee;
import com.employeemanagement.ems.entity.Leave;
import com.employeemanagement.ems.enums.AttendanceStatus;
import com.employeemanagement.ems.exception.ResourceNotFoundException;
import com.employeemanagement.ems.repository.AttendanceRepository;
import com.employeemanagement.ems.repository.DepartmentRepository;
import com.employeemanagement.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,EmployeeRepository employeeRepository){
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    public AttendanceResponseDto clockOut(AttendanceRequestDto requestDto) {


        LocalDate today = LocalDate.now();

        Attendance att = attendanceRepository.findByEmployeeIdAndDate(requestDto.getEmployeeId(),today).orElseThrow(()->new ResourceNotFoundException("No clock-in record found for toda"));
        att.setId(att.getId());

        if(att.getClockOutTime() != null){
            throw new IllegalArgumentException("Employee has already clocked out");
        }

        att.setClockOutTime(LocalTime.now());

        Attendance attendance1 = attendanceRepository.save(att);
        return mapToDto(attendance1);

    }

    public AttendanceResponseDto clockIn(AttendanceRequestDto requestDto){
        Attendance attendance = new Attendance();

        LocalDate today = LocalDate.now();
        if(requestDto.getEmployeeId() != null){
            Employee emp = employeeRepository.findById(requestDto.getEmployeeId()).orElseThrow(()->new ResourceNotFoundException("employee not found"));
            attendance.setEmployee(emp);

            if(attendanceRepository.findByEmployeeIdAndDate(emp.getId(),today).isPresent()){

                throw new IllegalArgumentException("Employee is already marked present");
            }


        }

        attendance.setDate(today);
        attendance.setClockInTime(LocalTime.now());
        attendance.setStatus(AttendanceStatus.PRESENT);
        Attendance attendanced = attendanceRepository.save(attendance);
        return mapToDto(attendanced);




    }

    private AttendanceResponseDto mapToDto(Attendance  attendance){
        AttendanceResponseDto dto = new AttendanceResponseDto();
        dto.setId(attendance.getId());
        dto.setDate(attendance.getDate());
        dto.setClockInTime(attendance.getClockInTime());
        dto.setClockOutTime(attendance.getClockOutTime());

        // Add the missing status mapping!
        dto.setStatus(attendance.getStatus());

        if(attendance.getEmployee() != null){
            // Add the missing employeeId mapping!
            dto.setEmployeeId(attendance.getEmployee().getId());

            // Combine first and last name
            dto.setEmployeeName(attendance.getEmployee().getFirstName() + " " + attendance.getEmployee().getLastName());
        }

        return dto;
    }
}
