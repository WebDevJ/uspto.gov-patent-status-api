package com.mastermind.patentstatus.dto;

import com.mastermind.patentstatus.model.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ApplicationRecordRequest {

    @NotBlank(message = "Application number is required")
    private String applicationNumber;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Applicant name is required")
    private String applicantName;

    @NotNull(message = "Status is required")
    private ApplicationStatus status;

    @NotNull(message = "Filing date is required")
    private LocalDate filingDate;

    private String assignedUnit;

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(LocalDate filingDate) {
        this.filingDate = filingDate;
    }

    public String getAssignedUnit() {
        return assignedUnit;
    }

    public void setAssignedUnit(String assignedUnit) {
        this.assignedUnit = assignedUnit;
    }
}