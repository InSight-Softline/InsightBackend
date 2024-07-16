package com.insight.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insight.backend.dto.AuditResponseDTO;
import com.insight.backend.dto.ErrorDTO;
import com.insight.backend.dto.NewAuditDTO;
import com.insight.backend.model.Audit;
import com.insight.backend.service.audit.CreateAuditService;
import com.insight.backend.service.audit.FindAuditService;

import jakarta.validation.Valid;

/**
 * Rest controller for handling audit-related operations.
 */
@RestController
@RequestMapping("/api/v1/audits")
@Validated
public class AuditsController {

    private final FindAuditService findAuditService;
    private final CreateAuditService createAuditService;

    /**
     * Constructor for dependency injection.
     * @param findAuditService Service for finding audits.
     * @param createAuditService Service for creating audits.
     */
    @Autowired
    public AuditsController(FindAuditService findAuditService, CreateAuditService createAuditService) {
        this.findAuditService = findAuditService;
        this.createAuditService = createAuditService;
    }

    /**
     * Endpoint to retrieve all audits.
     * @return A list of all audits.
     */
    @GetMapping
    public ResponseEntity<List<Audit>> getAudits() {
        List<Audit> response = findAuditService.findAllAudits();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to create a new audit.
     * @param newAuditDTO Data transfer object for the new audit.
     * @return The created audit or an error message if creation fails.
     */
    @PostMapping("/new")
    public ResponseEntity<Object> postWithRequestBody(@Valid @RequestBody NewAuditDTO newAuditDTO) {
        AuditResponseDTO responseDTO = createAuditService.createAudit(newAuditDTO);

        // Check if the response is null, indicating that the audit creation failed
        if (responseDTO == null) {
            ErrorDTO errorDTO = new ErrorDTO("Failed to create audit: non existing category provided");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
        }

        // Return the created audit with a status of 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
