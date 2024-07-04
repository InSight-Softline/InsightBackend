package com.insight.backend.controller;

import java.util.*;

import com.insight.backend.model.Audit;
import com.insight.backend.model.newAudit.AuditRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling requests related to audits.
 * <p>
 * This class provides RESTful endpoints to manage audit records. It includes endpoints for retrieving
 * a list of audits and for creating new audit records.
 * </p>
 * <p>
 * The {@code @RestController} annotation indicates that this class is a controller where {@code @RequestMapping}
 * methods are used to handle HTTP requests and return data directly to the client in JSON format.
 * </p>
 * 
 * @author Matthes Klemt, Fabian Mueller, Robert Eikmanns
 * @version 1.0
 * @since 2024
 */
@RestController
public class AuditsController {

    /**
     * Retrieves a list of all audits.
     * <p>
     * This method handles {@code GET} requests to the endpoint {@code /api/v1/audits}. It returns a list of
     * {@link Audit} objects. The current implementation includes temporary hard-coded data for demonstration
     * purposes. The data should be replaced with actual implementation in a production environment.
     * </p>
     * 
     * @return a {@link ResponseEntity} containing a list of {@link Audit} objects and an HTTP status of {@link HttpStatus#OK}.
     */
    @GetMapping("api/v1/audits")
    public ResponseEntity<List<Audit>> getAudits() {
        List<Audit> auditList = new ArrayList<>();

        // TODO: Temporary code for basic functionality | remove and reimplement properly later
        Audit audit1 = new Audit("Security Assessment; Kunde: Google LTD", Set.of());
        Audit audit2 = new Audit("Pentest; Kunde: Amazon LTD", Set.of());
        audit1.setId((long) 1);
        audit2.setId((long) 2);
        auditList.add(audit1);
        auditList.add(audit2);

        return ResponseEntity.ok(auditList);
    }

    private Integer ID = 1; 

    /**
     * Creates a new audit based on the provided request data.
     * <p>
     * This method handles {@code POST} requests to the endpoint {@code /api/v1/audits/new}. It expects a request body
     * containing an {@link AuditRequest} object. The method validates the input, increments an internal ID, and returns
     * a response with the new audit's ID and name. If input validation fails or if ID increment fails, an error response
     * is returned with an appropriate HTTP status code.
     * </p>
     * 
     * @param request the {@link AuditRequest} object containing the details of the new audit.
     * @return a {@link ResponseEntity} containing a map with the ID and name of the created audit if successful, 
     *         or an error message with an HTTP status code indicating the type of failure.
     */
    @PostMapping("/api/v1/audits/new")
    public ResponseEntity<Map<String, Object>> postWithRequestBody(@RequestBody AuditRequest request) {

        // Check if both keys are correctly given
        if (request.getName() == null || request.getCategories() == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "missing input objects");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // Increment ID and check for failure
        ID++;
        if (ID == 0) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "failed_to_create");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse); 
        } 

        // Create and return success response
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("id", ID); 
        responseMap.put("name", request.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }
}