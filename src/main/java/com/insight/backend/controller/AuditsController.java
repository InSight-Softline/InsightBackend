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
 * AuditsController is a REST controller that handles HTTP requests related to audits.
 */
@RestController
@RequestMapping("/api/v1/audits")
@Validated
public class AuditsController {
    /** 
     * The FindAuditService to use the service methods.
     */
    private final FindAuditService findAuditService;
    private final CreateAuditService createAuditService;
    /**
     * Constructs a new AuditsController with the specified FindAuditService.
     * 
     * @param findAuditService the service to find audits
     */
    @Autowired
    public AuditsController(FindAuditService findAuditService, CreateAuditService createAuditService) {
        this.findAuditService = findAuditService;
        this.createAuditService = createAuditService;
    }

    /**
     * GET requests for retrieving all audits.
     *
     * @return a ResponseEntity containing a list of Audit objects
     */
    @GetMapping
    public ResponseEntity<List<Audit>> getAudits() {
        List<Audit> response = findAuditService.findAllAudits();
        return ResponseEntity.ok(response);
    }

    /**
     * POST requests for creating new Audit.
     *
     * @param newAuditDTO the request body containing details for new audit
     * @return a ResponseEntity containing an ID and name of new Audit, or error details if creation failed
     */
    @PostMapping("/new")
    public ResponseEntity<Object> postWithRequestBody(@Valid @RequestBody NewAuditDTO newAuditDTO) {
        AuditResponseDTO responseDTO = createAuditService.createAudit(newAuditDTO);

        if (responseDTO == null) {
            ErrorDTO errorDTO = new ErrorDTO("Failed to create audit: non existing category provided");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
