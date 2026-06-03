package com.mastermind.patentstatus.dto;

import com.mastermind.patentstatus.model.ApplicationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApplicationRecordResponse {

    private String applicationNumber;
    private String title;
    private String applicantName;
    private ApplicationStatus status;
    private LocalDate filingDate;
    private String assignedUnit;
    private LocalDateTime lastUpdated;

    public ApplicationRecordResponse(
            String applicationNumber,
            String title,
            String applicantName,
            ApplicationStatus status,
            LocalDate filingDate,
            String assignedUnit,
            LocalDateTime lastUpdated
    ) {
        this.applicationNumber = applicationNumber;
        this.title = title;
        this.applicantName = applicantName;
        this.status = status;
        this.filingDate = filingDate;
        this.assignedUnit = assignedUnit;
        this.lastUpdated = lastUpdated;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDate getFilingDate() {
        return filingDate;
    }

    public String getAssignedUnit() {
        return assignedUnit;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}