package com.mastermind.patentstatus.repository;

import com.mastermind.patentstatus.model.ApplicationRecord;
import com.mastermind.patentstatus.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRecordRepository extends JpaRepository<ApplicationRecord, Long> {

    Optional<ApplicationRecord> findByApplicationNumber(String applicationNumber);

    List<ApplicationRecord> findByStatus(ApplicationStatus status);

    boolean existsByApplicationNumber(String applicationNumber);
}