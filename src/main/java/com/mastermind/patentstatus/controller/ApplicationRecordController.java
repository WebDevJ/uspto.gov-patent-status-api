package com.mastermind.patentstatus.controller;

import com.mastermind.patentstatus.dto.ApplicationRecordRequest;
import com.mastermind.patentstatus.dto.ApplicationRecordResponse;
import com.mastermind.patentstatus.model.ApplicationStatus;
import com.mastermind.patentstatus.service.ApplicationRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationRecordController {

    private final ApplicationRecordService service;

    public ApplicationRecordController(ApplicationRecordService service) {
        this.service = service;
    }

    @GetMapping
    public List<ApplicationRecordResponse> getApplications(
            @RequestParam(required = false) ApplicationStatus status
    ) {
        return service.getAllApplications(status);
    }

    @GetMapping("/{applicationNumber}")
    public ApplicationRecordResponse getApplicationByNumber(
            @PathVariable String applicationNumber
    ) {
        return service.getApplicationByNumber(applicationNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationRecordResponse createApplication(
            @Valid @RequestBody ApplicationRecordRequest request
    ) {
        return service.createApplication(request);
    }
}