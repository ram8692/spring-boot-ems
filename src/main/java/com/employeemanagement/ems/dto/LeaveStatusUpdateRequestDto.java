package com.employeemanagement.ems.dto;

import com.employeemanagement.ems.enums.LeaveStatus;
import jakarta.validation.constraints.NotNull;

public class LeaveStatusUpdateRequestDto {

    @NotNull(message = "status is required")
    private LeaveStatus status;

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public LeaveStatus getStatus() {
        return status;
    }
}
