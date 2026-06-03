package com.mastermind.patentstatus.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "application_records")
public class ApplicationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String applicationNumber;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String applicantName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Column(nullable = false)
    private LocalDate filingDate;

    private String assignedUnit;

    private LocalDateTime lastUpdated;

    public ApplicationRecord() {
    }

    public ApplicationRecord(
            String applicationNumber,
            String title,
            String applicantName,
            ApplicationStatus status,
            LocalDate filingDate,
            String assignedUnit
    ) {
        this.applicationNumber = applicationNumber;
        this.title = title;
        this.applicantName = applicantName;
        this.status = status;
        this.filingDate = filingDate;
        this.assignedUnit = assignedUnit;
        this.lastUpdated = LocalDateTime.now();
    }

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.lastUpdated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}