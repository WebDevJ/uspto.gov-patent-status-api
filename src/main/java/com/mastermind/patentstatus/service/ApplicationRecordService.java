package com.mastermind.patentstatus.service;

import com.mastermind.patentstatus.dto.ApplicationRecordRequest;
import com.mastermind.patentstatus.dto.ApplicationRecordResponse;
import com.mastermind.patentstatus.exception.ApplicationNotFoundException;
import com.mastermind.patentstatus.model.ApplicationRecord;
import com.mastermind.patentstatus.model.ApplicationStatus;
import com.mastermind.patentstatus.repository.ApplicationRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationRecordService {

    private final ApplicationRecordRepository repository;

    public ApplicationRecordService(ApplicationRecordRepository repository) {
        this.repository = repository;
    }

    public List<ApplicationRecordResponse> getAllApplications(ApplicationStatus status) {
        List<ApplicationRecord> records;

        if (status != null) {
            records = repository.findByStatus(status);
        } else {
            records = repository.findAll();
        }

        return records.stream()
                .map(this::toResponse)
                .toList();
    }

    public ApplicationRecordResponse getApplicationByNumber(String applicationNumber) {
        ApplicationRecord record = repository.findByApplicationNumber(applicationNumber)
                .orElseThrow(() -> new ApplicationNotFoundException(applicationNumber));

        return toResponse(record);
    }

    public ApplicationRecordResponse createApplication(ApplicationRecordRequest request) {
        if (repository.existsByApplicationNumber(request.getApplicationNumber())) {
            throw new IllegalArgumentException("Application number already exists: " + request.getApplicationNumber());
        }

        ApplicationRecord record = new ApplicationRecord(
                request.getApplicationNumber(),
                request.getTitle(),
                request.getApplicantName(),
                request.getStatus(),
                request.getFilingDate(),
                request.getAssignedUnit()
        );

        ApplicationRecord savedRecord = repository.save(record);

        return toResponse(savedRecord);
    }

    private ApplicationRecordResponse toResponse(ApplicationRecord record) {
        return new ApplicationRecordResponse(
                record.getApplicationNumber(),
                record.getTitle(),
                record.getApplicantName(),
                record.getStatus(),
                record.getFilingDate(),
                record.getAssignedUnit(),
                record.getLastUpdated()
        );
    }
}